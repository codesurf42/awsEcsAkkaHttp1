package com.example.ecs1.queue
import java.nio.ByteBuffer
import java.util.UUID

import com.amazonaws.services.kinesis.AmazonKinesis
import com.amazonaws.services.kinesis.model.PutRecordRequest
import com.example.ecs1.{ApiPayload, MessageId}

import scala.concurrent.{ExecutionContext, Future}

class KinesisQueuePutter(kinesis: AmazonKinesis, streamName: String)(implicit ec: ExecutionContext) extends QueuePutter {

  override def put(payload: ApiPayload): Future[MessageId] = {
    val putRecordRequest = new PutRecordRequest
    putRecordRequest.setData(ByteBuffer.wrap(payload.data.getBytes("UTF-8")))
    putRecordRequest.setStreamName(streamName)
    putRecordRequest.setPartitionKey(UUID.randomUUID().toString)
    Future { kinesis.putRecord(putRecordRequest) } map { res => MessageId(res.getSequenceNumber) }
  }

}
