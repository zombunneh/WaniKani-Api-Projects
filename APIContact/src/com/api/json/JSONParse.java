package com.api.json;

import org.json.JSONArray;
import org.restlet.representation.Representation;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

public class JSONParse {

    public static final String dataString = "data";
    public static final String pagesString = "pages";

    public static final String totalCount = "total_count";
    public static final String pageCount = "per_page";
    public static final String url = "next_url";
    public static final String updateDate = "data_updated_at";

    /**
     * TODO: Overload method for different response types
     * TODO: Need to check for next page url, and construct new query to retrieve next page if necessary
     *
     * @param repr The Representation object to read from
     */
    public void ReadResponse(Representation repr)
    {
        JSONObject object = null;
        InputStream is;
        String objectType;

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
            FormatCollection(object);
        }
        else if(objectType.equals("resource"))
        {
            FormatResource(object);
        }
        else
        {
            throw new IllegalArgumentException("Unable to determine resource type " + objectType);
        }
    }

    /**
     *
     * Method to format a collection or report object and display it to the user
     * TODO: Implement check for next page of results, set flag to notify re-query(?), format date received - maybe cache data
     *
     * @param obj The object containing the data to be formatted
     */
    private void FormatCollection(JSONObject obj)
    {
        int count = 0;
        int countPerPage = 0;
        String nextUrl = "";
        String date = "";

        JSONArray dataArray = obj.getJSONArray(dataString);
        System.out.println(dataArray.toString());

        JSONObject pagesObject = obj.getJSONObject(pagesString);

        //not currently using these variables in this iteration.
        count = obj.getInt(totalCount);
        countPerPage = pagesObject.getInt(pageCount);
        if(!pagesObject.isNull(url))
        {
            nextUrl = pagesObject.getString(url);
        }
        date = obj.getString(updateDate);

        System.out.println("Amount of results returned: " + count);
        System.out.println("Date updated: " + date);

        for(int i = 0; i < dataArray.length(); i++)
        {
            JSONObject tempObject = dataArray.getJSONObject(i);
            System.out.println(tempObject.toString());
        }
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

        JSONObject dataObject = obj.getJSONObject("data");
        System.out.println(dataObject.toString());
    }

    private void FormatCSV()
    {
        System.out.println("currently being built");
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
