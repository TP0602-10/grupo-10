package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;


import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridModel;

import java.awt.*;
import javax.swing.*;


public class SudokuView extends JFrame {

    private GridView table;

    public SudokuView(GridModel gridModel) {

        super("Grupo 10 - Nikoli");
        setWindowPreferences();

        table = new GridView(gridModel);
        this.add(table);

        // Actualiza la configuracion de la grilla
        table.updateUI();
    }

    private void setWindowPreferences() {
        setVisible(true);
        setSize(new Dimension(700, 500));
        setLocation(420, 220);
    }

}
