package com.gic.geopuzzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class GeopuzzleApplication {

	public static void main(String[] args) {








		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the GIC geometry puzzle app");

		System.out.println("[1] Create a custom shape");
		System.out.println("[2] Generate a random shape");
		System.out.print("Enter your choice: ");
		int choice = scanner.nextInt();

		if (choice == 1) {
			GeometryPuzzle.createCustomShape(scanner);
		} else if (choice == 2) {
			GeometryPuzzle.generateRandomShape(scanner);
		}

		GeometryPuzzle.puzzleSection(scanner);
		System.out.println("Thank you for playing the GIC geometry puzzle app");
		System.out.println("Have a nice day!");
	}

}
