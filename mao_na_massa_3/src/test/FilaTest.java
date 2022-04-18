package test;

import static org.junit.Assert.*;

import org.junit.Test;

import fila.Fila;
import fila.FilaCheiaException;
import fila.FilaVaziaException;

public class FilaTest {

	@Test
	public void testVazia() {
		Fila fila = new Fila(1);
		try {
			fila.removeDaFila();
			fail("Não deve ser possível remover de fila vazia");
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
			fail("Não deve ser possível inserir na fila cheia");
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
			fila.insereNaFila("Último");
			assertEquals("Primeiro", fila.getItem(0));
			assertEquals("Último", fila.getItem(fila.getFim()));
		}catch (Exception e){
			fail("Algo errado! Exceção lançada.");	
			e.printStackTrace();
		}
	}
	@Test
	public void testRemove() {
		Fila fila = new Fila(3);
		fila.insereNaFila("Primeiro");
		fila.insereNaFila("Segundo");
		fila.insereNaFila("Último");
		try {
			assertEquals("Primeiro", fila.removeDaFila());
			assertEquals("Segundo", fila.getItem(0));
			assertEquals("Último", fila.getItem(fila.getFim()));
		}catch (Exception e){
			fail("Algo errado! Exceção lançada.");	
			e.printStackTrace();
		}
	}
}
