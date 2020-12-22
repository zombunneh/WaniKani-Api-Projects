/*
 * APIVerify.java
 *
 * @author Matthew Winterbourn
 * @version 1.0
 * @since 10/11/2020
 *
 * Copyright (c) 2020.
 */

package com.api.queries;

import com.api.json.JSONParse;
import com.api.json.QueryResponse;
import org.json.JSONObject;
import org.restlet.representation.Representation;

public class APIVerify {

    private static final String max_level = "max_level_granted";
    private static final String subscription_active = "active";
    private static final String type_subscription = "type";

    private int max_level_num;
    private boolean subscription_bool;
    private String type;

    /**
     *
     * Retrieves information from a JSON Object contained within the Representation object regarding the user's subscription status and returns whether the query was successful
     *
     * @param repr The Representation object to read from
     * @return A boolean representing whether the supplied API Key was valid
     */
    public boolean verifyAPIKey(Representation repr)
    {
        JSONParse parser = new JSONParse();
        QueryResponse response;

        response = parser.readResponse(repr);

        if(response.jObject[0] != null)
        {
            JSONObject dataObject = response.jObject[0];

            max_level_num = dataObject.getInt(max_level);
            subscription_bool = dataObject.getBoolean(subscription_active);
            type = dataObject.getString(type_subscription);
        }
        else
        {
            return false;
        }

        return true;
    }

    public int getMax_level_num()
    {
        return max_level_num;
    }

    public boolean isSubscription_bool()
    {
        return subscription_bool;
    }

    public String getType()
    {
        return type;
    }
}
