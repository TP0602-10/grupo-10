package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.WrongNumberOfGridCellsException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.ImmutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.ImmutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.DistinctOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.RandomHelper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Factory for creating a new game of Sudoku.
 * It builds the grid and define the rules.
 */
public final class SudokuFactory {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int TOTAL_CELLS = ROWS * COLUMNS;

    private static final int ROW_DIVISIONS = 3;
    private static final int COLUMN_DIVISIONS = 3;

    private static final int MIN_CELL_CONTENT = 1;
    private static final int MAX_CELL_CONTENT = 9;

    private static final String GLOBAL_TAG = "Tag";

    private static boolean checkInsertNumberInTemporalGrid(List<List<Integer>> temporalGrid,
                                                           int number, int rowIndex, int colIndex,
                                                           int rowBlockIndex, int colBlockIndex) {
        boolean checkRow = !temporalGrid.get(rowIndex).contains(number);

        boolean checkColumn = !temporalGrid.stream().map(row -> row.get(colIndex))
                .collect(Collectors.toList())
                .contains(number);

        List<List<Integer>> actualRowsBlock = temporalGrid.subList(rowBlockIndex,rowIndex);
        List<Integer> valuesInActualBlock = new ArrayList<>();
        for ( List<Integer> column : actualRowsBlock ) {
            valuesInActualBlock.addAll(column.subList(colBlockIndex, colBlockIndex + COLUMN_DIVISIONS));
        }
        boolean checkBlock = !valuesInActualBlock.contains(number);

        return (checkBlock && checkColumn && checkRow);
    }

    private static List<List<Integer>> backtrackingConstructor(List<List<Integer>> temporalGrid, int step) {
        int rowIndex      = step / ROWS;
        int colIndex      = step % ROWS;
        int rowBlockIndex = rowIndex - rowIndex % ROW_DIVISIONS;
        int colBlockIndex = colIndex - colIndex % COLUMN_DIVISIONS;
        List<Integer> randomNumbers = ListHelper.createFromRange(MIN_CELL_CONTENT, MAX_CELL_CONTENT);
        Collections.shuffle(randomNumbers);
        for ( int number : randomNumbers) {
            if ( checkInsertNumberInTemporalGrid(temporalGrid, number,
                    rowIndex, colIndex, rowBlockIndex, colBlockIndex) ) {
                temporalGrid.get(rowIndex).set(colIndex,number);
                if ( step + 1 >= ROWS * COLUMNS || ( backtrackingConstructor(temporalGrid, step + 1) != null )) {
                    return temporalGrid;
                }
            }
        }
        temporalGrid.get(rowIndex).set(colIndex,0);
        return null;
    }

    private static List<List<Integer>> constructorAlgorithm() {
        List<List<Integer>> temporalGrid = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            temporalGrid.add(new ArrayList<>(Collections.nCopies(COLUMNS, 0)));
        }
        return backtrackingConstructor(temporalGrid, 0);
    }

    public static Grid createGridFromScratch(int numberOfHints) throws WrongNumberOfGridCellsException {
        List<Cell> cells = generateCellsInGridForm(numberOfHints);
        GridRuleManager ruleManager = createSudokuRuleManager(ListHelper.buildMatrixFromFlattenList(cells, ROWS, COLUMNS));
        Grid grid = new GridBuilder().setRows(ROWS).setColumns(COLUMNS).addCells(cells).addObserver(ruleManager).buildGrid();
        ruleManager.addObserver(grid);
        return grid;
    }

    private static List<Cell> generateCellsInGridForm(int numberOfHints) {
        List<List<Integer>> solution = constructorAlgorithm();
        List<Cell> allCells = generateEmptyCells(TOTAL_CELLS);
        Map<Integer,List<Integer>> selectedHints = new HashMap<>();
        int createdHints = 0;
        while ( createdHints < numberOfHints ) {
            int randomRow = RandomHelper.getRandomNumberInRange(0, ROWS - 1);
            if (!selectedHints.containsKey(randomRow)) {
                selectedHints.put(randomRow, new ArrayList<>());
            }

            int randomCol = RandomHelper.getRandomNumberInRange(0, COLUMNS - 1);
            if (!selectedHints.get(randomRow).contains(randomCol)) {
                selectedHints.get(randomRow).add(randomCol);
                allCells.set((ROWS * randomRow) + randomCol , createHintCell( solution.get(randomRow).get(randomCol) ) );
                createdHints++;
            }
        }
        return allCells;
    }

    private static GridRuleManager createSudokuRuleManager(List<List<Cell>> grid) {
        Collection<GridRule> sudokuRules = buildSudokuRules(grid);
        return new GridRuleManager(sudokuRules);
    }

    private static List<Cell> generateEmptyCells(int cant) {
        List<Cell> emptyCells = new ArrayList<>();
        for (int i = 0; i < cant; i++) {
            emptyCells.add(createEmptyCell());
        }
        return emptyCells;
    }

    private static Collection<GridRule> buildSudokuRules(List<List<Cell>> grid) {
        Collection<GridRule> sudokuRules = new ArrayList<>();
        String[] tags = {GLOBAL_TAG};
        List<String> cellTag = new ArrayList<>( Arrays.asList(tags) );
        final GridRuleOperation<Boolean> distinctOperation = new DistinctOperation(cellTag);
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
        return new ImmutableCell(new ImmutableContent<>(value, GLOBAL_TAG));
    }

    private static MutableCell createEmptyCell() {
        return new MutableCell(new MutableContent<>(null, GLOBAL_TAG));
    }
}
