package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;


import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridModel;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SudokuView extends JFrame {

    private GridView table;
    private JTextArea console;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");

    public SudokuView(GridModel gridModel) {

        super("Grupo 10 - Nikoli");
        setWindowPreferences();

        table = new GridView(gridModel);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;//change the y location
        panel.add(table, gbc);

        console = new JTextArea(10, 40);
        console.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(console);
        gbc.gridy = 1;//change the y location
        panel.add(scrollPane, gbc);

        getContentPane().add(panel);

        // Update grid settings
        table.updateUI();
    }

    private void setWindowPreferences() {
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public void updateConsole(String message) {
        String formattedDate = dateFormatter.format(new Date());
        console.append(formattedDate + ": " +  message + "\n");
    }

}
