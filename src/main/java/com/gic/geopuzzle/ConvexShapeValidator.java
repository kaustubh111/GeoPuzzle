package com.gic.geopuzzle;

import java.util.List;
import java.util.Vector;

public interface ConvexShapeValidator {

     boolean isConvexShape(List<List<Integer>> points);
     int orientation(List<Integer> p, List<Integer> q, List<Integer> r);
     Vector<List<Integer>> generateConvexShape(Vector<List<Integer>> points);
}
