package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;


import ar.fiuba.tdd.grupo10.nikoligames.SudokuFactory;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.SudokuController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenuView extends JFrame {
    private JComboBox gameCombo;

    private enum GameEnum {
        SUDOKU,
        KAKURO;
    }

    public MainMenuView() {
        gameCombo = createGameCombo();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setContentPane(createMainPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setTitle("Nikoli Games");
        setVisible(true);
        setResizable(false);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,
                BoxLayout.PAGE_AXIS));
        mainPanel.add(createComboPanel());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(createButtonPanel());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        return mainPanel;
    }

    private JPanel createComboPanel() {
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new BoxLayout(comboPanel,
                BoxLayout.PAGE_AXIS));
        comboPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboPanel.add(new JLabel("Seleccione un juego:"));
        comboPanel.add(gameCombo);
        return comboPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.add(createStartButton());
        return buttonPanel;
    }

    private JComboBox createGameCombo() {
        JComboBox gameCombo = new JComboBox(GameEnum.values());
        gameCombo.setEditable(false);
        gameCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        return gameCombo;
    }

    private JButton createStartButton() {
        JButton startButton = new JButton("Jugar");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                GameEnum game = (GameEnum) gameCombo.getSelectedItem();
                if (GameEnum.SUDOKU.equals(game)) {
                    try {
                        new SudokuController(SudokuFactory.createGridFromScratch(15));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else if (GameEnum.KAKURO.equals(game)) {
                    showUnavailableGameDialog();
                }
            }
        });
        return startButton;
    }

    private void showUnavailableGameDialog() {
        JOptionPane.showMessageDialog(this,
                "Juego no disponible.",
                "Nikoli Games",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
