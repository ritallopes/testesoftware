package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import temperature.Celsius;
import temperature.Fahrenheit;
import temperature.Fahrenheit;
import temperature.TempException;

public class FahrenheitTest {
	Fahrenheit c;

	@Before
	public void setUp() throws Exception {
		c = new Fahrenheit();
	}

	@Test
	public void testFreeze() {
		assertEquals(c.getFREEZE(), 32, 0);
	}
	@Test
	public void testBoil() {
		assertEquals(c.getBOIL(), 212, 0);
	}
	@Test
	public void testZeroAbsoluto() {
		assertEquals(c.getZEROABSOLUTO(), -459.4, 0);
	}
	@Test 
	public void testSetValue() {
		try {
			c.setValue(-500);
			fail("abaixo de zero absoluto");
		} catch (TempException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testEquals() {
		try {
			c.setValue(10);
			Fahrenheit c2 = new Fahrenheit(10);
			assertEquals(c.equals(c2), true);
		} catch (TempException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			c.setValue(10);
			Celsius f2 = new Celsius(10);
			assertEquals(c.equals(f2), false);
		} catch (TempException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testToString() {
		assertEquals(c.toString(), c.getValue()+" F");
	}

}
