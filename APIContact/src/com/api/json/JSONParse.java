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
            FormatResource(tempObject);
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

/**
 * This class for internal use to represent the parsed response to return to calling classes
 */
class Response {
    Boolean isCollection = false;

    String collectionDate;
    int collectionCount;
    int collectionCountPerPage;
    String nextUrl;
    String collectionUrl;

    int resourceCount = 0;
    String[] date;
    String[] resourceUrl;
    String[] id;
    JSONObject[] jObject;

    /**
     *
     * Constructor for a single resource response
     *
     * @param date The date the resource was last updated
     * @param resourceUrl The url of the resource returned
     * @param id The id of the resource
     * @param jObject The JSONObject containing the resource specific information
     */
    Response(String date, String resourceUrl, String id, JSONObject jObject)
    {
        // Set type of class to resource
        isCollection = false;
        // Initialise resource arrays to size 1
        this.date = new String[1];
        this.resourceUrl = new String[1];
        this.id = new String[1];
        this.jObject = new JSONObject[1];
        // Initialise resource specific variables
        this.date[0] = date;
        this.resourceUrl[0] = resourceUrl;
        this.id[0] = id;
        this.jObject[0] = jObject;
    }

    /**
     *
     * Constructor for a collection response
     *
     * @param count The amount of data objects returned
     * @param countPerPage The amount of objects per page
     * @param nextUrl The url, if any, of the next page of results
     * @param collectionUrl The url of the collection object returned
     * @param date The date the resource was last updated
     */
    Response(int count, int countPerPage, String nextUrl, String collectionUrl, String date)
    {
        // Set type of class to collection
        isCollection = true;
        // Initialise collection specific variables
        collectionCount = count;
        collectionCountPerPage = countPerPage;
        this.nextUrl = nextUrl;
        this.collectionUrl = collectionUrl;
        collectionDate = date;
        // Initialise resource arrays to size of response
        this.date = new String[count];
        this.resourceUrl = new String[count];
        this.id = new String[count];
        this.jObject = new JSONObject[count];
    }

    /**
     *
     * Adds a resource to a collection type response object
     *
     * @param date The date the resource was last updated
     * @param resourceUrl The url of the resource returned
     * @param id The id of the resource
     * @param jObject The JSONObject containing the resource specific information
     */
    public void addResource(String date, String resourceUrl, String id, JSONObject jObject)
    {
        if(isCollection)
        {
            // Add resource variables to current index
            this.date[resourceCount] = date;
            this.resourceUrl[resourceCount] = resourceUrl;
            this.id[resourceCount] = id;
            this.jObject[resourceCount] = jObject;
            // Increment count / index for next resource
            resourceCount++;
        }
    }
}
