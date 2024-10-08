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

    @Test
    public void testFillMapWithEmptyFile() {
        MyMap<String, Integer> map = new BSTMap<>();
        boolean result = CommonWordFinder.fillMap(map, "inputs/empty.txt");
        assertTrue(result, "File reading should succeed.");
        assertEquals(0, map.size(), "Word count should be zero for an empty file.");
    }

    @Test
    public void testFillMapWithSpecialCharacters() {
        MyMap<String, Integer> map = new BSTMap<>();
        boolean result = CommonWordFinder.fillMap(map, "inputs/SpecialCharacters.txt");
        assertTrue(result, "File reading should succeed.");
        assertEquals(0, map.size(), "Word count should be zero for a file with only special characters.");
    }

    @Test
    public void testFillMapWithNumbers() {
        MyMap<String, Integer> map = new BSTMap<>();
        boolean result = CommonWordFinder.fillMap(map, "inputs/Numbers.txt");
        assertTrue(result, "File reading should succeed.");
        assertEquals(0, map.size(), "Word count should be zero if numbers are ignored.");
    }

    @Test
    public void testFillMapWithLargeFile() {
        MyMap<String, Integer> map = new MyHashMap<>();
        boolean result = CommonWordFinder.fillMap(map, "inputs/Constitution.txt");
        assertTrue(result, "File reading should succeed.");
        assertTrue(map.size() > 0, "Word count should be greater than zero for a large file.");
    }
    
    @Test
    public void testFillMapWithHyphenatedWords() {
        MyMap<String, Integer> map = new AVLTreeMap<>();
        boolean result = CommonWordFinder.fillMap(map, "inputs/HyphenatedWords.txt");
        assertTrue(result, "File reading should succeed.");
        assertEquals(1, map.get("self-esteem"), "Word 'self-esteem' should be counted as a single instance.");
    }

    @Test
    public void testFillMapWithApostrophes() {
        MyMap<String, Integer> map = new MyHashMap<>();
        boolean result = CommonWordFinder.fillMap(map, "inputs/Apostrophes.txt");
        assertTrue(result, "File reading should succeed.");
        assertEquals(1, map.get("don't"), "Word 'don't' should be counted correctly.");
        assertEquals(1, map.get("teacher's"), "Word 'teacher's' should be counted correctly.");
    }

    @Test
    public void testSortingLogic() {
        MyMap<String, Integer> map = new MyHashMap<>();
        CommonWordFinder.fillMap(map, "inputs/SortedWordsTest.txt");
        String[][] sortedWords = CommonWordFinder.sortMap(map);
    
        // Verify that the words are sorted first by frequency and then alphabetically
        assertEquals("apple", sortedWords[0][0], "First word should be 'apple' (highest frequency).");
        assertEquals("banana", sortedWords[1][0], "Second word should be 'banana' (second highest frequency).");
    }
    
}
