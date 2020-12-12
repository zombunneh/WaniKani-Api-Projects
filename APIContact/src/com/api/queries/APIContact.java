package com.api.queries;

import java.util.Scanner;
import com.api.json.JSONParse;

public class APIContact {
    public static final Scanner UserInputScanner = new Scanner(System.in);

    private static final String WelcomeMessage = "Please enter a Query Type:";
    private static final String InvalidQueryTypeMessage = "Invalid Query Type. Please enter a valid Query Type:";
    private static final String SetAPIKeyMessage = "Please enter your API Key:";
    private static final String SetCategoryMessage = "Please enter the chosen Category:";
    public static final String InvalidCategoryMessage = "Invalid Category. Please enter a valid Category";
    public static final String SetFormatMessage = "Please choose a data format:";

    /**
     *
     * Main method of program to accept user input in order to construct specific queries to the WaniKani API
     *
     * TODO: Eventually overhaul main method and replace with gui
     *
     * TODO: check user subscription status and feedback
     *
     * TODO: Add-on database for longer term storage of results?
     *
     * TODO: Thread application
     *
     * @param args NO args need to be supplied
     */
    public static void main(String[] args) {

        Query q = null;
        String nextUrl = "";

        q = SetQueryType(q);

        SetAPIKey(q);
        System.out.println(q.GetAPIKey());

        SetCategory(q);
        System.out.println(q.GetEndpoint().toString());

        q.MakeQuery("");

        JSONParse parser = new JSONParse();
        nextUrl = parser.ReadResponse(q.GetRepresentation());

        if(!nextUrl.equals(""))
        {
            while(!nextUrl.equals(""))
            {
                q.MakeQuery(nextUrl);

                nextUrl = parser.ReadResponse(q.GetRepresentation());
            }
        }
    }

    /**
     *
     * Sets the type of query to be made, corresponding to the 4 HTTP Verbs (GET PUT POST DELETE)
     *
     * @param q The Query object to modify
     * @return The modified Query object that has been assigned a subclass
     */
    private static Query SetQueryType(Query q)
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
    private static void SetAPIKey(Query q)
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
    private static void SetCategory(Query q)
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

    private static void SetFormatQuery(JSONParse jparse)
    {
        System.out.println(SetFormatMessage);
    }
}
