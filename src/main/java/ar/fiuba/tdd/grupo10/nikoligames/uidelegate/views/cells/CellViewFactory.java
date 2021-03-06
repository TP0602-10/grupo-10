package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;

public class CellViewFactory {

    public static CellView createCellView(GameEnum gameEnum) {
        try {
            return gameEnum.getCellClass().newInstance();
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new RuntimeException("CellViewFactory:createCellView - Could not create cell view");
        }
    }

}
