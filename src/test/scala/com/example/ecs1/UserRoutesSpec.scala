package com.example.ecs1

//#test-top
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.example.ecs1.model.{SessionCreateRequest, SessionCreateResponse}
import com.example.ecs1.queue.QueuePutter
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

//#set-up
class UserRoutesSpec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest {
  //#test-top

  // Here we need to implement all the abstract members of UserRoutes.
  // We use the real UserRegistryActor to test it while we hit the Routes, 
  // but we could "mock" it by implementing it in-place or by using a TestProbe() 
  //  override val userRegistryActor: ActorRef =
  //    system.actorOf(UserRegistryActor.props, "userRegistry")
  

  class StubPutter extends QueuePutter {
    val items = new ListBuffer[ApiPayload]
    override def put(payload: ApiPayload): Future[MessageId] = {
      items += payload
      Future.successful(MessageId(items.size.toString))
    }
  }

  def stub1 = new {
    val putter = new StubPutter
    val userRoutesService = UserRoutes(putter)
    val routes = userRoutesService.userRoutes
  }

  //#set-up

  "NR routes" should {

    "open a session" in {
      val s = stub1
      val sessionCreateData = SessionCreateRequest("bus123", "auth123", "sub1", Map("foo" -> "bar"))

      val sessionCreateEntity = Marshal(sessionCreateData).to[MessageEntity].futureValue
      val request = Post("/sessions").withEntity(sessionCreateEntity)

      request ~> s.routes ~> check {
        status should ===(StatusCodes.Created)
        header("location").get.toString should include("/sessions/ses123")
        import io.circe.syntax._
        responseAs[SessionCreateResponse].asJson.noSpaces should include(""""expires":"20""")
//         responseAs[SessionCreateResponse] shouldBe SessionCreateResponse("foo")
      }
    }

    "takes json for request /payload" in {
      val s = stub1

      val tax = (Math.random() * 100000).toInt
      val apiData = ApiPayload(s"new tax: $tax")
      val apiEntity = Marshal(apiData).to[MessageEntity].futureValue
      val request = Post(uri = "/payload").withEntity(apiEntity)

      import akka.http.scaladsl.unmarshalling.Unmarshaller._

      request ~> s.routes ~> check {
        status should ===(StatusCodes.OK)
        contentType should ===(ContentTypes.`application/json`)
        entityAs[String] should include(apiData.data.length.toString)
        s.putter.items should have size 1
      }
    }
  }
  //
  //  //#actual-test
  //  "UserRoutes" should {
  //    "return no users if no present (GET /users)" in {
  //      // note that there's no need for the host part in the uri:
  //      val request = HttpRequest(uri = "/users")
  //
  //      request ~> routes ~> check {
  //        status should ===(StatusCodes.OK)
  //
  //        // we expect the response to be json:
  //        contentType should ===(ContentTypes.`application/json`)
  //
  //        // and no entries should be in the list:
  //        entityAs[String] should ===("""{"users":[]}""")
  //      }
  //    }
  //    //#actual-test
  //
  //    //#testing-post
  //    "be able to add users (POST /users)" in {
  //      val user = User("Kapi", 42, "jp")
  //      val userEntity = Marshal(user).to[MessageEntity].futureValue // futureValue is from ScalaFutures
  //
  //      // using the RequestBuilding DSL:
  //      val request = Post("/users").withEntity(userEntity)
  //
  //      request ~> routes ~> check {
  //        status should ===(StatusCodes.Created)
  //
  //        // we expect the response to be json:
  //        contentType should ===(ContentTypes.`application/json`)
  //
  //        // and we know what message we're expecting back:
  //        entityAs[String] should ===("""{"description":"User Kapi created."}""")
  //      }
  //    }
  //    //#testing-post
  //
  //    "be able to remove users (DELETE /users)" in {
  //      // user the RequestBuilding DSL provided by ScalatestRouteSpec:
  //      val request = Delete(uri = "/users/Kapi")
  //
  //      request ~> routes ~> check {
  //        status should ===(StatusCodes.OK)
  //
  //        // we expect the response to be json:
  //        contentType should ===(ContentTypes.`application/json`)
  //
  //        // and no entries should be in the list:
  //        entityAs[String] should ===("""{"description":"User Kapi deleted."}""")
  //      }
  //    }
  //    //#actual-test
  //  }
  //  //#actual-test

  //#set-up
}
//#set-up
