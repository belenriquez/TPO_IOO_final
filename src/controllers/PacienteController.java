package controllers;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import enums.Operacion;
import model.Paciente;
import model.Sucursal;

public class PacienteController {
	
	private List<Paciente> pacientes;
   	
	private static PacienteController instancia;
	private static LaboratorioController laboratorioController;
	
	private PacienteController(){
		laboratorioController = LaboratorioController.getInstancia();
		recuperarPacientesGuardados();
	}

	public static PacienteController getInstancia(){
		if (instancia == null) {
			instancia = new PacienteController();
		}
		return instancia;
	}

	private void recuperarPacientesGuardados() {
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Paciente>>(){}.getType();
		try (FileReader reader = new FileReader("src/json/pacientes.json")) {
			this.pacientes = new Gson().fromJson(reader , listType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void guardarPacientes(){
		try (Writer writer = new FileWriter("src/json/pacientes.json")) {
			Gson gson = new Gson();
		    gson.toJson(this.pacientes, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public Paciente getPaciente(int idPaciente) {
		Paciente paciente = null;
		int i = 0;
		while (idPaciente != -1 && paciente == null && i != pacientes.size()){
			if(pacientes.get(i).getIdPaciente() == idPaciente){
				paciente = pacientes.get(i);
			}
			i++;
		}
		return paciente;
	}

	public Paciente getPacienteByDni(int documento) {
		Paciente paciente = null;
		int i = 0;
		while (documento != -1 && paciente == null && i != pacientes.size()){
			if(pacientes.get(i).getDni() == documento){
				paciente = pacientes.get(i);
			}
			i++;
		}
		return paciente;
	}
	
	public boolean altaPaciente(Paciente paciente){
		if(!laboratorioController.validarRolUsuario(Operacion.PACIENTE_ALTA) || getPaciente(paciente.getDni()) != null) {
			return false;
		}
		pacientes.add(paciente);
		guardarPacientes();
		return true;
	}

	public boolean bajaPaciente(int idPaciente) {
		Paciente pacienteParaEliminar = getPaciente(idPaciente);
		if(!laboratorioController.validarRolUsuario(Operacion.PACIENTE_BAJA) || pacienteParaEliminar == null){
			return false;
		}
		pacientes.remove(pacienteParaEliminar);
		guardarPacientes();
		return true;
	}

		
	public boolean modificarPaciente(Paciente pacienteCambiado){
		Paciente pacienteParaEliminar = getPaciente(pacienteCambiado.getIdPaciente());
		if(!laboratorioController.validarRolUsuario(Operacion.PACIENTE_MODIFICACION) || pacienteParaEliminar == null){
			return false;
		}
		pacientes.remove(pacienteParaEliminar);
		pacientes.add(pacienteCambiado);
		guardarPacientes();
		return true;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public int generateIdPaciente() {
		Paciente aux = this.getPaciente(this.pacientes.size());
		while(aux != null) {
			aux = this.getPaciente(this.pacientes.size() + 1);
		}
		return this.pacientes.size() + 1;
	}
}