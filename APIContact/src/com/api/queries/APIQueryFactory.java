package com.api.queries;

public class APIQueryFactory {

    public Query createQueryType(String queryType)
    {
        Query q = null;
        APIQueryConstructor constructor = new APIQueryConstructor();
        if (queryType.equalsIgnoreCase("GET")) {
            q = new GetQuery();
            q.setQueryConstructor(constructor);
        } else if (queryType.equalsIgnoreCase("PUT")) {
            q = new PutQuery();
            q.setQueryConstructor(constructor);
        } else if (queryType.equalsIgnoreCase("POST")) {
            q = new PostQuery();
            q.setQueryConstructor(constructor);
        } else if (queryType.equalsIgnoreCase("DELETE")) {
            q = new DeleteQuery();
            q.setQueryConstructor(constructor);
        }
        return q;
    }
}
