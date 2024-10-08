import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
/**
 * Class that instantiates either a BSTMap, AVLTreeMap, or MyHashMap from the MyMap interface
 * to parse an input text file and return an array of the most common words, ordered primarily by frequency (from highest to lowest)
 * and secondarily in alphabetical order.
 *
 * @author Tymour A. Aidabole
 * @uni taa2146
 */
public class CommonWordFinder {

    /**
     * Main method that serves as the entry point for the program.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        if (!validateArguments(args)) return;

        MyMap<String, Integer> map = createMap(args[1]);
        if (map == null) return;

        if (!fillMap(map, args[0])) return;

        int limit = (args.length == 3) ? Integer.parseInt(args[2]) : 10;

        String[][] sortedWords = sortMap(map);

        printResults(sortedWords, limit);
    }

    /**
     * Validates the command line arguments according to the project specifications.
     * 
     * @param args Command line arguments
     * @return true if the arguments are valid, false otherwise
     */
    private static boolean validateArguments(String[] args) {
        if (args.length != 2 && args.length != 3) {
            System.err.println("Usage: java CommonWordFinder <filename> <bst|avl|hash> [limit]");
            return false;
        }

        File file = new File(args[0]);
        if (!file.exists()) {
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
     * 
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
                return null;
        }
    }

    /**
     * Fills the map with words and their counts from the input file.
     * 
     * @param map The map to populate
     * @param fileName The input file name
     * @return true if the file was processed successfully, false otherwise
     */
    private static boolean fillMap(MyMap<String, Integer> map, String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[^a-zA-Z0-9\\s'-]+", " ").trim();
                String[] words = line.split("\\s+");

                for (String word : words) {
                    String normalizedWord = word.trim().toLowerCase();
                    if (normalizedWord.isEmpty() || normalizedWord.equals("www") || normalizedWord.equals("ftp")) continue;

                    if (normalizedWord.matches("[a-zA-Z]+(['-][a-zA-Z]+)*(--[a-zA-Z]+)?(--)?'?")) {
                        Integer count = map.get(normalizedWord);
                        map.put(normalizedWord, (count == null) ? 1 : count + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error: An I/O error occurred reading '" + fileName + "'.");
            return false;
        }
        return true;
    }

    /**
     * Sorts the map entries first by frequency and then alphabetically.
     * 
     * @param map The map to sort
     * @return A 2D array of sorted words and their counts
     */
    private static String[][] sortMap(MyMap<String, Integer> map) {
        String[][] words = new String[map.size()][2];
        int index = 0;
        Iterator<Entry<String, Integer>> itr = map.iterator();
        while (itr.hasNext()) {
            Entry<String, Integer> pair = itr.next();
            words[index][0] = pair.key;
            words[index++][1] = String.valueOf(pair.value);
        }

        Arrays.sort(words, (o1, o2) -> {
            int countComparison = Integer.compare(Integer.parseInt(o2[1]), Integer.parseInt(o1[1]));
            return (countComparison != 0) ? countComparison : o1[0].compareTo(o2[0]);
        });

        return words;
    }

    /**
     * Prints the sorted words and their counts up to the specified limit.
     * 
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
