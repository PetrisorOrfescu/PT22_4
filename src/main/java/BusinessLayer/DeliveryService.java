package BusinessLayer;

import Presentation.EmployeePage;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.FileWriter;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    HashSet<BaseProduct> baseProducts = new HashSet<>();
    List<CompositeProduct> compositeProducts = new ArrayList<>();
    HashMap<Order, List<BaseProduct>> orders = new HashMap<>();
    List<Client> clients = new ArrayList<>();
    List<Observer> e = new ArrayList<>();

    public List<Client> getClients() {
        return clients;
    }

    int ordersId = 0;

    public HashSet<BaseProduct> getBaseProducts() {
        return baseProducts;
    }

    public void setBaseProducts(HashSet<BaseProduct> baseProducts) {
        this.baseProducts = baseProducts;
    }

    public List<CompositeProduct> getCompositeProducts() {
        return compositeProducts;
    }

    public void setCompositeProducts(List<CompositeProduct> compositeProducts) {
        this.compositeProducts = compositeProducts;
    }

    @Override
    public HashSet<BaseProduct> importProducts() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.ser"));
        //Set<String> aNewSet = (HashSet<String>) ois.readObject();
        HashSet<BaseProduct> baseProductsName = new HashSet<>();
        if (baseProductsName.isEmpty()) {
            Path fileName = Path.of("products.csv");
            //baseProducts = new HashSet<>();
            try {
                baseProducts = (HashSet<BaseProduct>) Files.lines(fileName)
                        .skip(1)
                        .map(line -> {
                                    String[] baseProductsFields = line.split(",");
                                    return new BaseProduct(baseProductsFields[0], Double.parseDouble(baseProductsFields[1]), Double.parseDouble(baseProductsFields[2]), Double.parseDouble(baseProductsFields[3]), Double.parseDouble(baseProductsFields[4]), Double.parseDouble(baseProductsFields[5]), Double.parseDouble(baseProductsFields[6]));
                                }

                        ).collect(Collectors.toSet());
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products.ser"));
                oos.writeObject(baseProducts);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else baseProducts = baseProductsName;
        return baseProducts;
    }

    @Override
    public HashSet<BaseProduct> findProductsByName(String name) throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.ser"));
        //Set<String> aNewSet = (HashSet<String>) ois.readObject();
        HashSet<BaseProduct> baseProductsName = new HashSet<>();
        baseProductsName = (HashSet<BaseProduct>) ois.readObject();

        HashSet<BaseProduct> foundItems = new HashSet<>();
        for (BaseProduct product : baseProductsName) {
            if (product.getTitle().contains(name))
                foundItems.add(product);
        }

        return foundItems;
    }

    @Override
    public HashSet<BaseProduct> findProductsByRating(Double rating) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.ser"));
        //Set<String> aNewSet = (HashSet<String>) ois.readObject();
        baseProducts = (HashSet<BaseProduct>) ois.readObject();

        HashSet<BaseProduct> foundItems = new HashSet<>();
        for (BaseProduct product : baseProducts) {
            if (product.getRating() == rating)
                foundItems.add(product);
        }

        return foundItems;
    }

    @Override
    public HashSet<BaseProduct> findProductsByCalories(Double calories) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.ser"));
        //Set<String> aNewSet = (HashSet<String>) ois.readObject();
        baseProducts = (HashSet<BaseProduct>) ois.readObject();

        HashSet<BaseProduct> foundItems = new HashSet<>();
        for (BaseProduct product : baseProducts) {
            if (product.getCalories() == calories)
                foundItems.add(product);
        }

        return foundItems;
    }

    @Override
    public HashSet<BaseProduct> findProductsByProteins(Double proteins) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.ser"));
        //Set<String> aNewSet = (HashSet<String>) ois.readObject();
        baseProducts = (HashSet<BaseProduct>) ois.readObject();

        HashSet<BaseProduct> foundItems = new HashSet<>();
        for (BaseProduct product : baseProducts) {
            if (product.getProteins() == proteins)
                foundItems.add(product);
        }

        return foundItems;
    }

    @Override
    public HashSet<BaseProduct> findProductsByFats(Double fats) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.ser"));
        //Set<String> aNewSet = (HashSet<String>) ois.readObject();
        baseProducts = (HashSet<BaseProduct>) ois.readObject();

        HashSet<BaseProduct> foundItems = new HashSet<>();
        for (BaseProduct product : baseProducts) {
            if (product.getFats() == fats)
                foundItems.add(product);
        }

        return foundItems;
    }

    @Override
    public HashSet<BaseProduct> findProductsBySodium(Double sodium) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.ser"));
        //Set<String> aNewSet = (HashSet<String>) ois.readObject();
        baseProducts = (HashSet<BaseProduct>) ois.readObject();

        HashSet<BaseProduct> foundItems = new HashSet<>();
        for (BaseProduct product : baseProducts) {
            if (product.getSodium() == sodium)
                foundItems.add(product);
        }

        return foundItems;
    }

    @Override
    public HashSet<BaseProduct> findProductsByPrice(Double price) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.ser"));
        //Set<String> aNewSet = (HashSet<String>) ois.readObject();
        baseProducts = (HashSet<BaseProduct>) ois.readObject();

        HashSet<BaseProduct> foundItems = new HashSet<>();
        for (BaseProduct product : baseProducts) {
            if (product.getPrice() == price)
                foundItems.add(product);
        }

        return foundItems;
    }


    @Override
    public void generateCompositeProduct(String name, List<BaseProduct> products) {
        CompositeProduct compositeProduct = new CompositeProduct(name, products);
        compositeProducts.add(compositeProduct);
    }

    @Override
    public void addProduct(BaseProduct product) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.ser"));
        //Set<String> aNewSet = (HashSet<String>) ois.readObject();
        HashSet<BaseProduct> baseProductsAdded = new HashSet<>();
        assert baseProductsAdded != null;
        int size = baseProductsAdded.size();
        baseProductsAdded = (HashSet<BaseProduct>) ois.readObject();
        baseProductsAdded.add(product);
        assert baseProductsAdded.size() != size;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products.ser"));
        oos.writeObject(baseProductsAdded);
    }

    @Override
    public void removeProduct(String name) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.ser"));
        //Set<String> aNewSet = (HashSet<String>) ois.readObject();
        HashSet<BaseProduct> baseProductsAdded = new HashSet<>();
        baseProductsAdded = (HashSet<BaseProduct>) ois.readObject();
        baseProductsAdded.removeIf(b -> b.getTitle().contains(name));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products.ser"));
        oos.writeObject(baseProductsAdded);

    }

    @Override
    public void updateProduct(BaseProduct product) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.ser"));
        //Set<String> aNewSet = (HashSet<String>) ois.readObject();
        HashSet<BaseProduct> baseProductsAdded = new HashSet<>();
        baseProductsAdded = (HashSet<BaseProduct>) ois.readObject();
        baseProductsAdded.removeIf(b -> b.getTitle().contains(product.getTitle()));
        baseProductsAdded.add(product);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products.ser"));
        oos.writeObject(baseProductsAdded);
    }

    @Override
    public List<BaseProduct> createOrder(Client client, List<BaseProduct> baseProducts) {
        ordersId++;
        double price = 0;
        for (BaseProduct baseProduct : baseProducts) {
            price += baseProduct.getPrice();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Order order = new Order(ordersId, client.getUsername(), now, baseProducts, price);
        orders.put(order, baseProducts);

        EmployeePage e1 = new EmployeePage();
        e.add(e1);
        String x = "Order with id: " + order.getOrderId() + " was palced by: " + order.getClientUsername() + "\n";
        e.get(0).update(this, x);

        try {
            String filename = "Bill.txt";
            FileWriter fileWriter = new FileWriter(filename);
            //PrintWriter printWriter = new PrintWriter(fileWriter);

            fileWriter.write(order.toString());
            fileWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }


        return baseProducts;
    }

    @Override
    public void addClient(Client client) throws IOException, ClassNotFoundException {

        /*ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\Petrisor\\IdeaProjects\\TP-Tema4\\clients.ser"));
        clients = (List<Client>)ois.readObject();*/

        String filename = "clients.ser";
        FileInputStream file1 = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file1);

        clients = (ArrayList<Client>) in.readObject();
        //preconditie
        assert clients != null;
        int size = clients.size();
        clients.add(client);
        //postconditie
        assert clients.size() != size;


        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(clients);
        out.close();
        file.close();


    }

    @Override
    public Client findClient(String username, String password) throws IOException, ClassNotFoundException {
        String filename = "clients.ser";
        ArrayList<Client> readClients = new ArrayList<>();
        FileInputStream file1 = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file1);

        readClients = (ArrayList<Client>) in.readObject();
        for (Client cl : readClients) {
            if (cl.getUsername().equals(username) && cl.getPassword().equals(password))
                return cl;
        }
        return null;
    }

    @Override
    public void generateTimeReport(int start, int finish) throws IOException, ClassNotFoundException {
        List<Order> listOfOrders = (List<Order>) orders.keySet()
                .stream()
                .filter(x -> x.getOrderDate().getHour() >= start && x.getOrderDate().getHour() <= finish)
                .toList();
        System.out.println(orders);


        try {
            String filename = "TimeReport.txt";
            FileWriter fileWriter = new FileWriter(filename);
            //PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Order o : listOfOrders) {
                fileWriter.write(o.toString());
            }
            fileWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }

    @Override
    public void generateProductReport(int dayNumber) throws IOException, ClassNotFoundException {

        Map<String, Integer> freq = new HashMap<>();
        List<Order> listOfOrders = (List<Order>) orders.keySet()
                .stream()
                .filter(x -> x.getOrderDate().getDayOfMonth() == dayNumber)
                .toList();

        for (BaseProduct product : baseProducts) {
            freq.put(product.getTitle(), 0);
        }
        for (Order o : listOfOrders) {
            List<BaseProduct> list = o.getOrderedItems();
            for (BaseProduct baseProduct : list) {
                freq.replace(baseProduct.getTitle(), freq.get(baseProduct.getTitle()) + 1);

            }

        }
        try {
            String filename = "ProductReport.txt";
            FileWriter fileWriter = new FileWriter(filename);
            //PrintWriter printWriter = new PrintWriter(fileWriter);
            for (String o : freq.keySet()) {
                if (freq.get(o) > 0)
                    fileWriter.write(o.toString() + " " + freq.get(o) + "\n");
            }
            fileWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void generateNumberReport(int numberOfTimes) throws IOException, ClassNotFoundException {
        Map<String, Integer> freq = new HashMap<>();
        List<Order> listOfOrders = (List<Order>) orders.keySet()
                .stream()
                .toList();

        for (BaseProduct product : baseProducts) {
            freq.put(product.getTitle(), 0);
        }
        for (Order o : listOfOrders) {
            List<BaseProduct> list = o.getOrderedItems();
            for (BaseProduct baseProduct : list) {
                freq.replace(baseProduct.getTitle(), freq.get(baseProduct.getTitle()) + 1);

            }

        }
        try {
            String filename = "NumberReport.txt";
            FileWriter fileWriter = new FileWriter(filename);
            //PrintWriter printWriter = new PrintWriter(fileWriter);
            for (String o : freq.keySet()) {
                if (freq.get(o) >= numberOfTimes)
                    fileWriter.write(o.toString() + " " + freq.get(o) + "\n");
            }
            fileWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void generateClientsReport(double minPrice, int numberOfTimes) throws IOException, ClassNotFoundException {
        Map<String, Integer> freq = new HashMap<>();
        List<Order> listOfOrders = (List<Order>) orders.keySet()
                .stream()
                .filter(x -> x.getTotalPrice() >= minPrice)
                .toList();
        for (Order o : listOfOrders) {
            freq.put(o.getClientUsername(), 0);
        }

        for (Order o : listOfOrders) {
            freq.replace(o.getClientUsername(), freq.get(o.getClientUsername()) + 1);
        }

        try {
            String filename = "ClientsReport.txt";
            FileWriter fileWriter = new FileWriter(filename);
            //PrintWriter printWriter = new PrintWriter(fileWriter);
            for (String o : freq.keySet()) {
                if (freq.get(o) >= numberOfTimes)
                    fileWriter.write(o.toString() + " " + freq.get(o) + "\n");
            }
            fileWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


}
