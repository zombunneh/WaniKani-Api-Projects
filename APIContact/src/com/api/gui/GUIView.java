package com.api.gui;

import javax.swing.*;

public class GUIView {

    public static final String frameTitle = "WaniKani Data Viewer";

    private JFrame frame;
    private JTextField APIInputField;
    private JLabel APIinputLabel;
    private JButton APIVerifyButton;
    private JPanel mainPanel;

    public GUIView()
    {
        frame = new JFrame(frameTitle); // Creating frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add components to frame

        frame.pack(); // Size frame
    }
}
