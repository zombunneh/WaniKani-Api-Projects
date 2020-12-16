package com.api.gui;

import com.api.queries.Query;

public class GUIModel{
    private String APIKey;
    private Boolean userSubscriptionStatus;
    private Query query;

    public GUIModel()
    {

    }

    public String getAPIKey() {
        return APIKey;
    }

    public void setAPIKey(String APIKey) {
        this.APIKey = APIKey;
    }
}