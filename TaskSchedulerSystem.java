
public class TaskSchedulerSystem {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        // Adding tasks
        scheduler.addTask(1, "Task A", 1, "2025-03-15", 0);
        scheduler.addTask(2, "Task B", 2, "2025-03-16", 1);
        scheduler.addTask(3, "Task C", 3, "2025-03-17", 2);

        // Display all tasks
        System.out.println("Task List:");
        scheduler.displayTasks();

        // View next task in circular order
        System.out.println("\nViewing Tasks in Circular Order:");
        scheduler.viewNextTask();
        scheduler.viewNextTask();
        scheduler.viewNextTask();
        scheduler.viewNextTask(); // Loops back to the start

        // Search for a task by priority
        System.out.println("\nSearch for Priority 2 Tasks:");
        scheduler.searchByPriority(2);

        // Remove a task
        System.out.println("\nRemoving Task with ID 2");
        scheduler.removeTask(2);
        scheduler.displayTasks();
    }
}


class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    // Constructor to initialize task details
    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    private Task head;
    private Task currentTask;

    // Constructor to initialize an empty circular list
    public TaskScheduler() {
        head = null;
        currentTask = null;
    }

    // Method to add a task at a specific position
    public void addTask(int taskId, String taskName, int priority, String dueDate, int position) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) { // If list is empty, create the first task
            head = newTask;
            head.next = head;
            currentTask = head;
            return;
        }
        if (position == 0) { // Insert at the beginning
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            newTask.next = head;
            temp.next = newTask;
            head = newTask;
        } else { // Insert at a specific position or end
            Task temp = head;
            int index = 0;
            while (temp.next != head && index < position - 1) {
                temp = temp.next;
                index++;
            }
            newTask.next = temp.next;
            temp.next = newTask;
        }
    }

    // Method to remove a task by Task ID
    public void removeTask(int taskId) {
        if (head == null) return;
        Task temp = head, prev = null;
        do {
            if (temp.taskId == taskId) {
                if (temp == head) { // Removing head node
                    Task last = head;
                    while (last.next != head) {
                        last = last.next;
                    }
                    head = head.next;
                    last.next = head;
                } else {
                    prev.next = temp.next;
                }
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    // Method to view the current task and move to the next task in circular order
    public void viewNextTask() {
        if (currentTask == null) return;
        System.out.println("Current Task: " + currentTask.taskId + " | " + currentTask.taskName + " | " + currentTask.priority + " | " + currentTask.dueDate);
        currentTask = currentTask.next;
    }

    // Method to display all tasks starting from the head
    public void displayTasks() {
        if (head == null) return;
        Task temp = head;
        do {
            System.out.println(temp.taskId + " | " + temp.taskName + " | " + temp.priority + " | " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    // Method to search tasks by Priority
    public void searchByPriority(int priority) {
        if (head == null) return;
        Task temp = head;
        do {
            if (temp.priority == priority) {
                System.out.println(temp.taskId + " | " + temp.taskName + " | " + temp.priority + " | " + temp.dueDate);
            }
            temp = temp.next;
        } while (temp != head);
    }
}
