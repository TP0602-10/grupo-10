package ar.fiuba.tdd.grupo10.nikoligames;


import ar.fiuba.tdd.grupo10.nikoligames.exceptions.WrongNumberOfGridCellsException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.DistinctOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.ProductOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.SumOperation;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class InshiNoHeyaFactory {
    private static final int COLS = 5;
    private static final int ROWS = 5;

    public static final String DEFAULT_TAG = "_";

    public static final String EXTERN_MAP_ROW = "row";
    public static final String EXTERN_MAP_COL = "col";
    public static final String EXTERN_MAP_VALUE = "val";

    private static final Integer MIN_VALUE = 1;
    private static final Integer MAX_VALUE = 5;

    private static final int[][] ROOMS_POSITIONS = {
            {0,5},
            {1,2},
            {3},
            {4,9,14},
            {6,7},
            {8,13},
            {10,11},
            {12,17,22},
            {15,20},
            {16,21},
            {18,19},
            {23,24},
    };

    private static boolean USE_POS = false;
    private static List<Cell> CELLS;
    private static GridRuleManager RULE_MANAGER = new GridRuleManager();

    private static int fromPositionToIndex( int rowIndex, int colIndex ) {
        int toIndex = (USE_POS) ? 1 : 0;
        return (ROWS * (rowIndex - toIndex)) + (colIndex - toIndex);
    }

    private static int searchIndexInRoomsPosition( int index ) {
        int searchIndex = 0;
        for ( int[] cellsIndex : ROOMS_POSITIONS ) {
            for ( int cellIndex : cellsIndex ) {
                if (cellIndex == index) {
                    return searchIndex;
                }
            }
            searchIndex++;

        }
        return -1;
    }

    private static List<Cell> getCellsFromPositions( int[] positions ) {
        List<Cell> cells = new ArrayList<>();
        for (int position : positions) {
            cells.add( CELLS.get(position) );
        }
        return cells;
    }

    private static void createRoomsRules(List<Map<String,Integer>> roomsGoals) {
        /*
            [ { row, col, value }, {...} ]
        */
        for ( Map<String,Integer> goals : roomsGoals ) {
            int rowIndex = goals.get(EXTERN_MAP_ROW);
            int colIndex = goals.get(EXTERN_MAP_COL);
            int value = goals.get(EXTERN_MAP_VALUE);

            int positionToSearch = fromPositionToIndex(rowIndex,colIndex);
            int indexInRoomsPositions = searchIndexInRoomsPosition(positionToSearch);

            if (indexInRoomsPositions < 0) {
                String pos = "index: " + positionToSearch + ", row: " + rowIndex + ", col: " + colIndex + ", value: " + value;
                System.out.print(
                        "Invalid position of the Room Goal (" + pos + "). Cannot find the room in the table"
                );
                System.exit(1);
            }

            int[] room = ROOMS_POSITIONS[indexInRoomsPositions];

            GridRuleOperation<Integer> operation = new ProductOperation(DEFAULT_TAG);
            GridRuleCondition<Integer> condition = new GridRuleCondition<>(
                    new EqualsMatcher<>(),
                    value
            );

            GridRuleIterator iterator = new GridRuleIterator(
                    getCellsFromPositions(room),
                    "Iterate Room [" + toStringPostionsRoom(room) + "] for goal value: " + value
            );

            GridRule<Integer> rule = new AlwaysVerifiableRule<>(iterator,operation,condition);
            RULE_MANAGER.addRule(rule);
        }

    }

    public static int[] getPositionFromIndex(int index) {
        int toPos = (USE_POS) ? 1 : 0;
        return new int[]{ (index / ROWS) + toPos, (index % COLS) + toPos };
    }

    private static String toStringPostionsRoom(int[] roomIndexs) {
        StringBuffer stringMsgBuf = new StringBuffer();
        for ( int indexGrid : roomIndexs) {
            int[] rowAndCol = getPositionFromIndex(indexGrid);
            stringMsgBuf.append("(" + rowAndCol[0] + "," + rowAndCol[1] + "),");
        }
        return stringMsgBuf.toString().substring(0, stringMsgBuf.length() - 1);
    }

    private static int getSumGoal() {
        int sumGoal = 0;
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            sumGoal += i;
        }
        return sumGoal;
    }

    private static void createDefaultRules() {
        GridRuleOperation<Boolean> distinctOperation = new DistinctOperation(DEFAULT_TAG);
        GridRuleCondition<Boolean> distinctCondition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                Boolean.TRUE
        );


        GridRuleOperation<Integer> sumOperation = new SumOperation(DEFAULT_TAG);
        GridRuleCondition<Integer> sumCondition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                getSumGoal()
        );

        List<List<Cell>> grid = ListHelper.buildMatrixFromFlattenList(CELLS,ROWS,COLS);

        List<GridRuleIterator> iterators = GridRuleIteratorFactory.iteratorsForAllColumns(grid);

        iterators.addAll( GridRuleIteratorFactory.iteratorsForAllRows(grid) );

        for (GridRuleIterator iterator : iterators) {
            RULE_MANAGER.addRule( new CompleteIteratorRule<>(
                    iterator, sumOperation, sumCondition)
            );
            RULE_MANAGER.addRule( new AlwaysVerifiableRule<>(
                    iterator, distinctOperation, distinctCondition
            ));
        }


    }

    private static void createCells() {
        CELLS = new ArrayList<>();
        for (int i = 0; i < ROWS * COLS; i++) {
            CELLS.add( new MutableCell( new MutableContent<Integer>(null,DEFAULT_TAG)) );
        }
    }

    public static Grid createGrid(List<Map<String,Integer>> roomsGoals, boolean usePostion) throws WrongNumberOfGridCellsException {
        USE_POS = usePostion;
        createCells();
        createDefaultRules();
        createRoomsRules(roomsGoals);
        Grid grid = new GridBuilder().setRows(ROWS).setColumns(COLS).addCells(CELLS).addObserver(RULE_MANAGER).buildGrid();
        RULE_MANAGER.addObserver(grid);
        return grid;
    }

}
