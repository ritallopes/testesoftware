package tdd;

import java.util.ArrayList;

public class ContaCorrente {
	private int balance = 0;
	private int saldoinicial=0;
	private String name = "";
	private ArrayList<Deposito> historico = new ArrayList<Deposito>();

	public ContaCorrente(String name) {
		this.name = name;
	}
	public ContaCorrente() {
		
	}
	public ContaCorrente(int bal) {
		this.saldoinicial = bal;
		this.balance = bal;		
	}

	public ContaCorrente(String string, int i) {
		this.saldoinicial = i;
		this.name = string;
		this.balance = i;
	}
	public int saldo() {
		return this.balance;
	}

	public void creditar(Deposito deposito) {
		this.balance = this.balance + deposito.valor;	
		this.historico.add(deposito);
	}
	public String extrato() {
		String hist = "";
		for(Deposito h: historico) {
			hist += h.toString();
		}
		return "Conta de "+this.name+"\nSaldo Inicial $"+saldoinicial+"\nSaldo Final $"+balance+"\n"+ (historico .isEmpty()? "Nenhuma transacao realizada\n" : hist);
	}

}
