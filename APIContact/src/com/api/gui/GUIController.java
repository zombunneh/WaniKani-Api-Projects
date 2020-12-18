package com.api.gui;

import com.api.json.JSONParse;
import com.api.queries.APIContact;
import com.api.queries.APIQueryFactory;

public class GUIController {
    private APIContact contact;
    private GUIModel model;
    private GUIView view;

    /**
     *
     *
     *
     * @param contact The object which controls API communication
     * @param model The model that stores program related data
     */
    public GUIController(APIContact contact, GUIModel model)
    {
        this.contact = contact;
        this.model = model;
        view = new GUIView(this);
    }

    /**
     *
     *
     *
     * @param APIKey The API key to verify and store
     */
    public void onRegisterVerifyButtonClick(String APIKey)
    {
        Boolean verified;
        verified = contact.verifyAPIKey(APIKey);
        if(verified)
        {
            model.setAPIKey(APIKey);
            view.enableComponents();
        }
    }

    public void onRegisterMakeQueryButtonClick(String query, String endpoint)
    {
        // Create query object in model
        APIQueryFactory fac = new APIQueryFactory();
        model.setQuery(fac.CreateQueryType(query));
        // Set endpoint of query
        contact.SetCategory(model.getQuery(), endpoint);
        // Set API key for query
        model.getQuery().setAPIKey(model.getAPIKey());
        // Make query
        model.getQuery().MakeQuery("");
        // Handle response
        parseResponse();
    }

    private void parseResponse()
    {
        String nextUrl;
        JSONParse parser = new JSONParse();
        nextUrl = parser.ReadResponse(model.getQuery().getRepresentation());

        if(!nextUrl.equals(""))
        {
            while(!nextUrl.equals(""))
            {
                model.getQuery().MakeQuery(nextUrl);

                nextUrl = parser.ReadResponse(model.getQuery().getRepresentation());
            }
        }
    }
}
