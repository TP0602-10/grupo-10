package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public abstract class CellView extends JTextField implements TableCellRenderer {

    private Object value;

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
}
