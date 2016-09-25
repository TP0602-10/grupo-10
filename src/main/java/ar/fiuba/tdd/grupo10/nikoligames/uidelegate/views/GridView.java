package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridModel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class GridView extends JTable {

    public GridView(GridModel grid) {
        super(grid);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.setGridColor(Color.red);
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        return new CellView(this.getModel().isCellEditable(row, column));
    }
}
