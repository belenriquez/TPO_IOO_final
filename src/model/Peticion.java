package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import controllers.LaboratorioController;
import controllers.PacienteController;
import controllers.PracticaController;
import enums.EstadoPeticion;

public class Peticion {

	private int idPeticion;
	private transient Paciente paciente;
	private String obraSocial;
	private LocalDateTime fechaCarga;
	private LocalDateTime fechaEntrega;
	private List<Practica> practicasAsociadas;
	private transient Sucursal sucursal;
	private EstadoPeticion estado;

	public Peticion(int idPeticion, int idPaciente, String os, String idSucursal, List<PracticaPeticion> pedidas) {
		this.idPeticion = idPeticion;
		this.paciente = PacienteController.getInstancia().getPaciente(idPaciente);
		this.obraSocial = os;
		this.fechaCarga = LocalDateTime.now();

		if (!pedidas.isEmpty()) {
			for (PracticaPeticion p : pedidas)
				this.practicasAsociadas
						.add(PracticaController.getInstancia().getPractica(p.getCodigoPracticaAsociada()));
		} else {
			this.practicasAsociadas = new ArrayList<Practica>();
		}

		this.sucursal = LaboratorioController.getInstancia().getSucursal(Integer.valueOf(idSucursal));
//    	this.finalizado = false;
//
//    	this.paciente.addPeticion(this);
//    	this.sucursal.addPeticion(this);
	}

	public Peticion(int idPeticion, Paciente paciente, String obraSocial, LocalDateTime fechaCarga,
			LocalDateTime fechaEntrega, List<Practica> practicasAsociadas, Sucursal sucursal, EstadoPeticion estado) {
		super();
		this.idPeticion = idPeticion;
		this.paciente = paciente;
		this.obraSocial = obraSocial;
		this.fechaCarga = fechaCarga;
		this.fechaEntrega = fechaEntrega;
		this.practicasAsociadas = practicasAsociadas;
		this.sucursal = sucursal;
		this.estado = estado;
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

//	public boolean esCritica() {
//		return practicasAsociadas.stream().anyMatch(Practica::esCritica);
//	}
//
//	public boolean esReservada() {
//		return practicasAsociadas.stream().anyMatch(Practica::esReservada);
//	}
//

}
