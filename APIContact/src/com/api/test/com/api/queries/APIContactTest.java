package com.api.queries;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class APIContactTest {

    private static final InputStream defaultStream = System.in;

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
        APIContact.main(null);
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