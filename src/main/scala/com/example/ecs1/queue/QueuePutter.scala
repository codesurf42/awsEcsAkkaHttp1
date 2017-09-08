package com.example.ecs1.queue

import com.example.ecs1.{ApiPayload, MessageId}

import scala.concurrent.Future

trait QueuePutter {

  def put(payload: ApiPayload): Future[MessageId]

}
