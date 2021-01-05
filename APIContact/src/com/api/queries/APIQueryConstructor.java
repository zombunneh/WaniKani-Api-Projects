package com.api.queries;

import com.api.APIEndpoint;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Header;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.File;
import java.io.FileNotFoundException;
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
        File settingsFile = new File(System.getProperty("user.dir") + "/src/com/api/queries/settings/settings.txt");
        Scanner readSettings;

        try {
            readSettings = new Scanner(settingsFile);

            if(readSettings.hasNextLine())
            {
                RequestURLInitial = readSettings.nextLine();
            }

            if(readSettings.hasNextLine())
            {
                APIVersion = readSettings.nextLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // potentially handle some way (popup -- error settings file unable to be located -- ask for location?)
        }
    }


    /**
     *
     * To access a specific data point an ID must be supplied, may overload method later to provide functionality
     *
     * TODO: FLEXIBLE API CALL
     *
     * @param APIKey The API key to be used for authorization
     * @param requestType The type of restful request to make, corresponds to the 4 verbs
     * @param endpoint The API endpoint to be accessed
     * @return A representation containing the API's response
     */
    public Representation MakeAPICall(String APIKey, QueryType requestType, APIEndpoint endpoint)
    {
        String requestURL = BuildAPIRequestURL(endpoint); // create the url for the api request to access

        WKAPI = new ClientResource(requestURL); // create a new client for restful calls
        WKAPI.setProtocol(Protocol.HTTPS);

        ChallengeResponse authHeader = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER); // here we create the authorization header with the API key required to access the API
        authHeader.setRawValue(APIKey);
        authHeader.setIdentifier("Bearer");
        Header versionHeader = new Header(VersionIdentifier, APIVersion); // here we create the version header with the API version required to access the API

        WKAPI.setChallengeResponse(authHeader); // setting the header for the authorization key
        WKAPI.getRequest().getHeaders().add(versionHeader); // setting the header for the current API version

        Representation repr = null;

        switch (requestType) // switch  to determine type of call to make based on given parameter
        {
            case GET:
                repr = WKAPI.get(); // here we make our call to the API
                break;
            case PUT:
                repr = WKAPI.put(null); // here we make our call to the API
                break;
            case POST:
                repr = WKAPI.post(null); // here we make our call to the API
                break;
            case DELETE:
                repr = WKAPI.delete(); // here we make our call to the API
                break;
        }
        
        return repr;
    }

    /**
     *
     *  Overloaded method to call a specific url to be used in cases where we already have a fully formed url for the API
     *
     * @param url The url to call the api from
     * @return A representation containing the API's response
     */
    public Representation MakeAPICall(String APIKey, String url)
    {
        Representation repr;

        WKAPI = new ClientResource(url); // create a new client for restful calls
        WKAPI.setProtocol(Protocol.HTTPS);

        ChallengeResponse authHeader = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER); // here we create the authorization header with the API key required to access the API
        authHeader.setRawValue(APIKey);
        authHeader.setIdentifier("Bearer");
        Header versionHeader = new Header(VersionIdentifier, APIVersion); // here we create the version header with the API version required to access the API

        WKAPI.setChallengeResponse(authHeader); // setting the header for the authorization key
        WKAPI.getRequest().getHeaders().add(versionHeader); // setting the header for the current API version

        repr = WKAPI.get(); // here we make our call to the API

        return repr;
    }

    /**
     * Builds the url used to query the API as a string
     *
     * @param endpoint The API endpoint to be accessed
     * @return A String to be used as the URL for restful calls to the API utilised
     */
    private String BuildAPIRequestURL(APIEndpoint endpoint)
    {
        StringBuilder buildRequestURL = new StringBuilder();
        buildRequestURL.append(RequestURLInitial);
        buildRequestURL.append(endpoint.toString().toLowerCase());

        return buildRequestURL.toString();
    }
}
