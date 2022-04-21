package de.hfu;

import java.util.Scanner;
/**
 * Hauptklasse App
 *
 */
public class App 
{
	/**
	* Main Methode
	*/
    public static void main( String[] args )
    {
    	/**
    	* Scanner Keyboard wird initialisiert
    	*/
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please write whatever you want");
        /**
        * Gets user input
        */
        String userInput = keyboard.next();
        /**
        * Output user input
        */
        System.out.println("Your input in only uppercase: " + userInput.toUpperCase());
    }
}
