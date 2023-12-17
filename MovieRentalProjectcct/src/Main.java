import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginSystem loginSystem = new LoginSystem();
        boolean isRunning = true;

        while (isRunning) {
        	// Menu
        	
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Rent a Movie");
            System.out.println("4. Display Rented Movies");
            System.out.println("5. Recommended Movies");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    loginSystem.register();
                    break;
                case 2:
                    if (loginSystem.login()) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Login failed!");
                    }
                    break;
                case 3:
                    if (loginSystem.isLoggedIn()) {
                        rentMovie(loginSystem);
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;
                case 4:
                    if (loginSystem.isLoggedIn()) {
                        displayRentedMovies(loginSystem.getLoggedInUser());
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;
                case 5:
                	 if (loginSystem.isLoggedIn()) {
                         recommendMovies(loginSystem.getLoggedInUser());
                     } else {
                         System.out.println("Please log in first.");
                     }
                    break;
                case 6:
                	 System.out.println("Exiting the application.");
                     isRunning = false;
                     break;
                	
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    
    // method which allows the user to rent a movie
    
    private static void rentMovie(LoginSystem loginSystem) {
    	
    	// Reading Movie_Metadata.csv file for the movies  and printing them 
    	
        String csvFilePath = "Movie_Metadata.csv";
        CSVReader csvReader = new CSVReader();
        List<Movie> movies = csvReader.readMoviesFromCSV(csvFilePath);
        System.out.println("Available movies:");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        
        
        // Taking input from user to choose a movie 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie you want to rent:");
        String movieTitle = scanner.nextLine();
        
        // filtering for the title that user gave from the list and adding it as an object to users temporary storage
        
        Movie selectedMovie = movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(movieTitle))
                .findFirst()
                .orElse(null);

        if (selectedMovie == null) {
            System.out.println("Movie not found.");
        } else {
        	User user = loginSystem.getLoggedInUser();
            Rental rental = new Rental(user, selectedMovie);
            System.out.println("Movie rented: " + rental);
            loginSystem.addRental(rental);
        }
    }
    
    
    // method to display all the available movies in the csv file

    private static void displayRentedMovies(User user) {
        System.out.println("Movies rented by " + user.getEmail() + ":");
        for (Rental rental : user.getRentals()) {
        	 if (!rental.isExpired()) {
                 System.out.println(rental);
             }
        }
    }
    
    // Method to recommend movies based on the previous rentals 
    private static void recommendMovies(User user) {
        MovieRecommendation movieRecommendation = new MovieRecommendation();
        List<Movie> recommendedMovies = movieRecommendation.recommendMovies(user);
        System.out.println("Recommended movies based on your last selections:");
        for (Movie recommendedMovie : recommendedMovies) {
            System.out.println(recommendedMovie);
        }
    }
}
