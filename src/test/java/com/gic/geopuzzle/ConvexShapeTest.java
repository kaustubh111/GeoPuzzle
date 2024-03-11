package com.gic.geopuzzle;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Vector;
import static org.junit.jupiter.api.Assertions.*;

public class ConvexShapeTest {

    @Test
    void testIsConvexShapeSuccess() {
        ConvexShapeValidatorImpl validator = new ConvexShapeValidatorImpl();

        // Define vertices of a convex shape
        List<List<Integer>> convexShape = new Vector<>();
        convexShape.add(List.of(0, 0));
        convexShape.add(List.of(1, 1));
        convexShape.add(List.of(2, 0));

        assertTrue(validator.isConvexShape(convexShape));
    }

    @Test
    void testIsConvexShapeFailure() {
        ConvexShapeValidatorImpl validator = new ConvexShapeValidatorImpl();

        // Define vertices of a non-convex shape
        List<List<Integer>> nonConvexShape = new Vector<>();
        nonConvexShape.add(List.of(0, 0));
        nonConvexShape.add(List.of(1, 1));
        nonConvexShape.add(List.of(2, 0));
        nonConvexShape.add(List.of(1, 9));

        assertFalse(validator.isConvexShape(nonConvexShape));
    }

    @Test
    void testConvexShapeCreator() {
        ConvexShapeValidatorImpl validator = new ConvexShapeValidatorImpl();

        // Define coordinates to create convex shape
        Vector<List<Integer>> coordinates = new Vector<>();
        coordinates.add(List.of(0, 0));
        coordinates.add(List.of(1, 1));
        coordinates.add(List.of(2, 0));

        Vector<List<Integer>> convexPolygon = validator.convexShapeCreator(coordinates);

        // Verify that the convex polygon is not null
        assertNotNull(convexPolygon);

        // Verify that the convex polygon is a convex shape
        assertTrue(validator.isConvexShape(convexPolygon));
    }
}

