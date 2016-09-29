package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.ImmutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.ImmutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRule;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleCondition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIteratorFactory;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.DistinctOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.SumOperation;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;

import java.util.*;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

public class KakuroFactory {
    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int TOTAL_CELLS = ROWS * COLUMNS;

    private static final String kakuroTags = "Number";
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

    private static List<Cell> generateCellListEasy(){
        List<Cell> returnableList = new ArrayList<>();
        for (int i = 0; i < TOTAL_CELLS;i++){
            returnableList.add(createMutableCell(0));
        }
        returnableList.set(0,createImmutableCell());
        returnableList.set(1,createImmutableCell());
        returnableList.set(2,createDownSidedImmutableCell(35));
        for(int i = 72; i<TOTAL_CELLS;i++){
            returnableList.set(i,createImmutableCell());
        }
        returnableList.set(63,createUpSidedImmutableCell(5));
        returnableList.set(54,createUpSidedImmutableCell(14));
        returnableList.set(46,createDoubleSidedImmutableCell(10,6));
        returnableList.set(37,createUpSidedImmutableCell(28));
        returnableList.set(27,createUpSidedImmutableCell(5));
        returnableList.set(18,createUpSidedImmutableCell(20));
        returnableList.set(10,createDoubleSidedImmutableCell(16,11));
        returnableList.set(3,createDownSidedImmutableCell(15));
        returnableList.set(4,createImmutableCell());
        returnableList.set(5,createImmutableCell());
        returnableList.set(6,createDownSidedImmutableCell(39));
        returnableList.set(7,createDownSidedImmutableCell(14));
        returnableList.set(8,createImmutableCell());
        returnableList.set(13,createImmutableCell());
        returnableList.set(14,createUpSidedImmutableCell(12));
        returnableList.set(17,createImmutableCell());
        returnableList.set(23,createDownSidedImmutableCell(7));
        returnableList.set(24,createDoubleSidedImmutableCell(17,12));
        returnableList.set(26,createImmutableCell());
        returnableList.set(30,createDoubleSidedImmutableCell(12,15));
        returnableList.set(34,createImmutableCell());
        returnableList.set(35,createImmutableCell());
        returnableList.set(43,createDownSidedImmutableCell(13));
        returnableList.set(44,createImmutableCell());
        returnableList.set(50,createDoubleSidedImmutableCell(5,13));
        returnableList.set(53,createImmutableCell());
        returnableList.set(57,createImmutableCell());
        returnableList.set(58,createUpSidedImmutableCell(20));
        returnableList.set(62,createImmutableCell());
        returnableList.set(66,createImmutableCell());
        returnableList.set(67,createUpSidedImmutableCell(16));
        returnableList.set(70,createImmutableCell());
        returnableList.set(71,createImmutableCell());

        return returnableList;
    }
    private static List<Cell> generateCellListMedium(){
        List<Cell> returnableList = new ArrayList<>();
        for (int i = 0; i< TOTAL_CELLS;i++){
            returnableList.add(createMutableCell(0));
        }
        for (int i = 72; i < TOTAL_CELLS;i++){
            returnableList.set(i,createImmutableCell());
        }
        for (int i = 0;i < ROWS;i++){
            returnableList.set(8+(i*ROWS),createImmutableCell());
        }
        returnableList.set(0,createImmutableCell());
        returnableList.set(1,createImmutableCell());
        returnableList.set(2,createDownSidedImmutableCell(39));
        returnableList.set(3,createDownSidedImmutableCell(29));
        returnableList.set(4,createImmutableCell());
        returnableList.set(5,createDownSidedImmutableCell(12));
        returnableList.set(6,createDownSidedImmutableCell(33));
        returnableList.set(7,createImmutableCell());
        returnableList.set(9,createImmutableCell());
        returnableList.set(10,createUpSidedImmutableCell(11));
        returnableList.set(13,createDoubleSidedImmutableCell(15,4));
        returnableList.set(16,createImmutableCell());
        returnableList.set(18,createImmutableCell());
        returnableList.set(19,createUpSidedImmutableCell(19));
        returnableList.set(25,createDownSidedImmutableCell(14));
        returnableList.set(27,createImmutableCell());
        returnableList.set(28,createDoubleSidedImmutableCell(9,9));
        returnableList.set(32,createDoubleSidedImmutableCell(16,11));
        returnableList.set(36,createUpSidedImmutableCell(19));
        returnableList.set(40,createDoubleSidedImmutableCell(12,3));
        returnableList.set(45,createUpSidedImmutableCell(16));
        returnableList.set(48,createDoubleSidedImmutableCell(7,4));
        returnableList.set(52,createImmutableCell());
        returnableList.set(54,createImmutableCell());
        returnableList.set(55,createUpSidedImmutableCell(18));
        returnableList.set(61,createImmutableCell());
        returnableList.set(63,createImmutableCell());
        returnableList.set(64,createUpSidedImmutableCell(9));
        returnableList.set(67,createUpSidedImmutableCell(7));
        returnableList.set(70,createImmutableCell());

        return returnableList;
    }

    public static Grid createGrid(int difficulty){
        List<Cell> cells = new ArrayList<>();
        if (difficulty == 1) cells = generateCellListEasy();
        if (difficulty == 2) cells = generateCellListMedium();
        Grid grid = new GridBuilder().setRows(ROWS).setColumns(COLUMNS).addCells(cells).buildGrid();
        return grid;
    }
    private static Collection<GridRule> buildKakuroRules(List<List<Cell>> grid){
        Collection<GridRule> kakuroRules = new ArrayList<>();

        //iterate over rows and create distinct and sum rules
        for(int i=0;i<ROWS;i++){

        }


        String[] tags = {kakuroTags};
        List<String> cellTag = new ArrayList<>(Arrays.asList(tags));

        final GridRuleOperation<Boolean> distinctOperation = new DistinctOperation(cellTag);

        final GridRuleOperation sumOperation= new SumOperation(cellTag);



        return kakuroRules;
    }

    private static <T> void cycleArray(List<T> anArray) {
        T lastElement = anArray.get(anArray.size() - 1);
        anArray.remove(anArray.size() - 1);
        anArray.add(0, lastElement);
    }

    private static Cell createMutableCell(int number) {
        return new MutableCell(new MutableContent(number, "Number"));
    }

    private static Cell createImmutableCell(){
        return new ImmutableCell(new ImmutableContent(null,"BlackBLock"));
    }

    private static Cell createUpSidedImmutableCell(int result){
        return new ImmutableCell(new ImmutableContent(result,"CompareToRight"));
    }

    private static Cell createDownSidedImmutableCell(int result){
        return new ImmutableCell(new ImmutableContent(result,"CompareToDown"));
    }

    private static Cell createDoubleSidedImmutableCell(int upperResult, int bottomResult){
        List<Content> lista = new ArrayList<Content>();
        lista.add(new ImmutableContent(upperResult,"CompareToRight"));
        lista.add(new ImmutableContent(bottomResult,"CompareToDown"));
        return new ImmutableCell(lista);
    }
}