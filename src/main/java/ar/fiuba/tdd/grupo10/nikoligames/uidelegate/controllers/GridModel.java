package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;

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
        Content content = grid.getCellAt(rowIndex, columnIndex).getContent("tag");
        return (content == null ? "" : content.getValue());
    }

    @Override
    public boolean isCellEditable(int row, int col) {
//        return this.grid.getCellAt(row,col).isContentEditable();
        return false;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        try {
            Cell cell = grid.getCellAt(row, column);
            if (value != null) {
                cell.setValue(value);
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
