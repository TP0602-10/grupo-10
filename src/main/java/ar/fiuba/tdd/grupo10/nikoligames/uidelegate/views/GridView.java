package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridAdapter;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;


public class GridView extends JTable {

    private static int RowHeightDefault = 50;

    public GridView(GridAdapter grid) {
        super(grid);
        this.setRowHeight(RowHeightDefault);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.setGridColor(Color.red);
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        if (this.getModel().isCellEditable(row, column)) {
            return new EnabledCellView();
        }
        return new DisabledCellView();
    }
}
