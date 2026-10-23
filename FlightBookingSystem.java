
import java.util.ArrayList;
import java.util.Scanner;

// Flight class
class Flight {
    private String code, airline, source, destination;
    private int seats;
    private double fare;

    Flight(String code, String airline, String source,
           String destination, int seats, double fare) {
        this.code = code;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.fare = fare;
    }

    String getCode() {
        return code;
    }

    int getSeats() {
        return seats;
    }

    double getFare() {
        return fare;
    }

    void bookSeats(int count) {
        seats -= count;
    }

    void display() {
        System.out.println(code + " | " + airline + " | "
                + source + " to " + destination
                + " | Seats: " + seats
                + " | Fare: Rs." + fare);
    }

    String getRoute() {
        return source + " to " + destination;
    }
}

// Abstract Passenger class
abstract class Passenger {
    private String name;
    private int age;

    Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    abstract double getDiscount();
    abstract String getType();
}

// Regular passenger
class RegularPassenger extends Passenger {
    RegularPassenger(String name, int age) {
        super(name, age);
    }

    double getDiscount() {
        return 0;
    }

    String getType() {
        return "Regular";
    }
}

// Student passenger
class StudentPassenger extends Passenger {
    StudentPassenger(String name, int age) {
        super(name, age);
    }

    double getDiscount() {
        return 10;
    }

    String getType() {
        return "Student";
    }
}

// Booking class
class Booking {
    private String bookingId;
    private Flight flight;
    private Passenger passenger;
    private int tickets;
    private double subtotal, discount, total;

    Booking(String bookingId, Flight flight,
            Passenger passenger, int tickets) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.tickets = tickets;

        subtotal = flight.getFare() * tickets;
        discount = subtotal * passenger.getDiscount() / 100;
        total = subtotal - discount;
    }

    void display() {
        System.out.println("\n--- Booking Summary ---");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Flight: " + flight.getCode());
        System.out.println("Route: " + flight.getRoute());
        System.out.println("Passenger: " + passenger.getName());
        System.out.println("Age: " + passenger.getAge());
        System.out.println("Type: " + passenger.getType());
        System.out.println("Tickets: " + tickets);
        System.out.println("Base fare per ticket: Rs."
                + flight.getFare());
        System.out.println("Subtotal: Rs." + subtotal);
        System.out.println("Discount: Rs." + discount);
        System.out.println("Final amount: Rs." + total);
    }
}

// Main flight booking system
public class FlightBookingSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Flight> flights = new ArrayList<>();
    static Booking latestBooking;
    static int bookingNumber = 1001;

    // Add predefined flights
    static void addFlights() {
        flights.add(new Flight("PN101", "Air India",
                "Pune", "Delhi", 20, 4000));

        flights.add(new Flight("MB102", "IndiGo",
                "Mumbai", "Bengaluru", 25, 3500));

        flights.add(new Flight("PN103", "Akasa Air",
                "Pune", "Hyderabad", 15, 3000));
    }

    // Display flights
    static void displayFlights() {
        System.out.println("\n--- Available Flights ---");
        for (Flight f : flights) {
            f.display();
        }
    }

    // Find flight using its code
    static Flight findFlight(String code) {
        for (Flight f : flights) {
            if (f.getCode().equalsIgnoreCase(code)) {
                return f;
            }
        }
        return null;
    }

    // Read a valid positive integer
    static int readPositiveInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Handle invalid input
            }
            System.out.println("Enter a valid positive number.");
        }
    }

    // Book flight
    static void bookFlight() {
        System.out.print("Enter flight code: ");
        String code = sc.nextLine().trim();

        Flight flight = findFlight(code);

        if (flight == null) {
            System.out.println("Invalid flight code.");
            return;
        }

        System.out.print("Enter passenger name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Passenger name cannot be blank.");
            return;
        }

        int age = readPositiveInt("Enter age: ");

        System.out.print("Passenger type (Regular/Student): ");
        String type = sc.nextLine().trim();

        Passenger passenger;

        if (type.equalsIgnoreCase("Student")) {
            passenger = new StudentPassenger(name, age);
        } else if (type.equalsIgnoreCase("Regular")) {
            passenger = new RegularPassenger(name, age);
        } else {
            System.out.println("Invalid passenger type.");
            return;
        }

        int tickets = readPositiveInt("Enter number of tickets: ");

        // Validate seats before booking
        if (tickets > flight.getSeats()) {
            System.out.println("Booking failed: Not enough seats.");
            return;
        }

        // Create booking only after validation
        Booking booking = new Booking(
                "BK" + bookingNumber,
                flight, passenger, tickets);

        flight.bookSeats(tickets);
        latestBooking = booking;
        bookingNumber++;

        System.out.println("\nBooking confirmed!");
        latestBooking.display();
    }

    // Main menu
    public static void main(String[] args) {
        addFlights();

        while (true) {
            System.out.println("\n=== Welcome to Kiran Air Booking ===");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Booking");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    displayFlights();
                    break;

                case "2":
                    bookFlight();
                    break;

                case "3":
                    if (latestBooking == null) {
                        System.out.println("No booking found.");
                    } else {
                        latestBooking.display();
                    }
                    break;

                case "4":
                    System.out.println(
                            "Thank you for using Kiran Air Booking!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
