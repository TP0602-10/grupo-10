package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.*;

public enum GameEnum {

    SUDOKU("Sudoku", new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 8, 9}, SudokuCellView.class),
    KAKURO("Kakuro", new Integer[]{null, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, KakuroCellView.class),
    COUNTRY_ROAD("Country Road", new String[]{null, "1", "2", "3", "4", "5", "6"}, CountryRoadCellView.class),
    GOKIGEN_NANAME("Gokigen Naname", new String[]{null, "1", "2"}, GokigenCellView.class),
    SLITHERLINK("Slitherlink", new String[]{null, "1", "2", "3", "4"}, SlitherlinkCellView.class);

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

    public Class<? extends CellView> getCellClass()
    {
        return cellClass;
    }

    public Object getNextValue(Object currentValue) {
        if (possibleValues != null) {
            if (currentValue == null) {
                for (int index = 0; index < possibleValues.length; index++) {
                    if (possibleValues[index] == null && index == (possibleValues.length - 1)) {
                        return possibleValues[0];
                    } else if (possibleValues[index] == null) {
                        return possibleValues[index + 1];
                    }
                }
            } else {
                for (int index = 0; index < possibleValues.length; index++) {
                    if (possibleValues[index] != null && possibleValues[index].equals(currentValue) && index == (possibleValues.length - 1)) {
                        return possibleValues[0];
                    } else if (possibleValues[index] != null && possibleValues[index].equals(currentValue)) {
                        return possibleValues[index + 1];
                    }
                }
            }
        }
        return null;
    }

    public Object getPrevValue(Object currentValue) {
        if (possibleValues != null) {
            if (currentValue == null) {
                for (int index = 0; index < possibleValues.length; index++) {
                    if (possibleValues[index] == null && index == 0) {
                        return possibleValues[possibleValues.length - 1];
                    } else if (possibleValues[index] == null) {
                        return possibleValues[index - 1];
                    }
                }
            } else {
                for (int index = 0; index < possibleValues.length; index++) {
                    if (possibleValues[index] != null && possibleValues[index].equals(currentValue) && index == 0) {
                        return possibleValues[possibleValues.length - 1];
                    } else if (possibleValues[index] != null && possibleValues[index].equals(currentValue)) {
                        return possibleValues[index - 1];
                    }
                }
            }
        }
        return null;
    }


}
