package com.example.amicablenumbers.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class represents 2 numbers. It contains 2 fields (numbers that are being
 * checked - they are amicabe or not). Its methods are responsible for checking
 * if numbers are amicable and returning them.
 *
 * @author Katarzyna Filus
 * @version 3.0
 */
public class AmicablePair {

    /**
     * Numbers that are being checked - they are amicabe or not
     *
     * @author Katarzyna Filus
     */
    private int number1;
    private int number2;


    /**
     * Constructor that takes 2 integers as arguments. Both fields are being
     * set.
     *
     * @author Katarzyna Filus
     * @param number1 is a first number of a pair
     * @param number2 is a second number of a pair
     */
    public AmicablePair(int number1, int number2) {
        super();
        this.number1 = number1;
        this.number2 = number2;

    }

    /**
     * Constructor that takes 2 integers as arguments. Both fields are being
     * set.
     *
     * @author Katarzyna Filus
     * @param number1 is a first number of a pair
     * @param number2 is a second number of a pair
     */
    public void setAmicablePair(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;

    }

    /**
     * Getter for number1. set.
     *
     * @author Katarzyna Filus
     * @return number1
     */
    public int getNumber1() {
        return number1;
    }

    /**
     * Getter for number2.
     *
     * @author Katarzyna Filus
     * @return number1
     */
    public int getNumber2() {
        return number2;
    }

    /**
     * Function checks if numbers are amicable. Definition from Wikipedia:
     * Amicable numbers are two different numbers so related that the sum of the
     * proper divisors of each is equal to the other number. To implement this
     * function I've chosen to use 2 lists. First, I generate lists that contain
     * numbers from 1 to number1/number2 - these are potential divisors. Next, I
     * have to filter the numbers. It is possible thanks to filter method which
     * takes lambda expression as an argument. The lambda exp. means that the
     * element of the list (which is currently being filtered) is a divisor of
     * number1 (List dividers1)/ number2 (List dividers2). At the end elements
     * of the lists are summed and compared.
     *
     * @author Katarzyna Filus
     * @return true if amicable and false if not
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean areAmicable() {
        if (number1 <= 0 && number2 <= 0) {
            return false;
        }
        List<Integer> divisor1 = IntStream.range(1, number1).boxed().collect(Collectors.toList());
        List<Integer> divisor2 = IntStream.range(1, number2).boxed().collect(Collectors.toList());
        //LambdaCheckInterface<Integer> canBeDivided1 = object -> number1 % object == 0;
        //LambdaCheckInterface<Integer> canBeDivided2 = object -> number2 % object == 0;
        divisor1 = divisor1.stream().filter(x ->  number1 % x == 0).collect(Collectors.toList());
        divisor2 = divisor2.stream().filter(x -> number2 % x == 0).collect(Collectors.toList());
        int sum1 = divisor1.stream().mapToInt(Integer::intValue).sum();
        int sum2 = divisor2.stream().mapToInt(Integer::intValue).sum();

        return sum1 == number2 && sum2 == number1 && number1 != number2;
    }

    /**
     * Function uses function areAmicable to chceck if numbers are amicable. It
     * throws the (our own) exception when at least one of the numbers are too
     * small.
     *
     * @return true if amicable and false if not
     * @throws WrongParametersException if nubers given aren't correct
     */
    public boolean areNumbersAmicable() throws WrongParametersException {

        if (number1 <= 0 || number2 <= 0) {
            throw new WrongParametersException("[EXCEPTION] Numbers must be greater than 0");
        } else {
            return areAmicable();
        }
    }

    /**
     * Function that converts object of the class to String.
     *
     * @return amicable numbers as a String, format: first_number second_number
     */
    @Override
    public String toString() {
        String amicableNumberString = new String();
        amicableNumberString = Integer.toString(number1) + ", " + Integer.toString(number2);
        return amicableNumberString;
    }
}