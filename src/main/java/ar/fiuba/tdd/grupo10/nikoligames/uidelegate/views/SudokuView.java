package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;


import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridAdapter;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.TITLE;


public class SudokuView extends JFrame {

    private JTextArea console;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");

    public SudokuView(GridAdapter gridAdapter) {
        super(TITLE);
        setWindowPreferences();

        GridView table = new GridView(gridAdapter);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;//change the y location
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(table, gbc);

        console = createConsole();
        JScrollPane scrollPane = new JScrollPane(console);
        gbc.gridy = 2;//change the y location
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(scrollPane, gbc);

        gbc.gridy = 4;//change the y location
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(createClearConsoleButton(), gbc);

        getContentPane().add(panel);
        setResizable(false);
        table.updateUI();
    }

    private JTextArea createConsole() {
        console = new JTextArea(10, 60);
        console.setEnabled(false);
        return console;
    }

    private JButton createClearConsoleButton() {
        JButton button = new JButton("Limpiar consola");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                console.setText("");
            }
        });
        return button;
    }

    private void setWindowPreferences() {
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public void updateConsole(String message) {
        String formattedDate = dateFormatter.format(new Date());
        console.append(formattedDate + ": " + message + "\n");
    }

}
