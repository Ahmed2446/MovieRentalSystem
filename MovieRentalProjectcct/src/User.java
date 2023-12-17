import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String password;
    private List<Rental> rentals;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.rentals = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    // method to validate the login 
    public boolean validateLogin(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void rentMovie(Rental rental) {
        rentals.add(rental);
    }
    // returns the recently rented movies
    public List<Rental> getRecentRentals(int count) {
        int rentalsCount = Math.min(count, rentals.size());
        return new ArrayList<>(rentals.subList(rentals.size() - rentalsCount, rentals.size()));
    }
}
