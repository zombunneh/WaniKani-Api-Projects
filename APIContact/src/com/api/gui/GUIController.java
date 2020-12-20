package com.api.gui;

import com.api.json.JSONParse;
import com.api.json.QueryResponse;
import com.api.queries.APIContact;
import com.api.queries.APIQueryConstructor;
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

    /**
     *
     *
     *
     * @param query
     * @param endpoint
     */
    public void onRegisterMakeQueryButtonClick(String query, String endpoint)
    {
        // Create query object in model
        APIQueryFactory fac = new APIQueryFactory();
        contact.setQueryType(query);
        // Set query constructor for query object
        final APIQueryConstructor constructor = new APIQueryConstructor();
        model.getQuery().setQueryConstructor(constructor);
        // Set endpoint of query
        contact.setCategory(model.getQuery(), endpoint);
        // Set API key for query
        model.getQuery().setAPIKey(model.getAPIKey());
        // Make query
        model.getQuery().MakeQuery("");
        // Handle response
        parseResponse();
    }

    /**
     *
     */
    private void parseResponse()
    {
        // Pass initial query response to parser
        QueryResponse response;
        JSONParse parser = new JSONParse();
        response = parser.ReadResponse(model.getQuery().getRepresentation());
        // Calculate the number of pages in the response
        int numPages = response.collectionCount / response.collectionCountPerPage;
        if(response.collectionCountPerPage % response.collectionCount != 0)
            numPages++;
        // Initialise the array in GUIModel with correct number of indexes
        model.initialiseResponseArray(numPages);
        // Keep making queries to retrieve any following pages
        int currentPage = 0;
        if(!response.nextUrl.equals(""))
        {
            while(!response.nextUrl.equals(""))
            {
                model.addResponseArray(response, currentPage);

                model.getQuery().MakeQuery(response.nextUrl);

                response = parser.ReadResponse(model.getQuery().getRepresentation());
                currentPage++;
            }
        }
    }
}
