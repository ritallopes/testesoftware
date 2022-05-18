package fila;

public class Fila implements IFila {

	private static final int capacidade = 5;
	private Object[] Fila;  //Array que representa a fila
	private final int tam; // capacidade total da fila
	private int fim = -1; //final da fila

	public int getFim() {
		return fim;
	}

	public int getTam() {
		return tam;
	}
	public Object getItem(int indice) {
		return Fila[indice];
	}
	
	public int getCapacidade() {
		return capacidade;
	}

	public Fila(){
		this.tam = capacidade;
	}

	public Fila(int cap){
		tam = cap;
		Fila = new Object[tam];
	}

	public int tamanho() {
		return fim;
	}

	public boolean estaVazia() {
		return (fim == -1) ? true : false;
	}

	public boolean estaCheia() {
		return (fim >= tam) ? true : false;
	}

	public void insereNaFila(Object obj) throws FilaCheiaException {
		fim++;
		if(estaCheia()) {
			fim--;
			throw new FilaCheiaException();
		}
		Fila[fim] = obj;
	}

	public Object removeDaFila() throws FilaVaziaException {
		if(estaVazia()) {
			throw new FilaVaziaException();
		}
		Object item = Fila[0];
		for(int k = 1; k <= fim; k++) {
			Fila[k-1] = Fila[k];
		}
		fim--;
		return item;
	}

	public void limpaFila(){
		for(int i=0; i < tam; i++)
			Fila[i] = null;
		fim=-1;
	}
}
