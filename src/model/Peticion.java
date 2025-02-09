package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import controllers.LaboratorioController;
import controllers.PracticaController;
import enums.EstadoPeticion;

public class Peticion {

	private int idPeticion;
	private Paciente paciente;
	private String obraSocial;
	private LocalDateTime fechaCarga;
	private LocalDateTime fechaEntrega;
	private List<Practica> practicasAsociadas = new ArrayList<Practica>();
	private Sucursal sucursal;
	private EstadoPeticion estado;
	private List<Resultado> resultado;

	public Peticion(int idPeticion, Paciente paciente, String os, String idSucursal, List<PracticaPeticion> pedidas) {
		int horas = 0;
		this.idPeticion = idPeticion;
		this.paciente = paciente;
		this.obraSocial = os;
		this.fechaCarga = LocalDateTime.now();

		if (!pedidas.isEmpty()) {
			for (PracticaPeticion p : pedidas) {
				this.practicasAsociadas.add(PracticaController.getInstancia().getPractica(p.getCodigoPracticaAsociada()));
				horas = PracticaController.getInstancia().getPractica(p.getCodigoPracticaAsociada()).getCantidadHorasEspera();
			}
		} else {
			this.practicasAsociadas = new ArrayList<Practica>();
		}
		this.fechaEntrega = fechaCarga.plusHours(horas);
		this.sucursal = LaboratorioController.getInstancia().getSucursalByNroSuc(Integer.valueOf(idSucursal));
		this.estado= EstadoPeticion.ACTIVA;
	}



//	public Peticion(int id, int pacienteId, String os, String sucursalId, List<PracticaPedida> practicasPedidas) {
//    	this.id = id; //PeticionesManager.getInstancia().generateId();
//    	this.paciente = PacientesManager.getInstancia().getPaciente(pacienteId);
//    	
//    	this.obraSocial = os;
//    	this.fechaCarga = LocalDateTime.now();
//    	
//    	if (!practicasPedidas.isEmpty()){
//    		this.practicasAsociada = practicasAsociada;
//    	} else {
//    		this.practicasAsociada = new ArrayList<Practica>();
//    	}
//    	
//    	this.sucursal = SucursalesManager.getInstancia().getSucursal(sucursalId);  	
//    	this.finalizado = false;
//    	
//    	this.paciente.addPeticion(this);
//    	this.sucursal.addPeticion(this);
//    }

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}

	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDateTime fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public List<Practica> getPracticasAsociadas() {
		return practicasAsociadas;
	}

	public void setPracticasAsociadas(List<Practica> practicasAsociadas) {
		this.practicasAsociadas = practicasAsociadas;
	}

	public EstadoPeticion getEstado() {
		return estado;
	}

	public void setEstado(EstadoPeticion estado) {
		this.estado = estado;
	}

	public void setIdPeticion(int idPeticion) {
		this.idPeticion = idPeticion;
	}

	public void setFechaCarga(LocalDateTime fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public int getIdPeticion() {
		return idPeticion;
	}

	public LocalDateTime getFechaCarga() {
		return fechaCarga;
	}

	public List<Resultado> getResultado() {
		return resultado;
	}

	public void setResultado(List<Resultado> resultado) {
		this.resultado = resultado;
	}


//	public boolean esCritica() {
//		return practicasAsociadas.stream().anyMatch(Practica::esCritica);
//	}
//
//	public boolean esReservada() {
//		return practicasAsociadas.stream().anyMatch(Practica::esReservada);
//	}
//

}
