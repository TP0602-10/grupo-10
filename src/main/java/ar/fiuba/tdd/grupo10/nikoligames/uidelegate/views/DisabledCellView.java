package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import java.awt.*;

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
    public void paintComponent(Graphics graphics) {

        //Cell cell = (Cell)this.value;

        String text = (this.value == null)?"": this.value.toString();

        setText(text);

        super.paintComponent(graphics);

        // TODO: Renderizado Kakuro
        /*graphics.drawLine(this.getWidth(), this.getHeight(), 0, 0);

        graphics.drawString(text, 5, (this.getHeight() / 2) + 5);
        graphics.drawString("?", this.getWidth() - 10, this.getHeight() / 2);*/
    }

}
