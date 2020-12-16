package com.api.gui;

import com.api.queries.APIContact;

public class GUIController {
    private APIContact contact;
    private GUIModel model;
    private GUIView view;

    public GUIController(APIContact contact, GUIModel model)
    {
        this.contact = contact;
        this.model = model;
        view = new GUIView(this);
    }

}
