package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operation;


import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.AlwaysVerifiableRule;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRule;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleCondition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.LineCircuitOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.LineContinousOperation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LineCircuitOperationTests {
    private List<Container> theCells;
    private GridRule<Boolean> ruleWithLineCircuitOperation;

    private static final String TAG = "tag";

    private void createRule() {
        GridRuleIterator iterator = new GridRuleIterator(
                theCells,
                "Iterator for LineCircuitOperationTests"
        );

        GridRuleCondition<Boolean> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                Boolean.TRUE
        );

        GridRuleOperation<Boolean> operation = new LineCircuitOperation(TAG);

        ruleWithLineCircuitOperation = new AlwaysVerifiableRule<>(
                iterator,
                operation,
                condition
        );
    }

    private Cell createCell( Line contentValue ) {
        return new Cell( new MutableContainer( new MutableContent<>(contentValue,TAG) ) );
    }

    private void generateNeighbours() {
        List<Integer> notLeftNeighbour = new ArrayList<>();
        notLeftNeighbour.add(0);
        notLeftNeighbour.add(3);
        notLeftNeighbour.add(6);

        List<Integer> notRighNeighbour = new ArrayList<>();
        notRighNeighbour.add(2);
        notRighNeighbour.add(5);
        notRighNeighbour.add(8);

        for (int i = 0; i < theCells.size(); i++) {
            Cell actualCell = (Cell)theCells.get(i);

            if ( !notLeftNeighbour.contains(i) ) {
                actualCell.setLeftNeighbour( theCells.get( i - 1 ) );
            }
            if ( i > 2 ) {
                actualCell.setTopNeighbour( theCells.get( i - 3 ) );
            }
            if ( i < 6 ) {
                actualCell.setBottomNeighbour( theCells.get( i + 3 ) );
            }
            if ( !notRighNeighbour.contains(i) ) {
                actualCell.setRightNeighbour( theCells.get( i + 1) );
            }
        }
    }

    private void createCellsWithCircuit() {
        /*
           Lines: ┐ ┌ - | └ ┘
         =============
          1.  4 ┌ ┐
          2.  ┌ ┘ |
          3.  └ - ┘
         =============
         */
        theCells = new ArrayList<>();

        //First Row
        theCells.add( new Cell(new MutableContainer( new MutableContent<>(4,TAG) )) );
        theCells.add( createCell( new FromBottomToRightLine() ));
        theCells.add( createCell( new FromBottomToLeftLine() ));

        //Second Row
        theCells.add(createCell( new FromBottomToRightLine() ));
        theCells.add(createCell( new FromTopToLeftLine() ));
        theCells.add(createCell( new VerticalLine() ));

        //Third Row
        theCells.add(createCell( new FromTopToRightLine() ));
        theCells.add(createCell( new HorizontalLine() ));
        theCells.add(createCell( new FromTopToLeftLine() ));
    }

    private void createCellsWithoutCircuit() {
        createCellsWithCircuit();
        /*
           Lines: ┐ ┌ - | └ ┘
         =============
          1.  4 ┌ ┐
          2.  ┌ ┘ |
          3.  └ - ┘
         =============

         Change to generate invalid circuit:
         =============
          3.  └ | ┘
         =============
         */

        theCells.set(7, createCell( new VerticalLine() ));
    }

    @Test(expected = Test.None.class)
    public void cellsHaveCircuit() {
        createCellsWithCircuit();
        generateNeighbours();
        createRule();
        ruleWithLineCircuitOperation.verifyRule();
    }

    @Test(expected = RuleNotSatisfiedException.class)
    public void cellsNotHaveCircuit() {
        createCellsWithoutCircuit();
        generateNeighbours();
        createRule();
        ruleWithLineCircuitOperation.verifyRule();
    }


}
