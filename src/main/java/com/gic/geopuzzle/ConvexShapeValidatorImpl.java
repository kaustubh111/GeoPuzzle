package com.gic.geopuzzle;

import java.util.*;


public class ConvexShapeValidatorImpl implements ConvexShapeValidator {

    @Override
    public boolean isConvexShape(List<List<Integer>> points) {
        int n = points.size();
        return points.stream()
                .mapToInt(p -> orientation(p, points.get((points.indexOf(p) + 1) % n), points.get((points.indexOf(p) + 2) % n)))
                .distinct()
                .count() == 1;
    }
    @Override
    public int orientation(List<Integer> p, List<Integer> q, List<Integer> r) {
        int val = (q.get(1) - p.get(1)) * (r.get(0) - q.get(0)) - (q.get(0) - p.get(0)) * (r.get(1) - q.get(1));
        if (val == 0) {
            return 0; // Collinear
        }
        return (val > 0) ? 1 : -1; // Clockwise or counterclockwise
    }

    @Override
    public Vector<List<Integer>> convexShapeCreator(Vector<List<Integer>> coordinates) {
        // Sort the coordinates lexicographically (first by x-coordinate, then by y-coordinate)
        Vector<List<Integer>> convexPolygon = computeConvexHull(coordinates);

        // Print the convex polygon
        return convexPolygon;
        }

    public Vector<List<Integer>> computeConvexHull(List<List<Integer>> points) {
        points.sort((p1, p2) -> {
            if (!p1.get(0).equals(p2.get(0))) {
                return p1.get(0) - p2.get(0);
            }
            return p1.get(1) - p2.get(1);
        });

        // Compute the convex hull using the Graham Scan algorithm
        Stack<List<Integer>> hull = new Stack<>();
        for (int i = 0; i < points.size(); i++) {
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.peek(), points.get(i)) <= 0) {
                hull.pop();
            }
            hull.push(points.get(i));
        }

        int lowerSize = hull.size() + 1;
        for (int i = points.size() - 2; i >= 0; i--) {
            while (hull.size() >= lowerSize && orientation(hull.get(hull.size() - 2), hull.peek(), points.get(i)) <= 0) {
                hull.pop();
            }
            hull.push(points.get(i));
        }

        hull.pop(); // Remove the duplicate point
        return new Vector<>(hull);
    }

}
