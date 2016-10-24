package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.FileReadException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GameBuilderErrorException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.ContainerState;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Value;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.GridRuleMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ClassPathHelper;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.FileHelper;
import ar.fiuba.tdd.grupo10.nikoligames.json.structures.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Create a game using a json file.
 */
public final class GamesBuilder {

    private static final Map<String,String> nameClass = ClassPathHelper.getClasesPath();

    public static Grid createUsingJson(String location) throws GameBuilderErrorException {

        GameStructure gameStructure = null;
        try {
            gameStructure = (GameStructure) FileHelper.getFileContent(location,GameStructure.class);
        } catch (FileReadException e) {
            throw new GameBuilderErrorException("Error on create game by json file: " + location);
        }

        if (gameStructure == null || gameStructure.getInitialBoard() == null
                || gameStructure.getBoard() == null || gameStructure.getRules() == null) {
            throw new GameBuilderErrorException("can't open file");
        }

        BoardStructure board = gameStructure.getBoard();

        checkCorrectInitialBoardSize(gameStructure.getInitialBoard().size(), board);

        List<Cell> gridCell = createGridCell(gameStructure.getInitialBoard());

        setLimitsOfBoard(gridCell,gameStructure.getCellLimits());

        List<GridRule> gridRules = createGrideRules(gameStructure.getRules(), gridCell);

        GridRuleManager gridRuleManager = new GridRuleManager(gridRules);
        Grid grid = new GridBuilder().setRows(board.getRows()).setColumns(board.getColumns())
                .addCells(gridCell).doNeighborlyRelations().addObserver(gridRuleManager).buildGrid();
        gridRuleManager.addObserver(grid);

        return grid;
    }

    private static void checkCorrectInitialBoardSize(int size, BoardStructure board) throws GameBuilderErrorException {
        if (size != (board.getRows() * board.getColumns())) {
            throw new GameBuilderErrorException("Board with wrong size");
        }
    }

    private static void setLimitsOfBoard(List<Cell> gridCell, List<CellLimit> cellLimits) {
        if (cellLimits != null && !cellLimits.isEmpty())  {
            setCellLimits(cellLimits,gridCell);
        }
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

        for (RuleStructure rule : rules) {
            GridRuleOperation operation = createOperation(rule.getOperation(), initialBoard);

            GridRuleCondition condition = createCondition(rule.getCondition(), rule.getOperation().getType());

            GridRuleIterator iterator = createIterator(rule.getIterator(), initialBoard);

            gridRules.add(createRule(rule.getType(), iterator, operation, condition));
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

    private static GridRuleCondition createCondition(ConditionStructure condition,String typeGoal)
            throws GameBuilderErrorException {
        try {
            Class matcherClass = Class.forName(getCompleteClassName(condition.getMatcher()));
            GridRuleMatcher<Object> matcher = (GridRuleMatcher<Object>) matcherClass.newInstance();
            Object goal = createGoal(typeGoal,condition.getGoal());
            return new GridRuleCondition<>(matcher, goal);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new GameBuilderErrorException("Error on create rule condition");
        }
    }

    private static Object createGoal(String typeGoal, String goal) throws GameBuilderErrorException {
        switch (typeGoal) {
            case "Boolean":
                return Boolean.valueOf(goal);
            case "Integer":
                return Integer.parseInt(goal);
            default:
                throw new GameBuilderErrorException("Error on create goal");
        }
    }

    private static GridRuleOperation createOperation(OperationStructure operationStructure, List<Cell> cells)
            throws GameBuilderErrorException {

        Class[] classes = getClassesToOperation(operationStructure);
        Object[] arguments = getArgumentsToOperation(operationStructure,cells);

        return (GridRuleOperation) createObject(getCompleteClassName(operationStructure.getName()),classes, arguments);
    }

    private static Class[] getClassesToOperation(OperationStructure operationStructure) {
        Class[] classes = {operationStructure.getContentTags().size() > 1 ? List.class : String.class };

        if (operationStructure.getCorner() != null) {
            classes = new Class[classes.length + 1];
            classes[classes.length - 1] = Container.class;
        }
        return classes;
    }

    private static Container findCorner(CornerStructure corner, List<Cell> cells) {
        Cell cell = cells.get(corner.getCellIndex());
        return cell.getNeighbourAt( NeighbourPosition.valueOf(corner.getNeighbourPosition()));
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
        return nameClass.getOrDefault(className,"ar.fiuba.tdd.grupo10.nikoligames") + "." + className;
    }

    public static void setCellLimits(List<CellLimit> cellLimits, List<Cell> grid) {
        cellLimits.forEach( cellLimit -> {
                Cell cell = grid.get(cellLimit.getIndex());
                Container cont = null;
                try {
                    cont = createContainer(cellLimit);
                } catch (GameBuilderErrorException e) {
                    e.printStackTrace();
                }
                cell.setLimitAt(cont, NeighbourPosition.valueOf(cellLimit.getNeighbourPosition()));
            });
    }

    private static Container createContainer(CellLimit cellLimit) throws GameBuilderErrorException {
        List<Content> contents = createContents(cellLimit.getContents());
        ContainerState containerState = (ContainerState) createObject(getCompleteClassName(cellLimit.getContainer()),
                new Class[] {List.class},new Object[] {contents});

        return new Container(containerState);
    }

    private static Object[] getArgumentsToOperation(OperationStructure operationStructure, List<Cell> cells) {
        Object[] arguments = { operationStructure.getContentTags().size() > 1
                ? operationStructure.getContentTags() : operationStructure.getContentTags().get(0)};

        if (operationStructure.getCorner() != null) {
            arguments = new Object[arguments.length + 1];
            arguments[arguments.length - 1] = findCorner(operationStructure.getCorner(),cells);
        }

        return arguments;
    }
}
