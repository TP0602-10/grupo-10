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

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.JSON_FOLDER;
import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.LINE;

public enum GameEnum {


    SUDOKU("Sudoku", createNumberPossibleValues(1, 9), SudokuCellView.class, JSON_FOLDER + "SudokuNormal.json", "Tag"),
    KAKURO("Kakuro", createNumberPossibleValues(0, 9), KakuroCellView.class,
            JSON_FOLDER + "Kakuro.json", "Number"),
    COUNTRY_ROAD("Country Road", createCountryRoadPossibleValues(), CountryRoadCellView.class,
            JSON_FOLDER + "CountryRoad.json", LINE),
    GOKIGEN_NANAME("Gokigen Naname", createGokigenPossibleValues(), GokigenCellView.class,
            JSON_FOLDER + "GokigenNaname.json", LINE),
    SLITHERLINK("Slitherlink", createSlitherlinkPossibleValues(), SlitherlinkCellView.class,
            JSON_FOLDER + "Slitherlink.json", "BORDER");


    private String description;
    private List<PossibleValue> possibleValues;
    private Class<? extends CellView> cellClass;
    private String defaultJsonPath;
    private String mutableContentTag;

    GameEnum(String description, List<PossibleValue> possibleValues,
             Class<? extends CellView> cellClass, String defaultJsonPath,
             String mutableContentTag) {
        this.description = description;
        this.possibleValues = possibleValues;
        this.cellClass = cellClass;
        this.defaultJsonPath = defaultJsonPath;
        this.mutableContentTag = mutableContentTag;
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
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{true, false, false, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{false, true, false, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{false, false, true, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{false, false, false, true}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{false, false, false, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{true, true, false, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{false, true, true, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{false, false, true, true}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{true, false, false, true}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{true, true, true, false}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{false, true, true, true}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{true, false, true, true}));
        slitherlinkValues.add(new SlitherlinkValue(new Boolean[]{true, true, true, false}));
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

    public String getDefaultJsonPath() {
        return defaultJsonPath;
    }

    public String getMutableContentTag() {
        return mutableContentTag;
    }

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
