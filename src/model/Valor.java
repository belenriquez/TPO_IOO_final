package model;

import enums.TipoValor;

public abstract class Valor {

	private int idValor;
	private TipoValor tipoValor;
	
	
	public int getIdValor() {
		return idValor;
	}
	public void setIdValor(int idValor) {
		this.idValor = idValor;
	}
	public TipoValor getTipoValor() {
		return tipoValor;
	}
	public void setTipoValor(TipoValor tipoValor) {
		this.tipoValor = tipoValor;
	}

}
