package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.disabled.DisabledCellView;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.disabled.KakuroDisabledCellView;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.disabled.SudokuDisabledCellView;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.enabled.EnabledCellView;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.enabled.KakuroEnabledCellView;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.enabled.SudokuEnabledCellView;

public enum GameEnum {

    SUDOKU("Sudoku", new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 8, 9}, SudokuDisabledCellView.class, SudokuEnabledCellView.class),
    KAKURO("Kakuro", new Integer[]{null, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, KakuroDisabledCellView.class, KakuroEnabledCellView.class),
    COUNTRY_ROAD("Country Road", null, null, null),
    GOKIGEN_NANAME("Gokigen Naname", null, null, null),
    SLITHERLINK("Slitherlink", null, null, null);

    private String description;
    private Object[] possibleValues;
    private Class<? extends DisabledCellView> disabledCellClass;
    private Class<? extends EnabledCellView> enabledCellClass;

    GameEnum(String description, Object[] possibleValues, Class<? extends DisabledCellView> disabledCellClass, Class<? extends EnabledCellView> enabledCellClass) {
        this.description = description;
        this.possibleValues = possibleValues;
        this.disabledCellClass = disabledCellClass;
        this.enabledCellClass = enabledCellClass;
    }

    @Override
    public String toString() {
        return description;
    }

    public Class<? extends DisabledCellView> getDisabledCellClass() {
        return disabledCellClass;
    }

    public Class<? extends EnabledCellView> getEnabledCellClass() {
        return enabledCellClass;
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
