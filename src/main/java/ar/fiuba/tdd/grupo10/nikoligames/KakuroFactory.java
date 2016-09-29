package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.ImmutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.ImmutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.DistinctOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.SumOperation;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;

import java.util.*;


public class KakuroFactory {
    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int TOTAL_CELLS = ROWS * COLUMNS;

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;

    /*
    Generate a grid with random numbers and certain immutable cells. Then compute the sums of these numbers
    in the special cells that contain the number.
    Finally remove all the mutable cells.
    For now, there's no way of tracking difficulty, as this is all random.
    You can have 3 types of cells:
    Mutable cells, which are the ones the user fills out.
    Hint cells, which store the number that a certain row or column should add up to.
    Black cells, which serve as a way of splitting and difficulting the game a little bit.

    NOTE: for now the grid is set to be 8x8 as a fixed size, but there should be no problems
    in increasing that size.
    */
    private static List<List<Integer>> gridNumberGenerator() {
        List<Integer> randomNumbers = ListHelper.createFromRange(MIN_NUMBER, MAX_NUMBER);
        Collections.shuffle(randomNumbers);
        List<List<Integer>> randomGrid = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            List<Integer> column = new ArrayList<>();
            column.addAll(randomNumbers);
            randomGrid.add(column);
            cycleArray(randomNumbers);
        }
        return randomGrid;
    }

    private static void setImmutableCells(List<Cell> grid, List<Integer> positions) {
        for (int pos : positions) {
            grid.set(pos, createImmutableCell());
        }
    }



    private static void setSingleSidedImmutableCells(List<Cell> grid, List<List<Integer>> positions, String tag) {
        for (List<Integer> info : positions) {
            grid.set(info.get(0), createSingleValuedImmutableCell(info.get(1), tag));
        }
    }

    private static void setDoubleSidedImmutableCells(List<Cell> grid, List<List<Integer>> positions) {
        for (List<Integer> info : positions) {
            grid.set(info.get(0), createDoubleSidedImmutableCell(info.get(1), info.get(2)));
        }
    }

    private static List<Cell> populateGrid() {
        List<Cell> grid = new ArrayList<>();
        for (int i = 0; i < TOTAL_CELLS; i++) {
            grid.add(createMutableCell());
        }
        return grid;
    }

    private static void generateTestUpSidedPositions(List<List<Integer>> upSidedPositions) {
        upSidedPositions.add(Arrays.asList(9, 16));
        upSidedPositions.add(Arrays.asList(13, 4));
        upSidedPositions.add(Arrays.asList(18, 23));
        upSidedPositions.add(Arrays.asList(33, 17));
        upSidedPositions.add(Arrays.asList(45, 9));
        upSidedPositions.add(Arrays.asList(48, 24));
        upSidedPositions.add(Arrays.asList(54, 4));
        upSidedPositions.add(Arrays.asList(63, 22));
        upSidedPositions.add(Arrays.asList(68, 23));
        upSidedPositions.add(Arrays.asList(72, 24));
        upSidedPositions.add(Arrays.asList(78, 16));
    }

    private static void generateTestDownSidedPositions(List<List<Integer>> downSidedPositions) {
        downSidedPositions.add(Arrays.asList(1, 17));
        downSidedPositions.add(Arrays.asList(2, 16));
        downSidedPositions.add(Arrays.asList(5, 12));
        downSidedPositions.add(Arrays.asList(6, 6));
        downSidedPositions.add(Arrays.asList(12, 23));
        downSidedPositions.add(Arrays.asList(16, 26));
        downSidedPositions.add(Arrays.asList(17, 23));
        downSidedPositions.add(Arrays.asList(32, 23));
        downSidedPositions.add(Arrays.asList(61, 17));
        downSidedPositions.add(Arrays.asList(62, 16));
    }

    private static void generateTestDoubleSidedPositions(List<List<Integer>> doubleSidedPositions) {
        doubleSidedPositions.add(Arrays.asList(22, 29, 29));
        doubleSidedPositions.add(Arrays.asList(29, 17, 18));
        doubleSidedPositions.add(Arrays.asList(37, 27, 27));
        doubleSidedPositions.add(Arrays.asList(42, 8, 23));
        doubleSidedPositions.add(Arrays.asList(57, 21, 8));
    }


    private static void generateEasyUpSidedPositions(List<List<Integer>> upSidedPositions) {

        upSidedPositions.add(Arrays.asList(63, 5));
        upSidedPositions.add(Arrays.asList(54, 14));
        upSidedPositions.add(Arrays.asList(37, 28));
        upSidedPositions.add(Arrays.asList(27, 5));
        upSidedPositions.add(Arrays.asList(18, 20));
        upSidedPositions.add(Arrays.asList(14, 12));
        upSidedPositions.add(Arrays.asList(58, 20));
        upSidedPositions.add(Arrays.asList(67, 16));

    }

    private static void generateEasyDownSidedPositions(List<List<Integer>> downSidedPositions) {

        downSidedPositions.add(Arrays.asList(2, 35));
        downSidedPositions.add(Arrays.asList(6, 39));
        downSidedPositions.add(Arrays.asList(7, 14));
        downSidedPositions.add(Arrays.asList(43, 13));
        downSidedPositions.add(Arrays.asList(24, 7));
        downSidedPositions.add(Arrays.asList(3, 15));
    }

    private static void generateEasyDoubleSidedPositions(List<List<Integer>> doubleSidedPositions) {
        doubleSidedPositions.add(Arrays.asList(24, 17, 12));
        doubleSidedPositions.add(Arrays.asList(30, 12, 15));
        doubleSidedPositions.add(Arrays.asList(50, 5, 13));
        doubleSidedPositions.add(Arrays.asList(46, 10, 6));
        doubleSidedPositions.add(Arrays.asList(10, 16, 11));
    }

    private static void buildTemporalGridTest(List<List<Integer>> upSidedPositions,
                                              List<List<Integer>> downSidedPositions,
                                              List<List<Integer>> doubleSidedPositions) {
        generateTestUpSidedPositions(upSidedPositions);
        generateTestDownSidedPositions(downSidedPositions);
        generateTestDoubleSidedPositions(doubleSidedPositions);
    }

    private static void buildTemporalEasyGrid(List<List<Integer>> upSidedPositions,
                                              List<List<Integer>> downSidedPositions,
                                              List<List<Integer>> doubleSidedPositions) {

        generateEasyUpSidedPositions(upSidedPositions);
        generateEasyDownSidedPositions(downSidedPositions);
        generateEasyDoubleSidedPositions(doubleSidedPositions);

    }

    private static void initializeGame(List<Integer> blackPositions, List<List<Integer>> upSidedPositions,
                                       List<List<Integer>> downSidedPositions,
                                       List<List<Integer>> doubleSidedPositions,
                                       List<Cell> returnableList) {
        setImmutableCells(returnableList, blackPositions);
        setSingleSidedImmutableCells(returnableList, downSidedPositions, "CompareToDown");
        setSingleSidedImmutableCells(returnableList, upSidedPositions, "CompareToRight");
        setDoubleSidedImmutableCells(returnableList, doubleSidedPositions);


    }



    private static List<Cell> generateCellList(int difficulty) {
        List<Cell> returnableList = populateGrid();
        List<Integer> blackPositions = new ArrayList<>();
        List<List<Integer>> upSidedPositions = new ArrayList<>();
        List<List<Integer>> downSidedPositions = new ArrayList<>();
        List<List<Integer>> doubleSidedPositions = new ArrayList<>();
        if (difficulty == 2) {
            buildTemporalGridTest(upSidedPositions, downSidedPositions, doubleSidedPositions);
            blackPositions.addAll(Arrays.asList(
                    0, 3, 4, 7, 8, 27, 28, 36, 53, 76, 77, 78));
        } else {
            buildTemporalEasyGrid(upSidedPositions, downSidedPositions, doubleSidedPositions);
            blackPositions.addAll(Arrays.asList(0, 1, 4, 5, 8, 13, 17, 26, 34, 35, 44, 53, 57, 62, 66,
                    70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80));
        }

        initializeGame(blackPositions, upSidedPositions, downSidedPositions, doubleSidedPositions, returnableList);
        return returnableList;
    }



    public static Grid createGrid(int difficulty) {
        List<Cell> cells = generateCellList(difficulty);
        GridRuleManager ruleManager = createKakuroRuleManager(ListHelper.buildMatrixFromFlattenList(cells, ROWS, COLUMNS));
        Grid returnableGrid = returnGrid(cells,ruleManager);
        return returnableGrid;
    }

    public static Grid returnGrid(List<Cell> cells,GridRuleManager ruleManager) {
        Grid grid = new GridBuilder().setRows(ROWS).setColumns(COLUMNS).addCells(cells).addObserver(ruleManager).buildGrid();
        ruleManager.addObserver(grid);
        return grid;
    }

    private static GridRuleManager createKakuroRuleManager(List<List<Cell>> grid) {
        Collection<GridRule> kakuroRules = buildKakuroRules(grid);
        return new GridRuleManager(kakuroRules);
    }

    private static Collection<GridRule> buildKakuroRules(List<List<Cell>> grid) {
        Collection<GridRule> kakuroRules = new ArrayList<>();

        String[] upperTag = {"CompareToRight"};
        String[] downTag = {"CompareToDown"};

        List<String> upperCellTag = new ArrayList<>(Arrays.asList(upperTag));
        List<String> downCellTag = new ArrayList<>(Arrays.asList(downTag));
        //iterate over rows and create distinct and sum rules
        iterateRowKakuroRules(grid, upperCellTag, kakuroRules);
        iterateColumnKakuroRules(grid, downCellTag, kakuroRules);


        return kakuroRules;
    }

    private static void iterateRowKakuroRules(List<List<Cell>> grid, List<String> upperCellTag,
                                              Collection<GridRule> kakuroRules) {
        for (int i = 0; i < ROWS; i++) {
            List<Cell> row = grid.get(i);
            Integer columnIndex = 0;
            while (columnIndex < COLUMNS) {
                Cell element = row.get(columnIndex);
                int startPos;
                if (element.getContents(upperCellTag).isEmpty()) {
                    columnIndex++;
                } else {
                    columnIndex++;
                    startPos = columnIndex;
                    int goalValue = (int) element.getContent("CompareToRight").getValue();
                    element = row.get(columnIndex);
                    while (columnIndex < COLUMNS - 1 && element.isContentEditable()) {

                        columnIndex++;
                        element = row.get(columnIndex);
                    }

                    generateKakuroRules(grid, i, startPos, columnIndex, kakuroRules, goalValue,
                            upperCellTag, GridRuleIteratorFactory.iteratorForCustomRow(grid, i, startPos,
                                    columnIndex));
                }
            }
        }
    }

    private static void iterateColumnKakuroRules(List<List<Cell>> grid, List<String> downCellTag,
                                                 Collection<GridRule> kakuroRules) {
        for (int j = 0; j < COLUMNS; j++) {
            Integer rowIndex = 0;
            while (rowIndex < ROWS) {
                int startPos;
                //List<Cell> row = grid.get(rowIndex);
                Cell element = grid.get(rowIndex).get(j);
                if (element.getContents(downCellTag).isEmpty()) {
                    rowIndex++;
                } else {
                    rowIndex++;
                    startPos = rowIndex;
                    List<Cell> row = grid.get(rowIndex);
                    int goalValue = (int) element.getContent("CompareToDown").getValue();
                    element = row.get(j);
                    while (rowIndex < ROWS - 1 && element.isContentEditable()) {
                        rowIndex++;
                        row = grid.get(rowIndex);
                        element = row.get(j);
                    }
                    GridRuleIterator anIterator = GridRuleIteratorFactory.iteratorForCustomColumn(grid, j,
                            startPos, rowIndex);
                    generateKakuroRules(grid, j, startPos, rowIndex, kakuroRules, goalValue, downCellTag, anIterator);
                }

            }
        }

    }


    private static void generateKakuroRules(List<List<Cell>> grid, int columnIndex, int startPos, int endPos,
                                            Collection<GridRule> kakuroRules, int goalValue, List<String> tag,
                                            GridRuleIterator anIterator) {

        kakuroRules.add(new GridRule<>(anIterator, new DistinctOperation("Number"),
                new GridRuleCondition<>(new EqualsMatcher<>(), Boolean.TRUE)));
        kakuroRules.add(new GridRule<>(anIterator, new SumOperation("Number"),
                new GridRuleCondition<>(new EqualsMatcher<>(), goalValue)));
    }

    private static <T> void cycleArray(List<T> anArray) {
        T lastElement = anArray.get(anArray.size() - 1);
        anArray.remove(anArray.size() - 1);
        anArray.add(0, lastElement);
    }

    private static Cell createMutableCell() {
        return new MutableCell(new MutableContent<>(null, "Number"));
    }

    private static Cell createSingleValuedImmutableCell(int result, String tag) {
        return new ImmutableCell(new ImmutableContent<>(result, tag));
    }

    private static Cell createImmutableCell() {
        return new ImmutableCell(new ImmutableContent<>(null, "BlackBLock"));
    }

    private static Cell createUpSidedImmutableCell(int result) {
        return new ImmutableCell(new ImmutableContent<>(result, "CompareToRight"));
    }

    private static Cell createDownSidedImmutableCell(int result) {
        return new ImmutableCell(new ImmutableContent<>(result, "CompareToDown"));
    }

    private static Cell createDoubleSidedImmutableCell(int upperResult, int bottomResult) {
        List<Content> contentList = new ArrayList<>();
        contentList.add(new ImmutableContent<>(upperResult, "CompareToRight"));
        contentList.add(new ImmutableContent<>(bottomResult, "CompareToDown"));
        return new ImmutableCell(contentList);
    }
}

