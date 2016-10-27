package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public abstract class CellView extends JLabel implements TableCellRenderer {

    private Object value;
    private int row;
    private int column;

    CellView() {
        setOpaque(true);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {
        this.row = row;
        this.column = column;
        setCustomRender(value);
        return this;
    }

    private void setCustomRender(Object value) {
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Color.black);
        setEnabled(false);
        setBackground(Color.lightGray);
        this.value = value;
    }

    protected Object getValue() {
        return value;
    }

    private boolean hasBorder(Cell cell, NeighbourPosition neighbourPosition, String tag) {
        Container container = cell.getLimitAt(neighbourPosition);
        if (container != null) {
            Content content = container.getContent(tag);
            return content != null;
        }
        return false;
    }

    void drawBorders(Cell cell, Graphics graphics) {
        if (hasBorder(cell, NeighbourPosition.BOTTOM, "LINEBORDERBOTTOM") || hasBorder(cell, NeighbourPosition.BOTTOM, "BORDER")) {
            drawBottomBorder(graphics);
        }
        if (hasBorder(cell, NeighbourPosition.RIGHT, "LINEBORDERRIGHT") || hasBorder(cell, NeighbourPosition.RIGHT, "BORDER")) {
            drawRightBorder(graphics);
        }
    }

    private void drawTopBorder(Graphics graphics) {
        graphics.fillRect(0, 0, this.getWidth(), 3);
    }

    private void drawBottomBorder(Graphics graphics) {
        graphics.fillRect(0, this.getHeight() - 3, this.getWidth(), this.getHeight());
    }

    private void drawLeftBorder(Graphics graphics) {
        graphics.fillRect(0, 0, 3, this.getHeight());
    }

    private void drawRightBorder(Graphics graphics) {
        graphics.fillRect(this.getWidth() - 3, 0, 3, this.getHeight());
    }

}
