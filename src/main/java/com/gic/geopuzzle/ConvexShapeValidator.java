package com.gic.geopuzzle;

import java.util.List;
import java.util.Vector;

public interface ConvexShapeValidator {

    public boolean isConvexShape(List<List<Integer>> points);
    public int orientation(List<Integer> p, List<Integer> q, List<Integer> r);
    public Vector<List<Integer>> convexShapeCreator(Vector<List<Integer>> points);
}
