package edu.ldj.planner.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ldj.planner.task.EasyDate;

public class EasyDateTest {
	EasyDate date = new EasyDate();

	@Before
	public void setUp() throws Exception {
		date = new EasyDate(2022, 05, 31);
	}

	@Test
	public void testGets() {
		assertEquals(date.getDay(), 31);
		assertEquals(date.getMonth(), 05);
		assertEquals(date.getYear(), 2022);
	}
	@Test
	public void testSets() {
		date.setDay(1);
		assertEquals(date.getDay(), 01);
		date.setMonth(12);
		assertEquals(date.getMonth(), 12);
		date.setYear(2013);
		assertEquals(date.getYear(), 2013);
	}
	@Test
	public void testGetDaysMonth() {
		int[] daysMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		for(int i = 0; i < 12; i++) {
			date = new EasyDate(2022, i+1, 1);
			assertEquals(date.getMonthDays(), daysMonth[i]);			
		}
		date = new EasyDate(2020, 2, 1);
		assertEquals(date.getMonthDays(), 29);
	}
	@Test
	public void testAddDay() {
		date.addDays(1);
		System.out.println(date.toString());
		assertEquals(date.getDay(), 1);
		assertEquals(date.getMonth(), 6);
		assertEquals(date.getYear(), 2022);
	}

}
