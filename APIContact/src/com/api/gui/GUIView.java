package com.api.gui;

import com.api.queries.APIEndpoint;
import com.api.queries.QueryType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

public class GUIView {
    private GUIController controller;

    public static final String frameTitle = "WaniKani Data Viewer";

    private JFrame frame;
    private JTextField APIInputField;
    private JLabel APIInputLabel;
    private JButton APIVerifyButton;
    private JPanel MainPanel;
    private JComboBox<String> QueryOptions;
    private JComboBox<String> EndpointOptions;
    private JButton QueryButton;

    public GUIView(GUIController controller) {
        this.controller = controller;

        frame = new JFrame(frameTitle); // Creating frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add components to frame
        frame.add(MainPanel);

        setComponents();

        setActions();

        frame.setMinimumSize(new Dimension(100, 100));
        frame.pack(); // Size frame
        frame.setVisible(true);
    }

    /**
     *
     * Sets up the individual components that make up the gui on construction.
     * All components aside from those related to API key are disabled until API key is verified
     *
     */
    private void setComponents() {
        // Set up combo box for 4 types of query
        QueryOptions.setEnabled(false);
        for (QueryType query : QueryType.values()) {
            QueryOptions.addItem(query.toString());
        }


        // Set up combo box for endpoints
        EndpointOptions.setEnabled(false);
        for (APIEndpoint endpoint : APIEndpoint.values()) {
            EndpointOptions.addItem(endpoint.toString());
        }

        QueryButton.setEnabled(false);
    }

    /**
     *
     * Enables all components to be usable after the API key is verified
     *
     */
    public void enableComponents()
    {
        for(Component component : MainPanel.getComponents())
        {
            component.setEnabled(true);
        }
    }

    /**
     * Sets the actions performed by the components of the gui when interacted with
     * TODO: Verify key format with regex
     */
    private void setActions() {
        APIVerifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!APIInputField.getText().equals("")) {
                    controller.onRegisterVerifyButtonClick(APIInputField.getText());
                } else {
                    JOptionPane.showMessageDialog(frame.getComponent(0), "Please enter an API Key");
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(5, 5, 5, 5), -1, -1));
        MainPanel.setBackground(new Color(-1245697));
        MainPanel.setToolTipText(this.$$$getMessageFromBundle$$$("com/api/res/GUIProperties", "QueryTypeLabel"));
        APIInputLabel = new JLabel();
        this.$$$loadLabelText$$$(APIInputLabel, this.$$$getMessageFromBundle$$$("com/api/res/GUIProperties", "APIKeyLabel"));
        MainPanel.add(APIInputLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        MainPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        APIInputField = new JTextField();
        MainPanel.add(APIInputField, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        APIVerifyButton = new JButton();
        this.$$$loadButtonText$$$(APIVerifyButton, this.$$$getMessageFromBundle$$$("com/api/res/GUIProperties", "APIButton"));
        MainPanel.add(APIVerifyButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        QueryOptions = new JComboBox();
        QueryOptions.setToolTipText(this.$$$getMessageFromBundle$$$("com/api/res/GUIProperties", "QueryTypeLabel"));
        MainPanel.add(QueryOptions, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        EndpointOptions = new JComboBox();
        EndpointOptions.setToolTipText(this.$$$getMessageFromBundle$$$("com/api/res/GUIProperties", "EndpointTypeLabel"));
        MainPanel.add(EndpointOptions, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        QueryButton = new JButton();
        this.$$$loadButtonText$$$(QueryButton, this.$$$getMessageFromBundle$$$("com/api/res/GUIProperties", "QueryMakeButton"));
        MainPanel.add(QueryButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        APIInputLabel.setLabelFor(APIInputField);
    }

    private static Method $$$cachedGetBundleMethod$$$ = null;

    private String $$$getMessageFromBundle$$$(String path, String key) {
        ResourceBundle bundle;
        try {
            Class<?> thisClass = this.getClass();
            if ($$$cachedGetBundleMethod$$$ == null) {
                Class<?> dynamicBundleClass = thisClass.getClassLoader().loadClass("com.intellij.DynamicBundle");
                $$$cachedGetBundleMethod$$$ = dynamicBundleClass.getMethod("getBundle", String.class, Class.class);
            }
            bundle = (ResourceBundle) $$$cachedGetBundleMethod$$$.invoke(null, path, thisClass);
        } catch (Exception e) {
            bundle = ResourceBundle.getBundle(path);
        }
        return bundle.getString(key);
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }

}
