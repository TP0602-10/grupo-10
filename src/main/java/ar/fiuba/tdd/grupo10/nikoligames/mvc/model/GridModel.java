package ar.fiuba.tdd.grupo10.nikoligames.mvc.model;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;

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

        CellContent content = grid.getCellAt(rowIndex, columnIndex).getContent();
        return (content == null? "" : content.getValue());
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return this.grid.getCellAt(row,col).isContentEditable();
    }
}
