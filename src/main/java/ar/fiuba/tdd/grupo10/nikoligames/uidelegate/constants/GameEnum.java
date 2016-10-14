package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants;

public enum GameEnum {

    SUDOKU("Sudoku", new Integer[]{null, 1, 2, 3, 4, 5, 6, 7, 8, 9}),
    KAKURO("Kakuro", null),
    COUNTRY_ROAD("Country Road", null),
    GOKIGEN_NANAME("Gokigen Naname", null),
    SLITHERLINK("Slitherlink", null);

    private String description;
    private Object[] possibleValues;

    GameEnum(String description, Object[] possibleValues) {
        this.description = description;
        this.possibleValues = possibleValues;
    }

    @Override
    public String toString() {
        return description;
    }

    public Object[] getPossibleValues() {
        return possibleValues;
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
