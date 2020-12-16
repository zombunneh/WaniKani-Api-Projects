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

import com.api.json.JSONParse;
import com.api.queries.APIQueryConstructor;
import com.api.queries.Query;
import com.api.queries.APIContact;

import javax.swing.*;

public class APILauncher {
    private static APIContact contact;
    private static final APIQueryConstructor constructor = new APIQueryConstructor();

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
        Query q = null;
        String nextUrl = "";

        q = contact.SetQueryType(q);
        q.setQueryConstructor(constructor);

        contact.SetAPIKey(q);
        System.out.println(q.GetAPIKey());

        contact.SetCategory(q);
        System.out.println(q.GetEndpoint().toString());

        q.MakeQuery("");

        JSONParse parser = new JSONParse();
        nextUrl = parser.ReadResponse(q.GetRepresentation());

        if(!nextUrl.equals(""))
        {
            while(!nextUrl.equals(""))
            {
                q.MakeQuery(nextUrl);

                nextUrl = parser.ReadResponse(q.GetRepresentation());
            }
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    contact.createAndShowWindow();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
