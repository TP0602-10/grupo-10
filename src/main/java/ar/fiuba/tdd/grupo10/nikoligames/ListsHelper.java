package ar.fiuba.tdd.grupo10.nikoligames;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper for simplifying the common operations of generic lists.
 */
public class ListsHelper {
    public static <T> boolean equals(List<T> list1, List<T> list2) {
        return list1.size() == list2.size()
                && list1.containsAll(list2)
                && list2.containsAll(list1);
    }

    public static <T> List<T> rejectDuplicateElements(List<T> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }
}
