package com.api.json;

import org.restlet.representation.Representation;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

public class JSONParse {

    /**
     * TODO: Overload method for different response types
     *
     * @param repr The Representation object to read from
     */
    public void readResponse(Representation repr)
    {
        try
        {
            InputStream is = repr.getStream();
            if(is == null)
            {
                throw new NullPointerException("Cannot acquire stream from object");
            }
            JSONTokener tokener = new JSONTokener(repr.getStream());
            JSONObject object = new JSONObject(tokener);
        }
        catch(IOException | NullPointerException e)
        {
            e.printStackTrace();
        }
    }
}
