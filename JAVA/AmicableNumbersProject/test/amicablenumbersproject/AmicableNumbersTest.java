/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amicablenumbersproject;

import model.WrongParametersException;
import model.AmicablePairModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author filus
 */
public class AmicableNumbersTest {

    @Before
    public void setUp() {
    }

    /**
     * Test of areAmicable method, of class AmicableNumbers.
     */
    @Test
    public void testAreAmicable() {
        AmicablePairModel instance = new AmicablePairModel(220, 284);
        boolean expResult = true;
        boolean result = instance.areAmicable();
        assertEquals(expResult, result);

        instance = new AmicablePairModel(220, 220);
        expResult = false;
        result = instance.areAmicable();
        assertEquals(expResult, result);

        instance = new AmicablePairModel(22, 28);
        expResult = false;
        result = instance.areAmicable();
        assertEquals(expResult, result);

        instance = new AmicablePairModel(-22, 28);
        expResult = false;
        result = instance.areAmicable();
        assertEquals(expResult, result);

        instance = new AmicablePairModel(-22, -28);
        expResult = false;
        result = instance.areAmicable();
        assertEquals(expResult, result);

        instance = new AmicablePairModel(22, 28);
        expResult = false;
        result = instance.areAmicable();
        assertEquals(expResult, result);

        instance = new AmicablePairModel(0, 0);
        expResult = false;
        result = instance.areAmicable();
        assertEquals(expResult, result);

        instance = new AmicablePairModel(0, 1);
        expResult = false;
        result = instance.areAmicable();
        assertEquals(expResult, result);
    }

    /**
     * Test of areNumbersAmicable method, of class AmicableNumbers.
     *
     * @throws model.WrongParametersException
     */
    @Test
    public void testAreNumbersAmicable() throws WrongParametersException {

        try {
            AmicablePairModel instance = new AmicablePairModel(100, 284);
            boolean expResult = false;
            boolean result = instance.areNumbersAmicable();
            assertEquals(expResult, result);

            instance = new AmicablePairModel(220, 284);
            expResult = true;
            result = instance.areNumbersAmicable();
            assertEquals(expResult, result);

            instance = new AmicablePairModel(-3, -284);
            instance.areNumbersAmicable();
        } catch (WrongParametersException e) {
        }

        try {
            AmicablePairModel instance = new AmicablePairModel(17, 284);
            boolean expResult = false;
            boolean result = instance.areNumbersAmicable();
            assertEquals(expResult, result);

            instance = new AmicablePairModel(0, 1);
            instance.areNumbersAmicable();
        } catch (WrongParametersException e) {
        }

        try {
            AmicablePairModel instance = new AmicablePairModel(-17, 284);
            instance.areNumbersAmicable();
        } catch (WrongParametersException e) {
        }

        try {
            AmicablePairModel instance = new AmicablePairModel(17, -284);
            instance.areNumbersAmicable();
        } catch (WrongParametersException e) {
        }
    }

    /**
     * Test of toString method, of class AmicableNumbers.
     */
    @Test
    public void testToString() {
        AmicablePairModel instance = new AmicablePairModel(220, 284);
        String expResult = "220, 284";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
