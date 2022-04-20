package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import temperature.Celsius;
import temperature.ConversorTemperatura;
import temperature.Fahrenheit;
import temperature.TempException;
import temperature.Temperatura;
@RunWith(Parameterized.class)
public class ConversorTemperaturaTeste {
	ConversorTemperatura conversor;
	@Before
	public void setUp() throws Exception {
		conversor = new ConversorTemperatura();
	}
	@Parameter(0)
	 public Temperatura entrada;
	
	 @Parameter(1)
	 public Temperatura esperado;
	 @Parameters
	 public static Iterable<Object[]> data() {
	 return Arrays.asList(new Object[][] {
	 { new Celsius(0), new Fahrenheit(32) }, { new Celsius(10), new Fahrenheit(50)}, { new Fahrenheit(32), new Celsius(0) },
	 { new Fahrenheit(50), new Celsius(10) }, {new Fahrenheit(0), new Celsius(-17.777)}});
	 }


	@Test
	public void test() {
		try {
			assertEquals(esperado.getValue(), conversor.converte(entrada).getValue(), 0.1);
		} catch (TempException e) {
			fail("Teste Falhou");
			e.printStackTrace();
		}
	}

}
