package com.gic.geopuzzle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class CustomShapeTest {

    private CustomShapeCreatorImpl customShapeCreator;

    @BeforeEach
    void setUp() {
        customShapeCreator = new CustomShapeCreatorImpl();
    }

    @Test
    void testCreateCustomShape_Success() {
        List<List<Integer>> expectedShape = new ArrayList<>();
        expectedShape.add(List.of(1, 2));
        expectedShape.add(List.of(3, 4));
        expectedShape.add(List.of(5, 6));

        String input = "1 2\n3 4\n5 6\n#\n#\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        customShapeCreator.createCustomShape(scanner);

        assertEquals(expectedShape, CustomShapeCreatorImpl.shape);
    }

    @Test
    void testCreateCustomShape_InvalidCoordinate() {
        String input = "1 1\n5 0\n5 1\n4 0\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        assertThrows(IllegalStateException.class, () -> customShapeCreator.createCustomShape(scanner));
    }

}
