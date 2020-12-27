/*
 * APIContact.java
 *
 * @author Matthew Winterbourn
 * @version 1.0
 * @since 10/11/2020
 *
 * Copyright (c) 2020.
 */

package com.api;

import com.api.queries.APIQueryFactory;
import com.api.queries.Query;
import com.api.queries.QueryType;

public class APIContact {
    /* These are unused string constants from a previous version of the program
    public static final Scanner UserInputScanner = new Scanner(System.in);

    private static final String WelcomeMessage = "Please enter a Query Type:";
    private static final String InvalidQueryTypeMessage = "Invalid Query Type. Please enter a valid Query Type:";
    private static final String SetAPIKeyMessage = "Please enter your API Key:";
    private static final String SetCategoryMessage = "Please enter the chosen Category:";
    public static final String InvalidCategoryMessage = "Invalid Category. Please enter a valid Category";
    public static final String SetFormatMessage = "Please choose a data format:";
    */

    private Query query;
    private APIQueryFactory fac;

    public APIContact()
    {
        fac = new APIQueryFactory();
        query = fac.createQueryType(QueryType.GET.toString());
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    /**
     *
     * Sets the type of query to be made, corresponding to the 4 HTTP Verbs (GET PUT POST DELETE)
     *
     * @return True if query type successfully set, false if not
     */
    public Boolean setQueryType(String queryTypeInput)
    {
        query = fac.createQueryType(queryTypeInput);

        return query != null;
    }

    /**
     *
     * Sets the API key of the Query object supplied
     *
     * @param APIKey The API Key to set
     */
    public void setAPIKey(String APIKey)
    {
        query.setAPIKey(APIKey);
    }

    /**
     *
     * Sets the Endpoint of the Query object supplied
     *
     * @param endpointInput The endpoint being set
     * @return True if category is successfully set, false if not
     */
    public Boolean setCategory(String endpointInput)
    {
        for (APIEndpoint e : APIEndpoint.values())
        {
            if (endpointInput.equalsIgnoreCase(e.toString()))
            {
                query.setEndpoint(e);
            }
        }

        return query.getEndpoint() != null;
    }

    /**
     *
     * Verifies the API key provided with the server, checking subscription status to validate use of program
     *
     */
    public void verifyAPIKeyQuery()
    {
        query.setEndpoint(APIEndpoint.USER);
        query.makeQuery("");
    }
}
