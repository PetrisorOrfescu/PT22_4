package BusinessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {

    public abstract double computePrice();

    public abstract String getTitle();

    public abstract double getRating();

    public abstract double getCalories();

    public abstract double getProteins();

    public abstract double getFats();

    public abstract double getSodium();

    public abstract double getPrice();


}
