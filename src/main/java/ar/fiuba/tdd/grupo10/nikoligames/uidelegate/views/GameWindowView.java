package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.TITLE;

public class GameWindowView extends JFrame {

    private GridView gridView;
    private JTextArea console;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");

    public GameWindowView(GridView gridView) {

        super(TITLE);
        this.gridView = gridView;
        setWindowPreferences();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;//change the y location
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(gridView, gbc);

        console = createConsole();
        JScrollPane scrollPane = new JScrollPane(console);
        gbc.gridy = 2;
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(scrollPane, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(createButtonsPanel(), gbc);

        getContentPane().add(panel);
        setResizable(true);
        gridView.updateUI();

    }

    private JTextArea createConsole() {
        console = new JTextArea(10, 60);
        console.setEnabled(false);
        console.setDisabledTextColor(Color.black);
        return console;
    }

    private JButton createClearConsoleButton() {
        JButton button = new JButton("Clear console");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                console.setText("");
            }
        });
        return button;
    }

    private JButton createUndoButton() {
        JButton button = new JButton("Undo");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updateConsole(gridView.undo());
            }
        });
        return button;
    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;//change the x location
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(createUndoButton(), gbc);
        gbc.gridx = 2;
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(createClearConsoleButton(), gbc);
        return panel;
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
