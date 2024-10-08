package com.tymour;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonWordFinderTest {

    // Method that runs before each test. Use this to set up your test environment.
    @BeforeEach
    public void setUp() {
        // Initialize resources, if needed.
    }

    @Test
    public void testFillMapWithBasicFile() {
        MyMap<String, Integer> map = new BSTMap<>();  // Or your desired map type
        boolean result = CommonWordFinder.fillMap(map, "inputs/Constitution.txt");
        assertTrue(result, "File reading should succeed.");

        // Check a few known words and their counts.
        assertEquals(1153, map.size(), "Word count should match expected value.");
        assertEquals(109, map.get("president"), "Word 'president' should have count 109.");
    }

    // Add more tests for specific cases
}
