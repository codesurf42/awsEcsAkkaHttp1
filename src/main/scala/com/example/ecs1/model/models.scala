package com.example.ecs1.model

final case class SessionCreate(
  businessId: String,
  userAuthKey: String,
  notableEvent: String,
  tags: Map[String, String] = Map.empty
)