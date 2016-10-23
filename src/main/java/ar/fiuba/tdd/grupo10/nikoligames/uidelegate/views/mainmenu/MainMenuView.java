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
    private JTextField filePathTextField;

    public MainMenuView() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setContentPane(createMainPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setTitle(TITLE);
        setSize(800, 200);
        setVisible(true);
        setResizable(false);
        chain = new GamesChain(this);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,
                BoxLayout.PAGE_AXIS));
        mainPanel.add(createComboPanel());
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createFileChooserPanel());
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createPlayButtonPanel());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        setFilePathTextFieldValue();
        return mainPanel;
    }

    private JPanel createComboPanel() {
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new BoxLayout(comboPanel,
                BoxLayout.X_AXIS));
        comboPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboPanel.add(new JLabel("Choose game:   "));
        comboPanel.add(Box.createHorizontalStrut(10));
        gameCombo = createGameCombo();
        comboPanel.add(gameCombo);
        return comboPanel;
    }

    private JPanel createFileChooserPanel() {
        JPanel fileChooserPanel = new JPanel();
        fileChooserPanel.setLayout(new BoxLayout(fileChooserPanel,
                BoxLayout.X_AXIS));
        fileChooserPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton chooseFileBtn = new JButton("Choose file");
        fileChooserPanel.add(chooseFileBtn);
        fileChooserPanel.add(Box.createHorizontalStrut(10));
        filePathTextField = new JTextField();
        fileChooserPanel.add(filePathTextField);
        chooseFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                final JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog(MainMenuView.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    filePathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        return fileChooserPanel;
    }

    private JPanel createPlayButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.add(createStartButton());
        return buttonPanel;
    }

    private JComboBox createGameCombo() {
        JComboBox gameCombo = new JComboBox(GameEnum.values());
        gameCombo.setEditable(false);
        gameCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        gameCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFilePathTextFieldValue();
            }
        });
        return gameCombo;
    }

    private void setFilePathTextFieldValue() {
        String jsonPath = ((GameEnum) gameCombo.getSelectedItem()).getDefaultJsonPath();
        filePathTextField.setText(String.valueOf(jsonPath));
    }

    private JButton createStartButton() {
        JButton startButton = new JButton("Play");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                GameEnum game = (GameEnum) gameCombo.getSelectedItem();
                chain.execute(game, filePathTextField.getText());
            }
        });
        return startButton;
    }

}
