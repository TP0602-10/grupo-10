package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.OnGridUpdatedObserver;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.DistinctOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.RandomHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuFactory {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int TOTAL_CELLS = ROWS * COLUMNS;

    private static final int ROW_DIVISIONS = 3;
    private static final int COLUMN_DIVISIONS = 3;

    private static final int MIN_CELL_CONTENT = 1;
    private static final int MAX_CELL_CONTENT = 9;

    public static Grid createFromScratch(int numberOfHints) {
        List<GridCell> cells = generateCellsInGridForm(numberOfHints);
        OnGridUpdatedObserver observer = createSudokuRuleManager(ListHelper.buildMatrixFromFlattenList(cells, ROWS, COLUMNS));
        return new GridBuilder().setRows(ROWS).setColumns(COLUMNS).addCells(cells).addObserver(observer).buildGrid();
    }

    private static List<GridCell> generateCellsInGridForm(int numberOfHints) {
        List<GridCell> hintCells = generateHintCells(numberOfHints);
        List<GridCell> emptyCells = generateEmptyCells(TOTAL_CELLS - numberOfHints);
        List<GridCell> allCells = ListHelper.merge(hintCells, emptyCells);
        Collections.shuffle(allCells);  // Random sort
        return allCells;
    }

    private static GridRuleManager createSudokuRuleManager(List<List<GridCell>> grid) {
        Collection<GridRule> sudokuRules = buildSudokuRules(grid);
        return new GridRuleManager(sudokuRules);
    }

    private static List<GridCell> generateHintCells(int numberOfHints) {
        List<Integer> hints = RandomHelper.getRandomNumbersInRange(numberOfHints, MIN_CELL_CONTENT, MAX_CELL_CONTENT);
        return hints.stream().map(SudokuFactory::createHintCell).collect(Collectors.toList());
    }

    private static List<GridCell> generateEmptyCells(int cant) {
        List<GridCell> emptyCells = new ArrayList<>();
        for (int i = 0; i < cant; i++) {
            emptyCells.add(createEmptyCell());
        }
        return emptyCells;
    }

    private static Collection<GridRule> buildSudokuRules(List<List<GridCell>> grid) {
        Collection<GridRule> sudokuRules = new ArrayList<>();
        final GridRuleOperation<Boolean> distinctOperation = new DistinctOperation();
        List<GridRuleIterator> iteratorsForAllRows = GridRuleIteratorFactory.iteratorsForAllRows(grid);
        List<GridRuleIterator> iteratorsForAllColumns = GridRuleIteratorFactory.iteratorsForAllColumns(grid);
        List<GridRuleIterator> iteratorsForAllCellBlocks = GridRuleIteratorFactory.iteratorsForAllCellBlocks(grid, ROW_DIVISIONS, COLUMN_DIVISIONS);
        List<GridRuleIterator> allIterators = ListHelper.merge(ListHelper.merge(iteratorsForAllRows, iteratorsForAllColumns), iteratorsForAllCellBlocks);
        allIterators.forEach(i -> sudokuRules.add(new EqualsRule<>(i, distinctOperation, Boolean.TRUE)));
        return sudokuRules;
    }

    private static ImmutableCell createHintCell(Integer value) {
        return new ImmutableCell(new FilledState(new CellContent<>(value)));
    }

    private static MutableCell createEmptyCell() {
        return new MutableCell(new EmptyState());
    }
}
