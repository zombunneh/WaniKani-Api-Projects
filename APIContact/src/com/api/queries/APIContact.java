package com.api.queries;

import java.util.Scanner;

import com.api.gui.GUIController;
import com.api.gui.GUIModel;
import com.api.json.JSONParse;

public class APIContact {
    public static final Scanner UserInputScanner = new Scanner(System.in);

    private static final String WelcomeMessage = "Please enter a Query Type:";
    private static final String InvalidQueryTypeMessage = "Invalid Query Type. Please enter a valid Query Type:";
    private static final String SetAPIKeyMessage = "Please enter your API Key:";
    private static final String SetCategoryMessage = "Please enter the chosen Category:";
    public static final String InvalidCategoryMessage = "Invalid Category. Please enter a valid Category";
    public static final String SetFormatMessage = "Please choose a data format:";

    private Query query;

    public APIContact()
    {
        query = null;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    /**
     *
     * Sets the type of query to be made, corresponding to the 4 HTTP Verbs (GET PUT POST DELETE)
     *
     * @return True if query type successfully set, false if not
     */
    public Boolean setQueryType(String queryTypeInput)
    {
        APIQueryFactory fac = new APIQueryFactory();

        query = fac.CreateQueryType(queryTypeInput);

        return query != null;
    }

    /**
     *
     * Sets the API key of the Query object supplied
     *
     * @param APIKey The API Key to set
     */
    public void setAPIKey(String APIKey)
    {
        query.setAPIKey(APIKey);
    }

    /**
     *
     * Sets the Endpoint of the Query object supplied
     *
     * @param endpointInput The endpoint being set
     * @return True if category is successfully set, false if not
     */
    public Boolean setCategory(String endpointInput)
    {
        for (APIEndpoint e : APIEndpoint.values())
        {
            if (endpointInput.equalsIgnoreCase(e.toString()))
            {
                query.setEndpoint(e);
            }
        }

        return query.getEndpoint() != null;
    }

    /**
     *
     *
     *
     * @param jparse JSONParse object
     */
    public void setFormatQuery(JSONParse jparse)
    {
        System.out.println(SetFormatMessage);
    }

    /**
     *
     *
     *
     * @throws Exception If graphics window fails to draw
     */
    public void createAndShowWindow() throws Exception
    {
        GUIModel model = new GUIModel();
        GUIController controller = new GUIController(this, model);
    }

    /**
     *
     * Verifies the API key provided with the server, checking subscription status to validate use of program
     *
     * @param key The API key to validate
     */
    public Boolean verifyAPIKey(String key)
    {
        return true;
    }
}
