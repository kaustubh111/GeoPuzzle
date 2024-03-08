package com.gic.geopuzzle;

import java.util.List;


public class ConvexShapeValidator {


    public static boolean isConvexShape(List<List<Integer>> points) {
        int n = points.size();
        return points.stream()
                .mapToInt(p -> orientation(p, points.get((points.indexOf(p) + 1) % n), points.get((points.indexOf(p) + 2) % n)))
                .distinct()
                .count() == 1;
    }

    public static int orientation(List<Integer> p, List<Integer> q, List<Integer> r) {
        int val = (q.get(1) - p.get(1)) * (r.get(0) - q.get(0)) - (q.get(0) - p.get(0)) * (r.get(1) - q.get(1));
        if (val == 0) {
            return 0; // Collinear
        }
        return (val > 0) ? 1 : -1; // Clockwise or counterclockwise
    }
}
