package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers.PossibleValue;

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
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cell cell = grid.getCellAt(rowIndex, columnIndex);
        if (!isCellEditable(rowIndex, columnIndex)) {
            return cell;
        }
        return cell.getValue();
    }

    @Override
    public void setValueAt(Object value, int row, int column) {

        try {
            PossibleValue possibleValue = (PossibleValue) value;
            Cell cell = grid.getCellAt(row, column);
            possibleValue.setValueInCell(cell);
            fireTableCellUpdated(row, column);
            grid.notifyGridUpdated(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Grid getGrid() {
        return grid;
    }

}
