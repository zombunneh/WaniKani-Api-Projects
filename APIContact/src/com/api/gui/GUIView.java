package com.api.gui;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

public class GUIView {

    public static final String frameTitle = "WaniKani Data Viewer";

    private JFrame frame;
    private JTextField APIInputField;
    private JLabel APIinputLabel;
    private JButton APIVerifyButton;
    private JPanel mainPanel;

    public GUIView() {
        frame = new JFrame(frameTitle); // Creating frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add components to frame
        //frame.add(mainPanel);
        frame.setSize(100, 100);
        frame.pack(); // Size frame
        frame.setVisible(true);
    }
}
