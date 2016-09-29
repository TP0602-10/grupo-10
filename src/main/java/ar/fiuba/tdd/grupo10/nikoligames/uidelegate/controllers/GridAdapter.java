package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;

import javax.swing.table.AbstractTableModel;

public class GridAdapter  extends AbstractTableModel {
    private Grid grid;

    public GridAdapter(Grid grid) {
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
    public boolean isCellEditable(int row, int col) {
        return this.grid.getCellAt(row,col).isContentEditable();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

}
