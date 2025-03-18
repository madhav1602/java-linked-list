
public class TicketReservationApp {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();

        system.addTicket(101, "Vicky", "Avengers", 12, "10:00 AM");
        system.addTicket(102, "Rahul", "Inception", 15, "2:00 PM");
        system.addTicket(103, "Rohit", "Interstellar", 18, "5:00 PM");

        system.displayTickets();
        system.searchTicket("Vicky");
        system.removeTicket(102);
        system.displayTickets();
        system.countTickets();
    }
}

class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    int seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket last;
    private int ticketCount;

    public TicketReservationSystem() {
        this.last = null;
        this.ticketCount = 0;
    }

    // Add a new ticket reservation at the end
    public void addTicket(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (last == null) {
            last = newTicket;
            last.next = last;
        } else {
            newTicket.next = last.next;
            last.next = newTicket;
            last = newTicket;
        }
        ticketCount++;
    }

    // Remove a ticket by Ticket ID
    public void removeTicket(int ticketId) {
        if (last == null) {
            System.out.println("No tickets available.");
            return;
        }
        Ticket current = last.next, prev = last;
        do {
            if (current.ticketId == ticketId) {
                if (current == last.next && current == last) {
                    last = null;
                } else {
                    prev.next = current.next;
                    if (current == last) {
                        last = prev;
                    }
                }
                ticketCount--;
                System.out.println("Ticket removed: " + ticketId);
                return;
            }
            prev = current;
            current = current.next;
        } while (current != last.next);

        System.out.println("Ticket not found.");
    }

    // Display all tickets
    public void displayTickets() {
        if (last == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket temp = last.next;
        do {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != last.next);
    }

    // Search for a ticket by Customer Name or Movie Name
    public void searchTicket(String searchTerm) {
        if (last == null) {
            System.out.println("No tickets available.");
            return;
        }
        Ticket temp = last.next;
        boolean found = false;
        do {
            if (temp.customerName.equalsIgnoreCase(searchTerm) || temp.movieName.equalsIgnoreCase(searchTerm)) {
                System.out.println("Found: Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber);
                found = true;
            }
            temp = temp.next;
        } while (temp != last.next);
        if (!found) {
            System.out.println("No matching ticket found.");
        }
    }

    // Calculate total number of booked tickets
    public void countTickets() {
        System.out.println("Total Tickets Booked: " + ticketCount);
    }
}