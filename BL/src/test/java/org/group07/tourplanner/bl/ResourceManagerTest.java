package org.group07.tourplanner.bl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceManagerTest {

    @Test
    void getInstance() {
        Object obj = ResourceManager.getInstance();

        assertTrue(obj instanceof ResourceManager);
    }

    @Test
    void load() {
        String expectedOutput = "TEST_UNIT_STRING";
        String output = ResourceManager.getInstance().load("TEST_UNIT_STRING");
        assertEquals(expectedOutput, output);
    }
}