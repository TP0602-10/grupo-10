package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;



public class CellView extends JLabel implements TableCellRenderer {

    private boolean enabled;

    public CellView(boolean enabled) {
        setOpaque(true);
        this.enabled = enabled;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (value != null) {
            String text = value.toString();
            setText(text);
        }
        setEnabled(this.enabled);
        setHorizontalAlignment(SwingConstants.CENTER);
        setBackground(this.enabled ? Color.white : Color.lightGray);
        setForeground(Color.black);

        return this;
    }
}
