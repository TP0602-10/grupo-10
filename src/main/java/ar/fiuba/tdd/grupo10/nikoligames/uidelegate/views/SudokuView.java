package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;


import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SudokuView extends JFrame {

    private GridView table;
    private JTextArea console;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");

    public SudokuView(GridAdapter gridAdapter) {

        super("Grupo 10 - Nikoli");
        setWindowPreferences();

        table = new GridView(gridAdapter);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;//change the y location
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(table, gbc);

        console = new JTextArea(10, 60);
        console.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(console);
        gbc.gridy = 2;//change the y location
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(scrollPane, gbc);

        gbc.gridy = 4;//change the y location
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(createClearConsoleButton(), gbc);

        getContentPane().add(panel);
        setResizable(false);
        // Actualiza la configuracion de la grilla
        table.updateUI();
    }

    private JButton createClearConsoleButton() {
        JButton button = new JButton("Limpiar consola");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                console.setText("");
            }
        });
        return button;
    }

    private void setWindowPreferences() {
        setVisible(true);
        setSize(new Dimension(700, 500));
        setLocation(420, 220);
    }

    public void updateConsole(String message) {
        String formattedDate = dateFormatter.format(new Date());
        console.append(formattedDate + ": " + message + "\n");
    }

}
