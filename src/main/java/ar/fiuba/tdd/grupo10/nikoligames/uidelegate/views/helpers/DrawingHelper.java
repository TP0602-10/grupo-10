package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.helpers;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.awt.*;

public class DrawingHelper {

    public static void drawVerticalLine(Graphics graphics, int width, int height) {
        graphics.drawLine(width / 2, 0, width / 2, height);
    }

    public static void drawHorizontalLine(Graphics graphics, int width, int height) {
        graphics.drawLine(0, height / 2, width, height / 2);
    }

    public static void drawCentralChar(Content content, Graphics graphics, int width, int height) {
        if (content != null) {
            graphics.drawString(String.valueOf(content.getValue()), (width / 2) - 4, (height / 2) + 4);
        }
    }

    public static void drawTopBorder(Graphics graphics, int width) {
        graphics.fillRect(0, 0, width, 3);
    }

    public static void drawBottomBorder(Graphics graphics, int width, int height) {
        graphics.fillRect(0, height - 3, width, height);
    }

    public static void drawLeftBorder(Graphics graphics, int height) {
        graphics.fillRect(0, 0, 3, height);
    }

    public static void drawRightBorder(Graphics graphics, int width, int height) {
        graphics.fillRect(width - 3, 0, 3, height);
    }

}
