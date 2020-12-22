package com.api.queries;

import com.api.APIEndpoint;
import org.restlet.representation.Representation;

public interface QueryInterface {
    void makeQuery(String url);
    APIQueryConstructor getQueryConstructor();
    void setQueryConstructor(APIQueryConstructor queryConstructor);
    void setAPIKey(String key);
    String getAPIKey();
    void setEndpoint(APIEndpoint endpoint);
    APIEndpoint getEndpoint();

    Representation getRepresentation();

}
