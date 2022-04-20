package tdd;

public class ContaCorrente {
	private double balance;
	private String name;

	public ContaCorrente() {
		
	}
	public ContaCorrente(double bal) {
		this.balance = bal;		
	}

	public double saldo() {
		return this.balance;
	}

	public void creditar(Deposito deposito) {
		this.balance = this.balance + deposito.valor;		
	}
	public String extrato() {
		String name = "James Grenning";
		double saldoinicial = 0 ;
		double saldofinal = 0; 
		return "Conta "+name+"\n";
	}

}
