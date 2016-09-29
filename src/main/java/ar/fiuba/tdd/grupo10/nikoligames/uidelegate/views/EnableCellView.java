package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import javax.swing.*;
import java.awt.*;

public class EnableCellView extends CellView{

    public EnableCellView() {
        super();
    }

    @Override
    protected void setCustomRender(Object value) {

        super.setCustomRender(value);

        if (value != null) {
            String text = value.toString();
            setText(text);
        }

        setEnabled(true);
        setBackground(Color.white);
    }
}
