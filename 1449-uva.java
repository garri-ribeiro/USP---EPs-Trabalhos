import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

class Main {

    private static Map<String, Integer> wordsMap = new HashMap<>();
    private static List<String> dominatingPatterns = new LinkedList<>();
    private static int countDominatingPattern = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            int numberOfWords = Integer.parseInt(scan.nextLine());
            if(numberOfWords == 0) {
                break;
            }

            Node root = new Node(null);
            while (numberOfWords != 0) {
                createTrie(root, scan.nextLine());
                numberOfWords--;
            }

            searchWord(root, scan.nextLine());
            printAnswer();
            wordsMap.clear();
        }
    }

    private static void searchWord(Node root, String word){
        Node node = root;
        List<Node> children;
        int head = 0;
        while(word.length() != 0) {
            char letter = word.charAt(head);

            children = node.getChildren();
            Optional<Node> possibleChild = searchChild(children, letter);

            if (!possibleChild.isPresent()) {
                node = root;
                head = 0;
                word = word.substring(1);

                continue;
            }

            Node child = possibleChild.get();
            if(child.isWord()) {
                Integer value = wordsMap.get(child.getWord());
                int newValue = value+1;

                if(newValue > countDominatingPattern) {
                    dominatingPatterns.clear();
                    dominatingPatterns.add(child.getWord());
                    countDominatingPattern = newValue;
                } else if (newValue == countDominatingPattern) {
                    dominatingPatterns.add(child.getWord());
                }

                wordsMap.put(possibleChild.get().getWord(), newValue);
            }

            head++;
            if(head == word.length()) {
                node = root;
                head = 0;
                word = word.substring(1);
            } else {
                node = child;
            }
        }
    }


    private static void createTrie(Node root, String word){
        String subWord = word;
        Node node = root;
        List<Node> children;

        while(subWord.length() != 0) {
            char letter = subWord.charAt(0);
            children = node.getChildren();
            Optional<Node> possibleChild = searchChild(children, letter);

            if(!possibleChild.isPresent()) {
                Node child = new Node(letter);
                node.addChild(child);
                node = child;
            } else {
                node = possibleChild.get();
            }

            if(subWord.length() == 1) {
                wordsMap.put(word, 0);
                node.setWord(word);
                return;
            }

            subWord = subWord.substring(1);
        }
    }

    private static Optional<Node> searchChild(List<Node> children, char letter) {
        return children.stream()
                       .filter(child -> child.getValue() == letter)
                       .findFirst();
    }

    private static void printAnswer() {
        System.out.format("%d%n", countDominatingPattern);
        dominatingPatterns.forEach(pattern -> {
            System.out.format("%s%n",  pattern);
        });
    }
}

class Node {
    private List<Node> children;
    private Character value;
    private boolean isWord;
    private String word;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    Node(Character value)
    {
        this.children = new ArrayList<>();
        this.value = value;
        this.isWord = false;
        this.word = "";
        this.count = 0;
    }


    void addChild(Node child)
    {
        children.add(child);
    }

    void setWord(String word) {
        this.isWord = true;
        this.word = word;
    }

    String getWord() {
        return word;
    }

    boolean isWord() {
        return isWord;
    }

    List<Node> getChildren() {
        return children;
    }

    char getValue() {
        return value;
    }
}
