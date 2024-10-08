package com.tymour;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Class that instantiates either a BSTMap, AVLTreeMap, or MyHashMap from the MyMap interface
 * to parse through an input txt file, and return an array primarily ordered by greatest values to
 * the smallest values and secondarily ordered by alphabetical order.
 * @author Tymour A. Aidabole
 * @uni taa2146
 */
public class CommonWordFinder {

    public static void main(String[] args) {
        // Validate command line arguments
        if (!validateArguments(args)) return;

        // Create the map based on the user input
        MyMap<String, Integer> map = createMap(args[1]);
        if (map == null) return;

        // Fill the map with words from the input file
        if (!fillMap(map, args[0])) return;

        // Determine the limit for output display
        int limit = (args.length == 3) ? Integer.parseInt(args[2]) : 10;

        // Sort the map entries by frequency and alphabetically
        String[][] sortedWords = sortMap(map);

        // Print the results
        printResults(sortedWords, limit);
    }

    /**
     * Validates the command line arguments according to the project specifications.
     * @param args Command line arguments
     * @return true if the arguments are valid, false otherwise
     */
    private static boolean validateArguments(String[] args) {
        if (args.length != 2 && args.length != 3) {
            System.err.println("Usage: java CommonWordFinder <filename> <bst|avl|hash> [limit]");
            return false;
        }

        File f = new File(args[0]);
        if (!f.exists()) {
            System.err.println("Error: Cannot open file '" + args[0] + "' for input.");
            return false;
        }

        if (!args[1].equals("bst") && !args[1].equals("avl") && !args[1].equals("hash")) {
            System.err.println("Error: Invalid data structure '" + args[1] + "' received.");
            return false;
        }

        if (args.length == 3) {
            try {
                int limit = Integer.parseInt(args[2]);
                if (limit <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid limit '" + args[2] + "' received.");
                return false;
            }
        }
        return true;
    }

    /**
     * Creates the map based on the user-specified data structure.
     * @param structure The data structure type (bst, avl, hash)
     * @return A MyMap instance of the specified type
     */
    private static MyMap<String, Integer> createMap(String structure) {
        switch (structure) {
            case "bst":
                return new BSTMap<>();
            case "avl":
                return new AVLTreeMap<>();
            case "hash":
                return new MyHashMap<>();
            default:
                return null; // Should not happen due to previous validation
        }
    }

    /**
    * Fills the map with words and their counts from the input file.
    * @param map The map to populate
    * @param fileName The input file name
    * @return true if the file was processed successfully, false otherwise
    */
    public static boolean fillMap(MyMap<String, Integer> map, String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
    
            // Process the file line by line.
            while ((line = br.readLine()) != null) {
                // Replace unwanted characters, keep alphanumeric, apostrophes, and hyphens (including double hyphens).
                line = line.replaceAll("[^a-zA-Z0-9\\s'-]+", " ").trim();
    
                // Split cleaned line into words based on whitespace.
                String[] words = line.split("\\s+");
    
                for (String word : words) {
                    // Normalize the word: convert to lowercase and trim any remaining whitespace.
                    String normalizedWord = word.trim().toLowerCase();
    
                    // Ignore empty strings or isolated punctuation.
                    if (normalizedWord.isEmpty()) continue;
    
                    // Skip common non-word patterns like "www", "http", etc.
                    if (normalizedWord.equals("www") || normalizedWord.equals("ftp")) continue;
    
                    // Check if the word is valid (allows words with apostrophes, hyphens, double hyphens, and possessive apostrophes).
                    if (normalizedWord.matches("[a-zA-Z]+(['-][a-zA-Z]+)*(--[a-zA-Z]+)?(--)?'?")) {
                        // Add or update the count for the word in the map.
                        Integer count = map.get(normalizedWord);
                        map.put(normalizedWord, (count == null) ? 1 : count + 1);
                    }
                }
            }
            br.close();
    
        } catch (IOException e) {
            System.err.println("Error: An I/O error occurred reading '" + fileName + "'.");
            return false;
        }
    
        return true;
    }
    
    
    
    
    
    
    
    
    
    


    /**
     * Sorts the map entries first by frequency and then alphabetically.
     * @param map The map to sort
     * @return A 2D array of sorted words and their counts
     */
    public static String[][] sortMap(MyMap<String, Integer> map) {
        String[][] words = new String[map.size()][2];
        int index = 0;
        Iterator<Entry<String, Integer>> itr = map.iterator();
        while (itr.hasNext()) {
            Entry<String, Integer> pair = itr.next();
            words[index][0] = pair.key;
            words[index++][1] = String.valueOf(pair.value);
        }
        Arrays.sort(words, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int countComparison = Integer.compare(Integer.parseInt(o2[1]), Integer.parseInt(o1[1]));
                return (countComparison != 0) ? countComparison : o1[0].compareTo(o2[0]);
            }
        });
        return words;
    }

    /**
     * Prints the sorted words and their counts up to the specified limit.
     * @param words The sorted words and their counts
     * @param limit The maximum number of words to display
     */
    private static void printResults(String[][] words, int limit) {
        System.out.println("Total unique words: " + words.length);
        for (int i = 0; i < Math.min(limit, words.length); i++) {
            System.out.printf("%" + (String.valueOf(words.length).length()) + "d. %s %s%n", i + 1, words[i][0], words[i][1]);
        }
    }
}
