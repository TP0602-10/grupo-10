package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GameBuilderErrorException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.ContainerState;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Value;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.GridRuleMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ClassPathHelper;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.FileHelper;
import ar.fiuba.tdd.grupo10.nikoligames.json.structures.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Create a game using a json file.
 */
public final class GamesBuilder {

    private static final Map<String,String> nameClass = ClassPathHelper.getClasesPath();

    public static Grid createUsingJson(String location) throws GameBuilderErrorException {

        GameStructure gameStructure = (GameStructure) FileHelper.getFileContent(location,GameStructure.class);

        if (gameStructure == null || gameStructure.getInitialBoard() == null
                || gameStructure.getBoard() == null || gameStructure.getRules() == null) {
            throw new GameBuilderErrorException("can't open file");
        }

        BoardStructure board = gameStructure.getBoard();

        if (gameStructure.getInitialBoard().size() != (board.getRows() * board.getColumns())) {
            throw new GameBuilderErrorException("Board with wrong size");
        }

        List<Cell> gridCell = createGridCell(gameStructure.getInitialBoard());

        List<GridRule> gridRules = createGrideRules(gameStructure.getRules(), gridCell);

        GridRuleManager gridRuleManager = new GridRuleManager(gridRules);
        Grid grid = new GridBuilder().setRows(board.getRows()).setColumns(board.getColumns())
                .addCells(gridCell).addObserver(gridRuleManager).buildGrid();
        gridRuleManager.addObserver(grid);

        return grid;
    }

    private static List<Cell> createGridCell(List<ContainerStructure> initialBoard) {
        List<Cell> cells = new ArrayList<>();
        initialBoard.forEach( container -> {
                Cell cell = null;
                try {
                    cell = createCell(container);
                } catch (GameBuilderErrorException e) {
                    e.printStackTrace();
                }
                cells.add(cell);
            });
        return cells;
    }

    private static Cell createCell(ContainerStructure container) throws GameBuilderErrorException {
        try {
            Class cl = Class.forName(getCompleteClassName(container.getType()));
            Constructor con = cl.getConstructor(ContainerState.class);
            ContainerState containerState = createContainerState(container.getState());
            return (Cell) con.newInstance(containerState);
        } catch (InstantiationException | InvocationTargetException
                    | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            throw new GameBuilderErrorException("Error on create Cell.");
        }
    }

    private static ContainerState createContainerState(StateStructure state) throws GameBuilderErrorException {
        try {
            Class cl = Class.forName(getCompleteClassName(state.getType()));
            Constructor con = cl.getConstructor(List.class);
            List<Content> contents = createContents(state.getContents());
            return (ContainerState) con.newInstance(contents);
        } catch (InstantiationException | InvocationTargetException
                | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            throw new GameBuilderErrorException("Error on create container state");
        }
    }

    private static List<Content> createContents(List<ContentStructure> contents) {

        List<Content> listContents = new ArrayList<>();

        contents.forEach(conten -> {
                try {
                    listContents.add(createContent(conten));
                } catch (GameBuilderErrorException e) {
                    e.printStackTrace();
                }
            });

        return listContents;
    }

    private static Content createContent(ContentStructure content) throws GameBuilderErrorException {
        Value value =  createValue(content.getValue());
        return (Content) createObject(getCompleteClassName(content.getType()),
                new Class[] {Value.class,String.class},new Object[] {value,content.getTag()});
    }

    private static Value createValue(ValueStructure valueStructure) throws GameBuilderErrorException {
        Class[] classes = {String.class};
        Object[] arguments = {valueStructure.getValue()};
        return (Value) createObject(getCompleteClassName(valueStructure.getType()),classes,arguments);
    }

    private static List<GridRule> createGrideRules(List<RuleStructure> rules, List<Cell> initialBoard)
            throws GameBuilderErrorException {

        List<GridRule> gridRules = new ArrayList<>();

        for (int i = 0; i < rules.size(); i++) {
            RuleStructure rule = rules.get(i);

            GridRuleOperation operation = createOperation(rule.getOperation());

            GridRuleCondition condition = createCondition(rule.getCondition());

            GridRuleIterator iterator = createIterator(rule.getIterator(), initialBoard);

            gridRules.add(createRule(rule.getType(),iterator,operation,condition));
        }
        return gridRules;
    }

    private static GridRule createRule(String typeGridRule,
                                       GridRuleIterator iterator,
                                       GridRuleOperation operation,
                                       GridRuleCondition condition) throws GameBuilderErrorException {
        try {
            Class cl = Class.forName(getCompleteClassName(typeGridRule));
            Constructor con = cl.getConstructor(GridRuleIterator.class, GridRuleOperation.class, GridRuleCondition.class);

            return (GridRule) con.newInstance(iterator, operation, condition);
        } catch (InstantiationException | InvocationTargetException
                | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            throw new GameBuilderErrorException("Error on create operation");
        }
    }

    private static GridRuleIterator createIterator(IteratorStructure iterator, List<Cell> board) {
        List<Container> cells = new ArrayList<>();
        iterator.getIndexes().forEach(
                index -> cells.add(board.get(index))
        );
        return new GridRuleIterator(cells ,iterator.getExplanation());
    }

    private static GridRuleCondition createCondition(ConditionStructure condition)
            throws GameBuilderErrorException {
        try {
            Class matcherClass = Class.forName(getCompleteClassName(condition.getMatcher()));
            GridRuleMatcher<Object> matcher = (GridRuleMatcher<Object>) matcherClass.newInstance();
            return new GridRuleCondition<>(matcher, condition.getGoal());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new GameBuilderErrorException("Error on create rule condition");
        }
    }

    private static GridRuleOperation createOperation(OperationStructure operationStructure)
            throws GameBuilderErrorException {
        return (GridRuleOperation) createObject(getCompleteClassName(operationStructure.getName()),
                new Class[] {List.class}, new Object[] {operationStructure.getContentTags()});
    }

    private static Object createObject(String nameClass,Class[] constructorClasses, Object[] parameters )
            throws GameBuilderErrorException {
        try {
            Class cl = Class.forName(nameClass);
            Constructor con = cl.getConstructor(constructorClasses);
            return con.newInstance(parameters);
        } catch (InstantiationException | InvocationTargetException
                | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            throw new GameBuilderErrorException("Error on create " + nameClass + ".");
        }
    }

    private static String getCompleteClassName(String className) {
        return nameClass.getOrDefault(className,"ar.fiuba.tdd.grupo10.nikoligames.") + className;
    }
}
