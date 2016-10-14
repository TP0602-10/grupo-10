package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.CellAdapter;

import java.awt.*;

class EnabledCellView extends CellView {

    public EnabledCellView() {
        super();
        addMouseListener(new CellAdapter());
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
