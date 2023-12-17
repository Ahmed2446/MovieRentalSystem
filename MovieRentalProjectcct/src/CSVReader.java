

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CSVReader {
    public List<Movie> readMoviesFromCSV(String fileName) {
        List<Movie> movies = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            // Pattern for CSV parsing, accounting for commas inside quotes
            Pattern pattern = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            // Skip the header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Split the line into values
                String[] values = pattern.split(line, -1);

                if (values.length > 10) {
                    // Assuming the last column is the price and one before last is the title
                    String title = values[values.length - 4].trim().replaceAll("^\"|\"$", ""); // Remove surrounding quotes
                    double price = Double.parseDouble(values[values.length - 1].trim());
                    movies.add(new Movie(title, price));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return movies;
    }
}