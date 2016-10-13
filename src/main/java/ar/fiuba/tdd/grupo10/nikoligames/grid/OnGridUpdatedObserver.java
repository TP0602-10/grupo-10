package ar.fiuba.tdd.grupo10.nikoligames.grid;

import java.util.Map;

/**
 * All classes that wants to be notified when the game grid changes must implement this interface.
 */
public interface OnGridUpdatedObserver {
    void onGridUpdated(Grid grid, Map<String, Object> extras);
}
