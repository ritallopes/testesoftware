package test;

import static org.junit.Assert.*;

import org.junit.Test;

import fila.Fila;
import fila.FilaCheiaException;
import fila.FilaVaziaException;

public class FilaTest {

	@Test
	public void testVazia() {
		Fila fila = new Fila();
		try {
			fila.removeDaFila();
			fail("N�o deve ser poss�vel remover de fila vazia");
		}catch(FilaVaziaException e) {
			e.printStackTrace();
		}
		}

	@Test
	public void testCheia() {
		Fila fila = new Fila(1);
		try {
			fila.insereNaFila("Primeiro");
			fila.insereNaFila("Segundo");
			fail("N�o deve ser poss�vel inserir na fila cheia");
		}catch(FilaCheiaException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testInsere() {
		Fila fila = new Fila(3);
		try {
			fila.insereNaFila("Primeiro");
			fila.insereNaFila("Segundo");
			fila.insereNaFila("�ltimo");
			assertEquals("Primeiro", fila.getItem(0));
			assertEquals("�ltimo", fila.getItem(fila.getFim()));
		}catch (Exception e){
			fail("Algo errado! Exce��o lan�ada.");	
			e.printStackTrace();
		}
	}
	@Test
	public void testRemove() {
		Fila fila = new Fila(3);
		fila.insereNaFila("Primeiro");
		fila.insereNaFila("Segundo");
		fila.insereNaFila("�ltimo");
		try {
			assertEquals("Primeiro", fila.removeDaFila());
			assertEquals("Segundo", fila.getItem(0));
			assertEquals("�ltimo", fila.getItem(fila.getFim()));
		}catch (Exception e){
			fail("Algo errado! Exce��o lan�ada.");	
			e.printStackTrace();
		}
	}
	@Test
	public void testLimpaFila() {
		Fila fila = new Fila(3);
		fila.insereNaFila("Primeiro");
		fila.insereNaFila("Segundo");
		fila.insereNaFila("Último");
		assertEquals(fila.tamanho(), -1);
	}
}
