//package com.gic.geopuzzle;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//class GeopuzzleApplicationTests {
//
//	@Test
//	public void testIsPointInsideShape_Success() {
//		GeometryPuzzle.shape.clear();
//		GeometryPuzzle.shape.add(new Integer[]{1, 1});
//		GeometryPuzzle.shape.add(new Integer[]{5, 1});
//		GeometryPuzzle.shape.add(new Integer[]{5, 5});
//
//		Integer[] testPoint1 =new Integer[]{3, 3};
//		Integer[] testPoint2 = new Integer[]{4, 2};
//
//		assertTrue(GeometryPuzzle.isPointInsideShape(testPoint1));
//		assertTrue(GeometryPuzzle.isPointInsideShape(testPoint2));
//	}
//
//	@Test
//	public void testIsPointInsideShape_Failure() {
//		GeometryPuzzle.shape.clear();
//		GeometryPuzzle.shape.add(new Integer[]{1, 1});
//		GeometryPuzzle.shape.add(new Integer[]{5, 1});
//		GeometryPuzzle.shape.add(new Integer[]{5, 5});
//
//		Integer[] testPoint1 = new Integer[]{6, 6};
//		Integer[] testPoint2 = new Integer[]{0, 0};
//
//		assertFalse(GeometryPuzzle.isPointInsideShape(testPoint1));
//		assertFalse(GeometryPuzzle.isPointInsideShape(testPoint2));
//	}
//
//	@Test
//	public void testIsValidCoordinate_Success() {
//		GeometryPuzzle.shape.clear();
//		GeometryPuzzle.shape.add(new Integer[]{1, 1});
//		GeometryPuzzle.shape.add(new Integer[]{5, 1});
//
//		Integer[] newPoint = new Integer[]{5, 5};
//
//		assertTrue(GeometryPuzzle.isValidCoordinate(newPoint));
//	}
//
//	@Test
//	public void testIsValidCoordinate_Failure() {
//		GeometryPuzzle.shape.clear();
//		GeometryPuzzle.shape.add(new Integer[]{1, 1});
//		GeometryPuzzle.shape.add(new Integer[]{5, 1});
//
//		Integer[] duplicatePoint = new Integer[]{5, 1};
//
//		assertFalse(GeometryPuzzle.isValidCoordinate(duplicatePoint));
//	}
//}
