package de.hfu;

import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please write whatever you want");
        String userInput = keyboard.next();
        System.out.println("Your input in only uppercase: " + userInput.toUpperCase());
    }
}
