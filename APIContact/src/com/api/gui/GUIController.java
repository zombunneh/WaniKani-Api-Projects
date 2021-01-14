package com.api.gui;

import com.api.json.JSONParse;
import com.api.json.QueryResponse;
import com.api.APIContact;
import com.api.queries.APIVerify;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GUIController {
    private final APIContact contact;
    private final GUIModel model;
    private final GUIView view;
    private final ExecutorService exService;

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
        exService = Executors.newSingleThreadExecutor();
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
            model.setMax_level_num(verifier.getMax_level_num());
            model.setSubscription_bool(verifier.isSubscription_bool());
            model.setType(verifier.getType());
            model.setUser_id(verifier.getUser_id());

            // edit the gui on the EDT
            SwingUtilities.invokeLater(() -> view.enableComponents());
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
        int currentPage = 0;
        // Pass initial query response to parser
        QueryResponse response;
        JSONParse parser = new JSONParse();
        response = parser.readResponse(contact.getQuery().getRepresentation());
        // Calculate the number of pages in the response
        if(response != null)
        {
            if(response.isCollection)
            {
                int numPages = response.collectionCount / response.collectionCountPerPage;
                if(response.collectionCountPerPage % response.collectionCount != 0)
                    numPages++;
                // Initialise the array in GUIModel with correct number of indexes
                model.initialiseResponseArray(numPages);
                // Keep making queries to retrieve any following pages

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
            else if(response.isReport)
            {
                model.initialiseResponseArray(1);

                model.addResponseArray(response, currentPage);
            }
            else if(response.isResource)
            {
                model.initialiseResponseArray(1);

                model.addResponseArray(response, currentPage);
            }
        }
        else
        {
            // If there was no data for the current user:
            SwingUtilities.invokeLater(() -> view.displayEmptyResultsMessage());
        }
    }
}
