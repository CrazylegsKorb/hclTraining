package com.hcl.trainingprojects.calculator;

import java.util.Scanner;

public class Calc {
	public static void main(String[] args) {
		boolean someFlag = true; // control when quitting loop
		Scanner uin = new Scanner(System.in); // Gather user input
		String proc; // Holds current user input for processing
		double num1, num2, ret;

		System.out.println("Welcome to the calculator!");
		do {
			// ask user input
			System.out.println(
					"What would you like to do?\n\"+\": To add\n\"-\": To subtract\n\"*\": To multiply\n\"/\": To divide\n\"%\": To find modulus\n\"^\": To power\n\"Q\"/\"QUIT\": To quit");
			// take user input
			proc = uin.next().toLowerCase();
			System.out.println();
			// ask what user wants to do, try to use a switch
			switch (proc) {
			case "q": // if 'q'
			case "quit": // if 'quit'
				someFlag = false; // someFlag = false
				break;
			case "+":
				num1 = getNum();
				num2 = getNum();
				ret = (double) num1 + num2;
				System.out.println(num1 + " " + proc + " " + num2 + " = " + ret + "\n");
				break;
			case "-":
				num1 = getNum();
				num2 = getNum();
				ret = (double) num1 - num2;
				System.out.println(num1 + " " + proc + " " + num2 + " = " + ret + "\n");
				break;
			case "*":
				num1 = getNum();
				num2 = getNum();
				ret = (double) num1 * num2;
				System.out.println(num1 + " " + proc + " " + num2 + " = " + ret + "\n");
				break;
			case "/":
				num1 = getNum();
				num2 = getNum();
				ret = (double) num1 / num2;
				System.out.println(num1 + " " + proc + " " + num2 + " = " + ret + "\n");
				break;
			case "%":
				num1 = getNum();
				num2 = getNum();
				ret = (double) num1 % num2;
				System.out.println(num1 + " " + proc + " " + num2 + " = " + ret + "\n");
				break;
			case "^":
				num1 = getNum();
				num2 = getNum();
				ret = (Double) Math.pow(num1, num2);
				System.out.println(num1 + " " + proc + " " + num2 + " = " + ret + "\n");
				break;
			default: // no match, try to gather correct user input again
				System.out.println("Command \"" + proc + "\" not found");
				break;
			}
		} while (someFlag); // while someFlag == True
		uin.close();
	}

	public static double getNum() { // handles errors with number collection, returns a single number
		double num = 0;
		Scanner bin = new Scanner(System.in);
		boolean flag = true;
		do {
			System.out.println("Please enter a number : ");
			try {
				num = Double.parseDouble(bin.next());
				flag = false;
			} catch (Exception e) {
				System.out.println("input was not valid");
			}
		} while (flag);
		System.out.println();
		return num;
	}

}
