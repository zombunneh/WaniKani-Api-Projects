package com.api.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.restlet.representation.Representation;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

public class JSONParse {

    private int pageNum;

    private static final String dataString = "data";
    private static final String pagesString = "pages";

    private static final String totalCount = "total_count";
    private static final String pageCount = "per_page";
    private static final String url = "url";
    private static final String nextUrl = "next_url";
    private static final String resourceID = "id";
    private static final String updateDate = "data_updated_at";

    public JSONParse()
    {
        pageNum = 0;
    }

    /**
     *
     *
     *
     * @param repr The Representation object to read from
     * @return Response object containing the data from the JSONObject collection or resource
     */
    public QueryResponse readResponse(Representation repr)
    {
        JSONObject object = null;
        InputStream is;
        String objectType;
        QueryResponse response;

        //get the json data from representation object into readable JSONObject
        try
        {
            is = repr.getStream();

            if(is == null)
            {
                throw new NullPointerException("Cannot acquire stream from object");
            }

            JSONTokener tokener = new JSONTokener(is);
            object = new JSONObject(tokener);
        }
        catch(IOException | NullPointerException e)
        {
            e.printStackTrace();
        }

        objectType = object.getString("object");

        // handle the json response according to response type
        if(objectType.equals("collection"))
        {
            response = formatCollection(object);
        }
        else if(objectType.equals("report"))
        {
            response = formatReport(object);
        }
        else
        {
            response = formatResource(object);
        }

        return response;
    }

    /**
     *
     * Method to format a collection object
     *
     * @param obj The object containing the data to be formatted
     * @return Response object containing the data from the JSONObject collection
     */
    private QueryResponse formatCollection(JSONObject obj)
    {
        int count;
        int countPerPage;
        String collectionUrl;
        String nextUrl = "";
        String date;

        QueryResponse response = null;

        pageNum++;

        JSONArray dataArray = obj.getJSONArray(dataString);
        JSONObject pagesObject = obj.getJSONObject(pagesString);

        count = obj.getInt(totalCount);
        collectionUrl = obj.getString(url);
        countPerPage = pagesObject.getInt(pageCount);

        if(count > 0)
        {
            if(!pagesObject.isNull(nextUrl))
            {
                nextUrl = pagesObject.getString(JSONParse.nextUrl);
            }
            else
            {
                nextUrl = "";
            }

            date = obj.getString(updateDate);

            System.out.println("Page: " + pageNum);
            System.out.println("Amount of results returned: " + count);
            System.out.println("Date updated: " + date);

            response = new QueryResponse(count, countPerPage, nextUrl, collectionUrl, date);

            for(int i = 0; i < dataArray.length(); i++)
            {
                JSONObject tempObject = dataArray.getJSONObject(i);
                System.out.println(tempObject.toString());
                formatResource(tempObject, response);
            }
        }

        return response;
    }

    /**
     *
     * Method to format a report object
     *
     * @param obj The object containing the data to be formatted
     * @return Response object containing the data from the JSONObject report
     */
    private QueryResponse formatReport(JSONObject obj)
    {
        String collectionUrl;
        String date;

        QueryResponse response = null;

        JSONObject dataObject = obj.getJSONObject(dataString);

        collectionUrl = obj.getString(url);

        date = obj.getString(updateDate);

        System.out.println(dataObject.toString());

        response = new QueryResponse(collectionUrl, date, dataObject);

        return response;
    }

    /**
     *
     * Method for formatting a singular resource on its own
     *
     * @param obj The object containing the data to be formatted
     * @return Response object containing the data from the JSONObject resource
     */
    private QueryResponse formatResource(JSONObject obj)
    {
        String date;
        String resourceUrl;
        int id;

        date = obj.getString(updateDate);
        resourceUrl = obj.getString(url);

        JSONObject dataObject = obj.getJSONObject("data");
        System.out.println(dataObject.toString());

        if(!obj.isNull(resourceID))
        {
            id = obj.getInt(resourceID);
        }
        else
        {
            try {
                id = dataObject.getInt(resourceID);
            }
            catch(JSONException e)
            {
                id = -1;
            }
        }

        return new QueryResponse(date, resourceUrl, id, dataObject);
    }

    /**
     *
     * Method for formatting a singular resource as part of a collection
     *
     * @param obj The object containing the data to be formatted
     * @param response The response object from the parent collection
     */
    private void formatResource(JSONObject obj, QueryResponse response)
    {
        String date;
        String resourceUrl;
        int id;

        date = obj.getString(updateDate);
        resourceUrl = obj.getString(url);

        JSONObject dataObject = obj.getJSONObject("data");
        System.out.println(dataObject.toString());

        if(!obj.isNull(resourceID))
        {
            id = obj.getInt(resourceID);
        }
        else
        {
            try {
                id = dataObject.getInt(resourceID);
            }
            catch(JSONException e)
            {
                id = -1;
            }
        }

        response.addResource(date, resourceUrl, id, dataObject);
    }

    private void FormatCSV()
    {
        System.out.println("under construction");
    }

    private void FormatTxt()
    {

    }

    private void SaveCSV()
    {

    }

    private void SaveTxt()
    {

    }
}

