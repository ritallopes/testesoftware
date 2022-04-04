package calc;

public class Calculadora {	
	public double somar(double op1, double op2) {
		return op1 + op2;
	}
	public double subtrair(double op1, double op2) {
		return op1 - op2;
	}
	public double multiplicar(double op1, double op2) {
		return op1 * op2;
	}
	public double dividir(double op1, double op2) {
		if(op2 == 0) {
			if (op1 < 0)return Double.NEGATIVE_INFINITY;
			return Double.POSITIVE_INFINITY;
		}
		return op1 / op2;
	}
	public double potenciar(double base) {
		return Math.pow(base, 2);
	}
	public double radical(double radiciando, double indice) {
		return Math.pow(radiciando, 1/indice);
	}
	public double inverter(double num) {
		return dividir(1, num);
	}

}
