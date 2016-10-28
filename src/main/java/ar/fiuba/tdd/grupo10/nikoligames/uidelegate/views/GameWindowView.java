package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.TITLE;

public class GameWindowView extends JFrame {

    private GridView table;
    private JPanel panel;
    private GridBagConstraints gbc;

    private JTextArea console;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");

    public GameWindowView(GridView gridView) {

        super(TITLE);
        setWindowPreferences();

        table = gridView;
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
        setResizable(true);
        table.updateUI();

    }

    protected JTextArea createConsole() {
        console = new JTextArea(10, 60);
        console.setEnabled(false);
        console.setDisabledTextColor(Color.black);
        return console;
    }

    protected JButton createClearConsoleButton() {
        JButton button = new JButton("Clear console");
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
