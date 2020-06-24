package model;

import java.util.Date;

import enums.TipoValor;

public class Resultado {
	
	private int idResultado;
	private Date fechaEntrega;
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

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Valor getValor() {
		return valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}
	
}
