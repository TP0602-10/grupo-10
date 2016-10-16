package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.enabled;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.CellView;

import java.awt.*;

public abstract class EnabledCellView extends CellView {

    public EnabledCellView() {
        super();
        setEnabled(false);
        setEditable(false);
    }

    @Override
    protected void setCustomRender(Object value) {
        super.setCustomRender(value);
        if (value != null) {
            setText(value.toString());
        }

        setDisabledTextColor(Color.black);
        setBackground(Color.white);
        //FIXME Class should be renamed since enabled is false now
        setEnabled(false);
        setEditable(false);
    }

}
