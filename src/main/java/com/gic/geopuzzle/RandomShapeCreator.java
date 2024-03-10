package com.gic.geopuzzle;

import java.util.Scanner;

public interface RandomShapeCreator {

    public void generateRandomShape(Scanner scanner);
    public void playPuzzle(Scanner scanner);
    public boolean isPointInsideShape(Integer[] testPoint);
}
