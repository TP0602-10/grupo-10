package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.ListsHelper;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIteratorHelper;

import java.util.List;
import java.util.stream.Collectors;

public class DistinctOperation implements GridRuleOperation<Boolean> {

    @Override
    public Boolean perform(GridRuleIterator iterator) {
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
