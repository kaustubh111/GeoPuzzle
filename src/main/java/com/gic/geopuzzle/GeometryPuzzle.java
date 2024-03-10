package com.gic.geopuzzle;

import java.util.*;
import java.util.stream.IntStream;

public class GeometryPuzzle{
    public static List<List<Integer>> shape = new ArrayList<>();

    public void createCustomShape(Scanner scanner) {
        System.out.println("Please enter coordinates 1 in x y format");
        addCoordinate(scanner,0,0);
    }

    private static void addCoordinate(Scanner scanner,Integer x, Integer y) {
        if(x==0 && y ==0) {
             x = scanner.nextInt();
             y = scanner.nextInt();
        }
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
                input += scanner.nextLine();
                if (input.equals("#")) {
                    System.out.println("Your finalized shape is");
                    printShape();
                    System.out.println("Please key in test coordinates in x y format or enter # to quit the game");
                    String input2 = scanner.next();
                    input2 += scanner.nextLine();
                    if (input2.equals("#")) {
                        System.out.println("Thank you for playing the GIC geometry puzzle app");
                        System.out.println("Have a nice day!");
                       System.exit(0);
                    }
                    else{
                        printShape();
                        String[] coordinate = input.split(" ");
                        x = Integer.parseInt(coordinate[0]);
                        y = Integer.parseInt(coordinate[1]);
                        addCoordinate(scanner,x,y);
                    }
                }
                else{
                    String[] coordinate = input.split(" ");
                    x = Integer.parseInt(coordinate[0]);
                    y = Integer.parseInt(coordinate[1]);
                    addCoordinate(scanner,x,y);
                }
            }
            else{
                if(shape.size() > 3) {
                    System.out.println("New coordinates" + "(" + point.get(0) + "," + point.get(1) + ")" + " is invalid!!!");
                    shape.remove(point);
                    System.out.println("Your current shape is ");
                    printShape();
                }else{
                    System.out.println("Your current shape is incomplete");
                }
                System.out.println("Please enter coordinates " + (shape.size() + 1)  + " in x y format");
                x = scanner.nextInt();
                y = scanner.nextInt();
                addCoordinate(scanner,x,y);
            }

        } else {
            if(shape.size() > 3) {
                System.out.println("New coordinates" + "(" + point.get(0) + "," + point.get(1) + ")" + " is invalid!!!");
                System.out.println("Not Adding new coordinates to current shape");
                shape.remove(point);
                System.out.println("Your current shape is ");
                printShape();
            }else{
                System.out.println("Your current shape is incomplete");
            }
            System.out.println("Please enter coordinates " + (shape.size() + 1)  + " in x y format");
            x = scanner.nextInt();
            y = scanner.nextInt();
            addCoordinate(scanner,x,y);
        }
    }

    private static boolean isValidCoordinate(List<Integer> point) {
        if(!shape.contains(point)) {
            shape.add(point);
            return !(shape.size() < 3);
        }
        else {
            return !shape.contains(point) && !(shape.size() < 3);
        }
    }

    public void generateRandomShape(Scanner scanner) {

        Random random = new Random(System.currentTimeMillis());
        Random random2 = new Random(System.nanoTime());
        Integer sides = random.nextInt(6) + 3; // Random between 3 to 8 points
        shape = new ArrayList<>();

        Integer[][] pointArr = new Integer[sides][2];

        for (int i = 0; i < sides; i++) {
            pointArr[i][0]= random.nextInt(10);
        }

        for (int i = 0; i < sides; i++) {
            pointArr[i][1]= random2.nextInt(10);
        }

        for(int i =0; i<pointArr.length;i++){
            shape.add(Arrays.asList(pointArr[i]));
        }
        if(!ConvexShapeValidator.isConvexShape(shape)){
            generateRandomShape(scanner);
        }else {

            System.out.println("Your random shape is");
            printShape();
            puzzleSection(scanner);
        }
    }

    private static void printShape() {
        IntStream.range(0, shape.size())
                .mapToObj(i -> (i + 1) + ":" +  "("+shape.get(i).get(0)+","+shape.get(i).get(1)+")")
                .forEach(System.out::println);
    }

    public void puzzleSection(Scanner scanner) {
        System.out.println("Please key in test coordinates in x y format or enter # to quit the game");
        String input = scanner.next();
        input += scanner.nextLine();
        if (input.equals("#")) {
            System.out.println("Thank you for playing the GIC geometry puzzle app");
            System.out.println("Have a nice day!");
            System.exit(0);
        }
        String[] coordinate = input.split(" ");
        int x = Integer.parseInt(coordinate[0]);
        int y = Integer.parseInt(coordinate[1]);
        Integer[] testPoint = new Integer[2];
        testPoint[0] =x;
        testPoint[1]=y;
        if (isPointInsideShape(testPoint)) {
            System.out.println("Coordinates " +  "("+testPoint[0]+","+testPoint[1]+")"+ " is inside your finalized shape");
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
