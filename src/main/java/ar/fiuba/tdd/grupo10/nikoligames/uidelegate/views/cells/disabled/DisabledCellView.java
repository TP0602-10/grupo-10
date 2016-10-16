package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.disabled;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.CellView;

import java.awt.*;

public abstract class DisabledCellView extends CellView {

    public DisabledCellView() {
        super();
    }

    @Override
    protected void setCustomRender(Object value) {
        super.setCustomRender(value);
        setEnabled(false);
        setBackground(Color.lightGray);
        setDisabledTextColor(Color.black);
    }

}
