package com.nashss.se.collectosstash.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Represents a Vendor in the DynamoDB table.
 */

@DynamoDBTable(tableName = "ComicBooks")
public class ComicBook {
    private String id;
    private String volumeNumber;
    private String issueNumber;
    private String date;
    private int price;
    private boolean isFavorite;
    private String publisher;

    /**
     *
     * @return id, the partition key of the comicBook in the ComicBooks table
     */
    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    /**
     *
     * @return volumeNumber of the ComicBook volumeNumber
     */
    @DynamoDBRangeKey(attributeName = "volumeNumber")
    public String getVolumeNumber() {
        return volumeNumber;
    }
    /**
     *
     * @return issueNumber of the ComicBook issueNumber
     */
    @DynamoDBAttribute(attributeName = "issueNumber")
    public String getIssueNumber() {
        return issueNumber;
    }



}
