package BusinessLayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface IDeliveryServiceProcessing {
    public HashSet<BaseProduct> importProducts() throws IOException, ClassNotFoundException;

    public HashSet<BaseProduct> findProductsByName(String name) throws IOException, ClassNotFoundException;

    public HashSet<BaseProduct> findProductsByRating(Double rating) throws IOException, ClassNotFoundException;

    public HashSet<BaseProduct> findProductsByCalories(Double calories) throws IOException, ClassNotFoundException;

    public HashSet<BaseProduct> findProductsByProteins(Double proteins) throws IOException, ClassNotFoundException;

    public HashSet<BaseProduct> findProductsByFats(Double fats) throws IOException, ClassNotFoundException;

    public HashSet<BaseProduct> findProductsBySodium(Double sodium) throws IOException, ClassNotFoundException;

    public HashSet<BaseProduct> findProductsByPrice(Double price) throws IOException, ClassNotFoundException;

    public void generateCompositeProduct(String name, List<BaseProduct> products);

    public void addProduct(BaseProduct product) throws IOException, ClassNotFoundException;

    public void removeProduct(String name) throws IOException, ClassNotFoundException;

    public void updateProduct(BaseProduct product) throws IOException, ClassNotFoundException;

    public List<BaseProduct> createOrder(Client client, List<BaseProduct> baseProducts);

    public void addClient(Client clients) throws IOException, ClassNotFoundException;

    public Client findClient(String username, String password) throws IOException, ClassNotFoundException;

    public void generateTimeReport(int firstHour, int secondHour) throws IOException, ClassNotFoundException;

    public void generateProductReport(int dayNumber) throws IOException, ClassNotFoundException;

    public void generateNumberReport(int dayNumber) throws IOException, ClassNotFoundException;

    public void generateClientsReport(double minPrice, int numberOfTimes) throws IOException, ClassNotFoundException;
}
