package com.gic.geopuzzle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RandomShapeCreatorImpl.class,ConvexShapeValidatorImpl.class})
class RandomShapeTest {


	private RandomShapeCreatorImpl randomShapeCreator;
	private ConvexShapeValidatorImpl convexShapeValidator;

	@BeforeEach
	void initMock(){
		randomShapeCreator = new RandomShapeCreatorImpl();
		convexShapeValidator = new ConvexShapeValidatorImpl();
		randomShapeCreator.shape.clear();
		List a1 = new ArrayList<>();
		List a2 = new ArrayList<>();
		List a3 = new ArrayList<>();

		a1.add(1);
		a1.add(1);

		a2.add(5);
		a2.add(1);

		a3.add(5);
		a3.add(5);

		randomShapeCreator.shape.add(a1);
		randomShapeCreator.shape.add(a2);
		randomShapeCreator.shape.add(a3);
	}

	@Test
	public void testIsPointInsideShape_Success() {

		Integer[] testPoint1 =new Integer[]{3, 3};
		Integer[] testPoint2 = new Integer[]{4, 2};

		assertTrue(randomShapeCreator.isPointInsideShape(testPoint1));
		assertTrue(randomShapeCreator.isPointInsideShape(testPoint2));
	}

	@Test
	public void testIsPointInsideShape_Failure() {

		Integer[] testPoint1 = new Integer[]{6, 6};
		Integer[] testPoint2 = new Integer[]{0, 0};

		assertFalse(randomShapeCreator.isPointInsideShape(testPoint1));
		assertFalse(randomShapeCreator.isPointInsideShape(testPoint2));
	}

}
