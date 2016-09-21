package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.ListsHelper;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIteratorHelper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Rule operation that checks if all the cells of matter for the rule are distinct between them.
 * It uses the generic Object#equals() so it may not know the actual content type of the cell.
 */
public class DistinctOperation implements GridRuleOperation<Boolean> {

    @Override
    public Boolean perform(GridRuleIterator iterator, Object... params) {
        List<GridCell> cellsToEvaluate = getAllCellsToEvaluate(iterator);
        return areAllCellsDistinct(cellsToEvaluate);
    }

    @Override
    public boolean isApplicableOn(GridCell cell) {
        return cell.areRulesApplicable()
                && cell.getContent() != null;
    }

    @Override
    public String getOperationExplanation() {
        return "The operation returns TRUE if all the cells are distinct.";
    }

    private List<GridCell> getAllCellsToEvaluate(GridRuleIterator iterator) {
        return GridRuleIteratorHelper.listAllCells(iterator)
                .stream().filter(this::isApplicableOn).collect(Collectors.toList());
    }

    private boolean areAllCellsDistinct(List<GridCell> allCells) {
        return ListsHelper.equals(
                allCells,
                ListsHelper.rejectDuplicateElements(allCells)
        );
    }
}
