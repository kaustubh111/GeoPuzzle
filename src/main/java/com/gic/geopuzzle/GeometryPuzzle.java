package com.gic.geopuzzle;

import java.util.*;
import java.util.stream.IntStream;

public class GeometryPuzzle{
    public static List<List<Integer>> shape = new ArrayList<>();

    public static void createCustomShape(Scanner scanner) {
        System.out.println("Please enter coordinates 1 in x y format");
        addCoordinate(scanner);
    }

    private static void addCoordinate(Scanner scanner) {
        Integer x = scanner.nextInt();
        Integer y = scanner.nextInt();
        List<Integer> point = new ArrayList<>();
        point.add(x);
        point.add(y);
        if (isValidCoordinate(point)) {
            boolean isConvex = ConvexShapeValidator.isConvexShape(shape);
            if(isConvex){
                System.out.println("Your current shape is valid and is complete");
                printShape();
                System.out.println("Please enter # to finalize your shape or enter coordinates "+ (shape.size() + 1) +" in x y format");
                String input = scanner.next();
                if (input.equals("#")) {
                    System.out.println("Your finalized shape is");
                    printShape();
                    System.out.println("Please key in test coordinates in x y format or enter # to quit the game");
                    String input2 = scanner.next();

                    if (input2.equals("#")) {
                        System.out.println("Thank you for playing the GIC geometry puzzle app");
                        System.out.println("Have a nice day!");
                       System.exit(130);
                    }
                    else{
                        printShape();
                        System.out.println("Please enter coordinates " + (shape.size() + 1) + " in x y format");
                        addCoordinate(scanner);
                    }
                }
                else{
                    addCoordinate(scanner);
                }
            }

            else{
                System.out.println("Your current shape is incomplete");
                printShape();
                System.out.println("Please enter coordinates " + (shape.size() + 1) + " in x y format");
                addCoordinate(scanner);
            }

        } else {
            System.out.println("New coordinates" + "("+point.get(0)+","+point.get(1)+")" + " is invalid!!!");
            if(!shape.contains(point))
                shape.add(point);
            else
                System.out.println("Not adding new coordinates to the current shape.");
            System.out.println("Your current shape is incomplete");
            printShape();
            System.out.println("Please enter coordinates " + (shape.size() + 1)  + " in x y format");
            addCoordinate(scanner);
        }
    }

    public static boolean isValidCoordinate(List<Integer> point) {
        return !shape.contains(point) && !(shape.size() < 3);
    }

    public static void generateRandomShape(Scanner scanner) {
        Random random = new Random();
        Integer numOfPoints = random.nextInt(6) + 3; // Random between 3 to 8 points
        shape = new ArrayList<>();
        List<Integer> point = new ArrayList<>();
        for (Integer i = 0; i < numOfPoints; i++) {
            point.add(random.nextInt(10));
            point.add(random.nextInt(10));
            shape.add(point);
        }

        System.out.println("Your random shape is");
        printShape();
        System.out.println("Please key in test coordinates in x y format or enter # to quit the game");
//        if (input.equals("#")) {
//            System.out.println("Thank you for playing the GIC geometry puzzle app");
//            System.out.println("Have a nice day!");
//            return;
//        }
        addCoordinate(scanner);
    }

    private static void printShape() {
        IntStream.range(0, shape.size())
                .mapToObj(i -> (i + 1) + ":" +  "("+shape.get(i).get(0)+","+shape.get(i).get(1)+")")
                .forEach(System.out::println);
    }

    public static void puzzleSection(Scanner scanner) {
        System.out.println("Please key in test coordinates in x y format or enter # to quit the game");
        String input = scanner.next();

        if (input.equals("#")) {
            System.out.println("Thank you for playing the GIC geometry puzzle app");
            System.out.println("Have a nice day!");
            return;
        }
        Integer x = scanner.nextInt();
        Integer y = scanner.nextInt();
        Integer[] testPoint = new Integer[2];
        testPoint[0] =x;
        testPoint[1]=y;
        if (isPointInsideShape(testPoint)) {
            System.out.println("Coordinates " +  "("+testPoint[0]+","+testPoint[1]+")"+ " is within your finalized shape");
        } else {
            System.out.println("Sorry, coordinates " +  "("+testPoint[0]+","+testPoint[1]+")"+ " is outside of your finalized shape");
        }

        puzzleSection(scanner);
    }

    static boolean isPointInsideShape(Integer[] testPoint) {
        boolean result = false;
        Integer j = shape.size() - 1;

        for (Integer i = 0; i < shape.size(); i++) {
            if ((shape.get(i).get(0) > testPoint[1]) != (shape.get(j).get(1) > testPoint[1]) &&
                    (testPoint[0] < (shape.get(j).get(0) - shape.get(i).get(0)) * (testPoint[1] - shape.get(i).get(1))
                            / (shape.get(j).get(1) - shape.get(i).get(1)) + shape.get(i).get(0))) {
                result = !result;
            }
            j = i;
        }

        return result;
    }
}
