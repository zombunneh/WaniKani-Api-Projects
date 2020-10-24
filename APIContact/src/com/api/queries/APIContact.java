package com.api.queries;

import java.util.Scanner;

public class APIContact {
    public static final Scanner UserInputScanner = new Scanner(System.in);

    private static final String WelcomeMessage = "Please enter a Query Type:";
    private static final String InvalidQueryTypeMessage = "Invalid Query Type. Please enter a valid Query Type:";
    private static final String SetAPIKeyMessage = "Please enter your API Key:";
    private static final String SetCategoryMessage = "Please enter the chosen Category:";

    private static final String APIKEY = "eb9ce760-29d4-4d5f-81e5-c6a7a80384da";

    /**
     *
     * Main method of program to accept user input in order to construct specific queries to the WaniKani API
     *
     * @param args NO args need to be supplied
     */
    public static void main(String[] args) {

        Query q = null;

        APIQueryConstructor WKQuery = new APIQueryConstructor();

        q = SetQueryType(q);

        SetAPIKey(q);
        System.out.println(q.GetAPIKey());

        SetCategory(q);
        System.out.println(q.GetCategory());

        q.MakeQuery();
    }

    /**
     *
     * TODO : MOVE ALL USER INPUT METHODS TO SEPARATE CLASS!
     *
     * @param q The Query object to modify
     * @return The modified Query object that has been assigned a subclass
     */
    private static Query SetQueryType(Query q)
    {
        String QueryTypeInput;
        APIQueryFactory fac = new APIQueryFactory();

        System.out.println(WelcomeMessage);

        while(q == null) {
            QueryTypeInput = UserInputScanner.nextLine();

            q = fac.CreateQueryType(QueryTypeInput);

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
     * Sets the Category of the Query object supplied
     *
     * @param q The Query object to modify
     */
    private static void SetCategory(Query q)
    {
        String CategoryInput;

        System.out.println(SetCategoryMessage);

        CategoryInput = UserInputScanner.nextLine();
        q.SetCategory(CategoryInput);
    }
}
