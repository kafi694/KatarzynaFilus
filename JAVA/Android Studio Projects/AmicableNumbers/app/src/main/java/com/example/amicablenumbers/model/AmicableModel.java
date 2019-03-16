package com.example.amicablenumbers.model;

import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Class is used as a model of application.
 *
 * @author Katarzyna Filus
 * @version 4.0
 */
public class AmicableModel {

    /**
     * Function is responsible for checking if 2 numbers are amicable.
     *
     * @param number1 first number of the pair.
     * @param number2 second number of the pair.
     * @return true if numbers are amicable or false if not.
     * @throws WrongParametersException if nubers given to check are incorrect.
     */
    public String checkNumbers(int number1, int number2) throws WrongParametersException {
        AmicablePair amicablePair = new AmicablePair(number1, number2);
        boolean ifAmicable = amicablePair.areNumbersAmicable();
        String retValue;
        if (ifAmicable==true){
            retValue = Integer.toString(number1)+", "+Integer.toString(number2)+"   "+"are amicable.";
        }else{
            retValue = Integer.toString(number1)+", "+Integer.toString(number2)+"   "+"aren't amicable.";
        }
        return retValue;
    }


    /**
     * Function is responsible for generating amicable numbers from the given
     * inteval.
     *
     * @param endpoint endopoint of the interval, from which client wants to
     * generate amicable numbers.
     * @return string with generated numbers.
     * @throws WrongParametersException if endpoint is
     * incorrect.
     */
    public String generate(int endpoint) throws WrongParametersException {
        AmicableNumbersGenerator amicableNumbersGenerator = new AmicableNumbersGenerator(endpoint);
        List<AmicablePair> list = amicableNumbersGenerator.getList();
        String generated = new String();
        for (AmicablePair numbers : list) {
            generated = generated + numbers.toString() + "\n";
        }
        return generated;
    }

}