package ar.fiuba.tdd.grupo10.nikoligames.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Helper for handling operations with random numbers.
 */
public final class RandomHelper {

    public static List<Integer> getRandomNumbersInRange(int cant, int min, int max) {
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < cant; i++) {
            randomNumbers.add(getRandomNumberInRange(min, max));
        }
        return randomNumbers;
    }

    public static int getRandomNumberInRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

}
