package io.javabrains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("When Running MathUtils")
class MathUtilsTest {

	MathUtils mathutils;
	TestInfo testInfo;
	TestReporter testReporter;

	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		mathutils = new MathUtils();
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with Tags" + testInfo.getTags());

	}

	@Test
	@DisplayName("Testing Add method")
	void testAdd() {
		int expecteds = 2;
		int actuals = mathutils.add(1, 1);
		assertEquals(expecteds, actuals, "the add method should add two numbers");
	}
	// =============================nested class =========================

	/*
	 * @Nested
	 * 
	 * @DisplayName("add method") class AddTest{
	 * 
	 * @Test
	 * 
	 * @DisplayName("when adding two positive Numbers") void testAddPositive() {
	 * assertEquals(2,mathutils.add(1, 1),"should return the right sum"); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("when adding two positive Numbers") void testAddNagative() {
	 * assertEquals(-1,mathutils.add(-1, -1),"should return the right sum"); } }
	 */

	// =======================================================================
	@Nested
	@DisplayName("add method")
	@Tag("Math")
	class AddTest {
		@Test
		@DisplayName("when adding two positive numbers ")
		void testAddPositive() {
			assertEquals(2, mathutils.add(1, 1), "should returns the right sum");
		}
		/*
		 * @Test
		 * 
		 * @DisplayName("when adding two positive numbers") void testAddNagative() {
		 * assertEquals(-1,mathutils.add(-1, -1),"should returns the right sum"); }
		 */

		@Test
		@DisplayName("when adding two positive numbers")
		void testAddNagative() {
			int excepted = -2;
			int actual = mathutils.add(-1, -1);
			assertEquals(excepted, actual, () -> "should returns the right sum" + excepted + "But returned" + actual);
		}
	}

	// ========================================================================
	@Test
	@DisplayName("multiply method")
	@Tag("Math")
	void testMultiply() {
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with Tags" + testInfo.getTags());
		assertAll(() -> assertEquals(4, mathutils.multiply(2, 2)), () -> assertEquals(0, mathutils.multiply(2, 0)),
				() -> assertEquals(-2, mathutils.multiply(2, -1)));
	}

	@Test
	@DisplayName("devide method")
	@Tag("Math")
	void testDivide() {
		boolean isServerUp = false;
		assumeTrue(isServerUp);
		assertThrows(ArithmeticException.class, () -> mathutils.devide(1, 0), "devide by zero should throw");
	}

	/*
	 * @RepeatedTest(3) void testComputeCircleRadius(RepetitionInfo repetitionInfo)
	 * { assertEquals(314.1592653589793, mathutils.computeCircleArea(10),
	 * "should return right circle area"); }
	 */
	@Test
	@Tag("Circle")
	void testComputeCircleRadius(RepetitionInfo repetitionInfo) {
		assertEquals(314.1592653589793, mathutils.computeCircleArea(10), "should return right circle area");
	}

	/*
	 * @Test
	 * 
	 * @Disabled
	 * 
	 * @DisplayName("TDD method, sholud not run") void testDisabled() {
	 * fail("this test should be disabled"); }
	 */

}
