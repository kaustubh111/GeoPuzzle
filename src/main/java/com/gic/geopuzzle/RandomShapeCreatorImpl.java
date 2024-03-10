package com.gic.geopuzzle;

import java.util.*;
import java.util.stream.IntStream;

public class RandomShapeCreatorImpl implements RandomShapeCreator{

    public static List<List<Integer>> shape = new ArrayList<>();
    ConvexShapeValidator convexShapeValidator = new ConvexShapeValidatorImpl();

    @Override
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
        if(!convexShapeValidator.isConvexShape(shape)){
            generateRandomShape(scanner);
        }else {

            System.out.println("Your random shape is");
            printShape();
            playPuzzle(scanner);
        }
    }

    private static void printShape() {
        IntStream.range(0, shape.size())
                .mapToObj(i -> (i + 1) + ":" +  "("+shape.get(i).get(0)+","+shape.get(i).get(1)+")")
                .forEach(System.out::println);
    }

    public void playPuzzle(Scanner scanner) {
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
        playPuzzle(scanner);
    }
    @Override
    public boolean isPointInsideShape(Integer[] testPoint) {
        boolean result = false;
        int j = shape.size() - 1;

        for (int i = 0; i < shape.size(); i++) {
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
