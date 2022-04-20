package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import param.CalculoImpostoRenda;
@RunWith(Parameterized.class)
public class CalculoImpostoRendaTest {

	@Parameter(0)
	 public int entrada;
	
	 @Parameter(1)
	 public int esperado;
	 @Parameters
	 public static Iterable<Object[]> data() {
	 return Arrays.asList(new Object[][] {
	 { 1300, 130 }, { 6000, 900 }, { 20000,4000 },
	 { 1000, 0 }});
	 }

	 @Test
	 public void test() {
		 assertEquals(esperado, CalculoImpostoRenda.calculaImposto(entrada),0);
	 }

}
