/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plagrism;

/**
 *
 * @author Kanchana
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Top level Facade that loads the synonyms, loads the tuples, instantiates the
 * appropriate service and scores the tuples
 *
 * @author kellyfj
 */
public class PlagiarismDetector {

    private Map<String, List<String>> synonyms = null;

    /**
     * Instantiate our Service
     *
     * @param fileName containing the synonyms - one set of synonyms per line
     * @throws IOException if there is some problem loading the file
     */
    public PlagiarismDetector(String fileName) throws IOException {
        synonyms = new HashMap<String, List<String>>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            System.out.println("Synonyms File: " + fileName);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] words = line.toLowerCase().split(" ");	 //NOTE: Lower case preprocessing done once here
                List<String> list = Arrays.asList(words);

                for (String word : words) {
                    if (synonyms.containsKey(word)) {
                        throw new RuntimeException("The word " + word + " was already in our map of synonyms. For now we don't handle homonyms e.g. bark (of a dog) vs bark (of a tree)");
                    } else {
                        synonyms.put(word, list);
                    }
                }
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    /**
     * Override default ctor to ensure the other Ctor is always called. Helps
     * ensure synonyms is always loaded if Ctor successful.
     */
    public int countMatches(List<NTuple<String>> nTuples1, List<NTuple<String>> nTuples2) {
        if (synonyms == null) {
            throw new IllegalStateException("No synonyms loaded");
        }

        int count = 0;
        for (NTuple<String> tuple1 : nTuples1) {
            for (NTuple<String> tuple2 : nTuples2) {
                //Design decision count each match just once
                if (isMatch(tuple1, tuple2, synonyms)) {
                    count++;
                    break;
                }
            }

        }
        return count;
    }

    /**
     * Checks if there is a match between two tuples such that for each word
     * pair, the words are the same or synonyms
     *
     * @param tuple1
     * @param tuple2
     * @param synonyms
     * @return
     */
    private boolean isMatch(NTuple<String> tuple1, NTuple<String> tuple2,
            Map<String, List<String>> synonyms) {
        if (synonyms == null) {
            throw new IllegalStateException("No synonyms loaded");
        }

        if (tuple1.size() != tuple2.size()) {
            throw new IllegalArgumentException("Your tuple sizes do not match");
        }

        for (int i = 0; i < tuple1.size(); i++) {
            String word1 = tuple1.get(i);
            String word2 = tuple2.get(i);
            // Don't need to to equalsIgnoreCase as preprocessing done earlier
            if (!word1.equals(word2)) {
                //check if they are synonyms
                if (synonyms.containsKey(word1) && synonyms.containsKey(word2)) {

                    List<String> syn1 = synonyms.get(word1);
                    List<String> syn2 = synonyms.get(word2);
                    //Perhaps we could get by with == that is are they pointing to the same object?
                    //See defn of list.equals() http://docs.oracle.com/javase/7/docs/api/java/util/List.html#equals(java.lang.Object)
                    if (!syn1.equals(syn2)) {
                        return false;
                    }
                } else {
                    //Not synonyms
                    return false;
                }
            } else {
                // continue if words are equals
            }

        }
        return true;
    }

    static String str = "";

    /**
     * Get the Plagiarism score - the higher the number the more likelihood.
     *
     * @return The percentage of tuples in input file 1 that have a match in
     * input file 2
     * @throws IOException if we are unable to read any of the three files
     * specified
     */
    public String getPlagiarismScore(String synonymsFileName, String inputFileName1, String inputFileName2, int tupleSize) throws IOException {
        str = "";
        //NOTE: Rearranging a string containing N words into tuples of size k in this way
        // results in a LOT of duplication and creation of strings
        List<NTuple<String>> nTuples1 = NTuple.loadTuplesFromFile(inputFileName1, tupleSize);
       str += ("\n");
        List<NTuple<String>> nTuples2 = NTuple.loadTuplesFromFile(inputFileName2, tupleSize);
      str += ("\n");

        int count = countMatches(nTuples1, nTuples2);
        str += ("\nNum matches " + count);

        double percentMatch = 0.0;
        if (nTuples1.size() > 0) {
            percentMatch = (100 * count) / nTuples1.size();
        }
        str += ("\n% of tuples that match: " + percentMatch);

        return str;
    }

    static class NTuple<T> {

        List<T> wordsInTuple = new ArrayList<T>();

        public void addWord(T string) {
            wordsInTuple.add(string);
        }

        public T get(int i) {
            return wordsInTuple.get(i);
        }

        public int size() {
            return wordsInTuple.size();
        }

        public static List<NTuple<String>> loadTuplesFromFile(String fileName, int tupleSize) throws IOException {
            BufferedReader br = null;
            List<NTuple<String>> tuples = new ArrayList<NTuple<String>>();
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                str += ("\nTuple File: " + fileName);
                String line;
                while ((line = br.readLine()) != null) {
           str += ("\n"+line);
                    tuples.addAll(lineToTuples(line, tupleSize));
                }
            } finally {
                if (br != null) {
                    br.close();
                }
            }

            return tuples;
        }

        /**
         * Convert a line of synonyms e.g. "run sprint jog" to a List of
         * tupleSized tuples
         *
         * @param line
         * @param tupleSize
         * @return
         */
        private static List<NTuple<String>> lineToTuples(String line, int tupleSize) {
            List<NTuple<String>> tuplesInLine = new ArrayList<NTuple<String>>();

            String[] splitwords = line.toLowerCase().split(" "); //Note lower case preprocessing done once here

            //Some more checking
            if (splitwords.length < tupleSize) {
                return tuplesInLine; //return empty list
            }
            //TODO what if number of words in the line is zero or 1? Should we throw an exception?
            for (int i = 0; i < splitwords.length - (tupleSize - 1); i++) {

                NTuple<String> n = new NTuple<String>();
                for (int j = i; j < i + tupleSize; j++) {
                    n.addWord(splitwords[j]);
                }
                //System.out.println("Tuple: "+tuple);
                tuplesInLine.add(n);
            }
            return tuplesInLine;
        }
    }

    /**
     * Main for the command line (and also for some testing)
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        String synonymsFileName;
        String comparisonFileName;
        String baseFileName;

        int numTuples = 3;
        synonymsFileName = "synonyms.txt";
        comparisonFileName = "file1.txt";
        baseFileName = "file2.txt";
        PlagiarismDetector pd = new PlagiarismDetector(synonymsFileName);

        pd.getPlagiarismScore(synonymsFileName, comparisonFileName,
                baseFileName, numTuples);

    }

}
