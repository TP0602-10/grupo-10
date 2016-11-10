package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers.MiniSnapshot;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers.PossibleValue;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers.PreviousPlay;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.table.AbstractTableModel;

public class GridAdapter extends AbstractTableModel {

    private Stack<MiniSnapshot> snapshots;
    private Grid grid;
    private GameEnum gameEnum;

    public GridAdapter(Grid grid, GameEnum gameEnum) {
        super();
        this.grid = grid;
        this.snapshots = new Stack<>();
        this.gameEnum = gameEnum;
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
            Cell cell = grid.getCellAt(row, column);
            snapshots.push(createMiniSnapshot(cell, row, column));
            PossibleValue possibleValue = (PossibleValue) value;
            possibleValue.setValueInCell(cell);
            fireTableCellUpdated(row, column);
            grid.notifyGridUpdated(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MiniSnapshot createMiniSnapshot(Cell cell, int row, int column) {
        List<PreviousPlay> previousPlays = new ArrayList<>();
        previousPlays.add(createPreviousPlay(row, column));
        return new MiniSnapshot(previousPlays);
    }

    private PreviousPlay createPreviousPlay(int row, int column) {
        Cell cell = grid.getCellAt(row, column);
        return new PreviousPlay(row, column, cell.getValue(gameEnum.getMutableContentTag()));
    }

    protected Grid getGrid() {
        return grid;
    }

    public String undo() {
        if (snapshots.isEmpty()) {
            return "There are no moves to undo.";
        }
        MiniSnapshot snapshot = snapshots.pop();
        for (PreviousPlay previousPlay : snapshot.getPreviousPlays()) {
            Cell cell = grid.getCellAt(previousPlay.getRow(), previousPlay.getColumn());
            PossibleValue possibleValue = gameEnum.getEquivalent(previousPlay.getValue());
            possibleValue.setValueInCell(cell);
            fireTableCellUpdated(previousPlay.getRow(), previousPlay.getColumn());
            grid.notifyGridUpdated(null);
        }
//        PreviousPlay actualPreviousPlay = snapshot.getPreviousPlays().get(0);
//        fireTableCellUpdated(actualPreviousPlay.getRow(), actualPreviousPlay.getColumn());
        return "Move undone successfully.";
    }

}
