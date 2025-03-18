
public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Adding items
        inventory.addItem("Laptop", 101, 10, 50000, 0);
        inventory.addItem("Mouse", 102, 50, 500, 1);
        inventory.addItem("Keyboard", 103, 30, 1000, 2);

        // Display all items
        System.out.println("Inventory List:");
        inventory.displayInventory();

        // Search for an item
        System.out.println("\nSearching for Item with ID 102:");
        inventory.searchItem(102, "");

        // Update quantity
        System.out.println("\nUpdating quantity for Item ID 101 to 20:");
        inventory.updateQuantity(101, 20);
        inventory.displayInventory();

        // Calculate total inventory value
        System.out.println("\nTotal Inventory Value: Rs." + inventory.calculateTotalValue());

        // Sort by Item Name
        System.out.println("\nSorting by Item Name:");
        inventory.sortByName();
        inventory.displayInventory();

        // Sort by Price
        System.out.println("\nSorting by Price:");
        inventory.sortByPrice();
        inventory.displayInventory();

        // Remove an item
        System.out.println("\nRemoving Item with ID 103");
        inventory.removeItem(103);
        inventory.displayInventory();
    }
}
// Node class
class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    // Constructor to initialize an item (Node)
    public Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    private Item head;

    // Add item at a specific position
    public void addItem(String itemName, int itemId, int quantity, double price, int position) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        if (position == 0 || head == null) { // Insert at the beginning
            newItem.next = head;
            head = newItem;
        } else {
            Item temp = head;
            int index = 0;
            while (temp.next != null && index < position - 1) {
                temp = temp.next;
                index++;
            }
            newItem.next = temp.next;
            temp.next = newItem;
        }
    }

    // Remove item by Item ID
    public void removeItem(int itemId) {
        if (head == null) return;
        if (head.itemId == itemId) {
            head = head.next;
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.itemId != itemId) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    // Traversing and updating item with specific id
    public void updateQuantity(int itemId, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQuantity;
                return;
            }
            temp = temp.next;
        }
    }

    // traversing and searching by item name and item id
    public void searchItem(int itemId, String itemName) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId || temp.itemName.equalsIgnoreCase(itemName)) {
                System.out.println(temp.itemId + " | " + temp.itemName + " | " + temp.quantity + " | " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    // Traversing and calculating total value
    public double calculateTotalValue() {
        double totalValue = 0;
        Item temp = head;
        while (temp != null) {
            totalValue = totalValue + (temp.quantity * temp.price);
            temp = temp.next;
        }
        return totalValue;
    }

    // Sort inventory by Item Name (Bubble Sort for simplicity)
    public void sortByName() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Item temp = head;
            while (temp.next != null) {
                if (temp.itemName.compareToIgnoreCase(temp.next.itemName) > 0) {
                    swap(temp, temp.next);
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
    }

    // Sort inventory by Price (Bubble Sort for simplicity)
    public void sortByPrice() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Item temp = head;
            while (temp.next != null) {
                if (temp.price > temp.next.price) {
                    swap(temp, temp.next);
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
    }

    // Swap helper method
    private void swap(Item a, Item b) {
        String tempName = a.itemName;
        int tempId = a.itemId;
        int tempQuantity = a.quantity;
        double tempPrice = a.price;

        a.itemName = b.itemName;
        a.itemId = b.itemId;
        a.quantity = b.quantity;
        a.price = b.price;

        b.itemName = tempName;
        b.itemId = tempId;
        b.quantity = tempQuantity;
        b.price = tempPrice;
    }

    // Traversing and printing
    public void displayInventory() {
        Item temp = head;
        while (temp != null) {
            System.out.println(temp.itemId + " | " + temp.itemName + " | " + temp.quantity + " | " + temp.price);
            temp = temp.next;
        }
    }
}