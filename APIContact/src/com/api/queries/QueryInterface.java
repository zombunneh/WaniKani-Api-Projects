package com.api.queries;

import org.restlet.representation.Representation;

public interface QueryInterface {
    void MakeQuery(String url);
    APIQueryConstructor getQueryConstructor();
    void setQueryConstructor(APIQueryConstructor queryConstructor);
    void SetAPIKey(String key);
    String GetAPIKey();
    void SetEndpoint(APIEndpoint endpoint);
    APIEndpoint GetEndpoint();

    Representation GetRepresentation();

}
