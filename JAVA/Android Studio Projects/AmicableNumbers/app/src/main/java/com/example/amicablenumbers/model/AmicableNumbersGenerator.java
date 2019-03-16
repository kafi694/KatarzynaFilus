package com.example.amicablenumbers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Class is used to generate all pairs of amicable numbers from 2, quantity.
 * Part of Model.
 *
 * @author Katarzyna Filus
 * @version 3.0
 */
public class AmicableNumbersGenerator {

    /**
     * List of generated amicable numbers.
     *
     */
    List<AmicablePair> amicableNumbersFromInterval;

    /**
     * quantity is a field that contains upper endpoint of the interval, from
     * which numbers are generated.
     *
     */
    private int quantity = 0;

    /**
     * 1 argument constructor that takes quantity as an argument.
     *
     * @param quantity upper endpoint of interval from which we want to generate
     * numbers
     * @throws WrongParametersException when endPoint is less (or equals) zero.
     */
    public AmicableNumbersGenerator(int quantity) throws WrongParametersException {
        super();
        this.quantity = quantity;
        amicableNumbersFromInterval = new ArrayList();
        this.generate();
    }

    /**
     * Function that is responsible for generating list of amicable numbers from
     * an interval. It uses a map and later, it is converted to a list. This method is much faster than
     * methon used in version 2.0. To count the sum of proper divisors of a number (part of a pair),
     * function properDivisorsSum(long number) is used.
     *
     * @throws WrongParametersException throws exception when the value of
     * the endpoint of the interval is incorrect.
     */
    private void generate() throws WrongParametersException {

        if (quantity <= 0) {
            throw new WrongParametersException("[EXCEPTION] Upper endpoint must be greater than 0");
        } else {
            List<AmicablePair> listOfPairs = new ArrayList();

            Map<Long, Long> amicablePairsMap = LongStream.rangeClosed(1, quantity)
                    .parallel()
                    .boxed()
                    .collect(Collectors.toMap(Function.identity(), AmicableNumbersGenerator::properDivisorsSum));

            LongStream.rangeClosed(1, quantity)
                    .forEach(number1 -> {
                        long number2 = amicablePairsMap.get(number1);
                        if (number2 > number1 && number2 <= quantity && amicablePairsMap.get(number2) == number1) {
                            AmicablePair newPair = new AmicablePair((int) number1, (int) number2);
                            listOfPairs.add(newPair);
                        }
                    });
            amicableNumbersFromInterval = listOfPairs;
        }
    }

    /**
     * Function is responsible for calculating sum of proper divisors of a number.
     *
     * @return sum of proper divisors of given number
     */
    private static Long properDivisorsSum(long number) {
        return LongStream.rangeClosed(1, (number + 1) / 2).filter(i -> number % i == 0).sum();
    }

    /**
     * Getter of list containing amical numbers.
     *
     * @return feild amicableNumbersFromInterval - generated amical numbers from
     * the interval.
     */
    public List<AmicablePair> getList() {
        return this.amicableNumbersFromInterval;
    }

}