package com.api.queries;

import org.restlet.representation.Representation;

public abstract class Query implements QueryInterface{
    private String APIKey;
    private String Category;
    protected QueryType QType;
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
    public void SetCategory(String Cat)
    {
        Category = Cat;
    }

    @Override
    public String GetCategory() {
        return Category;
    }

    @Override
    public Representation GetRepresentation() {
        return QRepr;
    }

    @Override
    public void MakeQuery()
    {
        QRepr = QueryConstructor.MakeAPICall(APIKey, QType, Category);
    }
}
