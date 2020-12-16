package com.api.queries;

import com.api.APILauncher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class APILauncherTest {

    private static final InputStream defaultStream = System.in;

    public static final String CorrectQueryType = "";
    public static final String CorrectAPIKey = "";
    public static final String CorrectEndpoint = "";
    public static final String IncorrectQueryType = "";
    public static final String IncorrectAPIKey = "";
    public static final String IncorrectEndpoint = "";

    /**
     * Setting up the test class
     */
    @BeforeEach
    void setUp()
    {
            InputStream TestFile = this.getClass().getResourceAsStream("MainTestValues.txt");
            System.setIn(TestFile);

    }

    @AfterEach
    void tearDown()
    {
        System.setIn(defaultStream);
    }

    /**
     * Testing our main method's execution with values from a specific test file to input
     */
    @Test
    void CorrectInputs()
    {
        APILauncher.main(null);

        // sleep thread to allow gui to be seen
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void IncorrectQueryType()
    {
        //System.setIn();
    }

    @Test
    void IncorrectAPIKey()
    {

    }

    @Test
    void IncorrectEndpoint()
    {

    }

}