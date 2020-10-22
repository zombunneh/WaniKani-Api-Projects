package com.api.queries;

import org.restlet.*;
import org.restlet.resource.ClientResource;

public class APIQueryConstructor {
    //private String APIKey;
    private ClientResource WKAPI;
    private static final String RequestURLInitial = "https://api.wanikani.com/v2/";

    public APIQueryConstructor()
    {

    }

    /*
    If you want to access a specific data point you must supply an ID -- potentially overload the method to allow such a call later on

    This method takes the 3 Strings to build an API request by forming the URL, and then adding the header etc..
     */
    public void MakeAPICall(String APIKey, String RequestType, String Category)
    {
        String RequestURL;
        StringBuilder BuildRequestURL = new StringBuilder();
        BuildRequestURL.append(RequestURLInitial);
        BuildRequestURL.append(Category);

        RequestURL = BuildRequestURL.toString();

        WKAPI = new ClientResource(RequestURL);
        Response Response = WKAPI.getResponse();
    }
}
