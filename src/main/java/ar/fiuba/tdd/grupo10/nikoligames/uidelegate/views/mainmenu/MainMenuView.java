package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu;


import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain.GamesChain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.TITLE;

public class MainMenuView extends JFrame {
    private JComboBox gameCombo;
    private GamesChain chain;

    public MainMenuView() {
        gameCombo = createGameCombo();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setContentPane(createMainPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setTitle(TITLE);
        setVisible(true);
        setResizable(false);
        chain = new GamesChain(this);
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
        comboPanel.add(new JLabel("Choose game:"));
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
        JButton startButton = new JButton("Play");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                GameEnum game = (GameEnum) gameCombo.getSelectedItem();
                chain.execute(game);
            }
        });
        return startButton;
    }

}
