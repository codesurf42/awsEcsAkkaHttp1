package com.example.ecs1.queue

import java.util.UUID

import com.example.ecs1.ApiPayload
import org.scalatest.FunSuite
import org.scalatest.Matchers._

import scala.concurrent.ExecutionContext.Implicits.global

class SQSQueuePutterSpec extends FunSuite {

  test("should enqueue with payload contents") {
    val sqs = new StubAmazonSQS
    val queueUrl = "queue-url"
    val putter = new SQSQueuePutter(sqs, queueUrl)
    val message = UUID.randomUUID().toString

    putter.put(ApiPayload(message))

    sqs.sendMessageCalls should have size 1

    sqs.sendMessageCalls.head.getMessageBody shouldBe message
    sqs.sendMessageCalls.head.getQueueUrl shouldBe queueUrl
  }

}