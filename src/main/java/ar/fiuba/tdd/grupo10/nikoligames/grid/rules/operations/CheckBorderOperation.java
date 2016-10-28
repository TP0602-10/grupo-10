package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRule;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;


public class CheckBorderOperation extends GridRuleOperation<Boolean> {

    private String theTag;
    private NeighbourPosition thePosition;

    public CheckBorderOperation(String tag, NeighbourPosition onePosition) {
        super(tag);
        theTag = tag;
        thePosition = onePosition;
    }


    @Override
    public Boolean perform(GridRuleIterator iterator, Object... params) {
        while (iterator.hasNext()) {
            Container theContainer = iterator.next();
            if (!checkBorder((Cell) theContainer, thePosition)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkBorder(Cell cellToApply, NeighbourPosition appliablePosition) {
        if (cellToApply.getLimitAt(appliablePosition) == null) {
            return false;
        }
        if (cellToApply.getLimitAt(appliablePosition).getContent(theTag).getValue() instanceof Line) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isApplicableOn(Container container) {
        if (container instanceof Cell) {
            for (NeighbourContainer element : ((Cell) container).getLimits()) {
                if (!element.getNeighbourContainer().getContent(theTag).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isApplicableOn(Content content) {
        return (!content.isEmpty() && content.getValue() instanceof Line);
    }

    @Override
    public String getOperationExplanation(Boolean result) {
        return null;
    }
}
