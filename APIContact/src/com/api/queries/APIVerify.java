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

    /**
     *
     *
     *
     * @param repr
     * @return
     */
    public boolean verifyAPIKey(Representation repr)
    {
        JSONParse parser = new JSONParse();
        QueryResponse response;

        response = parser.readResponse(repr);

        if(response.jObject[0] != null)
        {
            JSONObject dataObject = response.jObject[0];

            int max_level_num = dataObject.getInt(max_level);
            boolean subscription_bool = dataObject.getBoolean(subscription_active);


        }
        else
        {
            return false;
        }

        return true;
    }
}
