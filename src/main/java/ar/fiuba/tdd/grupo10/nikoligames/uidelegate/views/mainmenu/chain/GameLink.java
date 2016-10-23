package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.GamesBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GameBuilderErrorException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GameController;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import javax.swing.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.*;

abstract class GameLink {

    protected GameLink next;
    private Component context;
    GameEnum gameEnum;

    GameLink(Component context) {
        this.context = context;
    }

    public void setNext(GameLink next) {
        this.next = next;
    }


    void showMessage(int messageType, String message) {
        JOptionPane.showMessageDialog(context,
                message,
                TITLE,
                messageType);
    }

    void execute(GameEnum gameEnum, String filePath) {
        if (this.gameEnum.equals(gameEnum)) {
            if (StringUtils.isEmpty(filePath)) {
                filePath = gameEnum.getDefaultJsonPath();
            }
            try {
                createGame(gameEnum, filePath);
            } catch (Exception exception) {
                exception.printStackTrace();
                showMessage(JOptionPane.ERROR_MESSAGE, FILE_ERROR_MSG);
            }
        } else {
            next.execute(gameEnum, filePath);
        }
    }

    void createGame(GameEnum game, String filePath) {
        Grid grid;
        try {
            grid = GamesBuilder.createUsingJson(filePath);
            new GameController(grid, this.gameEnum);
        } catch (GameBuilderErrorException e) {
            showMessage(JOptionPane.ERROR_MESSAGE, ViewConstants.FILE_ERROR_MSG + ": " + e.getMessage());
        }
    }
}
