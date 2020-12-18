package com.api.gui;

import com.api.queries.Query;

public class GUIModel{
    private String APIKey;
    private Boolean userSubscriptionStatus;
    private Query query;

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
}