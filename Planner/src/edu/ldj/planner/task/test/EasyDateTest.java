package edu.ldj.planner.task.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import edu.ldj.planner.task.EasyDate;

//@RunWith(Parameterized.class)
public class EasyDateTest {

//	@Parameter
//	int entrada;
//	@Parameter
//	int esperado;
	
	
	@Test
	public void testYearBissexto() {
		EasyDate d =  new EasyDate(2020, 02, 23);
		assertEquals(d.getMonthDays(), 29);
		d.setYear(2022);
		assertEquals(d.getMonthDays(), 28);
	}
}
