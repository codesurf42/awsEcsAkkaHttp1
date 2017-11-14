package com.example.ecs1.model

import org.joda.time.DateTime

final case class SessionCreateRequest(
  businessId: String,
  userAuthKey: String,
  notableEvent: String,
  tags: Map[String, String] = Map.empty
)

final case class SessionCreateResponse(
//  expires: DateTime
  expires: String
)