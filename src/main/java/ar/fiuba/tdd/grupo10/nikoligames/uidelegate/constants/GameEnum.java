package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.*;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.*;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers.LineValue;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers.NumberValue;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers.PossibleValue;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers.SlitherlinkValue;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public enum GameEnum {

    SUDOKU("Sudoku", createNumberPossibleValues(1, 9), SudokuCellView.class),
    KAKURO("Kakuro", createNumberPossibleValues(0, 9), KakuroCellView.class),
    COUNTRY_ROAD("Country Road", createCountryRoadPossibleValues(), CountryRoadCellView.class),
    GOKIGEN_NANAME("Gokigen Naname", createGokigenPossibleValues(), GokigenCellView.class),
    SLITHERLINK("Slitherlink", createSlitherlinkPossibleValues(), SlitherlinkCellView.class);

    private String description;
    private List<PossibleValue> possibleValues;
    private Class<? extends CellView> cellClass;

    GameEnum(String description, List<PossibleValue> possibleValues, Class<? extends CellView> cellClass) {
        this.description = description;
        this.possibleValues = possibleValues;
        this.cellClass = cellClass;
    }

    private static List<PossibleValue> createNumberPossibleValues(int min, int max) {
        List<PossibleValue> sudokuValues = new ArrayList<>();
        sudokuValues.add(new NumberValue(null));
        for (int i = min; i <= max; i++) {
            sudokuValues.add(new NumberValue(i));
        }
        return sudokuValues;
    }

    private static List<PossibleValue> createSlitherlinkPossibleValues() {
        List<PossibleValue> slitherlinkValues = new ArrayList<>();
        slitherlinkValues.add(new SlitherlinkValue(null));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {false, false, false, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {true, false, false, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {false, true, false, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {false, false, true, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {false, false, false, true}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {true, true, false, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {false, true, true, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {false, false, true, true}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {true, false, false, true}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {true, true, true, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {false, true, true, true}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {true, false, true, true}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[] {true, true, true, false}));
        return slitherlinkValues;
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

    private static LineValue createLinePossibleValue(Class<? extends Line> clazz) {
        if (clazz != null) {
            try {
                Constructor constructor = clazz.getConstructor(String.class);
                return new LineValue((Line) constructor.newInstance(""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new LineValue(null);
    }

    private static List<PossibleValue> createGokigenPossibleValues() {
        List<PossibleValue> gokigenNanameValues = new ArrayList<>();
        gokigenNanameValues.add(new LineValue(null));
        gokigenNanameValues.add(new LineValue(new FromBottomLeftToTopRightDiagonal("")));
        gokigenNanameValues.add(new LineValue(new FromTopLeftToBottomRightDiagonal("")));
        return gokigenNanameValues;
    }

    @Override
    public String toString() {
        return description;
    }

    public Class<? extends CellView> getCellClass() {
        return cellClass;
    }

//    public Object getNextValue(Object currentValue) {
//        return getNextPossibleValue(currentValue).getValue();
//    }

    public PossibleValue getNextPossibleValue(Object currentValue) {
        for (int index = 0; index < possibleValues.size(); index++) {
            if (currentValue == null && possibleValues.get(index).getValue() == null
                    && index == (possibleValues.size() - 1)) {
                return possibleValues.get(0);
            } else if (currentValue == null && possibleValues.get(index).getValue() == null) {
                return possibleValues.get(index + 1);
            } else if (currentValue != null && possibleValues.get(index).getValue() != null
                    && possibleValues.get(index).isEquivalentTo(currentValue)
                    && index == (possibleValues.size() - 1)) {
                return possibleValues.get(0);
            } else if (currentValue != null && possibleValues.get(index).getValue() != null
                    && possibleValues.get(index).isEquivalentTo(currentValue)) {
                return possibleValues.get(index + 1);
            }
        }
        throw new RuntimeException("GameEnum:getNextValue not found for game: " + toString()
                + " currentValue: " + currentValue);
    }

//    public Object getPrevValue(Object currentValue) {
//        return getPrevPossibleValue(currentValue).getValue();
//    }

    public PossibleValue getPrevPossibleValue(Object currentValue) {
        for (int index = 0; index < possibleValues.size(); index++) {
            if (currentValue == null && possibleValues.get(index).getValue() == null && index == 0) {
                return possibleValues.get(possibleValues.size() - 1);
            } else if (currentValue == null && possibleValues.get(index).getValue() == null) {
                return possibleValues.get(index - 1);
            } else if (currentValue != null && possibleValues.get(index).getValue() != null
                    && possibleValues.get(index).isEquivalentTo(currentValue) && index == 0) {
                return possibleValues.get(possibleValues.size() - 1);
            } else if (currentValue != null && possibleValues.get(index).getValue() != null
                    && possibleValues.get(index).isEquivalentTo(currentValue)) {
                return possibleValues.get(index - 1);
            }
        }
        throw new RuntimeException("GameEnum:getPrevValue not found for game: " + toString()
                + " currentValue: " + currentValue);
    }


}
