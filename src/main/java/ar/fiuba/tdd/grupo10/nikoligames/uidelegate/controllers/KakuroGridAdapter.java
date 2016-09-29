package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

import javax.swing.table.AbstractTableModel;

public class KakuroGridAdapter extends AbstractTableModel {

    private Grid grid;

    public KakuroGridAdapter(Grid grid) {
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
        return grid.getCellAt(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return this.grid.getCellAt(row,col).isContentEditable();
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
//        try {
//            String stringValue = String.valueOf(value);
//            Cell cell = grid.getCellAt(row, column);
//            if (value != null && stringValue.matches("^[1-9]")) {
//                cell.setValue(Integer.valueOf(stringValue));
//            } else {
//                cell.setValue(null);
//            }
//            fireTableCellUpdated(row, column);
//            grid.notifyGridUpdated();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
