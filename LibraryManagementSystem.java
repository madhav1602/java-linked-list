public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        library.addBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 101, true, 0);
        library.addBook("To Kill a Mockingbird", "Harper Lee", "Classic", 102, true, 1);
        library.addBook("1984", "George Orwell", "Dystopian", 103, false, 2);

        // Display all books in forward order
        System.out.println("Library Books (Forward Order):");
        library.displayForward();

        // Display all books in reverse order
        System.out.println("\nLibrary Books (Reverse Order):");
        library.displayReverse();

        // Search for a book
        System.out.println("\nSearching for books by George Orwell:");
        library.searchBook("", "George Orwell");

        // Update availability
        System.out.println("\nUpdating availability of Book ID 103 to Available:");
        library.updateAvailability(103, true);
        library.displayForward();

        // Remove a book
        System.out.println("\nRemoving Book ID 102:");
        library.removeBook(102);
        library.displayForward();

        // Count total books
        System.out.println("\nTotal books in library: " + library.countBooks());
    }
}

// Node class
class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next, prev;

    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = null; // for next reference
        this.prev = null; // for previous reference
    }
}

class Library {
    private Book head, tail;
    private int bookCount;

    public Library() {
        this.head = null;
        this.tail = null;
        this.bookCount = 0;
    }

    // Add a book at a specific position
    public void addBook(String title, String author, String genre, int bookId, boolean isAvailable, int position) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);

        if (head == null || position == 0) { // Insert at the beginning
            newBook.next = head;
            if (head != null) head.prev = newBook;
            head = newBook;
            if (tail == null) tail = newBook;
        } else {
            Book temp = head;
            int index = 0;
            while (temp.next != null && index < position - 1) {
                temp = temp.next;
                index++;
            }
            newBook.next = temp.next;
            if (temp.next != null) temp.next.prev = newBook;
            temp.next = newBook;
            newBook.prev = temp;
            if (newBook.next == null) tail = newBook;
        }
        bookCount++;
    }

    // Remove a book by Book ID
    public void removeBook(int bookId) {
        if (head == null) return;
        Book temp = head;
        while (temp != null && temp.bookId != bookId) {
            temp = temp.next;
        }
        if (temp == null) return;
        if (temp.prev != null) temp.prev.next = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev;
        if (temp == head) head = temp.next;
        if (temp == tail) tail = temp.prev;
        bookCount--;
    }

    // Search for a book by Title or Author
    public void searchBook(String title, String author) {
        Book temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title) || temp.author.equalsIgnoreCase(author)) {
                System.out.println(temp.bookId + " | " + temp.title + " | " + temp.author + " | " + temp.genre + " | " + (temp.isAvailable ? "Available" : "Not Available"));
            }
            temp = temp.next;
        }
    }

    // Update availability status by Book ID
    public void updateAvailability(int bookId, boolean isAvailable) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = isAvailable;
                return;
            }
            temp = temp.next;
        }
    }

    // Display books in forward order
    public void displayForward() {
        Book temp = head;
        while (temp != null) {
            System.out.println(temp.bookId + " | " + temp.title + " | " + temp.author + " | " + temp.genre + " | " + (temp.isAvailable ? "Available" : "Not Available"));
            temp = temp.next;
        }
    }

    // Display books in reverse order
    public void displayReverse() {
        Book temp = tail;
        while (temp != null) {
            System.out.println(temp.bookId + " | " + temp.title + " | " + temp.author + " | " + temp.genre + " | " + (temp.isAvailable ? "Available" : "Not Available"));
            temp = temp.prev;
        }
    }

    // Count total books in library
    public int countBooks() {
        return bookCount;
    }
}