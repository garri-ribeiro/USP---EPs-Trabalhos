import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Main {

    private static int occurrencesDominatingPattern = 0;
    private static List<String> listOfWords = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            int numberOfWords = Integer.parseInt(scan.nextLine());

            if(numberOfWords == 0) {
                break;
            }

            Trie trie = new Trie();
            while (numberOfWords != 0) {
                String word = scan.nextLine();
                listOfWords.add(word);
                trie.insertWord(word);
                numberOfWords--;
            }

            List<String> dominatingPatterns = searchPatterns(trie.getRoot(), scan.nextLine());

            printAnswer(dominatingPatterns);

            listOfWords.clear();
            occurrencesDominatingPattern = 0;
        }
    }

    private static List<String> searchPatterns(TrieNode root, String phrase) {
        List<String> dominatingPatterns = new LinkedList<>();
        char[] phraseArray = phrase.toCharArray();
        int maxOccurrences = 0;
        int head = 0;
        int newBegin = 0;
        TrieNode current = root;

        while(phraseArray.length != newBegin) {
            char character = phraseArray[head];

            current = current.getChildren().get(character);

            if(current == null) {
                current = root;
                newBegin++;
                head = newBegin;
                continue;
            }

            if(current.isWord()) {
                int wordOccurrences = current.addWordOccurrence();

                if(wordOccurrences > maxOccurrences) {
                    dominatingPatterns.clear();
                    dominatingPatterns.add(current.getWord());
                    maxOccurrences = wordOccurrences;
                } else if(wordOccurrences == maxOccurrences) {
                    dominatingPatterns.add(current.getWord());
                }
            }

            head++;
            if(head == phraseArray.length) {
                current = root;
                newBegin++;
                head = newBegin;
            }
        }

        occurrencesDominatingPattern = maxOccurrences;
        if(occurrencesDominatingPattern == 0) {
            dominatingPatterns = listOfWords;
        }
        return dominatingPatterns;
    }

    private static void printAnswer(List<String> dominatingPatterns) {
        System.out.printf("%d%n", occurrencesDominatingPattern);
        dominatingPatterns.forEach(pattern -> {
            System.out.printf("%s%n",  pattern);
        });
    }
}

class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void insertWord(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            current = current.getChildren()
                             .computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }
        current.setWord(word);
    }

    TrieNode getRoot() {
        return root;
    }
}

class TrieNode {
    private Map<Character, TrieNode> children;
    private boolean isWord;
    private String word;
    private int occurrencesWords;

    TrieNode() {
        this.children = new HashMap<>();
        this.isWord = false;
        this.word = "";
        this.occurrencesWords = 0;
    }

    Map<Character, TrieNode> getChildren() {
        return children;
    }

    boolean isWord() {
        return isWord;
    }

    String getWord() {
        return word;
    }

    void setWord(String word) {
        this.isWord = true;
        this.word = word;
    }

    int addWordOccurrence() {
        this.occurrencesWords++;
        return this.occurrencesWords;
    }
}
