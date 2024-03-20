package com.gic.geopuzzle;

import java.util.Scanner;

public interface RandomShapeCreator {

    void generateRandomShape(Scanner scanner);
    void playPuzzle(Scanner scanner);
    boolean isPointInsideShape(Integer[] testPoint);
}
