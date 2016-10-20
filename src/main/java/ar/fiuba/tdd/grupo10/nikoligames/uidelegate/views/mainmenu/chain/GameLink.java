package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
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
                showMessage(JOptionPane.ERROR_MESSAGE, NO_FILE_ERROR_MSG);
            } else {
                try {
                    createGame(gameEnum, filePath);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    showMessage(JOptionPane.ERROR_MESSAGE, FILE_ERROR_MSG);
                }
            }
        } else {
            next.execute(gameEnum, filePath);
        }
    }

    abstract void createGame(GameEnum game, String filePath);

}
