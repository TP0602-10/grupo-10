package ar.fiuba.tdd.grupo10.nikoligames.grid;

/**
 * All classes that wants to be notified when the game grid changes must implement this interface.
 */
public interface OnGridUpdatedObserver {
    void onGridUpdated(Grid grid);
}
