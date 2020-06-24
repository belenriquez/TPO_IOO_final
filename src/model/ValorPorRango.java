package model;

public class ValorPorRango extends Valor {

	private double valorInicial;
	private double valorFinal;
	private double valor;
	private boolean debeEstarEnRango;
	
	
	public ValorPorRango(int min, int max) {
		this.valorInicial = min;
		this.valorFinal = max;
	}

	public double devolverValor() {
		return (Double) null;
	}
	
	private boolean analizarValorValido(double valorInicial, double valor, double valorFinal) {
		return false;
	}
	
	
	public double getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}
	public double getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}

}
