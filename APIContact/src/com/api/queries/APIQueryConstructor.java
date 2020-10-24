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
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.*;

public class APIQueryConstructor {
    //private String APIKey;
    private ClientResource WKAPI;
    private String RequestURLInitial = "";
    private String APIVersion = "";

    public APIQueryConstructor()
    {
        File SettingsFile = new File(System.getProperty("user.dir") + "/src/com/api/queries/settings/settings.txt");
        //InputStream SettingsFile = this.getClass().getResourceAsStream("settings.txt");
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

    /*
    If you want to access a specific data point you must supply an ID -- potentially overload the method to allow such a call later on

    This method takes the 3 Strings to build an API request by forming the URL, and then adding the header etc..
     */
    public void MakeAPICall(String APIKey, String RequestType, String Category)
    {
        String RequestURL = BuildAPIRequestURL(Category);

        WKAPI = new ClientResource(RequestURL);
        WKAPI.setProtocol(Protocol.HTTPS);

        ChallengeResponse AuthHeader = new ChallengeResponse(ChallengeScheme.HTTP_BASIC);
        AuthHeader.setRawValue("Authorization: Bearer " + APIKey);
        Header VersionHeader = new Header("WaniKani Revision", APIVersion);

        WKAPI.setChallengeResponse(AuthHeader);
        /*WKAPI.getRequest().getHeaders().add(VersionHeader);*/
        System.out.println(WKAPI.toString());
        System.out.println(AuthHeader.getRawValue());

        Representation response = WKAPI.get();

        long size = response.getSize();

        System.out.println(size);

        /*Response Response = WKAPI.getResponse();
        if(Response.getStatus().isSuccess())
        {
            System.out.println("API Call successful!");
        }
        else
        {
            System.out.println("API Call unsuccessful.");
        }

        System.out.println(Response.getEntityAsText());*/
    }

    private String BuildAPIRequestURL(String Category)
    {
        StringBuilder BuildRequestURL = new StringBuilder();
        BuildRequestURL.append(RequestURLInitial);
        BuildRequestURL.append(Category);

        return BuildRequestURL.toString();
    }
}
