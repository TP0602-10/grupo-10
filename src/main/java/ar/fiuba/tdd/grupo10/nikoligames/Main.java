package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.MainMenuView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        showMenu();
    }

    private static void showMenu() {
        JFrame frame = new JFrame("Nikoli Games");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new MainMenuView();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }


}
