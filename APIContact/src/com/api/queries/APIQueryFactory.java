package com.api.queries;

public class APIQueryFactory {

    public Query CreateQueryType(String QueryType)
    {
        Query q = null;
        if (QueryType.equalsIgnoreCase("GET")) {
            q = new GetQuery();
        } else if (QueryType.equalsIgnoreCase("PUT")) {
            q = new PutQuery();
        } else if (QueryType.equalsIgnoreCase("POST")) {
            q = new PostQuery();
        } else if (QueryType.equalsIgnoreCase("DELETE")) {
            q = new DeleteQuery();
        }
        return q;
    }
}
