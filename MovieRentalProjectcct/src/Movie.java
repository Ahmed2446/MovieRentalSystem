public class Movie {
    private String title;
    private double price;

    // Constructor to create a new Movie
    public Movie(String title, double price) {
        this.title = title;
        this.price = price;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Override the toString method for easy printing of Movie details
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}