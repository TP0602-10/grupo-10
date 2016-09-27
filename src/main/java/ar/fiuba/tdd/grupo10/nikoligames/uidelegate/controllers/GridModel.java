package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;

import javax.swing.table.AbstractTableModel;

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
        CellContent content = grid.getCellAt(rowIndex, columnIndex).getContent();
        return (content == null ? "" : content.getValue());
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return this.grid.getCellAt(row,col).isContentEditable();
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        boolean valueIsEmpty = value.toString().isEmpty();
        GridCell cell = grid.getCellAt(row, column);
        if (valueIsEmpty) {
            value = null;
        }
        if (value != null) {
            cell.setContent(new CellContent(value));
        } else {
            cell.clearContent();
        }
        fireTableCellUpdated(row, column);
        grid.notifyGridUpdated();
    }
}
