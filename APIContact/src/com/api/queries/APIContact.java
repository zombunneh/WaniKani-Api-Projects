package com.api.queries;

import java.util.Scanner;

import com.api.gui.GUIController;
import com.api.gui.GUIModel;
import com.api.gui.GUIView;
import com.api.json.JSONParse;

import javax.swing.*;

public class APIContact {
    public static final Scanner UserInputScanner = new Scanner(System.in);
    private static final APIQueryConstructor constructor = new APIQueryConstructor();

    private static final String WelcomeMessage = "Please enter a Query Type:";
    private static final String InvalidQueryTypeMessage = "Invalid Query Type. Please enter a valid Query Type:";
    private static final String SetAPIKeyMessage = "Please enter your API Key:";
    private static final String SetCategoryMessage = "Please enter the chosen Category:";
    public static final String InvalidCategoryMessage = "Invalid Category. Please enter a valid Category";
    public static final String SetFormatMessage = "Please choose a data format:";

    /**
     *
     * Sets the type of query to be made, corresponding to the 4 HTTP Verbs (GET PUT POST DELETE)
     *
     * @param q The Query object to modify
     * @return The modified Query object that has been assigned a subclass
     */
    public Query SetQueryType(Query q)
    {
        String queryTypeInput;
        APIQueryFactory fac = new APIQueryFactory();

        System.out.println(WelcomeMessage);

        while(q == null) {
            queryTypeInput = UserInputScanner.nextLine();

            q = fac.CreateQueryType(queryTypeInput);

            if(q == null)
            {
                System.out.println(InvalidQueryTypeMessage);
            }
        }

        return q;
    }

    /**
     *
     * Sets the API key of the Query object supplied
     *
     * @param q The Query object to modify
     */
    public void SetAPIKey(Query q)
    {
        String APIKeyInput;

        System.out.println(SetAPIKeyMessage);

        APIKeyInput = UserInputScanner.nextLine();
        q.SetAPIKey(APIKeyInput);
    }

    /**
     *
     * Sets the Endpoint of the Query object supplied
     *
     * @param q The Query object to modify
     */
    public void SetCategory(Query q)
    {
        String endpointInput;

        System.out.println(SetCategoryMessage);

        endpointInput = UserInputScanner.nextLine();

        while(q.GetEndpoint() == null)
        {
            for (APIEndpoint e : APIEndpoint.values())
            {
                if (endpointInput.equalsIgnoreCase(e.toString()))
                {
                    q.SetEndpoint(e);
                }
            }

            if(q.GetEndpoint() == null)
            {
                System.out.println(InvalidCategoryMessage);
            }
        }
    }

    /**
     *
     *
     *
     * @param jparse JSONParse object
     */
    private void SetFormatQuery(JSONParse jparse)
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
    private void verifyAPIKey(String key)
    {

    }
}
