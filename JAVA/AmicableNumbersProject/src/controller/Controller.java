/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.GraphicalUserInterface;
import model.AmicableNumbersGeneratorModel;
import model.WrongParametersException;
import model.AmicablePairModel;

/**
 * Class is taking care of the program. It uses other parts View(GraphicalUserInterface)
 * and Model(AmicableNumbers, AmicableNumbersGenerator, which use auxiliary classes) to run
 * the application.
 *
 * @author Katarzyna Filus
 * @version 2.0
 */
public class Controller {
/**
 * View of the application
 *
 */
    private final GraphicalUserInterface graphicalUserInterface;
    /**
 * Model of the application - checking 2 numbers
 *
 */
    private AmicablePairModel amicableNumbers;
    /**
 * Model of the application - generating numbers
 *
 */
    private AmicableNumbersGeneratorModel generator;
/**
 * Parameters given in the command line
 *
 */
    private final String[] parameters;
/**
 * Constructor of the controller
 * @param parameters args from command line
 *
 */
    public Controller(String[] parameters) {
        super();
        this.parameters = parameters;
        graphicalUserInterface =  new GraphicalUserInterface();
    }
/**
 * Function that runs the application. It uses Model and View and parameters from command line.
 * When there are no parameters or they are wrong it informs view that it must display options (modes of the app).
 * If the number of params and content of parameters is correct, it communicate with the view, uses data collected from the view 
 * and process in the model and give it back to the view.
 * It catches exceptions.
 *
 */
    public void runApplication() {
        try {
            if (parameters.length == 1) {
                switch (parameters[0]) {
                    case "check":
                        int number1 = graphicalUserInterface.readnumber1();
                        int number2 = graphicalUserInterface.readnumber2();
                        amicableNumbers = new AmicablePairModel(number1, number2);
                        graphicalUserInterface.writeAmicableNumbersResults(amicableNumbers.areNumbersAmicable());
                        break;
                    case "generate":
                        generator = new AmicableNumbersGeneratorModel(graphicalUserInterface.readEndpoint());
                        generator.generate();
                        graphicalUserInterface.writeAmicableNumbersGeneratedResults(generator.getList());
                        break;
                    default:
                        GraphicalUserInterface.ProgramOptions chooseAfterWrongArguments = graphicalUserInterface.chooseOption();
                        if (chooseAfterWrongArguments == GraphicalUserInterface.ProgramOptions.CHECK) {
                            number1 = graphicalUserInterface.readnumber1();
                            number2 = graphicalUserInterface.readnumber2();
                            amicableNumbers = new AmicablePairModel(number1, number2);
                            graphicalUserInterface.writeAmicableNumbersResults(amicableNumbers.areNumbersAmicable());
                        } else {
                            generator = new AmicableNumbersGeneratorModel(graphicalUserInterface.readEndpoint());
                            generator.generate();
                        graphicalUserInterface.writeAmicableNumbersGeneratedResults(generator.getList());
                        }   break;
                }
            } else {
                GraphicalUserInterface.ProgramOptions chooseAfterNoArguments = graphicalUserInterface.chooseOption();
                if (chooseAfterNoArguments == GraphicalUserInterface.ProgramOptions.CHECK) {
                    int number1 = graphicalUserInterface.readnumber1();
                    int number2 = graphicalUserInterface.readnumber2();
                    amicableNumbers = new AmicablePairModel(number1, number2);
                    graphicalUserInterface.writeAmicableNumbersResults(amicableNumbers.areNumbersAmicable());
                } else{
                    generator = new AmicableNumbersGeneratorModel(graphicalUserInterface.readEndpoint());
                    generator.generate();
                    graphicalUserInterface.writeAmicableNumbersGeneratedResults(generator.getList());
                }
            }
        } catch (NumberFormatException e) {
            graphicalUserInterface.viewWarning(e.getMessage() + ", instead use a number");
        } catch (ArrayIndexOutOfBoundsException e) {
            graphicalUserInterface.viewWarning(e.getMessage());
        } catch (WrongParametersException e) {
            graphicalUserInterface.viewWarning(e.getMessage());
        } catch (Exception e) {
           System.exit(0);
        }
    }

}
