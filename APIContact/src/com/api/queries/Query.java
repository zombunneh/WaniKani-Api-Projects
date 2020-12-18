package com.api.queries;

import org.restlet.representation.Representation;

public abstract class Query implements QueryInterface{
    private String APIKey;
    protected QueryType QType;
    private APIEndpoint AEndpoint;
    private Representation QRepr;
    private APIQueryConstructor QueryConstructor;

    @Override
    public void setAPIKey(String key)
    {
        APIKey = key;
    }

    @Override
    public String getAPIKey() {
        return APIKey;
    }

    @Override
    public void setEndpoint(APIEndpoint endpoint)
    {
        AEndpoint = endpoint;
    }

    @Override
    public APIEndpoint getEndpoint() {
        return AEndpoint;
    }

    @Override
    public Representation getRepresentation() {
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
