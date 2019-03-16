/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amicablenumbersproject;

import model.AmicableNumbersGeneratorModel;
import model.WrongParametersException;
import model.AmicablePairModel;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author filus
 */
public class AmicableNumbersGeneratorTest {

    @Before
    public void setUp() {
    }

    /**
     * Test of generate method, of class AmicableNumbersGenerator.
     *
     * @throws WrongParametersException
     */
    @Test
    public void testGenerate() throws WrongParametersException {
        try {
            AmicableNumbersGeneratorModel instance = new AmicableNumbersGeneratorModel(1215);
            List<AmicablePairModel> expResult = new ArrayList();
            expResult.add(new AmicablePairModel(220, 284));
            instance.generate();
            Assert.assertNotEquals(expResult.toString(), instance.getList().toString());

            instance = new AmicableNumbersGeneratorModel(-60);
            instance.generate();
            fail("An exception should be thrown when the amount equals zero");
        } catch (WrongParametersException e) {

        }

        try {
            AmicableNumbersGeneratorModel instance = new AmicableNumbersGeneratorModel(290);
            List<AmicablePairModel> expResult = new ArrayList();
            expResult.add(new AmicablePairModel(220, 284));
            instance.generate();
            Assert.assertEquals(expResult.toString(), instance.getList().toString());

            instance = new AmicableNumbersGeneratorModel(0);
            instance.generate();
            fail("An exception should be thrown when the amount equals zero");
        } catch (WrongParametersException e) {

        }
    }

}
