package test;

import static org.junit.Assert.*;

import org.junit.Test;

import banco.Conta;
import banco.OperacaoIlegalException;

public class TestConta {
	
	@Test 
	public void testCreditar() {
		Conta c1 = new Conta("a", 1500);
		try {
			c1.creditar(500);
			assertEquals(c1.getSaldo(), 2000.00, 0);
		} catch (OperacaoIlegalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Conta c2 = new Conta("b", 2000);
		try {
			c2.creditar(-1000);
			fail("não deve ser possível creditar valores negativos");
		} catch (OperacaoIlegalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDebitar() {
		Conta c1 = new Conta("a", 1500);
		try {
			c1.debitar(500);
			assertEquals(c1.getSaldo(), 1000.00, 0);
		} catch (OperacaoIlegalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Conta c2 = new Conta("b", 2000);
		try {
			c2.debitar(-1000);
			fail("não deve ser possível debitar valores negativos");
		} catch (OperacaoIlegalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Conta c3 = new Conta("c", 2000);
		try {
			c2.debitar(2001.00);
			fail("não deve ser possível debitar valor acima do saldo");
		} catch (OperacaoIlegalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testTransferir() {
		Conta c1 = new Conta("a",  3000);
		//transferir: retirar da primeira conta e 
		//adicionar na segunda conta
				//testar valor inválido;
		Conta c2 = new Conta("b", 1000);
		try {
			c1.transferir(c2, 1000);
		} catch (OperacaoIlegalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
