package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import java.awt.*;
import javax.swing.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.TITLE;

class UnavailableGameLink extends GameLink {

    private Component context;

    UnavailableGameLink(Component context) {
        this.context = context;
    }

    @Override
    public void execute(GameEnum gameEnum) {

        JOptionPane.showMessageDialog(context,
                "Game not available: " + gameEnum,
                TITLE,
                JOptionPane.INFORMATION_MESSAGE);

    }

}