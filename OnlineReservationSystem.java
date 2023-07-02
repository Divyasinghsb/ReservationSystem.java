import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Train {
    private String trainName;
    private String source;
    private String destination;

    public Train(String trainName, String source, String destination) {
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}

class ReservationSystem {
    private Map<String, Train> trains;
    private Map<String, String> users;

    public ReservationSystem() {
        trains = new HashMap<>();
        users = new HashMap<>();
        // Adding some sample trains
        trains.put("123", new Train("Express 123", "City A", "City B"));
        trains.put("456", new Train("Superfast 456", "City C", "City D"));
        trains.put("789", new Train("Local 789", "City E", "City F"));
    }

    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return false;
        }
        users.put(username, password);
        System.out.println("Registration successful!");
        return true;
    }

    public boolean loginUser(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!");
            return true;
        }
        System.out.println("Invalid username or password!");
        return false;
    }

    public void displayTrainDetails() {
        System.out.println("Available Trains:");
        System.out.println("Train Number\tTrain Name\tSource\t\tDestination");
        for (Map.Entry<String, Train> entry : trains.entrySet()) {
            Train train = entry.getValue();
            System.out.println(entry.getKey() + "\t\t" + train.getTrainName() + "\t" + train.getSource() + "\t\t" + train.getDestination());
        }
    }

    public void cancelReservation(String username) {
        // Perform cancellation logic here
        System.out.println("Reservation cancelled for user: " + username);
    }
}

public class OnlineReservationSystem {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*** Online Reservation System ***");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Display Train Details");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String registerUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String registerPassword = scanner.nextLine();
                    reservationSystem.registerUser(registerUsername, registerPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (reservationSystem.loginUser(loginUsername, loginPassword)) {
                        while (true) {
                            System.out.println("\n*** Welcome, " + loginUsername + "! ***");
                            System.out.println("1. Display Train Details");
                            System.out.println("2. Cancel Reservation");
                            System.out.println("3. Logout");
                            System.out.print("Enter your choice: ");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character

                            switch (userChoice) {
                                case 1:
                                    reservationSystem.displayTrainDetails();
                                    break;
                                case 2:
                                    reservationSystem.cancelReservation(loginUsername);
                                    break;
                                case 3:
                                    System.out.println("Logged out successfully!");
                                    break;
                                default:
                                    System.out.println("Invalid choice!");
                                    break;
                            }

                            if (userChoice == 3) {
                                break; // Exit the inner loop and go back to the main menu
                            }
                        }
                    }
                    break;
                case 3:
                    reservationSystem.displayTrainDetails();
                    break;
                case 4:
                    System.out.print("Enter username: ");
                    String cancelUsername = scanner.nextLine();
                    reservationSystem.cancelReservation(cancelUsername);
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
