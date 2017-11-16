package com.example.ecs1.model

import org.joda.time.DateTime

trait Message

final case class SessionCreateRequest(
  businessId: String,
  userAuthKey: String,
  notableEvent: String,
  tags: Map[String, String] = Map.empty
) extends Message

final case class SessionCreateResponse(
//  expires: DateTime
  expires: String
) extends Message
