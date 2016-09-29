package ar.fiuba.tdd.grupo10.nikoligames.helpers;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RandomHelperTest {
    private static final int MINVALUE = 12;
    private static final int MAXVALUE = 111;
    private static final int DEFAULTSIZE = 20;

    private boolean valueIn(int number) {
        return number >= MINVALUE && number <= MAXVALUE;
    }

    @Test
    public void getArrayOfRandomNumbers() {
        List<Integer> randomArrayInteger = RandomHelper.getRandomNumbersInRange(DEFAULTSIZE,MINVALUE,MAXVALUE);
        Assert.assertEquals(DEFAULTSIZE,randomArrayInteger.size());
        for (Integer number : randomArrayInteger) {
            Assert.assertTrue( valueIn(number) );
        }
    }

    @Test
    public void getRandomNumberTenTimes() {
        for (int i = 0; i < 10; i++) {
            Integer randomNumberInRange = RandomHelper.getRandomNumberInRange(MINVALUE, MAXVALUE);
            Assert.assertTrue( valueIn(randomNumberInRange) );
        }
    }
}
