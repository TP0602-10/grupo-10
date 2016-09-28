package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.OnRuleUnsatisfiedObserver;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.SudokuView;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class SudokuController implements TableModelListener, OnRuleUnsatisfiedObserver {

    private GridModel model;
    private SudokuView view;

    public SudokuController(Grid grid) {
        grid.addRuleObserver(this);
        model = new GridModel(grid);
        model.addTableModelListener(this);
        view = new SudokuView(model);
    }

    @Override
    public void tableChanged(TableModelEvent event) {
        //TODO maybe do extra work here, maybe this whole class is unnecessary
        model.toString();
        view.toString();
    }

    @Override
    public void onRuleUnsatisfied(String message) {
        view.updateConsole(message);
    }
}
