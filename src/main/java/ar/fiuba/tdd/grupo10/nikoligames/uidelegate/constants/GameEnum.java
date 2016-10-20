package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.*;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.*;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers.PossibleValue;

import java.util.ArrayList;
import java.util.List;

public enum GameEnum {

    SUDOKU("Sudoku", createIntegerPossibleValues(1, 9), SudokuCellView.class),
    KAKURO("Kakuro", createIntegerPossibleValues(0, 9), KakuroCellView.class),
    COUNTRY_ROAD("Country Road", createCountryRoadPossibleValues(), CountryRoadCellView.class),
    GOKIGEN_NANAME("Gokigen Naname", createGokigenPossibleValues(), GokigenCellView.class),
    SLITHERLINK("Slitherlink", createIntegerPossibleValues(1, 12), SlitherlinkCellView.class);

    private String description;
    private List<PossibleValue> possibleValues;
    private Class<? extends CellView> cellClass;

    GameEnum(String description, List<PossibleValue> possibleValues, Class<? extends CellView> cellClass) {
        this.description = description;
        this.possibleValues = possibleValues;
        this.cellClass = cellClass;
    }

    private static List<PossibleValue> createIntegerPossibleValues(int min, int max) {
        List<PossibleValue> sudokuValues = new ArrayList<>();
        sudokuValues.add(new PossibleValue<Integer>(null));
        for (int i = min; i <= max; i++) {
            sudokuValues.add(new PossibleValue<>(i));
        }
        return sudokuValues;
    }

    private static List<PossibleValue> createCountryRoadPossibleValues() {
        List<PossibleValue> countryRoadValues = new ArrayList<>();
        countryRoadValues.add(createLinePossibleValue(null));
        countryRoadValues.add(createLinePossibleValue(VerticalLine.class));
        countryRoadValues.add(createLinePossibleValue(HorizontalLine.class));
        countryRoadValues.add(createLinePossibleValue(FromBottomToLeftLine.class));
        countryRoadValues.add(createLinePossibleValue(FromBottomToRightLine.class));
        countryRoadValues.add(createLinePossibleValue(FromTopToLeftLine.class));
        countryRoadValues.add(createLinePossibleValue(FromTopToRightLine.class));

        return countryRoadValues;
    }

    private static PossibleValue<Line> createLinePossibleValue(Class<? extends Line> clazz) {
        if (clazz != null) {
            try {
                return new PossibleValue<>(clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new PossibleValue<Line>(null);
    }

    private static List<PossibleValue> createGokigenPossibleValues() {
        List<PossibleValue> gokigenNanameValues = new ArrayList<>();
        gokigenNanameValues.add(new PossibleValue<Line>(null));
        gokigenNanameValues.add(new PossibleValue<Line>(new FromBottomLeftToTopRightDiagonal()));
        gokigenNanameValues.add(new PossibleValue<Line>(new FromTopLeftToBottomRightDiagonal()));
        return gokigenNanameValues;
    }

    @Override
    public String toString() {
        return description;
    }

    public Class<? extends CellView> getCellClass() {
        return cellClass;
    }

    public Object getNextValue(Object currentValue) {
        for (int index = 0; index < possibleValues.size(); index++) {
            if (currentValue == null && possibleValues.get(index).getValue() == null && index == (possibleValues.size() - 1)) {
                return possibleValues.get(0).getValue();
            } else if (currentValue == null && possibleValues.get(index).getValue() == null) {
                return possibleValues.get(index + 1).getValue();
            } else if (currentValue != null && possibleValues.get(index).getValue() != null && possibleValues.get(index).isEquivalentTo(currentValue)
                    && index == (possibleValues.size() - 1)) {
                return possibleValues.get(0).getValue();
            } else if (currentValue != null && possibleValues.get(index).getValue() != null && possibleValues.get(index).isEquivalentTo(currentValue)) {
                return possibleValues.get(index + 1).getValue();
            }
        }
        throw new RuntimeException("GameEnum:getNextValue not found for game: " + toString()
                + " currentValue: " + currentValue);
    }

    public Object getPrevValue(Object currentValue) {
        for (int index = 0; index < possibleValues.size(); index++) {
            if (currentValue == null && possibleValues.get(index).getValue() == null && index == 0) {
                return possibleValues.get(possibleValues.size() - 1).getValue();
            } else if (currentValue == null && possibleValues.get(index).getValue() == null) {
                return possibleValues.get(index - 1).getValue();
            } else if (currentValue != null && possibleValues.get(index).getValue() != null && possibleValues.get(index).isEquivalentTo(currentValue) && index == 0) {
                return possibleValues.get(possibleValues.size() - 1).getValue();
            } else if (currentValue != null && possibleValues.get(index).getValue() != null && possibleValues.get(index).isEquivalentTo(currentValue)) {
                return possibleValues.get(index - 1).getValue();
            }
        }
        throw new RuntimeException("GameEnum:getPrevValue not found for game: " + toString()
                + " currentValue: " + currentValue);
    }


}
