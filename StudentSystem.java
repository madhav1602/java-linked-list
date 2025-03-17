public class StudentSystem {
    public static void main(String[] args) {
        StudentList list = new StudentList();

        // Adding students
        list.addStudent(101, "Vishwas", 20, 'A', 0);
        list.addStudent(102, "Deepak", 21, 'B', 1);
        list.addStudent(103, "Kamal", 22, 'C', 2);

        // Display all students
        System.out.println("Student Records:");
        list.displayStudents();

        // Search for a student
        System.out.println("\nSearching for Roll Number 102:");
        list.searchStudent(102);

        // Update a student's grade
        System.out.println("\nUpdating grade for Roll Number 101 to 'A+':");
        list.updateGrade(101, 'A');
        list.displayStudents();

        // Delete a student
        System.out.println("\nDeleting student with Roll Number 103:");
        list.deleteStudent(103);
        list.displayStudents();
    }
}

// Node class and constructor
class Node {
    int rollNumber;
    String name;
    int age;
    char grade;
    Node next;

    public Node(int rollNumber, String name, int age, char grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

// class with all methods
class StudentList {
    private Node head;

    // constructor for this class to initialise head
    public StudentList() {
        head = null;
    }

    // Method to add student at particular position
    public void addStudent(int rollNumber, String name, int age, char grade, int position) {
        Node newNode = new Node(rollNumber, name, age, grade);
        if (position == 0 || head == null) {
            newNode.next = head;
            head = newNode;
        } else {
            Node temp = head;
            int index = 0;
            while (temp.next != null && index < position - 1) {
                temp = temp.next;
                index++;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    // Method to delete student from particular postion by changing references
    public void deleteStudent(int rollNumber) {
        if (head == null) return;
        if (head.rollNumber == rollNumber) {
            head = head.next;
            return;
        }
        Node temp = head;
        while (temp.next != null && temp.next.rollNumber != rollNumber) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    // Traversing the list to search
    public void searchStudent(int rollNumber) {
        Node temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                System.out.println(temp.rollNumber + "  " + temp.name + "  " + temp.age + "  " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Traversing the list to search by roll number and update grade
    public void updateGrade(int rollNumber, char newGrade) {
        Node temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                temp.grade = newGrade;
                return;
            }
            temp = temp.next;
        }
    }

    // Method to display students
    public void displayStudents() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.rollNumber + "  " + temp.name + "  " + temp.age + "  " + temp.grade);
            temp = temp.next;
        }
    }
}
