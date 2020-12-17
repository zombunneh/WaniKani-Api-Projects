package com.api.gui;

import com.api.queries.APIContact;

public class GUIController {
    private APIContact contact;
    private GUIModel model;
    private GUIView view;

    /**
     *
     *
     *
     * @param contact The object which controls API communication
     * @param model The model that stores program related data
     */
    public GUIController(APIContact contact, GUIModel model)
    {
        this.contact = contact;
        this.model = model;
        view = new GUIView(this);
    }

    /**
     *
     *
     *
     * @param APIKey The API key to verify and store
     */
    public void onRegisterVerifyButtonClick(String APIKey)
    {
        Boolean verified;
        verified = contact.verifyAPIKey(APIKey);
        if(verified)
        {
            model.setAPIKey(APIKey);
            view.enableComponents();
        }
    }
}
