package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;

import javax.swing.table.AbstractTableModel;

public class GridAdapter extends AbstractTableModel {

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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value;
        try {
            value = grid.getCellAt(rowIndex, columnIndex).getValue();
        } catch (Exception exception) {
            value = null;
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return this.grid.getCellAt(row,col).isContentEditable();
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        try {
            Cell cell = grid.getCellAt(row, column);
            if (value != null && !value.equals("")) {
                cell.setValue(Integer.valueOf(String.valueOf(value)));
            } else {
                cell.setValue(null);
            }
            fireTableCellUpdated(row, column);
            grid.notifyGridUpdated();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
