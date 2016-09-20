package ar.fiuba.tdd.grupo10.nikoligames;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper for simplifying the common operations of generic lists.
 */
public class ListsHelper {
    public static boolean equals(List<?> list1, List<?> list2) {
        return list1.size() == list2.size()
                && list1.contains(list2)
                && list2.contains(list1);
    }

    public static List<?> rejectDuplicateElements(List<?> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }
}
