package test;

import static org.junit.Assert.*;

import org.junit.Test;

import lib.exceptions.InvalidDateException;
import lib.util.Date;

public class DateTest {

	@Test
	public void testContructor() {
		try {
			Date d = new Date(1, 2,2,2,2,2022);
		} catch (InvalidDateException e){
			fail("Falha no construtor");
			}
		try {
			Date d = new Date(1, 2,2,2,2,2022);
		} catch (InvalidDateException e){
			fail("Falha no construtor com se");
		}

	}
	
	
	@Test
	public void testAnteriorData() {
		try {
			Date d = new Date(1,1,2022);
			Date da = d.anteriorData();
			assertEquals(da.getAno(), 2021);
			assertEquals(da.getDia(), 31);
			assertEquals(da.getMes(), 12);
		} catch (InvalidDateException e){
			fail("Falha em anterior data");
			}
		try {
			Date d = new Date(1,3,2020);
			Date da = d.anteriorData();
			assertEquals(da.getAno(), 2020);
			assertEquals(da.getDia(), 29);
			assertEquals(da.getMes(), 02);
		} catch (InvalidDateException e){
			fail("Falha em anterior data - ano bissexto");
			}
	}
	
	@Test 
	public void testDiferencaEmDias() {
		try {
			Date d1 = new Date(1,3,2020);
			Date d2 = new Date (1, 3, 2019);
			assertEquals(Date.diferencaEmDias(d1, d2), 366);
		} catch (InvalidDateException e){
			fail("Falha em Diferença em Dias");
		}
	}
	
	@Test
	public void testFormat() {
		Date d;
		try {
			d = new Date(1,3,2020);
			d.format(1);
			assertEquals(d.toString(), "1/3/2020");
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
