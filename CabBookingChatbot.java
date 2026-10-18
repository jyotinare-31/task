
import java.util.Scanner;

public class CabBookingChatbot {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Book Ride");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 2) {
                System.out.println("Thank you!");
                break;
            }

            if (choice != 1) {
                System.out.println("Invalid choice!");
                continue;
            }

            System.out.println("\n1. Auto - Rs.12/km");
            System.out.println("2. Sedan - Rs.18/km");
            System.out.println("3. SUV - Rs.25/km");
            System.out.print("Choose cab: ");
            int cab = sc.nextInt();

            int rate;
            String cabName;

            if (cab == 1) {
                rate = 12;
                cabName = "Auto";
            } else if (cab == 2) {
                rate = 18;
                cabName = "Sedan";
            } else if (cab == 3) {
                rate = 25;
                cabName = "SUV";
            } else {
                System.out.println("Invalid cab choice!");
                continue;
            }

            System.out.println("\n1. Pune Station to Shivajinagar - 5 km");
            System.out.println("2. Swargate to Kothrud - 8 km");
            System.out.println("3. Hinjawadi to Wakad - 10 km");
            System.out.print("Choose route: ");
            int route = sc.nextInt();

            String source, destination;
            int distance;

            if (route == 1) {
                source = "Pune Station";
                destination = "Shivajinagar";
                distance = 5;
            } else if (route == 2) {
                source = "Swargate";
                destination = "Kothrud";
                distance = 8;
            } else if (route == 3) {
                source = "Hinjawadi";
                destination = "Wakad";
                distance = 10;
            } else {
                System.out.println("Invalid route!");
                continue;
            }

            int fare = rate * distance;

            System.out.println("\nCab: " + cabName);
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.println("Distance: " + distance + " km");
            System.out.println("Rate: Rs." + rate + "/km");
            System.out.println("Total Fare: Rs." + fare);

            System.out.println("\n1. Confirm & Share OTP");
            System.out.println("2. Cancel Ride");
            System.out.print("Enter choice: ");
            int confirm = sc.nextInt();

            if (confirm == 2) {
                System.out.println("Ride cancelled. No OTP shared.");
            } else if (confirm == 1) {
                int otp = 1000 + (int)(Math.random() * 9000);
                System.out.println("Your OTP is: " + otp);
                System.out.println("Booking confirmed!");
                System.out.println("Ride cannot be cancelled after OTP sharing.");
            } else {
                System.out.println("Invalid choice. No OTP shared.");
            }
        }

        sc.close();
    }
}
