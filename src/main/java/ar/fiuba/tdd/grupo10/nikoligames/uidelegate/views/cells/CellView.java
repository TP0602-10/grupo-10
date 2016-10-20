package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.BORDER;

public abstract class CellView extends JLabel implements TableCellRenderer {

    private Object value;
    protected boolean hasTopBorder;
    protected boolean hasBottomBorder;
    protected boolean hasLeftBorder;
    protected boolean hasRightBorder;

    public CellView() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setCustomRender(value);
        return this;
    }

    protected void setCustomRender(Object value) {
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Color.black);
        setEnabled(false);
        setBackground(Color.lightGray);
        this.value = value;
    }

    protected Object getValue() {
        return value;
    }

    protected String getTextValue(Object text) {
        return (text != null ? String.valueOf(text) : "" );
    }

    protected void setBorders(Cell cell) {

        setTopBorder(cell);
        setBottomBorder(cell);
        setLeftBorder(cell);
        setRightBorder(cell);
    }

    protected void setTopBorder(Cell cell) {
        Container container = cell.getLimitAt(NeighbourPosition.TOP);
        hasTopBorder = (container != null) ? true : false;
    }

    protected void setBottomBorder(Cell cell) {

        Container container = cell.getLimitAt(NeighbourPosition.BOTTOM);
        hasBottomBorder = (container != null && container.getValue(BORDER) != null);
    }

    protected void setLeftBorder(Cell cell) {

        Container container = cell.getLimitAt(NeighbourPosition.LEFT);
        hasLeftBorder = (container != null && container.getValue(BORDER) != null);
    }

    protected void setRightBorder(Cell cell) {

        Container container = cell.getLimitAt(NeighbourPosition.RIGHT);
        hasRightBorder = (container != null && container.getValue(BORDER) != null);
    }


    protected void drawBorders(Graphics graphics) {

        drawTopBorder(graphics);
        drawBottomBorder(graphics);
        drawLeftBorder(graphics);
        drawRightBorder(graphics);
    }

    private void drawTopBorder(Graphics graphics) {
        if (hasTopBorder) {
            graphics.fillRect(0, 0, this.getWidth(), 3);
        }
    }

    private void drawBottomBorder(Graphics graphics) {

        if (hasBottomBorder) {
            graphics.fillRect(0, this.getHeight() - 3, this.getWidth(), this.getHeight());
        }
    }

    private void drawLeftBorder(Graphics graphics) {

        if (hasLeftBorder) {
            graphics.fillRect(0, 0, 3, this.getHeight());
        }
    }

    private void drawRightBorder(Graphics graphics) {

        if (hasRightBorder) {
            graphics.fillRect(0, 0, 3, this.getHeight());
        }
    }

}
