package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;


import ar.fiuba.tdd.grupo10.nikoligames.SudokuFactory;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.SudokuController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenuView extends JPanel {
    private JComboBox gameCombo;
    private JButton startButton;

    private enum GameEnum {
        SUDOKU,
        KAKURO;
    }

    public MainMenuView() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel selectionLabel = new JLabel("Seleccione un juego:");

        gameCombo = new JComboBox(GameEnum.values());
        gameCombo.setEditable(false);

        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new BoxLayout(comboPanel,
                BoxLayout.PAGE_AXIS));
        comboPanel.add(selectionLabel);
        gameCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboPanel.add(gameCombo);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        startButton = new JButton("Jugar");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                GameEnum game = (GameEnum) gameCombo.getSelectedItem();
                if (GameEnum.SUDOKU.equals(game)) {
                    new SudokuController(SudokuFactory.createFromScratch(70));
                } else if (GameEnum.KAKURO.equals(game)) {
                    showGameUnavailableDialog();
                }
            }
        });
        buttonPanel.add(startButton);

        comboPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(comboPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(buttonPanel);

        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    }

    private void showGameUnavailableDialog() {
        JOptionPane.showMessageDialog(this,
                "Juego no disponible.",
                "Nikoli Games",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
