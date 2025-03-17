import java.util.*;

public class SocialMediaApp {
    public static void main(String[] args) {
        SocialMedia sm = new SocialMedia();

        sm.addUser(1, "Raunak", 25);
        sm.addUser(2, "Arvind", 27);
        sm.addUser(3, "Ritik", 22);
        sm.addUser(4, "Rohit", 30);

        sm.addFriend(1, 2);
        sm.addFriend(1, 3);
        sm.addFriend(2, 3);
        sm.addFriend(3, 4);

        sm.displayFriends(1);
        sm.findMutualFriends(1, 3);
        sm.countFriends();
    }
}

class Node {
    int userId;
    String name;
    int age;
    List<Integer> friendIds;
    Node next;

    public Node(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }
}

class SocialMedia {
    private Node head;

    // Add a new user
    public void addUser(int userId, String name, int age) {
        Node newUser = new Node(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
    }

    // Find user by ID
    private Node findUser(int userId) {
        Node temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    // Add a friend connection between two users
    public void addFriend(int userId1, int userId2) {
        Node user1 = findUser(userId1);
        Node user2 = findUser(userId2);
        if (user1 != null && user2 != null && userId1 != userId2) {
            if (!user1.friendIds.contains(userId2)) user1.friendIds.add(userId2);
            if (!user2.friendIds.contains(userId1)) user2.friendIds.add(userId1);
        }
    }

    // Remove a friend connection
    public void removeFriend(int userId1, int userId2) {
        Node user1 = findUser(userId1);
        Node user2 = findUser(userId2);
        if (user1 != null && user2 != null) {
            user1.friendIds.remove(Integer.valueOf(userId2));
            user2.friendIds.remove(Integer.valueOf(userId1));
        }
    }

    // Find mutual friends between two users
    public void findMutualFriends(int userId1, int userId2) {
        Node user1 = findUser(userId1);
        Node user2 = findUser(userId2);
        if (user1 != null && user2 != null) {
            Set<Integer> mutualFriends = new HashSet<>(user1.friendIds);
            mutualFriends.retainAll(user2.friendIds);
            System.out.println("Mutual Friends: " + mutualFriends);
        }
    }

    // Display all friends of a specific user
    public void displayFriends(int userId) {
        Node user = findUser(userId);
        if (user != null) {
            System.out.println("Friends of " + user.name + ": " + user.friendIds);
        }
    }

    // Search for a user by Name or User ID
    public void searchUser(String nameOrId) {
        Node temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(nameOrId) || String.valueOf(temp.userId).equals(nameOrId)) {
                System.out.println("User Found: " + temp.name + " (ID: " + temp.userId + ")");
                return;
            }
            temp = temp.next;
        }
        System.out.println("User not found.");
    }

    // Count the number of friends for each user
    public void countFriends() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friendIds.size() + " friends.");
            temp = temp.next;
        }
    }
}
