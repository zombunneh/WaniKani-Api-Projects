package com.api.gui;

import com.api.json.QueryResponse;
import com.api.queries.Query;

public class GUIModel{
    private String APIKey;
    private Boolean userSubscriptionStatus;
    private Query query;
    private QueryResponse[] responses;
    private int numPages;

    public GUIModel()
    {
        query = null;
    }

    public String getAPIKey() {
        return APIKey;
    }

    public void setAPIKey(String APIKey) {
        this.APIKey = APIKey;
    }

    public Boolean getUserSubscriptionStatus() {
        return userSubscriptionStatus;
    }

    public void setUserSubscriptionStatus(Boolean userSubscriptionStatus) {
        this.userSubscriptionStatus = userSubscriptionStatus;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public void initialiseResponseArray(int pages)
    {
        responses = new QueryResponse[pages];
        numPages = pages;
    }

    public void addResponseArray(QueryResponse response, int pageNum)
    {

    }
}