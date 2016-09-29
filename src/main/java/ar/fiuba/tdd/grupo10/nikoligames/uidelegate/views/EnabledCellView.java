package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import java.awt.*;
import javax.swing.*;

public class EnabledCellView extends CellView{

    public EnabledCellView() {
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
