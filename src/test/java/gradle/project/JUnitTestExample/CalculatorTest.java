package gradle.project.JUnitTestExample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 */

/**
 * @author khant
 *
 */
class CalculatorTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("This Method is Executed Before All the Test Method!");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("This Method is Executed Before Each Test!");
	}

	/**
	 * Test method for
	 * {@link gradle.project.JUnitTestExample.Calculator#add(int, int)}.
	 */
	@Test
	void testAdd() {
		System.out.println("testAdd Running!");
		Calculator calculator = new Calculator();
		int a = 50;
		int b = 30;
		int actual = calculator.add(a, b);

		int expected = 80;

		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link gradle.project.JUnitTestExample.Calculator#subtract(int, int)}.
	 */
	@Test
	void testSubtract() {
		System.out.println("testSubtract Running!");
		Calculator calculator = new Calculator();
		int a = 50;
		int b = 20;
		int actual = calculator.subtract(a, b);

		int expected = 30;

		assertEquals(expected, actual);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		System.out.println("This Method is Executed After Each Test!");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("This Method is Executed After All test Methods!");
	}

}
