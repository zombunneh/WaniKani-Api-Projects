package com.api.queries;

public class APIQueryFactory {

    public Query CreateQueryType(String queryType)
    {
        Query q = null;
        if (queryType.equalsIgnoreCase("GET")) {
            q = new GetQuery();
        } else if (queryType.equalsIgnoreCase("PUT")) {
            q = new PutQuery();
        } else if (queryType.equalsIgnoreCase("POST")) {
            q = new PostQuery();
        } else if (queryType.equalsIgnoreCase("DELETE")) {
            q = new DeleteQuery();
        }
        return q;
    }
}
