package com.api.json;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.restlet.representation.Representation;

import static org.junit.jupiter.api.Assertions.*;

class JSONParseTest {
    JSONParse parser;
    Representation repr;
    QueryResponse response;

    @BeforeEach
    void setUp() {
        parser = new JSONParse();
    }

    @AfterEach
    void tearDown() {
        parser = null;
        repr = null;
        response = null;
    }

    @Test
    void readResponse() {

    }

    @Test
    void readNullResponse()
    {
        repr = null;
        assertThrows(NullPointerException.class, () -> parser.readResponse(repr));
    }
}