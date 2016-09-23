package ar.fiuba.tdd.grupo10.nikoligames.mvc.views;


import ar.fiuba.tdd.grupo10.nikoligames.mvc.model.GridModel;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;


public class NikoliView extends JFrame implements Observer {

    private JTable table;

    public NikoliView(GridModel gridModel) {

        super("Grupo 10 - Nikoli");
        setWindowPreferences();
        table = new JTable(gridModel);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        cellRenderer.setBackground(Color.blue);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setGridColor(Color.red);
        table.setDefaultRenderer(String.class, cellRenderer);
        this.add(table);
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
