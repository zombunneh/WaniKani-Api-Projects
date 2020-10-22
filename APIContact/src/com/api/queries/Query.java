package com.api.queries;

public abstract class Query implements QueryInterface{
    private String APIKey;
    private String Category;
    protected QueryType QType;

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
}
