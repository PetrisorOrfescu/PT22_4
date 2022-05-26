package Presentation;

import BusinessLayer.BaseProduct;
import BusinessLayer.Client;
import BusinessLayer.CompositeProduct;
import BusinessLayer.DeliveryService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletionService;

public class Controller {
    public Controller() {
        DeliveryService service = new DeliveryService();
        ClientPage clientPage = new ClientPage();
        AdminPage adminPage = new AdminPage();
        final Client[] currentClient = new Client[1];
        //adminPage.setVisible(true);
        //clientPage.setVisible(true);
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
        List<BaseProduct> orderedItems = new ArrayList<>();
        loginPage.jButton3ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, passWord;
                name = loginPage.getjTextField1().getText();
                passWord = loginPage.getjPasswordField1().getText();
                Client client = new Client(name, passWord);
                try {
                    service.addClient(client);
                    System.out.println(service.getClients());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        loginPage.jButton1ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, passWord;
                name = loginPage.getjTextField1().getText();
                passWord = loginPage.getjPasswordField1().getText();
                if (name.equals("admin") && passWord.equals("admin")) {
                    adminPage.setVisible(true);
                } else {
                    try {
                        currentClient[0] = service.findClient(name, passWord);
                        if (currentClient[0] == null)
                            JOptionPane.showMessageDialog(null, "Client not found");
                        else
                            clientPage.setVisible(true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        clientPage.jButton2ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int menuNumber = Integer.parseInt(clientPage.getjTextField7().getText());
                if (menuNumber > service.getCompositeProducts().size())
                    JOptionPane.showMessageDialog(null, "Incorrect menu number");
                else {
                    int i = 0;
                    for (CompositeProduct compositeProduct : service.getCompositeProducts()) {
                        if (i == menuNumber) {
                            DefaultTableModel tableModel = (DefaultTableModel) clientPage.getjTable2().getModel();
                            for (BaseProduct baseProduct : compositeProduct.getMenu()) {
                                orderedItems.add(baseProduct);
                                tableModel.addRow(new Object[]{baseProduct.getTitle(), String.valueOf(baseProduct.getPrice())});

                            }
                            break;
                        } else {
                            i++;
                        }

                    }
                }
            }
        });
        clientPage.jButton1ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                HashSet<BaseProduct> found = new HashSet<>();
                String name, rating, calories, proteins, fats, sodium, price;
                name = clientPage.getjTextField8().getText();
                rating = clientPage.getjTextField1().getText();
                calories = clientPage.getjTextField2().getText();
                proteins = clientPage.getjTextField3().getText();
                fats = clientPage.getjTextField4().getText();
                sodium = clientPage.getjTextField5().getText();
                price = clientPage.getjTextField6().getText();


                if (name.isEmpty() && rating.isEmpty() && calories.isEmpty() && proteins.isEmpty() && fats.isEmpty() && sodium.isEmpty() && price.isEmpty()) {
                    try {
                        service.importProducts();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        found = service.importProducts();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    if (!name.isEmpty()) {
                        try {
                            found = service.findProductsByName(name);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!rating.isEmpty()) {
                        try {
                            found = service.findProductsByRating(Double.parseDouble(rating));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!calories.isEmpty()) {
                        try {
                            found = service.findProductsByCalories(Double.parseDouble(calories));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!proteins.isEmpty()) {
                        try {
                            found = service.findProductsByProteins(Double.parseDouble(proteins));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!fats.isEmpty()) {
                        try {
                            found = service.findProductsByFats(Double.parseDouble(fats));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!sodium.isEmpty()) {
                        try {
                            found = service.findProductsBySodium(Double.parseDouble(sodium));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!price.isEmpty()) {
                        try {
                            found = service.findProductsByPrice(Double.parseDouble(price));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }


                }
                DefaultTableModel tbl1Model = (DefaultTableModel) clientPage.getjTable1().getModel();
                tbl1Model.setRowCount(0);
                for (BaseProduct product : found) {
                    tbl1Model.addRow(new Object[]{product.getTitle(), String.valueOf(product.getRating()), String.valueOf(product.getCalories()), String.valueOf(product.getProteins()), String.valueOf(product.getFats()), String.valueOf(product.getSodium()), String.valueOf(product.getPrice())});
                }
                int i = 0;
                try {
                    for (CompositeProduct prod : service.getCompositeProducts()) {
                        clientPage.getjTextArea2().append("Menu no:" + i + "\n");
                        clientPage.getjTextArea2().append(prod.getTitle() + "\n");
                        for (BaseProduct baseProduct : prod.getMenu())
                            clientPage.getjTextArea2().append(baseProduct.getTitle() + "\n");
                        clientPage.getjTextArea2().append("Rating: " + prod.getRating() + "\n");
                        clientPage.getjTextArea2().append("Calories: " + prod.getCalories() + "\n");
                        clientPage.getjTextArea2().append("Proteins: " + prod.getProteins() + "\n");
                        clientPage.getjTextArea2().append("Fats: " + prod.getFats() + "\n");
                        clientPage.getjTextArea2().append("Sodium: " + prod.getSodium() + "\n");
                        clientPage.getjTextArea2().append("Price: " + prod.getPrice() + "\n\n");

                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }

        });
        clientPage.jButton4ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DefaultTableModel tbl1Model = (DefaultTableModel) clientPage.getjTable1().getModel();
                    int i = clientPage.getjTable1().getSelectedRow();
                    String name = tbl1Model.getValueAt(i, 0).toString();
                    String price = tbl1Model.getValueAt(i, 6).toString();
                    DefaultTableModel tbl2Model = (DefaultTableModel) clientPage.getjTable2().getModel();
                    tbl2Model.addRow(new Object[]{name, price});

                    BaseProduct product = new BaseProduct();
                    product.setTitle(tbl1Model.getValueAt(i, 0).toString());
                    product.setRating(Double.parseDouble(tbl1Model.getValueAt(i, 1).toString()));
                    product.setRating(Double.parseDouble(tbl1Model.getValueAt(i, 2).toString()));
                    product.setProteins(Double.parseDouble(tbl1Model.getValueAt(i, 3).toString()));
                    product.setFats(Double.parseDouble(tbl1Model.getValueAt(i, 4).toString()));
                    product.setSodium(Double.parseDouble(tbl1Model.getValueAt(i, 5).toString()));
                    product.setPrice(Double.parseDouble(tbl1Model.getValueAt(i, 6).toString()));

                    orderedItems.add(product);


                } catch (ArrayIndexOutOfBoundsException exception1) {
                    JOptionPane.showMessageDialog(null, "No item to add");
                }
            }
        });
        clientPage.jButton3ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = clientPage.getjTable2().getSelectedRow();
                DefaultTableModel tbl2Model = (DefaultTableModel) clientPage.getjTable2().getModel();
                try {

                    orderedItems.removeIf(baseProduct -> baseProduct.getTitle().equals(tbl2Model.getValueAt(i, 0)));
                    tbl2Model.removeRow(i);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    JOptionPane.showMessageDialog(null, "No item to remove");
                }
            }
        });
        clientPage.jButton5ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(orderedItems.toString());
                service.createOrder(currentClient[0], orderedItems);
                DefaultTableModel tbl2Model = (DefaultTableModel) clientPage.getjTable2().getModel();
                tbl2Model.setRowCount(0);
                DefaultTableModel tblModel = (DefaultTableModel) clientPage.getjTable1().getModel();
                tblModel.setRowCount(0);
            }
        });
        adminPage.jButton8ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                HashSet<BaseProduct> found = new HashSet<>();
                String name, rating, calories, proteins, fats, sodium, price;
                name = adminPage.getjTextField8().getText();
                rating = adminPage.getjTextField1().getText();
                calories = adminPage.getjTextField2().getText();
                proteins = adminPage.getjTextField3().getText();
                fats = adminPage.getjTextField4().getText();
                sodium = adminPage.getjTextField5().getText();
                price = adminPage.getjTextField6().getText();


                if (name.isEmpty() && rating.isEmpty() && calories.isEmpty() && proteins.isEmpty() && fats.isEmpty() && sodium.isEmpty() && price.isEmpty()) {
                    try {
                        service.importProducts();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        found = service.importProducts();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    if (!name.isEmpty()) {
                        try {
                            found = service.findProductsByName(name);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!rating.isEmpty()) {
                        try {
                            found = service.findProductsByRating(Double.parseDouble(rating));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!calories.isEmpty()) {
                        try {
                            found = service.findProductsByCalories(Double.parseDouble(calories));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!proteins.isEmpty()) {
                        try {
                            found = service.findProductsByProteins(Double.parseDouble(proteins));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!fats.isEmpty()) {
                        try {
                            found = service.findProductsByFats(Double.parseDouble(fats));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!sodium.isEmpty()) {
                        try {
                            found = service.findProductsBySodium(Double.parseDouble(sodium));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!price.isEmpty()) {
                        try {
                            found = service.findProductsByPrice(Double.parseDouble(price));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }


                }
                DefaultTableModel tbl1Model = (DefaultTableModel) adminPage.getjTable1().getModel();
                tbl1Model.setRowCount(0);
                for (BaseProduct product : found) {
                    tbl1Model.addRow(new Object[]{product.getTitle(), String.valueOf(product.getRating()), String.valueOf(product.getCalories()), String.valueOf(product.getProteins()), String.valueOf(product.getFats()), String.valueOf(product.getSodium()), String.valueOf(product.getPrice())});
                }
            }


        });
        adminPage.jButton4ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DefaultTableModel tbl1Model = (DefaultTableModel) adminPage.getjTable1().getModel();
                    int i = adminPage.getjTable1().getSelectedRow();
                    DefaultTableModel tbl2Model = (DefaultTableModel) adminPage.getjTable3().getModel();
                    tbl2Model.addRow(new Object[]{tbl1Model.getValueAt(i, 0).toString(), tbl1Model.getValueAt(i, 1).toString(), tbl1Model.getValueAt(i, 2).toString(), tbl1Model.getValueAt(i, 3).toString(), tbl1Model.getValueAt(i, 4).toString(), tbl1Model.getValueAt(i, 5).toString(), tbl1Model.getValueAt(i, 6).toString()});
                } catch (ArrayIndexOutOfBoundsException exception1) {
                    JOptionPane.showMessageDialog(null, "No item to add");
                }
            }
        });
        adminPage.jButton3ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = adminPage.getjTable3().getSelectedRow();
                DefaultTableModel tbl2Model = (DefaultTableModel) adminPage.getjTable3().getModel();
                try {
                    tbl2Model.removeRow(i);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    JOptionPane.showMessageDialog(null, "No item to remove");
                }
            }
        });
        adminPage.jButton5ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tbl2Model = (DefaultTableModel) adminPage.getjTable3().getModel();
                try {
                    List<BaseProduct> baseProducts = new ArrayList<>();
                    for (int j = 0; j < tbl2Model.getRowCount(); j++) {
                        String name = tbl2Model.getValueAt(j, 0).toString();
                        double rating = Double.parseDouble(tbl2Model.getValueAt(j, 1).toString()),
                                calories = Double.parseDouble(tbl2Model.getValueAt(j, 2).toString()),
                                proteins = Double.parseDouble(tbl2Model.getValueAt(j, 3).toString()),
                                fats = Double.parseDouble(tbl2Model.getValueAt(j, 4).toString()),
                                sodium = Double.parseDouble(tbl2Model.getValueAt(j, 5).toString()),
                                price = Double.parseDouble(tbl2Model.getValueAt(j, 6).toString());
                        BaseProduct product = new BaseProduct(name, rating, calories, proteins, fats, sodium, price);
                        baseProducts.add(product);
                    }
                    String compName = adminPage.getjTextField9().getText();
                    service.generateCompositeProduct(compName, baseProducts);
                    int i = 1;
                    for (CompositeProduct prod : service.getCompositeProducts()) {
                        adminPage.getjTextArea2().append("Menu no:" + i + "\n");
                        adminPage.getjTextArea2().append(prod.getTitle() + "\n");
                        for (BaseProduct baseProduct : prod.getMenu())
                            adminPage.getjTextArea2().append(baseProduct.getTitle() + "\n");
                        adminPage.getjTextArea2().append("Rating: " + prod.getRating() + "\n");
                        adminPage.getjTextArea2().append("Calories: " + prod.getCalories() + "\n");
                        adminPage.getjTextArea2().append("Proteins: " + prod.getProteins() + "\n");
                        adminPage.getjTextArea2().append("Fats: " + prod.getFats() + "\n");
                        adminPage.getjTextArea2().append("Sodium: " + prod.getSodium() + "\n");
                        adminPage.getjTextArea2().append("Price: " + prod.getPrice() + "\n\n");

                    }
                    adminPage.getjTextField9().setText("");
                    tbl2Model.setRowCount(0);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    JOptionPane.showMessageDialog(null, "No item to remove");
                }
            }
        });
        adminPage.jButton7ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = adminPage.getjTextField8().getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please insert the name of the product to remove");
                } else {
                    try {
                        service.removeProduct(name);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        adminPage.jButton1ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, rating, calories, proteins, fats, sodium, price;
                name = adminPage.getjTextField8().getText();
                rating = adminPage.getjTextField1().getText();
                calories = adminPage.getjTextField2().getText();
                proteins = adminPage.getjTextField3().getText();
                fats = adminPage.getjTextField4().getText();
                sodium = adminPage.getjTextField5().getText();
                price = adminPage.getjTextField6().getText();


                if (name.isEmpty() && rating.isEmpty() || calories.isEmpty() || proteins.isEmpty() || fats.isEmpty() || sodium.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Insert valid data");
                } else {

                    System.out.println(name + " " + rating + " " + calories + " " + proteins + " " + fats + " " + sodium + " " + price);
                    BaseProduct produsnou = new BaseProduct(name, Double.parseDouble(rating), Double.parseDouble(calories), Double.parseDouble(proteins),
                            Double.parseDouble(fats), Double.parseDouble(sodium), Double.parseDouble(price));
                    try {
                        service.addProduct(produsnou);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        System.out.println(service.findProductsByName("Mihalti"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }


            }
        });
        adminPage.jButton6ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rating, calories, proteins, fats, sodium, price;
                //name = adminPage.getjTextField8().getText();
                rating = adminPage.getjTextField1().getText();
                calories = adminPage.getjTextField2().getText();
                proteins = adminPage.getjTextField3().getText();
                fats = adminPage.getjTextField4().getText();
                sodium = adminPage.getjTextField5().getText();
                price = adminPage.getjTextField6().getText();

                DefaultTableModel tbl1Model = (DefaultTableModel) adminPage.getjTable1().getModel();
                int i = adminPage.getjTable1().getSelectedRow();
                try {
                    BaseProduct product = new BaseProduct();
                    product.setTitle(tbl1Model.getValueAt(i, 0).toString());

                    if (rating.isEmpty())
                        product.setRating(Double.parseDouble(tbl1Model.getValueAt(i, 1).toString()));
                    else
                        product.setRating(Double.parseDouble(rating));

                    if (calories.isEmpty())
                        product.setRating(Double.parseDouble(tbl1Model.getValueAt(i, 2).toString()));
                    else
                        product.setCalories(Double.parseDouble(calories));

                    if (proteins.isEmpty())
                        product.setProteins(Double.parseDouble(tbl1Model.getValueAt(i, 3).toString()));
                    else
                        product.setProteins(Double.parseDouble(proteins));

                    if (fats.isEmpty())
                        product.setFats(Double.parseDouble(tbl1Model.getValueAt(i, 4).toString()));
                    else
                        product.setFats(Double.parseDouble(fats));

                    if (sodium.isEmpty())
                        product.setSodium(Double.parseDouble(tbl1Model.getValueAt(i, 5).toString()));
                    else
                        product.setSodium(Double.parseDouble(sodium));

                    if (price.isEmpty())
                        product.setPrice(Double.parseDouble(tbl1Model.getValueAt(i, 6).toString()));
                    else
                        product.setPrice(Double.parseDouble(price));
                    try {
                        System.out.println(product.toString());
                        service.updateProduct(product);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } catch (IndexOutOfBoundsException ex) {
                    ex.printStackTrace();
                }

            }
        });
        adminPage.jButton9ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    service.generateTimeReport(0, 24);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        adminPage.jButton12ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    service.generateProductReport(27);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        adminPage.jButton10ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    service.generateNumberReport(1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        adminPage.jButton11ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    service.generateClientsReport(1, 1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
