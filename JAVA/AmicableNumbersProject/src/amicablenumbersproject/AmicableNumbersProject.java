/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amicablenumbersproject;

import controller.Controller;

/**
 * Class that contains main function.
 * 
 * @author Katarzyna Filus
 * @version 2.0
 */
public class AmicableNumbersProject {

    /**
     * Main function of the program creates controller and makes it responsible for navigating the application.
     * @param args the command line arguments check - program checks if 2 numbers given by user are amicable
     * generate - generate amicable numbers from the chosen interval.
     */
    public static void main(String[] args) {
        Controller applicationController = new Controller(args);
        while(true)
            applicationController.runApplication();
    }
    
}
