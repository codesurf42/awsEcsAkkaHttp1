package com.example.ecs1

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.amazonaws.regions.Regions
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.example.ecs1.queue.{KinesisQueuePutter, QueuePutter, SQSQueuePutter}

import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn

//#main-class
case class QuickstartServer(userRoutes: UserRoutes)(implicit val system: ActorSystem, ec: ExecutionContext) {

  // set up ActorSystem and other dependencies here
  //#main-class
  //#server-bootstrapping
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  val port = 9000

  lazy val log = Logging(system, classOf[QuickstartServer])
  //#server-bootstrapping

//  val userRegistryActor: ActorRef = system.actorOf(UserRegistryActor.props, "userRegistryActor")

  //#main-class
  // from the UserRoutes trait
  lazy val routes: Route = userRoutes.userRoutes
  //#main-class

  Http().bindAndHandle(routes, "0.0.0.0", port)
}
//#main-class


object Main extends App {

  val clientBuilder = AmazonKinesisClientBuilder.standard()
  clientBuilder.setRegion(Regions.EU_WEST_2.getName)
  val kinesis = clientBuilder.build()

  implicit val system: ActorSystem = ActorSystem("helloAkkaHttpServer")
  implicit val ec: ExecutionContext = system.dispatcher

  val qs = QuickstartServer(UserRoutes(new KinesisQueuePutter(kinesis, System.getenv("KINESIS_TOPIC"))))

}

class StubPutter extends QueuePutter {
  val items = new ListBuffer[ApiPayload]
  override def put(payload: ApiPayload): Future[MessageId] = {
    items += payload
    Future.successful(MessageId(items.size.toString))
  }
}
