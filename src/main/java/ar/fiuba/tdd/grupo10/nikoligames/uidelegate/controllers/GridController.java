package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GameRulesObserver;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.GameView;

import java.util.Map;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class GridController implements TableModelListener, GameRulesObserver {

    private GridAdapter model;
    private GameView view;

    public GridController(Grid grid) {
        grid.addRuleObserver(this);
        model = new GridAdapter(grid);
        model.addTableModelListener(this);
        view = new GameView(model);
    }

    @Override
    public void tableChanged(TableModelEvent event) {
        //TODO maybe do extra work here, maybe this whole class is unnecessary
        model.toString();
        view.toString();
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
