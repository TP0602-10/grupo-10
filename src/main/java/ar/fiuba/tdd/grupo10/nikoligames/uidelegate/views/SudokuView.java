package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;


import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridModel;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;


public class SudokuView extends JFrame implements Observer {

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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(420, 220);
    }

    @Override
    public void update(Observable observable, Object arg) {
        //TODO
    }

}
