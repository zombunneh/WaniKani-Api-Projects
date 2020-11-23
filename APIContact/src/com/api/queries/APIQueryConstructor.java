package com.api.queries;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Header;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class APIQueryConstructor {
    private ClientResource WKAPI;
    private String RequestURLInitial = "";
    private String APIVersion = "";
    private static final String VersionIdentifier = "Wanikani-Revision";

    /**
     * Here we setup the member variables read in from a settings file containing the base url for the API endpoint and the current API version
     */
    public APIQueryConstructor()
    {
        File SettingsFile = new File(System.getProperty("user.dir") + "/src/com/api/queries/settings/settings.txt");
        Scanner ReadSettings;

        try {
            ReadSettings = new Scanner(SettingsFile);

            if(ReadSettings.hasNextLine())
            {
                RequestURLInitial = ReadSettings.nextLine();
            }

            if(ReadSettings.hasNextLine())
            {
                APIVersion = ReadSettings.nextLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // potentially handle some way (popup -- error settings file unable to be located -- ask for location?)
        }
    }


    /**
     *
     * To access a specific data point an ID must be supplied, may overload method later to provide functionality
     *
     * TODO: FLEXIBLE API CALL + PROCESS API RESPONSE AS JSON OBJECT + CHECK FOR NEXT PAGE IN RESPONSE (new method/class)?
     *
     * @param APIKey The API key to be used for authorization
     * @param RequestType The type of restful request to make, corresponds to the 4 verbs
     * @param Endpoint The API endpoint to be accessed
     */
    public Representation MakeAPICall(String APIKey, QueryType RequestType, APIEndpoint Endpoint)
    {
        String RequestURL = BuildAPIRequestURL(Endpoint); // create the url for the api request to access

        WKAPI = new ClientResource(RequestURL); // create a new client for restful calls
        WKAPI.setProtocol(Protocol.HTTPS);

        ChallengeResponse AuthHeader = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER); // here we create the authorization header with the API key required to access the API
        AuthHeader.setRawValue(APIKey);
        AuthHeader.setIdentifier("Bearer");
        Header VersionHeader = new Header(VersionIdentifier, APIVersion); // here we create the version header with the API version required to access the API

        WKAPI.setChallengeResponse(AuthHeader); // setting the header for the authorization key
        WKAPI.getRequest().getHeaders().add(VersionHeader); // setting the header for the current API version

        Representation Repr = null;

        switch (RequestType) // switch  to determine type of call to make based on given parameter
        {
            case GET:
                Repr = WKAPI.get(); // here we make our call to the API
                break;
            case PUT:
                Repr = WKAPI.get(); // here we make our call to the API
                break;
            case POST:
                Repr = WKAPI.get(); // here we make our call to the API
                break;
            case DELETE:
                Repr = WKAPI.get(); // here we make our call to the API
                break;
        }

        try {
            System.out.println(Repr.getText()); // printing the response we received
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return Repr;
    }

    /**
     *
     * @param Endpoint The API endpoint to be accessed
     * @return A String to be used as the URL for restful calls to the API utilised
     */
    private String BuildAPIRequestURL(APIEndpoint Endpoint)
    {
        StringBuilder BuildRequestURL = new StringBuilder();
        BuildRequestURL.append(RequestURLInitial);
        BuildRequestURL.append(Endpoint.toString().toLowerCase());

        return BuildRequestURL.toString();
    }
}
