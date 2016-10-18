package ar.fiuba.tdd.grupo10.nikoligames;


import ar.fiuba.tdd.grupo10.nikoligames.exceptions.WrongNumberOfGridCellsException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.ImmutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.GridRuleMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.LessOrEqualToIntegerMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.DistinctOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.ProductOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.SumOperation;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Create a Grid of InshiNoHeya Game. A grid it has preset the rooms. (ROOMS_POSITIONS)
 * The params are
 * roomsGoals: List of hashMaps who have the goals of the rooms.
 *  Each hash should have three keys EXTERN_MAP_ROW, EXTERN_MAP_COL and EXTERN_MAP_VALUE.
 * usePostion: Boolean. if true. ROW and COL are read as positions (first = 1).
 * If false, are read as index (first = 0). Position = Index + 1
 */
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
        String pos = "index: " + index;
        System.out.print(
                "Invalid position of the Room Goal (" + pos + "). Cannot find the room in the table"
        );
        System.exit(1);
        return -1;
    }

    private static List<Container> getCellsFromPositions(int[] positions ) {
        List<Container> cells = new ArrayList<>();
        for (int position : positions) {
            cells.add( CELLS.get(position) );
        }
        return cells;
    }

    private static GridRuleCondition<Integer> createCondition(GridRuleMatcher<Integer> matcher, int goal) {
        return new GridRuleCondition<>(
                matcher,
                goal
        );
    }

    private static void createRoomsRules(List<Map<String,Integer>> roomsGoals) {
        for ( Map<String,Integer> goals : roomsGoals ) {
            int row = goals.get(EXTERN_MAP_ROW);
            int col = goals.get(EXTERN_MAP_COL);
            int value = goals.get(EXTERN_MAP_VALUE);

            int positionToSearch = fromPositionToIndex(row,col);
            int indexInRoomsPositions = searchIndexInRoomsPosition(positionToSearch);


            CELLS.get(positionToSearch).setContent( new ImmutableContent<>(value,"GOAL") );



            int[] room = ROOMS_POSITIONS[indexInRoomsPositions];

            GridRuleOperation<Integer> operation = new ProductOperation(DEFAULT_TAG);
            GridRuleCondition<Integer> conditionAlways = createCondition(
                    new LessOrEqualToIntegerMatcher<>(),
                    value
            );

            GridRuleCondition<Integer> conditionComplete = createCondition(
                    new EqualsMatcher<>(),
                    value
            );

            GridRuleIterator iterator = new GridRuleIterator(
                    getCellsFromPositions(room),
                    "Iterate Room [" + toStringPostionsRoom(room) + "] for goal value: " + value
            );

            GridRule<Integer> ruleAlways = new AlwaysVerifiableRule<>(iterator,operation,conditionAlways);
            RULE_MANAGER.addRule(ruleAlways);
            GridRule<Integer> ruleComplete = new CompleteIteratorRule<>(iterator,operation,conditionComplete);
            RULE_MANAGER.addRule(ruleComplete);
        }

    }

    public static int[] getPositionFromIndex(int index) {
        int toPos = (USE_POS) ? 1 : 0;
        return new int[]{ (index / ROWS) + toPos, (index % COLS) + toPos };
    }

    private static String toStringPostionsRoom(int[] roomIndexs) {
        StringBuilder stringMsgBuf = new StringBuilder();
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


        int sumGoal = getSumGoal();
        GridRuleOperation<Integer> sumOperation = new SumOperation(DEFAULT_TAG);
        GridRuleCondition<Integer> sumConditionAlways = new GridRuleCondition<>(
                new LessOrEqualToIntegerMatcher<>(),
                sumGoal
        );

        GridRuleCondition<Integer> sumConditionComplete = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                sumGoal
        );

        List<List<Container>> grid = ListHelper.buildMatrixFromFlattenList(
                CELLS.stream().map(c -> (Container) c).collect(Collectors.toList()),
                ROWS,
                COLS
        );

        List<GridRuleIterator> iterators = GridRuleIteratorFactory.iteratorsForAllColumns(grid);

        iterators.addAll( GridRuleIteratorFactory.iteratorsForAllRows(grid) );

        for (GridRuleIterator iterator : iterators) {
            RULE_MANAGER.addRule( new AlwaysVerifiableRule<>(
                    iterator, sumOperation, sumConditionAlways)
            );
            RULE_MANAGER.addRule( new CompleteIteratorRule<>(
                    iterator, sumOperation, sumConditionComplete)
            );
            RULE_MANAGER.addRule( new AlwaysVerifiableRule<>(
                    iterator, distinctOperation, distinctCondition
            ));
        }


    }

    private static void createCells() {
        CELLS = new ArrayList<>();
        for (int i = 0; i < ROWS * COLS; i++) {
            CELLS.add( new Cell(new MutableContainer(new MutableContent<Integer>(null,DEFAULT_TAG))) );
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
