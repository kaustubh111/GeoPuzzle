package com.gic.geopuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CustomShapeCreatorImpl implements CustomShapeCreator {

    public static List<List<Integer>> shape = new ArrayList<>();
    ConvexShapeValidator convexShapeValidator = new ConvexShapeValidatorImpl();

    @Override
    public void createCustomShape(Scanner scanner) {
        System.out.println("Please enter coordinates 1 in x y format");
        addCoordinate(scanner,0,0);
    }

    private void addCoordinate(Scanner scanner,Integer x, Integer y) {
        if(x==0 && y ==0) {
            x = scanner.nextInt();
            y = scanner.nextInt();
        }
        List<Integer> point = new ArrayList<>();
        point.add(x);
        point.add(y);
        if (isValidCoordinate(point)) {
            boolean isConvex = convexShapeValidator.isConvexShape(shape);
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
                        String[] coordinate = input2.split(" ");
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

        } else {
            if(shape.size() >= 3) {
                System.out.println("New coordinates" + "(" + point.get(0) + "," + point.get(1) + ")" + " is invalid!!!");
                System.out.println("Not Adding new coordinates to current shape");
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

    private static void printShape() {
        IntStream.range(0, shape.size())
                .mapToObj(i -> (i + 1) + ":" +  "("+shape.get(i).get(0)+","+shape.get(i).get(1)+")")
                .forEach(System.out::println);
    }


}
