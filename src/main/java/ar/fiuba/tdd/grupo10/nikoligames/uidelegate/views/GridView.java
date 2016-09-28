package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridAdapter;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;


public class GridView extends JTable {

    public GridView(GridAdapter grid) {
        super(grid);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.setGridColor(Color.red);
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        return new CellView(this.getModel().isCellEditable(row, column));
    }
}
