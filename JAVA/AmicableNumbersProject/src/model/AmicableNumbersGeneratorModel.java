/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Class is used to generate all pairs of amicable numbers from 2, quantity. Part of Model.
 *
 * @author Katarzyna Filus
 * @version 2.0
 */
public class AmicableNumbersGeneratorModel {

    /**
     * List of generated amicable numbers.
     *
     */
    List<AmicablePairModel> amicableNumbersFromInterval;

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
     */
    public AmicableNumbersGeneratorModel(int quantity) {
        super();
        this.quantity = quantity;
        amicableNumbersFromInterval = new ArrayList();
    }

    /**
     * Function that is responsible for generating list of amicable numbers from
     * an interval 2, quantity. It uses list of every different pair from this
     * interval and then filters it to get pairs of amicable numbers only. Pairs
     * are being filtered thanks to method filter and lambda expression (it uses
     * method that cheks if numbers are amicable).
     *
     * @throws model.WrongParametersException Upper endpoint
     * must be greater than 0
     */
    public void generate() throws WrongParametersException {

        if (quantity <= 0) {
            throw new WrongParametersException("Upper endpoint must be greater than 0");
        } else {
            List<AmicablePairModel> listOfPairs = new ArrayList();

            Map<Long, Long> amicablePairsMap = LongStream.rangeClosed(1, quantity)
                    .parallel()
                    .boxed()
                    .collect(Collectors.toMap(Function.identity(), AmicableNumbersGeneratorModel::properDivisorsSum));

            LongStream.rangeClosed(1, quantity)
                    .forEach(number1 -> {
                        long number2 = amicablePairsMap.get(number1);
                        if (number2 > number1 && number2 <= quantity && amicablePairsMap.get(number2) == number1) {
                            AmicablePairModel newPair = new AmicablePairModel((int) number1, (int) number2);
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
     * @return feild amicableNumbersFromInterval - generated amical numbers from the interval.
     */
    public List<AmicablePairModel> getList() {
        return this.amicableNumbersFromInterval;
    }

}
