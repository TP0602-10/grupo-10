package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.CellView;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.KakuroCellView;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.SudokuCellView;

public enum GameEnum {

    SUDOKU("Sudoku", new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 8, 9}, SudokuCellView.class),
    KAKURO("Kakuro", new Integer[]{null, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, KakuroCellView.class),
    COUNTRY_ROAD("Country Road", null, null),
    GOKIGEN_NANAME("Gokigen Naname", null, null),
    SLITHERLINK("Slitherlink", null, null);

    private String description;
    private Object[] possibleValues;
    private Class<? extends CellView> cellClass;

    GameEnum(String description, Object[] possibleValues, Class<? extends CellView> cellClass) {
        this.description = description;
        this.possibleValues = possibleValues;
        this.cellClass = cellClass;
    }

    @Override
    public String toString() {
        return description;
    }

    public Class<? extends CellView> getCellClass() {
        return cellClass;
    }

    public Object getNextValue(Object currentValue) {
        for (int index = 0; index < possibleValues.length; index++) {
            if (currentValue == null && possibleValues[index] == null && index == (possibleValues.length - 1)) {
                return possibleValues[0];
            } else if (currentValue == null && possibleValues[index] == null) {
                return possibleValues[index + 1];
            } else if (currentValue != null && possibleValues[index] != null && possibleValues[index].equals(currentValue)
                    && index == (possibleValues.length - 1)) {
                return possibleValues[0];
            } else if (currentValue != null && possibleValues[index] != null && possibleValues[index].equals(currentValue)) {
                return possibleValues[index + 1];
            }
        }
        throw new RuntimeException("GameEnum:getNextValue not found for game: " + toString()
                + " currentValue: " + currentValue);
    }

    public Object getPrevValue(Object currentValue) {
        for (int index = 0; index < possibleValues.length; index++) {
            if (currentValue == null && possibleValues[index] == null && index == 0) {
                return possibleValues[possibleValues.length - 1];
            } else if (currentValue == null && possibleValues[index] == null) {
                return possibleValues[index - 1];
            } else if (currentValue != null && possibleValues[index] != null && possibleValues[index].equals(currentValue) && index == 0) {
                return possibleValues[possibleValues.length - 1];
            } else if (currentValue != null && possibleValues[index] != null && possibleValues[index].equals(currentValue)) {
                return possibleValues[index - 1];
            }
        }
        throw new RuntimeException("GameEnum:getPrevValue not found for game: " + toString()
                + " currentValue: " + currentValue);
    }


}
