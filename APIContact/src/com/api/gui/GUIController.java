package com.api.gui;

import com.api.json.JSONParse;
import com.api.json.QueryResponse;
import com.api.APIContact;
import com.api.queries.APIVerify;

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
        boolean verified;
        APIVerify verifier = new APIVerify();

        contact.setAPIKey(APIKey);
        contact.verifyAPIKeyQuery();

        verified = verifier.verifyAPIKey(contact.getQuery().getRepresentation());

        if(verified)
        {
            model.setAPIKey(APIKey);
            view.enableComponents();

            model.setMax_level_num(verifier.getMax_level_num());
            model.setSubscription_bool(verifier.isSubscription_bool());
            model.setType(verifier.getType());
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
        contact.setQueryType(query);
        // Set endpoint of query
        contact.setCategory(endpoint);
        // Set API key for query
        contact.setAPIKey(model.getAPIKey());
        // Make query
        contact.getQuery().makeQuery("");
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
        response = parser.readResponse(contact.getQuery().getRepresentation());
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

                contact.getQuery().makeQuery(response.nextUrl);

                response = parser.readResponse(contact.getQuery().getRepresentation());
                currentPage++;
            }
        }
    }
}
