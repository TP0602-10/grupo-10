package ar.fiuba.tdd.grupo10.nikoligames.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper for simplifying the common operations of generic lists.
 */
public class ListHelper {
    public static <T> boolean equals(List<T> list1, List<T> list2) {
        return list1.size() == list2.size()
                && list1.containsAll(list2)
                && list2.containsAll(list1);
    }

    public static <T> List<T> rejectDuplicateElements(List<T> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }

    public static <T> List<T> merge(List<T> list1, List<T> list2) {
        List<T> merged = new ArrayList<>();
        merged.addAll(list1);
        merged.addAll(list2);
        return merged;
    }

    public static <T> List<List<T>> buildMatrixFromFlattenList(List<T> list, int rows, int columns) {
        List<List<T>> matrix = new ArrayList<>(rows);
        int elementsFromListAlreadyAdded = 0;
        for (int r = 0; r < rows; r++) {
            List<T> row = new ArrayList<>(columns);
            int col;
            for (col = 0; col < columns; col++) {
                row.add( list.get(elementsFromListAlreadyAdded + (r + col)) );
            }
            matrix.add(row);
            elementsFromListAlreadyAdded += col - 1;
        }
        return matrix;
    }

    public static <T> List<Integer> createFromRange(int initialValue, int endValue) {
        List<Integer> theList = new ArrayList<Integer>();
        for (int i = initialValue; i <= endValue; i++) {
            theList.add(i);
        }
        return theList;
    }
}
