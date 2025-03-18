
public class UndoRedoEditor {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor(10); // Limit history to 10 states

        editor.addState("Hello");
        editor.addState("Hello, World!");
        editor.addState("Hello, World! This is a text editor.");
        editor.displayCurrentState();

        editor.undo();
        editor.undo();
        editor.displayCurrentState();

        editor.redo();
        editor.displayCurrentState();
    }
}

class TextState {
    String content;
    TextState prev, next;

    public TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    private TextState current;
    private int maxHistory;
    private int historySize;

    public TextEditor(int maxHistory) {
        this.maxHistory = maxHistory;
        this.historySize = 0;
        this.current = new TextState(""); // Initial empty state
    }

    // Add new text state (typing or action performed)
    public void addState(String newText) {
        TextState newState = new TextState(newText);
        newState.prev = current;
        if (current != null) {
            current.next = newState;
        }
        current = newState;
        historySize++;

        // Limit history size
        if (historySize > maxHistory) {
            removeOldestState();
        }
    }

    // Undo functionality
    public void undo() {
        if (current.prev != null) {
            current = current.prev;
            System.out.println("Undo: " + current.content);
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    // Redo functionality
    public void redo() {
        if (current.next != null) {
            current = current.next;
            System.out.println("Redo: " + current.content);
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    // Display current text state
    public void displayCurrentState() {
        System.out.println("Current Text: " + current.content);
    }

    // Remove the oldest state when exceeding history size
    private void removeOldestState() {
        TextState temp = current;
        while (temp.prev != null) {
            temp = temp.prev;
        }
        if (temp.next != null) {
            temp.next.prev = null;
        }
        historySize--;
    }
}