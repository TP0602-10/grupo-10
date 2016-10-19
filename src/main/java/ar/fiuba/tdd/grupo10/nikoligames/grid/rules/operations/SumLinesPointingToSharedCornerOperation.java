package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Point;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.NeighbourType;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.List;

public class SumLinesPointingToSharedCornerOperation extends GridRuleOperation<Integer> {
    private Container cornerToEvaluate;

    public SumLinesPointingToSharedCornerOperation(List<String> contentTags, Container cornerToEvaluate) {
        super(contentTags);
        this.cornerToEvaluate = cornerToEvaluate;
    }

    public SumLinesPointingToSharedCornerOperation(String tag, Container cornerToEvaluate) {
        super(tag);
        this.cornerToEvaluate = cornerToEvaluate;
    }

    @Override
    public Integer perform(GridRuleIterator iterator, Object... params) {
        int linesPointingToSharedCorner = 0;
        while (iterator.hasNext()) {
            Cell cell = (Cell) iterator.next();
            if (isApplicableOn(cell) && isApplicableOn(cell.getContent())) {
                if (doesCellLineMeetTargetCorner(cell, cornerToEvaluate)) {
                    linesPointingToSharedCorner++;
                }
            }
        }
        return linesPointingToSharedCorner;
    }

    private boolean doesCellLineMeetTargetCorner(Cell cell, Container corner) {
        NeighbourType boundaryType = cell.getBoundaryFrom(corner);
        return boundaryType.isValid(
                (Line) cell.getValue(),
                (Point) corner.getValue()
        );
    }

    @Override
    public boolean isApplicableOn(Container container) {
        return ! container.getContents(getContentTags()).isEmpty();
    }

    @Override
    public boolean isApplicableOn(Content content) {
        return ( !content.isEmpty() && (content.getValue() instanceof Line) );
    }

    @Override
    public String getOperationExplanation(Integer result) {
        return "The operation sums all the line contents of the iterator cells pointing to a corner shared by all."
                + " The result is " + result.toString() + ".";
    }
}
