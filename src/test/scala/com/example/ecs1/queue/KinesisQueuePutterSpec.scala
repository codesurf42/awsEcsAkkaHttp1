package com.example.ecs1.queue

import java.nio.ByteBuffer
import java.util.UUID

import com.example.ecs1.ApiPayload
import org.scalatest.FunSuite
import org.scalatest.Matchers._

import scala.concurrent.ExecutionContext.Implicits.global

class KinesisQueuePutterSpec extends FunSuite {

  test("should enqueue with payload contents") {
    val kinesis = new StubAmazonKinesis
    val streamName = UUID.randomUUID().toString
    val putter = new KinesisQueuePutter(kinesis, streamName)

    val payload = new ApiPayload(UUID.randomUUID().toString)

    putter.put(payload)

    kinesis.putRecordRequests should have size 1
    new String(kinesis.putRecordRequests.head.getData.array(), "UTF-8") shouldBe payload.data
    kinesis.putRecordRequests.head.getStreamName shouldBe streamName
  }

}
