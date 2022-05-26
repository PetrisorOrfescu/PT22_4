package BusinessLayer;

import java.io.Serializable;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable {
    private String title;
    private List<BaseProduct> menu;

    @Override
    public double computePrice() {
        double price = 0;
        for (BaseProduct prod : menu) {
            price += prod.getPrice();
        }
        return price;
    }


    public CompositeProduct(String title, List<BaseProduct> menu) {
        this.title = title;
        this.menu = menu;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public double getRating() {
        double rat = 0;
        for (BaseProduct baseProduct : menu) {
            rat += baseProduct.getRating();
        }
        return rat /= menu.size();
    }

    @Override
    public double getCalories() {
        double rat = 0;
        for (BaseProduct baseProduct : menu) {
            rat += baseProduct.getCalories();
        }
        return rat;
    }

    @Override
    public double getProteins() {
        double rat = 0;
        for (BaseProduct baseProduct : menu) {
            rat += baseProduct.getProteins();
        }
        return rat;
    }

    @Override
    public double getFats() {
        double rat = 0;
        for (BaseProduct baseProduct : menu) {
            rat += baseProduct.getFats();
        }
        return rat;
    }

    @Override
    public double getSodium() {
        double rat = 0;
        for (BaseProduct baseProduct : menu) {
            rat += baseProduct.getSodium();
        }
        return rat;
    }

    @Override
    public double getPrice() {
        double rat = 0;
        for (BaseProduct baseProduct : menu) {
            rat += baseProduct.getPrice();
        }
        return rat;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BaseProduct> getMenu() {
        return menu;
    }

    public void setMenu(List<BaseProduct> menu) {
        this.menu = menu;
    }

}
