package calculadora.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import calc.Calculadora;

class TestCalculadora {
	static Calculadora calculadora;
	@BeforeAll
	static void setUpBeforeAll() throws Exception {
		calculadora = new Calculadora();
	}

	@Test
	void somaTest() {
		assertEquals(calculadora.somar(23, 27), 50);
		assertEquals(calculadora.somar(-23, -27), -50);
		assertEquals(calculadora.somar(-23, 27), 4);
		assertEquals(calculadora.somar(1.4, 1.6), 3);
	}
	@Test
	void subTest() {
		assertEquals(calculadora.subtrair(23, 27), -4);
		assertEquals(calculadora.subtrair(-23, -27), 4);
		assertEquals(calculadora.subtrair(-23, 27), -50);
	}
	@Test
	void divisaoTest() {
		assertEquals(calculadora.dividir(4, 2), 2);
		assertEquals(calculadora.dividir(-4, 2), -2);
		assertEquals(calculadora.dividir(-4, -2), 2);
		assertEquals(calculadora.dividir(4, 0), Double.POSITIVE_INFINITY);
		assertEquals(calculadora.dividir(-4, 0), Double.NEGATIVE_INFINITY);
	}
	@Test
	void multTest() {
		assertEquals(calculadora.multiplicar(4, 2), 8);
		assertEquals(calculadora.multiplicar(-4, 2), -8);
		assertEquals(calculadora.multiplicar(-4, -2), 8);
		assertEquals(calculadora.multiplicar(4, 0), 0);
	}
	@Test
	void PotTest() {
		assertEquals(calculadora.potenciar(2),4);
		assertEquals(calculadora.potenciar(-2),4);
	}
	@Test
	void RadTest() {
		assertEquals(calculadora.radical(4, 2), 2);
	}
	@Test
	void InvTest() {
		assertEquals(calculadora.inverter(2),0.5, 0.1);
	}
}
