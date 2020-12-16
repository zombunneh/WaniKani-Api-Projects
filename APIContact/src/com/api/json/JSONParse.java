package com.api.json;

import org.json.JSONArray;
import org.restlet.representation.Representation;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

public class JSONParse {

    private int pageNum = 0;

    private static final String dataString = "data";
    private static final String pagesString = "pages";

    private static final String totalCount = "total_count";
    private static final String pageCount = "per_page";
    private static final String url = "url";
    private static final String nextUrl = "next_url";
    private static final String resourceID = "id";
    private static final String updateDate = "data_updated_at";

    /**
     * TODO: Overload method for different response types
     * TODO: Need to check for next page url, and construct new query to retrieve next page if necessary
     * Might convert next url to class attribute when threaded
     *
     * @param repr The Representation object to read from
     */
    public String ReadResponse(Representation repr)
    {
        JSONObject object = null;
        InputStream is;
        String objectType;
        String nextUrl = "";

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
        if(objectType.equals("collection") || objectType.equals("report"))
        {
            nextUrl = FormatCollection(object);
        }
        else if(objectType.equals("resource"))
        {
            FormatResource(object);
            nextUrl = "";
        }
        else
        {
            throw new IllegalArgumentException("Unable to determine resource type " + objectType);
        }

        return nextUrl;
    }

    /**
     *
     * Method to format a collection or report object and display it to the user
     * TODO: Implement check for next page of results, return non-empty string to query for next page, format date received - maybe cache data
     *
     * @param obj The object containing the data to be formatted
     */
    private String FormatCollection(JSONObject obj)
    {
        int count = 0;
        int countPerPage = 0;
        String collectionUrl = "";
        String nextUrl = "";
        String date = "";

        pageNum++;

        JSONArray dataArray = obj.getJSONArray(dataString);
        //System.out.println(dataArray.toString());

        JSONObject pagesObject = obj.getJSONObject(pagesString);

        //not currently using these variables in this iteration.
        count = obj.getInt(totalCount);
        collectionUrl = obj.getString(url);
        countPerPage = pagesObject.getInt(pageCount);
        if(!pagesObject.isNull(nextUrl))
        {
            nextUrl = pagesObject.getString(nextUrl);
        }
        else
        {
            nextUrl = "";
        }
        date = obj.getString(updateDate);

        System.out.println("Page: " + pageNum);
        System.out.println("Amount of results returned: " + count);
        System.out.println("Date updated: " + date);

        // still need to acquire individual data points from objects returned - use FormatResource
        for(int i = 0; i < dataArray.length(); i++)
        {
            JSONObject tempObject = dataArray.getJSONObject(i);
            System.out.println(tempObject.toString());
        }

        return nextUrl;
    }

    /**
     *
     *
     *
     * @param obj The object containing the data to be formatted
     */
    private void FormatResource(JSONObject obj)
    {
        String date = "";
        String resourceUrl = "";
        String id = "";

        date = obj.getString(updateDate);
        resourceUrl = obj.getString(url);
        id = obj.getString(resourceID);

        JSONObject dataObject = obj.getJSONObject("data");
        System.out.println(dataObject.toString());
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
