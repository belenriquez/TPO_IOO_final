package model;

import java.util.Date;
import java.util.List;

public class PracticaPeticion {

    private int idPracticaPeticion;
    private List<Muestra> muestras;
    private Date fechaRealizacion;
    private int codigoPracticaAsociada;
    private int idPeticionAsociada;
    private Resultado resultado;
    private int dniPaciente;

    public PracticaPeticion(int idPractica, int idPeticion) {
    	this.idPeticionAsociada = idPeticion;
    	this.idPracticaPeticion = idPractica;
    }

    public PracticaPeticion(Practica practica, Object o) {
    	this.codigoPracticaAsociada = practica.getCodigoPractica();
    }

    public Muestra getMuestra(int IdMuestra) {
    	return null;
    }
    
    
    
	public int getIdPracticaPeticion() {
		return idPracticaPeticion;
	}
	public void setIdPracticaPeticion(int idPracticaPeticion) {
		this.idPracticaPeticion = idPracticaPeticion;
	}
	public List<Muestra> getMuestras() {
		return muestras;
	}
	public void setMuestras(List<Muestra> muestras) {
		this.muestras = muestras;
	}
	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}
	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}
	public int getCodigoPracticaAsociada() {
		return codigoPracticaAsociada;
	}
	public void setCodigoPracticaAsociada(int codigoPracticaAsociada) {
		this.codigoPracticaAsociada = codigoPracticaAsociada;
	}

	public int getIdPeticionAsociada() {
		return idPeticionAsociada;
	}

	public void setIdPeticionAsociada(int idPeticionAsociada) {
		this.idPeticionAsociada = idPeticionAsociada;
	}

	public Resultado getResultado() {
		return resultado;
	}
	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}
	public int getDniPaciente() {
		return dniPaciente;
	}
	public void setDniPaciente(int dniPaciente) {
		this.dniPaciente = dniPaciente;
	}

}
