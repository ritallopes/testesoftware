package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import game.ArgumentoInvalidoException;
import game.GameSeller;

@RunWith(Parameterized.class)
public class GameSellerTest {
	GameSeller gs;
	@Before
	public void setUp() throws Exception {
		gs = new GameSeller();
	}

	@Parameter(0)
	 public int tipoEntrada;
	@Parameter(1)
	 public double valorEntrada;
	
	 @Parameter(2)
	 public double esperado;
	 
	 @Parameters(name = "{index}: calcularValor({0},{1})={2}")
	 public static Iterable<Object[]> data() {
	 return Arrays.asList(new Object[][] {
	 {1,100,130},{1,200,200}, {2,100,100}, {3,99,129}, {3,200,220} });
	 }
	@Test
	public void test() throws ArgumentoInvalidoException {
		assertEquals(gs.calculaPreco(tipoEntrada, valorEntrada), esperado, 0.1);
	}

}