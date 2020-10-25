package com.api.queries;

import com.api.queries.APIContact;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class APIContactTest {

    private static final InputStream defaultStream = System.in;

    @org.junit.jupiter.api.BeforeEach
    void setUp()
    {
        System.out.println("Set up");

            System.out.println("Input stream changing");

            InputStream TestFile = this.getClass().getResourceAsStream("MainTestValues.txt");
            System.setIn(TestFile);

            System.out.println("Input stream changed");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown()
    {
        System.out.println("Tear down");
        System.setIn(defaultStream);
    }

    /**
     * Testing our main method's execution with values from a specific test file to input
     */
    @org.junit.jupiter.api.Test
    void main()
    {
        APIContact.main(null);
    }


}