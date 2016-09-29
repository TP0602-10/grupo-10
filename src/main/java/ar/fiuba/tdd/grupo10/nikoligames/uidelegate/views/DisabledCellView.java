package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import java.awt.*;
import javax.swing.*;

public class DisabledCellView extends CellView {

    private Object value;

    public DisabledCellView() {
        super();
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    protected void setCustomRender(Object value) {

        super.setCustomRender(value);

        setValue(value);
        setEnabled(false);
        setBackground(Color.lightGray);
    }

    @Override
    public void paintComponent(Graphics grapichs) {
        // TODO: Value debería ser un array de valores? Como identificamos si hay que renderizar un solo valor o varios en la misma celda
        String text = (this.value != null) ? this.value.toString() : "";

        setText(text);

        super.paintComponent(grapichs);

        // TODO: Renderizado Kakuro
        /*g.drawLine(this.getWidth(), this.getHeight(), 0, 0);

        g.drawString(text, 5, (this.getHeight() / 2) + 5);
        g.drawString("?", this.getWidth() - 10, this.getHeight() / 2);*/
    }

}
