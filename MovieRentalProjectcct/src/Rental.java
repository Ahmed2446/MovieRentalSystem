import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Rental {
    private User user;
    private Movie movie;
    private LocalDateTime rentalTime;

    // Constructor to create a new Rental
    public Rental(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
        this.rentalTime = LocalDateTime.now(); // Set the rental time to the current time
    }

	public User getUser() {
		return user;
	}



	public Movie getMovie() {
		return movie;
	}


	public LocalDateTime getRentalTime() {
		return rentalTime;
	}
	
	// based on the rental time if the movie is rented for more than 1 minute then remove it from the list
    public boolean isExpired() {
        // Check if the rental is expired (1 minute limit in this example)
        LocalDateTime expirationTime = rentalTime.plus(1, ChronoUnit.MINUTES);
        return LocalDateTime.now().isAfter(expirationTime);
    }
	
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Objects.equals(user, rental.user) && Objects.equals(movie, rental.movie) && Objects.equals(rentalTime, rental.rentalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, movie, rentalTime);
    }
	
	// Override the toString method for easy printing of Rental details
    @Override
    public String toString() {
        return "Rental{" +
                "user=" + user.getEmail() +
                ", movie=" + movie.getTitle() +
                ", rentalTime=" + rentalTime +
                '}';
    }
    
}
