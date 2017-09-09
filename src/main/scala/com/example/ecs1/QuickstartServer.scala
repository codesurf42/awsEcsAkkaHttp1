package com.example.ecs1

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.example.ecs1.queue.QueuePutter

import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn

//#main-class
case class QuickstartServer(userRoutes: UserRoutes) {

  // set up ActorSystem and other dependencies here
  //#main-class
  //#server-bootstrapping
  implicit val system: ActorSystem = ActorSystem("helloAkkaHttpServer")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  val port = 9000

  lazy val log = Logging(system, classOf[QuickstartServer])
  //#server-bootstrapping

  // Needed for the Future and its methods flatMap/onComplete in the end
  implicit val executionContext: ExecutionContext = system.dispatcher

//  val userRegistryActor: ActorRef = system.actorOf(UserRegistryActor.props, "userRegistryActor")

  //#main-class
  // from the UserRoutes trait
  lazy val routes: Route = userRoutes.userRoutes
  //#main-class

  Http().bindAndHandle(routes, "0.0.0.0", port)
}
//#main-class


object Main extends App {

  val qs = QuickstartServer(UserRoutes(new StubPutter()))
}

class StubPutter extends QueuePutter {
  val items = new ListBuffer[ApiPayload]
  override def put(payload: ApiPayload): Future[MessageId] = {
    items += payload
    Future.successful(MessageId(items.size.toString))
  }
}
