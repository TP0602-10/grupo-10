package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.TITLE;

public class GameView extends JFrame {

    private GridView table;
    private JPanel panel;
    private GridBagConstraints gbc;

    private JTextArea console;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");

    public GameView(GridAdapter gridAdapter) {

        super(TITLE);
        setWindowPreferences();

        table = new GridView(gridAdapter);
        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

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

    protected JTextArea createConsole() {
        console = new JTextArea(10, 60);
        console.setEnabled(false);
        return console;
    }

    protected JButton createClearConsoleButton() {
        JButton button = new JButton("Limpiar consola");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                console.setText("");
            }
        });
        return button;
    }

    protected void setWindowPreferences() {
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public void updateConsole(String message) {
        String formattedDate = dateFormatter.format(new Date());
        console.append(formattedDate + ": " + message + "\n");
    }
}
