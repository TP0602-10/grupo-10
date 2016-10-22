package ar.fiuba.tdd.grupo10.nikoligames.helpers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.ImmutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.NullValue;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Number;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Point;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.StringValue;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.AlwaysVerifiableRule;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.DistinctOperation;

import java.util.HashMap;
import java.util.Map;

public class ClassPathHelper {

    private static final Map<String,String> classesPackage = new HashMap<>();

    private static final Class[] classesName = {
            Cell.class,
            ImmutableContainer.class,
            MutableContainer.class,
            ImmutableContainer.class,
            MutableContent.class,
            DistinctOperation.class,
            EqualsMatcher.class,
            AlwaysVerifiableRule.class,
            Number.class,
            NullValue.class,
            StringValue.class,
            VerticalLine.class,
            HorizontalLine.class,
            FromTopToRightLine.class,
            FromBottomToLeftLine.class,
            FromTopLeftToBottomRightDiagonal.class,
            FromBottomToRightLine.class,
            FromTopToLeftLine.class,
            FromBottomLeftToTopRightDiagonal.class,
            Point.class
    };

    static {
        for (Class cl : classesName) {
            classesPackage.put(cl.getSimpleName(),cl.getPackage().getName());
        }
    }

    public static Map<String,String> getClasesPath() {
        return classesPackage;
    }
}
