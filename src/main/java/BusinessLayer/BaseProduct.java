package BusinessLayer;


import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {
    private String title;
    private double rating;
    private double calories;
    private double proteins;
    private double fats;
    private double sodium;
    private double price;

    public BaseProduct(String title, double rating, double calories, double proteins, double fats, double sodium, double price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.sodium = sodium;
        this.price = price;
    }

    public BaseProduct() {
    }

    @Override
    public String toString() {
        return "BaseProduct{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }

    @Override
    public double computePrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean equals(Object o) {
        /*if (o == null || getClass() != o.getClass()) return false;
        return (((BaseProduct) o).getTitle()).equals(this.getTitle());*/
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseProduct that = (BaseProduct) o;
        return Double.compare(that.getRating(), getRating()) == 0 && getCalories() == that.getCalories() && getProteins() == that.getProteins() && getFats() == that.getFats() && getSodium() == that.getSodium() && getPrice() == that.getPrice() && getTitle().equals(that.getTitle());

    }

    public int hashCode() {
        return getTitle().hashCode();
    }
}
