package model;

import java.util.Date;
import java.util.List;

import controllers.LaboratorioController;


public class Paciente extends Persona{

	private int idPaciente;
    private String sexo;
	private int edad;

	public Paciente(int idPaciente, int dni, String nombre, String sexo, Date fechaNacimiento, String domicilio, int edad, String email){
		this.idPaciente = idPaciente;
		this.setDni(dni);
		this.setNombre(nombre);
		this.sexo = sexo;
//		this.setFechaNacimientoFormateada(fechaNacimiento);
		this.setFechaNacimiento(fechaNacimiento);
//		calcularEdad();
		this.setDomicilio(domicilio);
		this.edad = edad;
		this.setEmail(email);
	}

	public boolean bajaPaciente(int dni) {
		return false;
	}
	
	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		calcularEdad();
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

//	public void editPaciente (int dni, String nombre, String domicilio, String sexo, int edad) {
//		this.setNombre(nombre);
//		this.setDomicilio(domicilio);
//		this.sexo = sexo;
//		this.edad = edad;
//	}
    
    public boolean tienePeticiones() {
    	List<Peticion> peticionesPaciente = LaboratorioController.getInstancia().getPeticionesPorDNI(this.getDni());
    	if (peticionesPaciente.size() == 0){
        	return false ; 
        } else { return true ; }
    }

	public void calcularEdad() {
//		Calendar now = Calendar.getInstance();
//		Calendar dob = Calendar.getInstance();
//		dob.setTime(this.getFechaNacimiento());
//		int year1 = now.get(Calendar.YEAR);
//		int year2 = dob.get(Calendar.YEAR);
//		int age = year1 - year2;
//		int month1 = now.get(Calendar.MONTH);
//		int month2 = dob.get(Calendar.MONTH);
//		if (month2 > month1) {
//			age--;
//		} else if (month1 == month2) {
//			int day1 = now.get(Calendar.DAY_OF_MONTH);
//			int day2 = dob.get(Calendar.DAY_OF_MONTH);
//			if (day2 > day1) {
//				age--;
//			}
//		}
//		this.edad = age;
	}

}
