package com.lightbend.akka.http.sample.queue

import com.lightbend.akka.http.sample.{ApiPayload, MessageId}

import scala.concurrent.Future

trait QueuePutter {

  def put(payload: ApiPayload): Future[MessageId]

}
