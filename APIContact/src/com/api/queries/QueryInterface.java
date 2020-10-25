package com.api.queries;

import org.restlet.representation.Representation;

public interface QueryInterface {
    public void MakeQuery();
    public void SetAPIKey(String Key);
    public String GetAPIKey();
    public void SetCategory(String Cat);
    public String GetCategory();
    public Representation GetRepresentation();

}
