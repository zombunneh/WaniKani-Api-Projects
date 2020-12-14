package com.api.queries;

import org.restlet.representation.Representation;

public abstract class Query implements QueryInterface{
    private String APIKey;
    protected QueryType QType;
    private APIEndpoint AEndpoint;
    private Representation QRepr;
    private APIQueryConstructor QueryConstructor;

    @Override
    public void SetAPIKey(String key)
    {
        APIKey = key;
    }

    @Override
    public String GetAPIKey() {
        return APIKey;
    }

    @Override
    public void SetEndpoint(APIEndpoint endpoint)
    {
        AEndpoint = endpoint;
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
    public APIQueryConstructor getQueryConstructor() {
        return QueryConstructor;
    }

    @Override
    public void setQueryConstructor(APIQueryConstructor queryConstructor) {
        QueryConstructor = queryConstructor;
    }

    @Override
    public void MakeQuery(String url)
    {
        if(url.equals(""))
            QRepr = QueryConstructor.MakeAPICall(APIKey, QType, AEndpoint);
        else
            QRepr = QueryConstructor.MakeAPICall(APIKey, url);
    }
}
