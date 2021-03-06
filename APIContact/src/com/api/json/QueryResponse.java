/*
 * QueryResponse.java
 *
 * @author Matthew Winterbourn
 * @version 1.0
 * @since 10/11/2020
 *
 * Copyright (c) 2020.
 */

package com.api.json;

import org.json.JSONArray;
import org.json.JSONObject;

// Consider switching to private constructors if threading to prevent creation of multiple query responses

/**
 * This class for internal use to represent the parsed response to return to calling classes
 */
public class QueryResponse {
    public Boolean isCollection = false;
    public Boolean isReport = false;
    public Boolean isResource = false;
    // Collection variables
    public String collectionDate;
    public int collectionCount;
    public int collectionCountPerPage;
    public String nextUrl;
    public String collectionUrl;
    // Report variables
    public JSONObject reportObject;
    public String reportUrl;
    public String reportDate;
    // Resource variables
    public int resourceCount = 0;
    public String[] date;
    public String[] resourceUrl;
    public int[] id;
    public JSONObject[] jObject;

    /**
     * Constructor for a single resource response
     *  @param date        The date the resource was last updated
     * @param resourceUrl The url of the resource returned
     * @param id          The id of the resource
     * @param jObject     The JSONObject containing the resource specific information
     */
    public QueryResponse(String date, String resourceUrl, int id, JSONObject jObject) {
        // Set type of class to resource
        isCollection = false;
        isReport = false;
        isResource = true;
        // Initialise resource arrays to size 1
        this.date = new String[1];
        this.resourceUrl = new String[1];
        this.id = new int[1];
        this.jObject = new JSONObject[1];
        // Initialise resource specific variables
        this.date[0] = date;
        this.resourceUrl[0] = resourceUrl;
        this.id[0] = id;
        this.jObject[0] = jObject;
    }

    /**
     * Constructor for a collection response
     *
     * @param count         The amount of data objects returned
     * @param countPerPage  The amount of objects per page
     * @param nextUrl       The url, if any, of the next page of results
     * @param collectionUrl The url of the collection object returned
     * @param date          The date the resource was last updated
     */
    public QueryResponse(int count, int countPerPage, String nextUrl, String collectionUrl, String date) {
        // Set type of class to collection
        isCollection = true;
        isReport = false;
        isResource = false;
        // Initialise collection specific variables
        collectionCount = count;
        collectionCountPerPage = countPerPage;
        this.nextUrl = nextUrl;
        this.collectionUrl = collectionUrl;
        collectionDate = date;
        // Initialise resource arrays to size of response
        this.date = new String[count];
        this.resourceUrl = new String[count];
        this.id = new int[count];
        this.jObject = new JSONObject[count];
    }

    public QueryResponse(String reportUrl, String date, JSONObject reportData)
    {
        // Set type of class to report
        isCollection = false;
        isReport = true;
        isResource = false;
        // Initialise report specific variables
        this.reportUrl = reportUrl;
        this.reportDate = date;
        this.reportObject = reportData;
    }

    /**
     * Adds a resource to a collection type response object
     *  @param date        The date the resource was last updated
     * @param resourceUrl The url of the resource returned
     * @param id          The id of the resource
     * @param jObject     The JSONObject containing the resource specific information
     */
    public void addResource(String date, String resourceUrl, int id, JSONObject jObject) {
        if (isCollection) {
            // Add resource variables to current index
            this.date[resourceCount] = date;
            this.resourceUrl[resourceCount] = resourceUrl;
            this.id[resourceCount] = id;
            this.jObject[resourceCount] = jObject;
            // Increment count / index for next resource
            resourceCount++;
        }
    }
}
