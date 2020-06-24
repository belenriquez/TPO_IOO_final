package model;

import java.util.ArrayList;
import java.util.List;

import enums.EstadoPractica;

public class Practica {
	
    private int codigoPractica;
    private String nombrePractica;
    private List<Practica> grupo;
    private EstadoPractica estado;
    private int cantidadHorasEspera;
    private boolean enUso;


    //VC= Valor Critico
    public List<Practica> getPracticasGrupoVC(){
    	
		return null;
    }
    
	public Practica(int codigoPractica, String nombrePractica, List<Practica> grupo, EstadoPractica estado,
			int cantidadHorasEspera, boolean enUso) {
		super();
		this.codigoPractica = codigoPractica;
		this.nombrePractica = nombrePractica;
//		this.grupo = grupo;
		this.estado = estado;
		this.cantidadHorasEspera = cantidadHorasEspera;
		this.enUso = enUso;
	}
     
    
	public Practica(int codigoPractica, String nombrePractica, EstadoPractica estado,
			int cantidadHorasEspera, boolean enUso) {
		super();
		this.grupo = new ArrayList<Practica>();
		this.codigoPractica = codigoPractica;
		this.nombrePractica = nombrePractica;
		this.estado = estado.HABILITADA;
		this.cantidadHorasEspera = cantidadHorasEspera;
		this.enUso = false;
	}

	public void editPractica(int codigo, String nombre, int hs) {
	    this.codigoPractica = codigo;
	    this.nombrePractica = nombre;
	    this.cantidadHorasEspera = hs;
    }
	
	public String toString(){
		return this.nombrePractica.toString();
	}

	
	public int getCodigoPractica() {
		return codigoPractica;
	}

	public void setCodigoPractica(int codigoPractica) {
		this.codigoPractica = codigoPractica;
	}

	public String getNombrePractica() {
		return nombrePractica;
	}

	public void setNombrePractica(String nombrePractica) {
		this.nombrePractica = nombrePractica;
	}

	public List<Practica> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<Practica> grupo) {
		this.grupo = grupo;
	}

	public EstadoPractica getEstado() {
		return estado;
	}

	public void setEstado(EstadoPractica estado) {
		this.estado = estado;
	}

	public int getCantidadHorasEspera() {
		return cantidadHorasEspera;
	}

	public void setCantidadHorasEspera(int cantidadHorasEspera) {
		this.cantidadHorasEspera = cantidadHorasEspera;
	}

	public boolean isEnUso() {
		return enUso;
	}

	public void setEnUso(boolean enUso) {
		this.enUso = enUso;
	}
	
}
