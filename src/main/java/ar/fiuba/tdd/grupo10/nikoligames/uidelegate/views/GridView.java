package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridAdapter;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.ROW_HEIGHT_DEFAULT;

public class GridView extends JTable {

    public GridView(GridAdapter grid) {
        super(grid);
        this.setRowHeight(ROW_HEIGHT_DEFAULT);
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
