
import java.util.*;

public class Texteditor {

    // Linked List to store text
    static LinkedList<String> document = new LinkedList<>();

    // Two stacks for Undo and Redo
    static Stack<LinkedList<String>> undoStack = new Stack<>();
    static Stack<LinkedList<String>> redoStack = new Stack<>();

    // Save previous state
    static void saveState() {
        undoStack.push(new LinkedList<>(document));
        redoStack.clear();
    }

    // Display document
    static void displayText() {
        System.out.println("\n----- YOUR DOCUMENT -----");

        if (document.isEmpty()) {
            System.out.println("Document is empty!");
        } else {
            for (int i = 0; i < document.size(); i++) {
                System.out.println((i + 1) + ". " + document.get(i));
            }
        }
    }

    // Insert text
    static void insertText(String text) {
        saveState();
        document.add(text);
        System.out.println("Text inserted successfully!");
    }

    // Update text
    static void updateText(int line, String text) {
        if (line < 1 || line > document.size()) {
            System.out.println("Invalid line number!");
            return;
        }

        saveState();
        document.set(line - 1, text);
        System.out.println("Text updated successfully!");
    }

    // Delete text
    static void deleteText(int line) {
        if (line < 1 || line > document.size()) {
            System.out.println("Invalid line number!");
            return;
        }

        saveState();
        document.remove(line - 1);
        System.out.println("Text deleted successfully!");
    }

    // Undo operation
    static void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to Undo!");
            return;
        }

        redoStack.push(new LinkedList<>(document));
        document = undoStack.pop();

        System.out.println("Undo successful!");
    }

    // Redo operation
    static void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to Redo!");
            return;
        }

        undoStack.push(new LinkedList<>(document));
        document = redoStack.pop();

        System.out.println("Redo successful!");
    }

    // Searching a word
    static void searchText(String word) {
        boolean found = false;

        for (int i = 0; i < document.size(); i++) {
            if (document.get(i).toLowerCase()
                    .contains(word.toLowerCase())) {

                System.out.println("Found at line: " + (i + 1));
                System.out.println(document.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("Word not found!");
        }
    }

    // Hashing using HashMap
    static void wordFrequency() {
        HashMap<String, Integer> map = new HashMap<>();

        for (String line : document) {
            String[] words = line.toLowerCase().split("\\W+");

            for (String word : words) {
                if (!word.isEmpty()) {
                    map.put(word, map.getOrDefault(word, 0) + 1);
                }
            }
        }

        System.out.println("\n----- WORD FREQUENCY -----");

        if (map.isEmpty()) {
            System.out.println("No words available!");
        } else {
            for (String word : map.keySet()) {
                System.out.println(word + " : " + map.get(word));
            }
        }
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== TEXT EDITOR =====");
            System.out.println("1. Insert Text");
            System.out.println("2. Display Text");
            System.out.println("3. Update Text");
            System.out.println("4. Delete Text");
            System.out.println("5. Undo");
            System.out.println("6. Redo");
            System.out.println("7. Search Text");
            System.out.println("8. Word Frequency");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter text: ");
                    String text = sc.nextLine();
                    insertText(text);
                    break;

                case 2:
                    displayText();
                    break;

                case 3:
                    System.out.print("Enter line number: ");
                    int updateLine = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new text: ");
                    String newText = sc.nextLine();

                    updateText(updateLine, newText);
                    break;

                case 4:
                    System.out.print("Enter line number: ");
                    int deleteLine = sc.nextInt();

                    deleteText(deleteLine);
                    break;

                case 5:
                    undo();
                    break;

                case 6:
                    redo();
                    break;

                case 7:
                    System.out.print("Enter word to search: ");
                    String word = sc.nextLine();

                    searchText(word);
                    break;

                case 8:
                    wordFrequency();
                    break;

                case 9:
                    System.out.println("Thank you for using Text Editor!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 9);

        sc.close();
    }
}