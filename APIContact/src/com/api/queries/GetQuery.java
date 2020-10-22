package com.api.queries;


public class GetQuery extends Query {

    public GetQuery()
    {
        QType = QueryType.GET;
    }

    @Override
    public void MakeQuery()
    {

    }

    @Override
    public void printq()
    {
        System.out.println("hi from getquery");
    }

}
