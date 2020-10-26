package com.api.queries;

import org.restlet.representation.Representation;

public abstract class Query implements QueryInterface{
    private String APIKey;
    protected QueryType QType;
    private APIEndpoint AEndpoint;
    private Representation QRepr;
    private APIQueryConstructor QueryConstructor = new APIQueryConstructor();

    @Override
    public void SetAPIKey(String Key)
    {
        APIKey = Key;
    }

    @Override
    public String GetAPIKey() {
        return APIKey;
    }

    @Override
    public void SetEndpoint(APIEndpoint Endpoint)
    {
        AEndpoint = Endpoint;
    }

    @Override
    public APIEndpoint GetEndpoint() {
        return AEndpoint;
    }

    @Override
    public Representation GetRepresentation() {
        return QRepr;
    }

    @Override
    public void MakeQuery()
    {
        QRepr = QueryConstructor.MakeAPICall(APIKey, QType, AEndpoint);
    }
}
