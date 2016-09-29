package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractGameView extends JFrame {

    protected JTextArea console;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");

    public AbstractGameView(String title) {
        super(title);
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
