package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters.GridAdapter;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters.GridMouseAdapter;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.CellViewFactory;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.ROW_HEIGHT_DEFAULT;

public class GridView extends JTable {

    private GameEnum gameEnum;
    private GridAdapter adapter;

    public GridView(GridAdapter adapter, GameEnum gameEnum) {
        super(adapter);
        this.adapter = adapter;
        this.gameEnum = gameEnum;
        this.setRowHeight(ROW_HEIGHT_DEFAULT);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.setGridColor(Color.red);
        this.addMouseListener(new GridMouseAdapter(this, gameEnum));
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        return CellViewFactory.createCellView(gameEnum);
    }

}
