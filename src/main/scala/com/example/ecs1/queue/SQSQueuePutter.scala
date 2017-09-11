package com.example.ecs1.queue

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.example.ecs1.{ApiPayload, MessageId}

import scala.concurrent.{ExecutionContext, Future}

class SQSQueuePutter(client: AmazonSQS, queueUrl: String)(implicit ec: ExecutionContext) extends QueuePutter {

  override def put(payload: ApiPayload): Future[MessageId] = {
    val sendMessageRequest = new SendMessageRequest()
    sendMessageRequest.setQueueUrl(queueUrl)
    sendMessageRequest.setMessageBody(payload.data)
    Future { client.sendMessage(sendMessageRequest) } map { res => MessageId(res.getMessageId) }
  }

}