package com.example.ecs1.queue

import java.nio.ByteBuffer

import com.amazonaws.{AmazonWebServiceRequest, ResponseMetadata}
import com.amazonaws.regions.Region
import com.amazonaws.services.kinesis.AmazonKinesis
import com.amazonaws.services.kinesis.model._
import com.amazonaws.services.kinesis.waiters.AmazonKinesisWaiters

import scala.collection.mutable.ListBuffer

class StubAmazonKinesis extends AmazonKinesis {

  val putRecordRequests = new ListBuffer[PutRecordRequest]

  override def putRecords(putRecordsRequest: PutRecordsRequest): PutRecordsResult = ???

  override def removeTagsFromStream(removeTagsFromStreamRequest: RemoveTagsFromStreamRequest): RemoveTagsFromStreamResult = ???

  override def getCachedResponseMetadata(request: AmazonWebServiceRequest): ResponseMetadata = ???

  override def setEndpoint(endpoint: String): Unit = ???

  override def describeLimits(describeLimitsRequest: DescribeLimitsRequest): DescribeLimitsResult = ???

  override def increaseStreamRetentionPeriod(increaseStreamRetentionPeriodRequest: IncreaseStreamRetentionPeriodRequest): IncreaseStreamRetentionPeriodResult = ???

  override def updateShardCount(updateShardCountRequest: UpdateShardCountRequest): UpdateShardCountResult = ???

  override def createStream(createStreamRequest: CreateStreamRequest): CreateStreamResult = ???

  override def createStream(streamName: String, shardCount: Integer): CreateStreamResult = ???

  override def waiters(): AmazonKinesisWaiters = ???

  override def putRecord(putRecordRequest: PutRecordRequest): PutRecordResult = {
    putRecordRequests += putRecordRequest
    new PutRecordResult().withSequenceNumber(putRecordRequests.size.toString)
  }

  override def putRecord(streamName: String, data: ByteBuffer, partitionKey: String): PutRecordResult = ???

  override def putRecord(streamName: String, data: ByteBuffer, partitionKey: String, sequenceNumberForOrdering: String): PutRecordResult = ???

  override def getRecords(getRecordsRequest: GetRecordsRequest): GetRecordsResult = ???

  override def listStreams(listStreamsRequest: ListStreamsRequest): ListStreamsResult = ???

  override def listStreams(): ListStreamsResult = ???

  override def listStreams(exclusiveStartStreamName: String): ListStreamsResult = ???

  override def listStreams(limit: Integer, exclusiveStartStreamName: String): ListStreamsResult = ???

  override def getShardIterator(getShardIteratorRequest: GetShardIteratorRequest): GetShardIteratorResult = ???

  override def getShardIterator(streamName: String, shardId: String, shardIteratorType: String): GetShardIteratorResult = ???

  override def getShardIterator(streamName: String, shardId: String, shardIteratorType: String, startingSequenceNumber: String): GetShardIteratorResult = ???

  override def setRegion(region: Region): Unit = ???

  override def listTagsForStream(listTagsForStreamRequest: ListTagsForStreamRequest): ListTagsForStreamResult = ???

  override def decreaseStreamRetentionPeriod(decreaseStreamRetentionPeriodRequest: DecreaseStreamRetentionPeriodRequest): DecreaseStreamRetentionPeriodResult = ???

  override def mergeShards(mergeShardsRequest: MergeShardsRequest): MergeShardsResult = ???

  override def mergeShards(streamName: String, shardToMerge: String, adjacentShardToMerge: String): MergeShardsResult = ???

  override def startStreamEncryption(startStreamEncryptionRequest: StartStreamEncryptionRequest): StartStreamEncryptionResult = ???

  override def describeStream(describeStreamRequest: DescribeStreamRequest): DescribeStreamResult = ???

  override def describeStream(streamName: String): DescribeStreamResult = ???

  override def describeStream(streamName: String, exclusiveStartShardId: String): DescribeStreamResult = ???

  override def describeStream(streamName: String, limit: Integer, exclusiveStartShardId: String): DescribeStreamResult = ???

  override def enableEnhancedMonitoring(enableEnhancedMonitoringRequest: EnableEnhancedMonitoringRequest): EnableEnhancedMonitoringResult = ???

  override def splitShard(splitShardRequest: SplitShardRequest): SplitShardResult = ???

  override def splitShard(streamName: String, shardToSplit: String, newStartingHashKey: String): SplitShardResult = ???

  override def addTagsToStream(addTagsToStreamRequest: AddTagsToStreamRequest): AddTagsToStreamResult = ???

  override def disableEnhancedMonitoring(disableEnhancedMonitoringRequest: DisableEnhancedMonitoringRequest): DisableEnhancedMonitoringResult = ???

  override def deleteStream(deleteStreamRequest: DeleteStreamRequest): DeleteStreamResult = ???

  override def deleteStream(streamName: String): DeleteStreamResult = ???

  override def stopStreamEncryption(stopStreamEncryptionRequest: StopStreamEncryptionRequest): StopStreamEncryptionResult = ???

  override def shutdown(): Unit = ???
}
