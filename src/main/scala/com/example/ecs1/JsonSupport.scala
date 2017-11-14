package com.example.ecs1

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.example.ecs1.model.{SessionCreateRequest, SessionCreateResponse}
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport {
  // import the default encoders for primitive types (Int, String, Lists etc)
  import DefaultJsonProtocol._

  implicit val userJsonFormat = jsonFormat3(User)
  implicit val usersJsonFormat = jsonFormat1(Users)

  implicit val apiPayloadFormat = jsonFormat1(ApiPayload)
  implicit val messageIdFormat = jsonFormat1(MessageId)
  implicit val sessionCreateReqFormat = jsonFormat4(SessionCreateRequest)
  implicit val sessionCreateResFormat = jsonFormat1(SessionCreateResponse)

//  implicit val actionPerformedJsonFormat = jsonFormat1(ActionPerformed)
}
