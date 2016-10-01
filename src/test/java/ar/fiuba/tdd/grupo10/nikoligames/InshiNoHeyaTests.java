package ar.fiuba.tdd.grupo10.nikoligames;


import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InshiNoHeyaTests {

    private List<Map<String,Integer>> theGoalsValues;
    private boolean usePositionToIndex = true;
    private List<Map<String,Integer>> solution;

    private void createSolution() {
        int[] solutionArray = {
                3,4,1,5,2,
                2,1,3,4,5,
                5,3,2,1,4,
                1,5,4,2,3,
                4,2,5,3,1
        };
        solution = new ArrayList<>();
        int index = 0;
        for (int solValue : solutionArray) {
            Map<String,Integer> cellSolution = new HashMap<>();
            cellSolution.put(InshiNoHeyaFactory.EXTERN_MAP_VALUE, solValue);
            int[] rowColPostion = InshiNoHeyaFactory.getPositionFromIndex(index);
            cellSolution.put(InshiNoHeyaFactory.EXTERN_MAP_ROW, rowColPostion[0]);
            cellSolution.put(InshiNoHeyaFactory.EXTERN_MAP_COL, rowColPostion[1]);
            solution.add(cellSolution);
            index++;
        }

    }

    private void createGame() {
        // GoalArray = [row,col,value]
        int[][] goalsArray = {
                {1,1,6},
                {1,2,4},
                {1,4,5},
                {1,5,40},
                {2,2,3},
                {2,4,4},
                {3,2,15},
                {3,3,40},
                {4,1,4},
                {4,2,10},
                {4,4,6},
                {5,4,3}
        };

        theGoalsValues = new ArrayList<>();
        for (int i = 0; i < goalsArray.length; i++) {
            int row     = goalsArray[i][0];
            int col     = goalsArray[i][1];
            int value   = goalsArray[i][2];
            Map<String,Integer> goalMap = new HashMap<>();
            goalMap.put(InshiNoHeyaFactory.EXTERN_MAP_ROW,row);
            goalMap.put(InshiNoHeyaFactory.EXTERN_MAP_COL,col);
            goalMap.put(InshiNoHeyaFactory.EXTERN_MAP_VALUE,value);
            theGoalsValues.add(goalMap);
        }
    }

    @Before
    public void setUp() {
        createGame();
        createSolution();

    }

    @Test
    public void createGrid() {
        Grid inshiGrid = InshiNoHeyaFactory.createGrid(theGoalsValues,usePositionToIndex);
        Assert.assertNotNull(inshiGrid);
        Assert.assertFalse(inshiGrid.isComplete());
    }

    @Test
    public void playTheGame() {
        Grid inshiGrid = InshiNoHeyaFactory.createGrid(theGoalsValues,usePositionToIndex);
        Assert.assertFalse(inshiGrid.isComplete());
        for (Map<String,Integer> play : solution) {
            int value = play.get(InshiNoHeyaFactory.EXTERN_MAP_VALUE);
            int row = play.get(InshiNoHeyaFactory.EXTERN_MAP_ROW);
            int col = play.get(InshiNoHeyaFactory.EXTERN_MAP_COL);
            inshiGrid.getCellAt(row,col).setValue(value);
        }
        Assert.assertTrue(inshiGrid.isComplete());
    }
}
