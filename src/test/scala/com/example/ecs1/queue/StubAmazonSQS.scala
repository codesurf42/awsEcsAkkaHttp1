package com.example.ecs1.queue

import java.util

import com.amazonaws.{AmazonWebServiceRequest, ResponseMetadata}
import com.amazonaws.regions.Region
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model._

import scala.collection.mutable.ListBuffer

class StubAmazonSQS extends AmazonSQS {

  val sendMessageCalls = new ListBuffer[SendMessageRequest]

  override def addPermission(addPermissionRequest: AddPermissionRequest): AddPermissionResult = ???

  override def addPermission(queueUrl: String, label: String, aWSAccountIds: util.List[String], actions: util.List[String]): AddPermissionResult = ???

  override def getCachedResponseMetadata(request: AmazonWebServiceRequest): ResponseMetadata = ???

  override def setQueueAttributes(setQueueAttributesRequest: SetQueueAttributesRequest): SetQueueAttributesResult = ???

  override def setQueueAttributes(queueUrl: String, attributes: util.Map[String, String]): SetQueueAttributesResult = ???

  override def setEndpoint(endpoint: String): Unit = ???

  override def sendMessageBatch(sendMessageBatchRequest: SendMessageBatchRequest): SendMessageBatchResult = ???

  override def sendMessageBatch(queueUrl: String, entries: util.List[SendMessageBatchRequestEntry]): SendMessageBatchResult = ???

  override def listQueues(listQueuesRequest: ListQueuesRequest): ListQueuesResult = ???

  override def listQueues(): ListQueuesResult = ???

  override def listQueues(queueNamePrefix: String): ListQueuesResult = ???

  override def setRegion(region: Region): Unit = ???

  override def receiveMessage(receiveMessageRequest: ReceiveMessageRequest): ReceiveMessageResult = ???

  override def receiveMessage(queueUrl: String): ReceiveMessageResult = ???

  override def changeMessageVisibilityBatch(changeMessageVisibilityBatchRequest: ChangeMessageVisibilityBatchRequest): ChangeMessageVisibilityBatchResult = ???

  override def changeMessageVisibilityBatch(queueUrl: String, entries: util.List[ChangeMessageVisibilityBatchRequestEntry]): ChangeMessageVisibilityBatchResult = ???

  override def getQueueUrl(getQueueUrlRequest: GetQueueUrlRequest): GetQueueUrlResult = ???

  override def getQueueUrl(queueName: String): GetQueueUrlResult = ???

  override def getQueueAttributes(getQueueAttributesRequest: GetQueueAttributesRequest): GetQueueAttributesResult = ???

  override def getQueueAttributes(queueUrl: String, attributeNames: util.List[String]): GetQueueAttributesResult = ???

  override def sendMessage(sendMessageRequest: SendMessageRequest): SendMessageResult = {
    sendMessageCalls += sendMessageRequest
    new SendMessageResult().withSequenceNumber(sendMessageCalls.size.toString)
  }

  override def sendMessage(queueUrl: String, messageBody: String): SendMessageResult = ???

  override def deleteMessage(deleteMessageRequest: DeleteMessageRequest): DeleteMessageResult = ???

  override def deleteMessage(queueUrl: String, receiptHandle: String): DeleteMessageResult = ???

  override def removePermission(removePermissionRequest: RemovePermissionRequest): RemovePermissionResult = ???

  override def removePermission(queueUrl: String, label: String): RemovePermissionResult = ???

  override def listDeadLetterSourceQueues(listDeadLetterSourceQueuesRequest: ListDeadLetterSourceQueuesRequest): ListDeadLetterSourceQueuesResult = ???

  override def deleteMessageBatch(deleteMessageBatchRequest: DeleteMessageBatchRequest): DeleteMessageBatchResult = ???

  override def deleteMessageBatch(queueUrl: String, entries: util.List[DeleteMessageBatchRequestEntry]): DeleteMessageBatchResult = ???

  override def purgeQueue(purgeQueueRequest: PurgeQueueRequest): PurgeQueueResult = ???

  override def createQueue(createQueueRequest: CreateQueueRequest): CreateQueueResult = ???

  override def createQueue(queueName: String): CreateQueueResult = ???

  override def deleteQueue(deleteQueueRequest: DeleteQueueRequest): DeleteQueueResult = ???

  override def deleteQueue(queueUrl: String): DeleteQueueResult = ???

  override def changeMessageVisibility(changeMessageVisibilityRequest: ChangeMessageVisibilityRequest): ChangeMessageVisibilityResult = ???

  override def changeMessageVisibility(queueUrl: String, receiptHandle: String, visibilityTimeout: Integer): ChangeMessageVisibilityResult = ???

  override def shutdown(): Unit = ???

  override def untagQueue(untagQueueRequest: UntagQueueRequest) = ???

  override def untagQueue(queueUrl: String, tagKeys: util.List[String]) = ???

  override def tagQueue(tagQueueRequest: TagQueueRequest) = ???

  override def tagQueue(queueUrl: String, tags: util.Map[String, String]) = ???

  override def listQueueTags(listQueueTagsRequest: ListQueueTagsRequest) = ???

  override def listQueueTags(queueUrl: String) = ???
}
