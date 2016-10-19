package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;

public class KakuroGridAdapter extends GridAdapter {

    public KakuroGridAdapter(Grid grid) {
        super(grid);
    }

    @Override
    protected String getGridValueRegex() {
        return "^[0-9]";
    }

}
