package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.NeighbourType;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.Arrays;
import java.util.List;


public class CheckLeftBorderOperation extends CheckBorderOperation {


    public CheckLeftBorderOperation(String tag) {
        super(tag, NeighbourPosition.LEFT);


    }

    /* @Override
     public Boolean perform(GridRuleIterator iterator, Object... params) {
         while ( iterator.hasNext()) {
             Container aContainer = iterator.next();
             if ( !checkBorder((Cell) aContainer,NeighbourPosition.LEFT) ) {
                 return false;
             }
         }
         return true;
     }






     @Override
     public boolean isApplicableOn(Container container) {
         if(container instanceof Cell) {
             for (NeighbourContainer element:((Cell) container).getLimits()) {
                 if (!element.getNeighbourContainer().getContent(aTag).isEmpty()) {
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
 */
    @Override
    public String getOperationExplanation(Boolean result) {
        return "This operation checks whether the cells have their left borders marked";
    }
}
