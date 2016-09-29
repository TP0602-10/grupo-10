package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;

public class KakuroGridAdapter extends GridAdapter{

    private Grid grid;

    public KakuroGridAdapter(Grid grid) {
        super(grid);
        this.grid = grid;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return grid.getCellAt(rowIndex, columnIndex);
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
