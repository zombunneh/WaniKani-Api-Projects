package com.api.queries;

import org.restlet.*;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Header;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.util.Series;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.*;

public class APIQueryConstructor {
    private ClientResource WKAPI;
    private String RequestURLInitial = "";
    private String APIVersion = "";

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
            e.printStackTrace();
        }
    }


    /**
     *
     * To access a specific data point an ID must be supplied, may overload method later to provide functionality
     *
     * TODO: FLEXIBLE API CALL + PROCESS API RESPONSE AS JSON OBJECT
     *
     * @param APIKey The API key to be used for authorization
     * @param RequestType The type of restful request to make, corresponds to the 4 verbs
     * @param Category The API endpoint to be accessed
     */
    public void MakeAPICall(String APIKey, QueryType RequestType, String Category)
    {
        String RequestURL = BuildAPIRequestURL(Category); // create the url for the api request to access

        WKAPI = new ClientResource(RequestURL); // create a new client for restful calls
        WKAPI.setProtocol(Protocol.HTTPS);

        ChallengeResponse AuthHeader = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER); // here we set the authorization header with the API key required to access the API
        AuthHeader.setRawValue(APIKey);
        AuthHeader.setIdentifier("Bearer");
        //Header VersionHeader = new Header("WaniKani Revision", APIVersion);
        WKAPI.setChallengeResponse(AuthHeader);
        //System.out.println(WKAPI.getChallengeResponse().getRawValue());
        //WKAPI.getRequest().getHeaders().add(VersionHeader);
        WKAPI.setAttribute("WaniKani Revision", APIVersion); // setting the attribute for the current API version, unsure if this does anything

        switch (RequestType)
        {
            case GET:
                break;
            case PUT:
                break;
            case POST:
                break;
            case DELETE:
                break;
        }

        Representation Response = WKAPI.get(); // here we make our call to the API

        try {
            System.out.println(Response.getText()); // printing the response we received
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param Category The API endpoint to be accessed
     * @return A String to be used as the URL for restful calls to the API utilised
     */
    private String BuildAPIRequestURL(String Category)
    {
        StringBuilder BuildRequestURL = new StringBuilder();
        BuildRequestURL.append(RequestURLInitial);
        BuildRequestURL.append(Category);

        return BuildRequestURL.toString();
    }
}
