package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import javax.swing.*;
import java.awt.*;

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
