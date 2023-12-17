import java.util.ArrayList;
import java.util.Scanner;

public class LoginSystem {

    private ArrayList<User> users;
    private User loggedInUser; // Keep track of the currently logged-in user
    private Scanner scanner;

  
    public LoginSystem() {
        users = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Method to allow user to make an account and also checks user is already made in the system
    public void register() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Check if user already exists
        for (User user : users) {
            if (user.validateLogin(email, password)) {
                System.out.println("A user with this email already exists.");
                return;
            }
        }

        users.add(new User(email, password));
        System.out.println("Registration successful!");
    }

    // Method to allow the user to login with initials 
    public boolean login() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.validateLogin(email, password)) {
                loggedInUser = user; // Set the logged-in user
                System.out.println("Login successful!");
                return true;
            }
        }
        System.out.println("Login failed!");
        return false;
    }
    // logout session
    public User getLoggedInUser() {
        return loggedInUser;
    }
    // logged in session
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }
    
    // adding rental movies 
    public void addRental(Rental rental) {
        if (isLoggedIn()) {
            loggedInUser.rentMovie(rental);
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
