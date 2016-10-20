package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GameBuilderErrorException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.GridRuleMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.json.structures.*;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create a game using a json file.
 */
public final class GamesBuilder {

    public static Grid createUsingJson(String location) throws GameBuilderErrorException {

        GameStructure gameStructure;

        //open json
        try (Reader reader = new InputStreamReader(GamesBuilder.class.getResourceAsStream(location), "UTF-8")) {
            Gson gson = new GsonBuilder().create();
            gameStructure = gson.fromJson(reader, GameStructure.class);
        } catch (IOException e) {
            throw new GameBuilderErrorException("Can't open file");
        }

        BoardStructure board = gameStructure.getBoard();

        if (gameStructure.getInitialBoard().size() != (board.getRows() * board.getColumns())) {
            throw new GameBuilderErrorException("Board with wrong size");
        }

        List<GridRule> gridRules = createGrideRules(gameStructure.getRules(), gameStructure.getInitialBoard());

        GridRuleManager gridRuleManager = new GridRuleManager(gridRules);
        Grid grid = new GridBuilder().setRows(board.getRows()).setColumns(board.getColumns())
                .addCells(gameStructure.getInitialBoard()).addObserver(gridRuleManager).buildGrid();
        gridRuleManager.addObserver(grid);

        return grid;
    }

    private static List<GridRule> createGrideRules(List<RuleStructure> rules, List<Cell> initialBoard)
            throws GameBuilderErrorException {

        List<GridRule> gridRules = new ArrayList<>();

        for (int i = 0; i < rules.size(); i++) {
            RuleStructure rule = rules.get(i);

            GridRuleOperation operation = createOperation(rule.getOperation());

            GridRuleCondition condition = createCondition(rule.getCondition());

            GridRuleIterator iterator = createIterator(rule.getIterator(), initialBoard);

            gridRules.add(createRule(rule.getTypeGridRule(),iterator,operation,condition));
        }
        return gridRules;
    }

    private static GridRule createRule(String typeGridRule,
                                       GridRuleIterator iterator,
                                       GridRuleOperation operation,
                                       GridRuleCondition condition) throws GameBuilderErrorException {
        try {
            Class cl = Class.forName(typeGridRule);
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

            Class matcherClass = Class.forName(condition.getMatcher());
            GridRuleMatcher<Object> matcher = (GridRuleMatcher<Object>) matcherClass.newInstance();
            return new GridRuleCondition<>(matcher, condition.getGoal());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new GameBuilderErrorException("Error on create rule condition");
        }
    }

    private static GridRuleOperation createOperation(OperationStructure operationStructure)
            throws GameBuilderErrorException {

        try {
            Class cl = Class.forName(operationStructure.getType());
            Constructor con = cl.getConstructor(List.class);

            return (GridRuleOperation) con.newInstance(operationStructure.getContentTags());
        } catch (InstantiationException | InvocationTargetException
                | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            throw new GameBuilderErrorException("Error on create operation");
        }
    }
}
