/*
 * APILauncher.java
 *
 * @author Matthew Winterbourn
 * @version 1.0
 * @since 10/11/2020
 *
 * Copyright (c) 2020.
 */

package com.api;

import com.api.gui.GUIController;
import com.api.gui.GUIModel;
import com.api.json.JSONParse;
import com.api.queries.APIQueryConstructor;
import com.api.queries.Query;
import com.api.queries.APIContact;

import javax.swing.*;

public class APILauncher {
    private static APIContact contact;

    /**
     *
     * Main method of program to accept user input in order to construct specific queries to the WaniKani API
     *
     * TODO: Eventually overhaul main method and replace with gui
     *
     * TODO: check user subscription status and feedback
     *
     * TODO: Add-on database for longer term storage of results?
     *
     * TODO: Thread application
     *
     * @param args NO args need to be supplied
     */
    public static void main(String[] args) {
        contact = new APIContact();
        APILauncher launcher = new APILauncher();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    launcher.createAndShowWindow();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     *
     *
     *
     * @throws Exception If graphics window fails to draw
     */
    public void createAndShowWindow() throws Exception
    {
        GUIModel model = new GUIModel();
        GUIController controller = new GUIController(contact, model);
    }
}
