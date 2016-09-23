package ar.fiuba.tdd.grupo10.nikoligames.mvc.model;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;

import javax.swing.table.AbstractTableModel;

/**
 * Created by tinchop on 23/09/16.
 */
public class GridModel extends AbstractTableModel {

    private Grid grid;

    public GridModel(Grid grid) {
        super();
        this.grid = grid;
    }

    @Override
    public int getRowCount() {
        return grid.getCells().size();
    }

    @Override
    public int getColumnCount() {
        return grid.getCells().get(0).size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return grid.getCellAt(rowIndex, columnIndex).getContent().getValue();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }
}
