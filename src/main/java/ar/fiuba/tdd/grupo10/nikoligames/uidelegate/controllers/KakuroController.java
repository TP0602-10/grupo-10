package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters.GridAdapter;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters.KakuroGridAdapter;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.GameView;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.GridView;

public class KakuroController extends GameController {

    public KakuroController(Grid grid) {
        super(grid);
        GridAdapter adapter = new KakuroGridAdapter(grid);
        adapter.addTableModelListener(this);
        setView(new GameView(new GridView(adapter, GameEnum.KAKURO)));
    }

}
