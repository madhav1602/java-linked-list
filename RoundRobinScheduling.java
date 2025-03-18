import java.util.Scanner;


public class RoundRobinScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter time quantum: ");
        int timeQuantum = scanner.nextInt();

        RoundRobinScheduler scheduler = new RoundRobinScheduler(timeQuantum);

        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 3);
        scheduler.addProcess(4, 6, 2);

        scheduler.displayProcesses();
        scheduler.executeProcesses();
    }
}

class Process {
    int processId;
    int burstTime;
    int remainingTime;
    int priority;
    Process next;

    public Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process head = null, tail = null;
    private int processCount = 0;
    private int timeQuantum;

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    // Add a process to the circular queue
    public void addProcess(int processId, int burstTime, int priority) {
        Process newProcess = new Process(processId, burstTime, priority);
        if (head == null) {
            head = newProcess;
            tail = newProcess;
            newProcess.next = head;
        } else {
            tail.next = newProcess;
            newProcess.next = head;
            tail = newProcess;
        }
        processCount++;
    }

    // Remove a process after execution
    public void removeProcess(int processId) {
        if (head == null) return;

        Process temp = head, prev = tail;
        do {
            if (temp.processId == processId) {
                if (temp == head) head = head.next;
                if (temp == tail) tail = prev;
                prev.next = temp.next;
                processCount--;
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    // Simulate round robin scheduling
    public void executeProcesses() {
        if (head == null) {
            System.out.println("No processes to execute.");
            return;
        }

        int time = 0;
        Process current = head;

        System.out.println("Executing Round Robin Scheduling:");
        while (processCount > 0) {
            if (current.remainingTime > 0) {
                int executionTime = Math.min(timeQuantum, current.remainingTime);
                current.remainingTime -= executionTime;
                time += executionTime;
                System.out.println("Process " + current.processId + " executed for " + executionTime + " units. Remaining: " + current.remainingTime);

                if (current.remainingTime == 0) {
                    System.out.println("Process " + current.processId + " completed execution.");
                    removeProcess(current.processId);
                }
            }
            current = current.next;
        }
        System.out.println("All processes executed successfully.");
    }

    // Display all processes in the queue
    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }
        Process temp = head;
        System.out.println("Process Queue:");
        do {
            System.out.println("ID: " + temp.processId + " | Burst Time: " + temp.burstTime + " | Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }
}