package ar.fiuba.tdd.grupo10.nikoligames;


import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.ImmutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.ImmutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Number;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.HorizontalLine;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.VerticalLine;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.GreaterThanIntegerMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.CountFilledOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.LineCircuitOperation;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountryRoadFactory {
    private static final int ROWS = 7;
    private static final int COLUMNS = 7;
    private static final Integer[][] roomsIndex = {
            {0,1,7,8},
            {2,3,4,5,11,18},
            {6},
            {9,10,17},
            {12,19},
            {13,20,27},
            {14},
            {15,16,22,23},
            {24},
            {25,26},
            {32,33},
            {34},
            {40,41},
            {47,48},
            {31,38,39},
            {29,36},
            {21,28,35},
            {30,37,44,45,46,43},
            {47,48,40,41},
            {42}
    };

    private static final Integer[][] goalRoomsIndexValues = {
            {0,4},
            {12,1},
            {13,3},
            {15,2},
            {21,1},
            {25,4},

    };

    private static final Integer[] cellsWithRightBorder = {
            1,8,5,10,11,12,14,16,17,19,18,21,23,24,26,28,29,30,31,33,35,36,37,39,42,46
    };

    private static final Integer[] cellsWithBottomBorder = {
            2,3,5,6,7,8,9,14,17,18,19,22,23,24,27,32,33,34,35,36,38,39
    };

    private static final int goalIndex = 0;
    private static final int goalValue = 1;

    private static final String LINETAG = "LINE";
    private static final String LINEBORDERRIGHTTAG = "LINEBORDERRIGHT";
    private static final String LINEBORDERBOTTOMTAG = "LINEBORDERBOTTOM";
    private static final String GOALTAG = "NUMBER";

    private static Cell createMutableCell() {
        return new Cell( new MutableContainer( new MutableContent<>(null,LINETAG) ) );
    }

    private static List<Cell> generateCellsInGridForm() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < ROWS * COLUMNS; i++) {
            cells.add( createMutableCell() );
        }
        //doCellNeighbours( cells );
        //TODO: When the Boundaries fix set neighbour change this line position
        generateCellsBordersRooms( cells );

        return cells;
    }

    private static Container createBorder( String tag, Line line ) {
        return new Container( new ImmutableContainer( new ImmutableContent<>(line,tag) ) );
    }

    private static void generateCellsBordersRooms( List<Cell> cells ) {
        addBottomBordersToCells(cells);
        addRightBordersToCells(cells);
    }

    private static void addRightBordersToCells( List<Cell> cells ) {
        for (int index : cellsWithRightBorder) {
            cells.get(index).setLimitAt( createBorder(LINEBORDERRIGHTTAG, new VerticalLine()), NeighbourPosition.RIGHT );
        }
    }

    private static void addBottomBordersToCells( List<Cell> cells ) {
        for (int index : cellsWithBottomBorder) {
            cells.get(index).setLimitAt( createBorder(LINEBORDERBOTTOMTAG,new HorizontalLine()), NeighbourPosition.BOTTOM );
        }
    }

    private static void setTopNeighbour( List<Cell> cells, int index ) {
        if ( index >= COLUMNS ) {
            cells.get(index).setNeighbourAt( cells.get(index - COLUMNS), NeighbourPosition.TOP );
        }
    }

    private static void setBottomNeighbour( List<Cell> cells, int index ) {
        if ( index < (COLUMNS * (ROWS - 1 )) ) {
            cells.get(index).setNeighbourAt( cells.get(index + COLUMNS), NeighbourPosition.BOTTOM );
        }
    }

    private static void setLeftNeighbour( List<Cell> cells, int index ) {
        if ( index % COLUMNS != 0 ) {
            cells.get(index).setNeighbourAt( cells.get(index - 1), NeighbourPosition.LEFT );
        }
    }

    private static void setRightNeighbour( List<Cell> cells, int index ) {
        if ( (index + 1) % COLUMNS != 0 ) {
            cells.get(index).setNeighbourAt( cells.get(index + 1), NeighbourPosition.RIGHT );
        }
    }

    private static void doCellNeighbours( List<Cell> cells ) {
        for (int i = 0; i < cells.size(); i++) {
            setTopNeighbour( cells, i );
            setBottomNeighbour( cells, i );
            setLeftNeighbour( cells, i );
            setRightNeighbour( cells, i );
        }
    }

    private static int goalRoomsIndexFromRoom( Integer[] cellsRoomIndex ) {
        int index = 0;
        for ( Integer[] goal : goalRoomsIndexValues ) {
            List<Integer> room = ListHelper.createListFromArray( cellsRoomIndex );
            if (room.contains(goal[goalIndex])) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private static List<GridRule> createRoomRules( List<Cell> cells ) {
        List<GridRule> roomRules = new ArrayList<>();
        for ( Integer[] cellsIndex : roomsIndex ) {
            List<Container> listForIterator = new ArrayList<>();
            for (int index : cellsIndex ) {
                listForIterator.add( cells.get(index) );
            }
            GridRuleIterator iterator = new GridRuleIterator(listForIterator,"Rule for a Room");
            GridRuleOperation<Integer> operation = new CountFilledOperation(LINETAG);
            GridRuleCondition<Integer> condition;

            int goalIndexSearched = goalRoomsIndexFromRoom(cellsIndex);

            if (goalIndexSearched == -1) {
                condition = new GridRuleCondition<>(
                        new GreaterThanIntegerMatcher<>(),
                        0
                );
            } else {
                int goalCellIndex = goalRoomsIndexValues[goalIndexSearched][goalIndex];
                int goalCellValue = goalRoomsIndexValues[goalIndexSearched][goalValue];
                cells.get(goalCellIndex).setContent(new ImmutableContent<>(new Number(Integer.toString(goalCellValue)),GOALTAG));
                condition = new GridRuleCondition<>(
                        new EqualsMatcher<>(),
                        goalCellValue
                );
            }
            roomRules.add( new AlwaysVerifiableRule<>(iterator,operation,condition) );
        }
        return roomRules;
    }

    private static GridRule createCircuitRule( List<Cell> cells ) {
        List<Container> cellAsContainers = new ArrayList<>();
        for (Cell cell : cells) {
            cellAsContainers.add(cell);
        }
        GridRuleIterator iterator = new GridRuleIterator(cellAsContainers, "Rule for line circuit in grid");
        GridRuleOperation<Boolean> operation = new LineCircuitOperation(LINETAG);
        GridRuleCondition<Boolean> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                Boolean.TRUE
        );
        return new AlwaysVerifiableRule<>(iterator,operation,condition);
    }

    private static GridRuleManager createCountryRoadRuleManager( List<Cell> cells ) {
        List<GridRule> rules = new ArrayList<>();
        rules.addAll(createRoomRules(cells));
        rules.add(createCircuitRule(cells));

        return new GridRuleManager(rules);

    }

    public static Grid createGrid() {
        List<Cell> cells = generateCellsInGridForm();
        GridRuleManager ruleManager = createCountryRoadRuleManager( cells );
        Grid grid = new GridBuilder().setRows(ROWS).setColumns(COLUMNS).addCells(cells)
                .doNeighborlyRelations()
                .addObserver(ruleManager).buildGrid();
        ruleManager.addObserver(grid);
        return grid;
    }

}
