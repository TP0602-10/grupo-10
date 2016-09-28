package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.OnGridUpdatedObserver;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.DistinctOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.RandomHelper;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.SudokuController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Factory for creating a new game of Sudoku.
 * It builds the grid and define the rules.
 */
public class SudokuFactory {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int TOTAL_CELLS = ROWS * COLUMNS;

    private static final int ROW_DIVISIONS = 3;
    private static final int COLUMN_DIVISIONS = 3;

    private static final int MIN_CELL_CONTENT = 1;
    private static final int MAX_CELL_CONTENT = 9;

    private static List<List<Integer>> backtrackingConstructor(List<List<Integer>> temporalGrid, int step) {
        int rowIndex    = step / ROWS;
        int columnIndex = step % ROWS;
        int rowBlockIndex    = rowIndex - rowIndex % ROW_DIVISIONS;
        int columnBlockIndex = columnIndex - columnIndex % COLUMN_DIVISIONS;
        List<Integer> randomNumbers = ListHelper.createFromRange(MIN_CELL_CONTENT,MAX_CELL_CONTENT);
        Collections.shuffle(randomNumbers);
        for ( int number : randomNumbers) {
            boolean checkRow = !temporalGrid.get(rowIndex).contains(number);
            List<Integer> theColumn = temporalGrid.stream().map(row -> row.get(columnIndex)).collect(Collectors.toList());
            boolean checkColumn = !theColumn.contains(number);

            boolean checkBlock = true;
            //TODO: This is what I could do. It can be with "stream"?
            List<List<Integer>> actualRowBlock = temporalGrid.subList(rowBlockIndex,rowIndex);
            for ( List<Integer> column : actualRowBlock ) {
                List<Integer> actualColumnBlock = column.subList(columnBlockIndex,columnBlockIndex + COLUMN_DIVISIONS);
                if ( actualColumnBlock.contains(number) ) {
                    checkBlock = false;
                    break;
                }
            }
            if (checkBlock && checkColumn && checkRow) {
                temporalGrid.get(rowIndex).set(columnIndex,number);
                if ( step + 1 >= ROWS * COLUMNS || ( backtrackingConstructor(temporalGrid, step + 1) != null )) {
                    return temporalGrid;
                }
            }
        }
        temporalGrid.get(rowIndex).set(columnIndex,0);
        return null;
    }

    public static List<List<Integer>> constructorAlgorithm() {
        List<List<Integer>> temporalGrid = new ArrayList<List<Integer>>();

        //TODO: How initializate array of arrays of integers with default values?
        for ( int i = 0; i < ROWS; i++ ) {
            List<Integer> column = new ArrayList<Integer>();
            for ( int j = 0; j < COLUMNS; j++ ) {
                column.add(0);
            }
            temporalGrid.add( column );
        }
        return backtrackingConstructor(temporalGrid,0);
    }

    public static Grid createFromScratch(int numberOfHints) {
        List<GridCell> cells = generateCellsInGridForm(numberOfHints);
        OnGridUpdatedObserver observer = createSudokuRuleManager(ListHelper.buildMatrixFromFlattenList(cells, ROWS, COLUMNS));
        return new GridBuilder().setRows(ROWS).setColumns(COLUMNS).addCells(cells).addObserver(observer).buildGrid();
    }

    public static void createGameFromScratch(int numberOfHints) {
        List<GridCell> cells = generateCellsInGridForm(numberOfHints);
        GridRuleManager ruleManager = createSudokuRuleManager(ListHelper.buildMatrixFromFlattenList(cells, ROWS, COLUMNS));
        Grid grid = new GridBuilder().setRows(ROWS).setColumns(COLUMNS).addCells(cells).addObserver(ruleManager).buildGrid();
        SudokuController controller = new SudokuController(grid);
        ruleManager.addObserver(controller);
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
        final GridRuleCondition<Boolean> ruleCondition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                Boolean.TRUE
        );
        List<GridRuleIterator> iteratorsForAllRows = GridRuleIteratorFactory.iteratorsForAllRows(grid);
        List<GridRuleIterator> iteratorsForAllColumns = GridRuleIteratorFactory.iteratorsForAllColumns(grid);
        List<GridRuleIterator> iteratorsForAllCellBlocks =
                GridRuleIteratorFactory.iteratorsForAllCellBlocks(grid, ROW_DIVISIONS, COLUMN_DIVISIONS);
        List<GridRuleIterator> allIterators =
                ListHelper.merge(
                        ListHelper.merge(iteratorsForAllRows, iteratorsForAllColumns),
                        iteratorsForAllCellBlocks
                );
        allIterators.forEach(i -> sudokuRules.add(new GridRule<>(i, distinctOperation, ruleCondition)));
        return sudokuRules;
    }

    private static ImmutableCell createHintCell(Integer value) {
        return new ImmutableCell(new FilledState(new CellContent<>(value)));
    }

    private static MutableCell createEmptyCell() {
        return new MutableCell(new EmptyState());
    }
}
