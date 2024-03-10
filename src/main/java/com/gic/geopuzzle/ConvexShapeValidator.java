package com.gic.geopuzzle;

import java.util.List;

public interface ConvexShapeValidator {

    public boolean isConvexShape(List<List<Integer>> points);
    public int orientation(List<Integer> p, List<Integer> q, List<Integer> r);
}
