package Presentation;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class EmployeePage extends JPanel implements Observer {
    DefaultListModel obsListItems = new DefaultListModel();
    private JList obsList = new JList(obsListItems);

    public EmployeePage() {
        JFrame frame = new JFrame("Employee");
        //frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        //construct preComponents


        //construct components

        //adjust size and set layout

        frame.setSize(400, 420);
        frame.setLayout(null);

        //add components
        frame.add(obsList);

        //set component bounds (only needed by Absolute Positioning)
        obsList.setBounds(10, 20, 365, 350);
    }

    @Override
    public void update(Observable o, Object arg) {
        obsListItems.addElement((String) arg);
    }
}
