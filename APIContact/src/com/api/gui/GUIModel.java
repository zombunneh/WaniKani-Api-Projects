package com.api.gui;

import com.api.json.QueryResponse;

public class GUIModel{
    private String APIKey;
    private Boolean userSubscriptionStatus;
    private QueryResponse[] responses;
    private int numPages;

    public GUIModel()
    {

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

    public void initialiseResponseArray(int pages)
    {
        responses = new QueryResponse[pages];
        numPages = pages;
    }

    public void addResponseArray(QueryResponse response, int pageNum)
    {
        responses[pageNum] = response;
    }

    public QueryResponse getResponseAtIndex(int index)
    {
        return responses[index];
    }
}