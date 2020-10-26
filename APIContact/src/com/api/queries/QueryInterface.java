package com.api.queries;

import org.restlet.representation.Representation;

public interface QueryInterface {
    void MakeQuery();
    void SetAPIKey(String Key);
    String GetAPIKey();
    void SetEndpoint(APIEndpoint Endpoint);
    APIEndpoint GetEndpoint();

    Representation GetRepresentation();

}
