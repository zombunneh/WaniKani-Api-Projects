package com.api.gui;

import com.api.json.QueryResponse;

public class GUIModel{
    private String APIKey;
    private Boolean userSubscriptionStatus;
    private QueryResponse[] responses;
    private int numPages;

    // These variables are for user management / caching and are a layer of security checks to ensure invalid API calls are not made according to the stated limitations in the API docs
    private int max_level_num;
    private boolean subscription_bool;
    private String user_type;
    private String user_id;

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

    public int getMax_level_num() {
        return max_level_num;
    }

    public void setMax_level_num(int max_level_num) {
        this.max_level_num = max_level_num;
    }

    public boolean isSubscription_bool() {
        return subscription_bool;
    }

    public void setSubscription_bool(boolean subscription_bool) {
        this.subscription_bool = subscription_bool;
    }

    public String getType() {
        return user_type;
    }

    public void setType(String type) {
        this.user_type = type;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String id)
    {
        this.user_id = id;
    }
}