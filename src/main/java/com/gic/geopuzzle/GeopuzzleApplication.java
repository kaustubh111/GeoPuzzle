package com.gic.geopuzzle;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class GeopuzzleApplication {

	public static void main(String[] args) {
		try {
			CustomShapeCreator customShapeCreator = new CustomShapeCreatorImpl();
			RandomShapeCreator randomShapeCreator = new RandomShapeCreatorImpl();

			Scanner scanner = new Scanner(System.in);

			System.out.println("Welcome to the GIC geometry puzzle app");

			System.out.println("[1] Create a custom shape");
			System.out.println("[2] Generate a random shape");

			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			if (choice == 1) {
				customShapeCreator.createCustomShape(scanner);
			} else if (choice == 2) {
				randomShapeCreator.generateRandomShape(scanner);
			}

			randomShapeCreator.playPuzzle(scanner);
			System.out.println("Thank you for playing the GIC geometry puzzle app");
			System.out.println("Have a nice day!");
		}
		catch(Exception e){
			System.out.println("Application Crashed...!  "+ e.getMessage());
			main(new String[]{"", ""});
		}
	}


}