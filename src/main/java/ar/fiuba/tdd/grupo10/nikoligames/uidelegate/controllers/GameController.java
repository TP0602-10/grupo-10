package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GameRulesObserver;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.GameView;

import java.util.Map;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public abstract class GameController implements TableModelListener, GameRulesObserver {

    protected void setView(GameView view) {
        this.view = view;
    }

    private GameView view;

    public GameController(Grid grid) {
        grid.addRuleObserver(this);
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
