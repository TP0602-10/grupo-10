package ar.fiuba.tdd.grupo10.nikoligames.grid;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GameWonException;

/**
 * All classes that wants to be notified when the game grid changes must implement this interface.
 */
public interface OnGridUpdatedObserver {
    void onGridUpdated(Grid grid);
}
