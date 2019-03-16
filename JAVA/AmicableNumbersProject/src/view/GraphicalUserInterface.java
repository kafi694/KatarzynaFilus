/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.AmicablePairModel;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

/**
 * Class is a View from MVC. It communicates with a user and take arguments from
 * them. It also give back processed data.
 *
 * @author Katarzyna Filus
 * @version 2.0
 */
public class GraphicalUserInterface {

    /**
     * It contains options from which user can choose the mode of the
     * application.
     *
     */
    private final String[] programOptions;

    /**
     * Enum is used for returning information what program option was chosen by the user.
     *
     */
    public enum ProgramOptions {

        /**
         *User wants to check if 2 numbers are amicable.
         */
        CHECK,

        /**
         *User wants to generate amicable numbers from the chosen interval.
         */
        GENERATE;
    }

    /**
     * Constractor which initiatize programOptions.
     *
     */
    public GraphicalUserInterface() {
        this.programOptions = new String[]{"check if 2 numbers are amicable", "generate amicable numbers"};
    }

    /**
     * Method is responisble for displaying options to choose for the user
     * (modes of the app).
     *
     * @return 1 when user wants to check if 2 numbers are amicable and 2 when
     * they want to generate amicable numbers from an interval.
     *
     */
    public ProgramOptions chooseOption() {
        JFrame frame = new JFrame("No input parameters");
        String chooseProgramOption = (String) JOptionPane.showInputDialog(frame,
                "What do you want to do?",
                "Choose your option",
                JOptionPane.QUESTION_MESSAGE,
                null,
                programOptions,
                programOptions[0]);
        if (chooseProgramOption.equals("check if 2 numbers are amicable")) {
            return ProgramOptions.CHECK;
        } else {
            return ProgramOptions.GENERATE;
        }
    }

    /**
     * It reads number1 from the user.
     *
     * @return number1
     */
    public int readnumber1() {
        String number1 = JOptionPane.showInputDialog(null, "Write first number:", "Amicable numbers check", QUESTION_MESSAGE);
        return Integer.parseInt(number1);
    }

    /**
     * It reads number2 from the user.
     *
     * @return number2
     */
    public int readnumber2() {
        String number2 = JOptionPane.showInputDialog(null, "Write second number:", "Amicable numbers check", QUESTION_MESSAGE);
        return Integer.parseInt(number2);
    }

    /**
     * It reads upper endpoint of the interval from which program generates
     * amicable numbers.
     *
     * @return int value of the upper endpoint of the interval
     */
    public int readEndpoint() {
        String endpoint = JOptionPane.showInputDialog(null, "Write your upper endpoint:", "Amicable numbers generator", QUESTION_MESSAGE);
        return Integer.parseInt(endpoint);
    }

    /**
     * It displays results in check mode. Result determines if numbers are
     * amicable or not.
     *
     * @param areAmicable true if numbers are amicable, false when they are not.
     */
    public void writeAmicableNumbersResults(boolean areAmicable) {
        if (areAmicable == true) {
            JOptionPane.showMessageDialog(null, "Numbers are amicable. ", "Results", INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Numbers aren't amicable. ", "Results", INFORMATION_MESSAGE);
        }
    }

    /**
     * It displays results in generate mode. View displays the list of the
     * amicable numbers from the chosen interval.
     *
     * @param amicableNumbersList list of amicable numbers generated
     */
    public void writeAmicableNumbersGeneratedResults(List<AmicablePairModel> amicableNumbersList) {
        int returned;
        if (amicableNumbersList.isEmpty()) {
            returned = JOptionPane.showOptionDialog(new JFrame(), "No results", "Results", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, new Object[]{"Ok"}, JOptionPane.OK_OPTION);
        } else {
            //String result = amicableNumbersList.stream().map(n -> String.valueOf(n)).collect(Collectors.joining("\n"));
            String result = new String();
            for (AmicablePairModel iter : amicableNumbersList) {
                result = result + iter.toString() + "\n";
            }
            returned = JOptionPane.showOptionDialog(new JFrame(), result, "Results", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, new Object[]{"Ok"}, JOptionPane.OK_OPTION);

        }
        if (returned == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

    }
    /**
     * It views information about incorrect data given by the user (for example letters instead of numbers or negative numbers).
     * 
     * @param message - message that is shown as a warning.
     */
    public void viewWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "Results", JOptionPane.WARNING_MESSAGE);
    }
}
