package model;

import java.util.Scanner;

public class Utilities {
	static public int readInt() {
		Scanner s = new Scanner(System.in);
		int number;
		while(true) {
			try {
				number = Integer.valueOf(s.next());
				break;
			}
			catch(Exception e) {
				System.out.println("Try Again!!!");
			}
		}
		return number;
	}
	static public double readDouble() {
		Scanner s = new Scanner(System.in);
		double number;
		while(true) {
			try {
				number = Double.valueOf(s.next());
				break;
			}
			catch(Exception e) {
				System.out.println("Try Again!!!");
			}
		}
		return number;
	}
}
