package com.gic.geopuzzle;

import java.util.*;
import java.util.stream.Collectors;

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

public class Main {
    private static List<Point> shape = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to the GIC geometry puzzle app");
        System.out.println("[1] Create a custom shape");
        System.out.println("[2] Generate a random shape");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            createCustomShape(scanner);
        } else if (choice == 2) {
            generateRandomShape();
        }

        puzzleSection(scanner);
    }

    private static void createCustomShape(Scanner scanner) {
        System.out.println("Please enter coordinates 1 in x y format");
        addCoordinate(scanner);
    }

    private static void addCoordinate(Scanner scanner) {
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        Point point = new Point(x, y);

        if (isValidCoordinate(point)) {
            shape.add(point);
            System.out.println("Your current shape is incomplete");
            printShape();
            System.out.println("Please enter coordinates " + (shape.size() + 1) + " in x y format");
            addCoordinate(scanner);
        } else {
            System.out.println("New coordinates" + point + " is invalid!!!");
            System.out.println("Not adding new coordinates to the current shape.");
            System.out.println("Your current shape is incomplete");
            printShape();
            System.out.println("Please enter coordinates " + shape.size() + " in x y format");
            addCoordinate(scanner);
        }
    }

    private static boolean isValidCoordinate(Point point) {
        return !shape.contains(point) && shape.size() < 3;
    }

    private static void generateRandomShape() {
        Random random = new Random();
        int numOfPoints = random.nextInt(6) + 3; // Random between 3 to 8 points
        shape = new ArrayList<>();

        for (int i = 0; i < numOfPoints; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            shape.add(new Point(x, y));
        }

        System.out.println("Your random shape is");
        printShape();
    }

    private static void printShape() {
        shape.stream()
                .map(point -> point.toString())
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static void puzzleSection(Scanner scanner) {
        System.out.println("Please key in test coordinates in x y format or enter # to quit the game");
        String input = scanner.next();

        if (input.equals("#")) {
            System.out.println("Thank you for playing the GIC geometry puzzle app");
            System.out.println("Have a nice day!");
            return;
        }

        String[] coordinates = input.split(" ");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        Point testPoint = new Point(x, y);

        if (isPointInsideShape(testPoint)) {
            System.out.println("Coordinates " + testPoint + " is within your finalized shape");
        } else {
            System.out.println("Sorry, coordinates " + testPoint + " is outside of your finalized shape");
        }

        puzzleSection(scanner);
    }

    private static boolean isPointInsideShape(Point testPoint) {
        boolean result = false;
        int j = shape.size() - 1;

        for (int i = 0; i < shape.size(); i++) {
            if ((shape.get(i).getY() > testPoint.getY()) != (shape.get(j).getY() > testPoint.getY()) &&
                    (testPoint.getX() < (shape.get(j).getX() - shape.get(i).getX()) * (testPoint.getY() - shape.get(i).getY())
                            / (shape.get(j).getY() - shape.get(i).getY()) + shape.get(i).getX())) {
                result = !result;
            }
            j = i;
        }

        return result;
    }
}

