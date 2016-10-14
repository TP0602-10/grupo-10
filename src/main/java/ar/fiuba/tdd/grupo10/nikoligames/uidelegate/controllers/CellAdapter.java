package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.CellView;

public class CellAdapter extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        CellView cell = (CellView) mouseEvent.getSource(); // JLabel label = (JLabel) mouseEvent.getComponent()
        cell.setText("897987");
        cell.repaint();
        //super.mouseClicked(e);
    }
}
