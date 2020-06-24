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
import model.Peticion;
import model.Practica;
import model.PracticaPeticion;
import model.Sucursal;

public class LaboratorioController {
	
	private List<Sucursal> sucursales;
	private List<Peticion> peticiones;
	private List<PracticaPeticion> practicasPeticiones;

	private static LaboratorioController instancia;
	private static UsuarioController usuarioController;
	
	private LaboratorioController(){
		usuarioController = UsuarioController.getInstancia();
		recuperarSucursalesGuardadas();
		recuperarPeticionesGuardadas();
		recuperarPracticasPeticionesGuardadas();
	}

	public static LaboratorioController getInstancia(){
    	if (instancia == null) {
    		instancia = new LaboratorioController()	;
    	}
    	return instancia;
    }

    public boolean validarRolUsuario(Operacion operacion){
		return usuarioController.validarRolUsuario(operacion);
	}

	//--------------SUCURSALES-----------------

	private void recuperarSucursalesGuardadas() {
		Gson gson = new Gson();
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Sucursal>>(){}.getType();
		try (FileReader reader = new FileReader("src/json/sucursales.json")) {
			this.sucursales = gson.fromJson(reader , listType);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void guardarSucursales(){
		try (Writer writer = new FileWriter("src/json/sucursales.json")) {
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(this.sucursales, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public Sucursal getSucursal(int idSucursal) {
		Sucursal sucursal = null;
		int i = 0;
		while (sucursal == null && i != sucursales.size()){
			if(sucursales.get(i).getIdSucursal() == idSucursal){
				sucursal = sucursales.get(i);
			}
			i++;
		}
		return sucursal;
	}

	public boolean altaSucursal(Sucursal sucursal) {
		if(!validarRolUsuario(Operacion.SUCURSAL_ALTA) || getSucursal(sucursal.getIdSucursal()) != null){
			return false;
		}
		existeSucursal : sucursales.add(sucursal);
		guardarSucursales();
		return true;
	}

	public boolean bajaSucursal(int id) {
		Sucursal sucursalParaEliminar = getSucursal(id);
		if (!validarRolUsuario(Operacion.SUCURSAL_BAJA) || sucursalParaEliminar == null){
			return false;
		}
		this.sucursales.remove(sucursalParaEliminar);
		guardarSucursales();
		return true;
	}

	public boolean modificarSucursal(Sucursal sucursalCambiada){
		Sucursal sucursalParaModificar = getSucursal(sucursalCambiada.getIdSucursal());
		if (!validarRolUsuario(Operacion.SUCURSAL_MODIFICACION) || sucursalParaModificar == null){
			return false;
		}
		sucursales.remove(sucursalParaModificar);
		sucursales.add(sucursalCambiada);
		guardarSucursales();
		return true;
	}

	//----------------PETICIONES--------------------

	private void recuperarPeticionesGuardadas() {
		Gson gson = new Gson();
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Peticion>>(){}.getType();
		try (FileReader reader = new FileReader("src/json/peticiones.json")) {
			this.peticiones = gson.fromJson(reader , listType);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void guardarPeticiones(){
		try (Writer writer = new FileWriter("src/json/peticiones.json")) {
			Gson gson = new GsonBuilder().create();
			gson.toJson(this.peticiones, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Peticion getPeticion(int idPeticion) {
		Peticion peticion = null;
		int i = 0;
		while (idPeticion != -1 && peticion == null && i != peticiones.size()){
			if(peticiones.get(i).getIdPeticion() == idPeticion){
				peticion = peticiones.get(i);
			}
			i++;
		}
		return peticion;
	}

	public boolean altaPeticion(Peticion peticion) {
		if(!validarRolUsuario(Operacion.PETICION_ALTA) || getPeticion(peticion.getIdPeticion()) != null){
			return false;
		}
		peticiones.add(peticion);
		guardarPeticiones();
		return true;
	}

	public boolean bajaPeticion(int idPeticion) {
		Peticion peticionParaEliminar = getPeticion(idPeticion);
		if (!validarRolUsuario(Operacion.PETICION_BAJA) || peticionParaEliminar == null){
			return false;
		}
		this.peticiones.remove(peticionParaEliminar);
		guardarPeticiones();
		return true;
	}

	public boolean modificarPeticion(Peticion peticionCambiada) {
		Peticion peticionParaModificar = getPeticion(peticionCambiada.getIdPeticion());
		if (!validarRolUsuario(Operacion.PETICION_MODIFICACION) || peticionParaModificar == null){
			return false;
		}
		peticiones.remove(peticionParaModificar);
		peticiones.add(peticionCambiada);
		guardarPeticiones();
		return true;
	}



	public List<Peticion> getPeticionesPorDNI(int dniPaciente) {
		//TODO: cambiar para reflejar PracticaPeticion
		List<Peticion> peticionesPaciente = new ArrayList<Peticion>();
		for(Peticion pet : peticiones) {
			if (pet.getPaciente().getDni() == dniPaciente) {
				peticionesPaciente.add(pet);
			}
		}
		return peticionesPaciente;
	}

	//------------------PRACTICAPETICION------------------------

	private void recuperarPracticasPeticionesGuardadas() {
		Gson gson = new Gson();
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Sucursal>>(){}.getType();
		try (FileReader reader = new FileReader("src/json/sucursales.json")) {
			this.practicasPeticiones = gson.fromJson(reader , listType);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void guardarPracticasPeticiones(){
		try (Writer writer = new FileWriter("src/json/sucursales.json")) {
			Gson gson = new GsonBuilder().create();
			gson.toJson(this.practicasPeticiones, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<PracticaPeticion> getPracticasPedidasByPeticion(Integer codigoPeticion) {
		List<PracticaPeticion> resultado = new ArrayList<PracticaPeticion>();

		for (PracticaPeticion pp : practicasPeticiones) {
			if(pp.getIdPeticionAsociada() == codigoPeticion){
				resultado.add(pp);
			}
		}

		return resultado;
	}

	public PracticaPeticion getPracticaPeticion(int idPracticaPeticion){
		PracticaPeticion practicaPeticion = null;
		int i = 0;
		while (idPracticaPeticion != -1 && practicaPeticion == null && i != practicasPeticiones.size()){
			if(practicasPeticiones.get(i).getIdPracticaPeticion() == idPracticaPeticion){
				practicaPeticion = practicasPeticiones.get(i);
			}
			i++;
		}
		return practicaPeticion;
	}

	public int generateIdSucursal() {
		return this.sucursales.size() + 1;
	}

	public int generateIdPeticion() {
		return peticiones != null ? this.peticiones.size() + 1 : 1;
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public List<Peticion> getPeticiones() {
		return peticiones;
	}
}