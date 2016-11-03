package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GameRulesObserver;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters.GridAdapter;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.GameWindowView;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.GridView;

import java.util.Map;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class GameController implements TableModelListener, GameRulesObserver {

    public void setView(GameWindowView view) {
        this.view = view;
    }

    private GameWindowView view;

    public GameController(Grid grid, GameEnum gameEnum) {
        super();
        grid.addRuleObserver(this);
        GridAdapter adapter = new GridAdapter(grid, gameEnum);
        adapter.addTableModelListener(this);
        setView(new GameWindowView(new GridView(adapter, gameEnum)));
    }

    @Override
    public void tableChanged(TableModelEvent event) {

    }

    @Override
    public void onRuleUnsatisfied(String message, Map<String, Object> extras) {
        view.updateConsole(message);
    }

    @Override
    public void onGameWon(String message) {
        view.updateConsole(message);
    }

}
