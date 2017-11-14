package com.example.ecs1

import akka.http.scaladsl.model.{StatusCodes, headers}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.{get, post}
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.example.ecs1.model.{SessionCreateRequest, SessionCreateResponse}
import com.example.ecs1.queue.QueuePutter
import org.joda.time.DateTime

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import akka.util.Timeout
import spray.json.{JsNumber, JsObject, JsString}

//#user-routes-class
//case class UserRoutes(putter: QueuePutter)(implicit system: ActorSystem) extends JsonSupport {
case class UserRoutes(putter: QueuePutter)(implicit ec: ExecutionContext) extends JsonSupport {
  //#user-routes-class

//  lazy val log = Logging(system, classOf[UserRoutes])

  // Required by the `ask` (?) method below
  implicit lazy val timeout = Timeout(5.seconds) // usually we'd obtain the timeout from the system's configuration

  //#all-routes
  //#users-get-post
  //#users-get-delete
  lazy val userRoutes: Route =
  pathPrefix("sessions") {
    post {
      entity(as[SessionCreateRequest]) { input =>
        complete(
          201,
          List(headers.Location("/sessions/ses123")),
          // TODO: json lib
          SessionCreateResponse(DateTime.now().plusHours(24).toString))
      }
    } ~ path(IntNumber) { sessionId =>
      pathEndOrSingleSlash {
        post {
          // "submit metadata"
          complete(201, s"## POST session $sessionId")
        }
      } ~ pathPrefix("payloads") {
        path(IntNumber) { payloadId =>
          put {
            complete(s"## submit payload: payload=$payloadId")
          }
        }
      } ~ pathPrefix("complete") {
        post {
          complete("## session complete")
        }
      }
    }
  } ~ pathPrefix("payload") {
    post {
      entity(as[ApiPayload]) { payload =>
        val messageCreated = putter.put(payload)

        onSuccess(messageCreated) { msgId =>
          complete((StatusCodes.OK, JsObject(
            "test" -> JsNumber(payload.data.length),
            "messageId" -> JsString(msgId.data)
          )))

        }
      }
    } ~
      get {
        complete("Ok nr.................")
      }
  } // ~
  //      pathPrefix("users") {
  //        concat(
  //          //#users-get-delete
  //          pathEnd {
  //            concat(
  //              get {
  //                val users: Future[Users] =
  //                  (userRegistryActor ? GetUsers).mapTo[Users]
  //                complete(users)
  //              },
  //              post {
  //                entity(as[User]) { user =>
  //                  val userCreated: Future[ActionPerformed] =
  //                    (userRegistryActor ? CreateUser(user)).mapTo[ActionPerformed]
  //                  onSuccess(userCreated) { performed =>
  //                    log.info("Created user [{}]: {}", user.name, performed.description)
  //                    complete((StatusCodes.Created, performed))
  //                  }
  //                }
  //              }
  //            )
  //          },
  //          //#users-get-post
  //          //#users-get-delete
  //          path(Segment) { name =>
  //            concat(
  //              get {
  //                //#retrieve-user-info
  //                val maybeUser: Future[Option[User]] =
  //                  (userRegistryActor ? GetUser(name)).mapTo[Option[User]]
  //                rejectEmptyResponse {
  //                  complete(maybeUser)
  //                }
  //                //#retrieve-user-info
  //              },
  //              delete {
  //                //#users-delete-logic
  //                val userDeleted: Future[ActionPerformed] =
  //                  (userRegistryActor ? DeleteUser(name)).mapTo[ActionPerformed]
  //                onSuccess(userDeleted) { performed =>
  //                  log.info("Deleted user [{}]: {}", name, performed.description)
  //                  complete((StatusCodes.OK, performed))
  //                }
  //                //#users-delete-logic
  //              }
  //            )
  //          }
  //        )
  //        //#users-get-delete
  //      }
  //  //#all-routes
}
