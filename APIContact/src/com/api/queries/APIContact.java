package com.api.queries;

import java.util.Scanner;

public class APIContact {
    public static final Scanner UserInputScanner = new Scanner(System.in);

    private static final String WelcomeMessage = "Please enter a Query Type:";
    private static final String InvalidQueryTypeMessage = "Invalid Query Type. Please enter a valid Query Type:";
    private static final String SetAPIKeyMessage = "Please enter your API Key:";
    private static final String SetCategoryMessage = "Please enter the chosen Category:";

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

    /*
    TODO : MOVE ALL USER INPUT METHODS TO SEPARATE CLASS!
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

        q.printq();
        return q;
    }

    private static void SetAPIKey(Query q)
    {
        String APIKeyInput;

        System.out.println(SetAPIKeyMessage);

        APIKeyInput = UserInputScanner.nextLine();
        q.SetAPIKey(APIKeyInput);
    }

    private static void SetCategory(Query q)
    {
        String CategoryInput;

        System.out.println(SetCategoryMessage);

        CategoryInput = UserInputScanner.nextLine();
        q.SetCategory(CategoryInput);
    }
}
