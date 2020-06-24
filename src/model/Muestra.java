package model;

import java.util.Date;

public class Muestra {
	private int idMuestra;
	private String descripcion;
	private Date fechaRecepcion;
	
	public boolean procesarMuestra() {
		
		return false;
	}
	
	public int getIdMuestra() {
		return idMuestra;
	}
	public void setIdMuestra(int idMuestra) {
		this.idMuestra = idMuestra;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	
	
}
