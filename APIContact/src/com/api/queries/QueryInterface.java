package com.api.queries;

import org.restlet.representation.Representation;

public interface QueryInterface {
    void MakeQuery(String url);
    APIQueryConstructor getQueryConstructor();
    void setQueryConstructor(APIQueryConstructor queryConstructor);
    void setAPIKey(String key);
    String getAPIKey();
    void setEndpoint(APIEndpoint endpoint);
    APIEndpoint getEndpoint();

    Representation getRepresentation();

}
