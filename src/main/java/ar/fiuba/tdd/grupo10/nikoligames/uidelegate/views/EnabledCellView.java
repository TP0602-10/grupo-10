package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import java.awt.*;

class EnabledCellView extends CellView {

    public EnabledCellView() {
        super();
    }

    @Override
    protected void setCustomRender(Object value) {

        super.setCustomRender(value);

        if (value != null) {
            setText(value.toString());
        }

        setEnabled(true);
        setBackground(Color.white);
    }
}
