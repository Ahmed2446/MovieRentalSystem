
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class MovieRecommendation {
	
	 public List<Movie> recommendMovies(User user) {
	        List<Rental> recentRentals = user.getRecentRentals(5);
	        List<Movie> recommendedMovies = new ArrayList<>();

	        if (!recentRentals.isEmpty()) {
	            LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);

	            // Filter rentals within the last 5 minutes
	            List<Rental> recentRentalsWithin5Minutes = recentRentals.stream()
	                    .filter(rental -> rental.getRentalTime().isAfter(fiveMinutesAgo))
	                    .collect(Collectors.toList());

	            // Count the occurrences of each movie
	            Map<Movie, Long> movieCountMap = recentRentalsWithin5Minutes.stream()
	                    .collect(Collectors.groupingBy(Rental::getMovie, Collectors.counting()));

	            // Sort movies by the number of rentals in descending order
	            List<Map.Entry<Movie, Long>> sortedMovies = new ArrayList<>(movieCountMap.entrySet());
	            sortedMovies.sort((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()));

	            // Take the top 5 most rented movies
	            int moviesToRecommend = Math.min(sortedMovies.size(), 5);
	            for (int i = 0; i < moviesToRecommend; i++) {
	                recommendedMovies.add(sortedMovies.get(i).getKey());
	            }
	        }

	        return recommendedMovies;
	    }

}
