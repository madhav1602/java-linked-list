public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieList list = new MovieList();

        // Adding movies to the list
        list.addMovie("Inception", "Christopher Nolan", 2010, 8.8, 0);
        list.addMovie("Interstellar", "Christopher Nolan", 2014, 8.6, 1);
        list.addMovie("The Dark Knight", "Christopher Nolan", 2008, 9.0, 2);

        // Display movies in forward order
        System.out.println("Movies in Forward Order:");
        list.displayMoviesForward();

        // Display movies in reverse order
        System.out.println("Movies in Reverse Order:");
        list.displayMoviesReverse();

        // Search for movies by director
        System.out.println("Search by Director (Christopher Nolan):");
        list.searchByDirectorOrRating("Christopher Nolan", null);

        // Update movie rating
        System.out.println("Updating rating of Inception to 9.0");
        list.updateRating("Inception", 9.0);
        list.displayMoviesForward();

        // Remove a movie
        System.out.println("\nRemoving The Dark Knight");
        list.removeMovie("The Dark Knight");
        list.displayMoviesForward();
    }
}


class Movie {
    String title; // Movie title
    String director; // Director of the movie
    int year; // Year of release
    double rating; // Movie rating
    Movie next, prev; // Pointers for doubly linked list

    // Constructor to initialize movie details
    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieList {
    private Movie head, tail; // Head and tail pointers

    // Constructor to initialize an empty list
    public MovieList() {
        head = tail = null;
    }

    // Method to add a movie at a specific position
    public void addMovie(String title, String director, int year, double rating, int position) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) { // If list is empty, set new movie as head and tail
            head = tail = newMovie;
            return;
        }
        if (position == 0) { // Insert at the beginning
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        } else { // Insert at a specific position or end
            Movie temp = head;
            int index = 0;
            while (temp.next != null && index < position - 1) {
                temp = temp.next;
                index++;
            }
            newMovie.next = temp.next;
            if (temp.next != null) temp.next.prev = newMovie;
            temp.next = newMovie;
            newMovie.prev = temp;
            if (newMovie.next == null) tail = newMovie; // Update tail if inserted at the end
        }
    }

    // Method to remove a movie by title
    public void removeMovie(String title) {
        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }
        if (temp == null) return; // Movie not found
        if (temp == head) { // Removing head node
            head = head.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) { // Removing tail node
            tail = tail.prev;
            tail.next = null;
        } else { // Removing a middle node
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }

    // Method to search movies by director or rating
    public void searchByDirectorOrRating(String director, Double rating) {
        Movie temp = head;
        while (temp != null) {
            if ((director != null && temp.director.equalsIgnoreCase(director)) ||
                    (rating != null && temp.rating == rating)) {
                System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            }
            temp = temp.next;
        }
    }

    // Method to update the rating of a movie
    public void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                return;
            }
            temp = temp.next;
        }
    }

    // Method to display movies in forward order
    public void displayMoviesForward() {
        Movie temp = head;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            temp = temp.next;
        }
    }

    // Method to display movies in reverse order
    public void displayMoviesReverse() {
        Movie temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            temp = temp.prev;
        }
    }
}