package ar.fiuba.tdd.grupo10.nikoligames;


import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.ImmutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
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

    private static final int goalIndex = 0;
    private static final int goalValue = 1;

    private static final String LINETAG = "LINE";
    private static final String GOALTAG = "NUMBER";

    private Cell createMutableCell() {
        return new Cell( new MutableContainer( new MutableContent<>(null,LINETAG) ) );
    }

    private List<Cell> generateCellsInGridForm() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < ROWS * COLUMNS; i++) {
            cells.add( createMutableCell() );
        }
        return cells;
    }

    private int goalRoomsIndexFromRoom( Integer[] cellsRoomIndex ) {
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

    private List<GridRule> createRoomRules( List<Cell> cells ) {
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
                cells.get(goalCellIndex).setContent(new ImmutableContent<>(goalCellValue,GOALTAG));
                condition = new GridRuleCondition<>(
                        new EqualsMatcher<>(),
                        goalCellValue
                );
            }
            roomRules.add( new AlwaysVerifiableRule<>(iterator,operation,condition) );
        }
        return roomRules;
    }

    private GridRule createCircuitRule( List<Cell> cells ) {
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

    private GridRuleManager createCountryRoadRuleManager( List<Cell> cells ) {
        List<GridRule> rules = new ArrayList<>();
        rules.addAll(createRoomRules(cells));
        rules.add(createCircuitRule(cells));

        return new GridRuleManager(rules);

    }

    public Grid createGrid() {
        List<Cell> cells = generateCellsInGridForm();
        GridRuleManager ruleManager = createCountryRoadRuleManager( cells );
        Grid grid = new GridBuilder().setRows(ROWS).setColumns(COLUMNS).addCells(cells).addObserver(ruleManager).buildGrid();
        ruleManager.addObserver(grid);
        return grid;
    }

}
