package model;

import enums.TipoValor;

public class Resultado {
	
	private int idResultado;
	private Valor valor;
	
	public TipoValor tipoValor () {
		return null;
	}

	public int getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(int idResultado) {
		this.idResultado = idResultado;
	}



	public Valor getValor() {
		return valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}

	public Resultado(int idResultado, Valor valor) {
		super();
		this.idResultado = idResultado;
		this.valor = valor;
	}
	
}
