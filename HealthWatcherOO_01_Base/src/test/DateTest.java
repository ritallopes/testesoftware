package test;

import static org.junit.Assert.*;

import org.junit.Test;

import lib.exceptions.InvalidDateException;
import lib.util.Date;

public class DateTest {

	@Test
	public void testMes() {
		try {
			Date d = new Date(1,1,2022);
			assertEquals(d, new Date(1,1,2022));
		} catch (InvalidDateException e) {
			fail("A data é válida!");
		}
		try {
			Date dt = new Date(1,12,2022);
			assertEquals(dt, new Date(1,12,2022));
		} catch (InvalidDateException e) {
			fail("A data é válida!");
		}
	}

}
