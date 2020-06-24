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

import enums.EstadoPractica;
import enums.Operacion;
import model.Peticion;
import model.Practica;
import model.Resultado;

public class PracticaController {

	private List<Practica> practicas;
	private List<Resultado> resultados;
	private static UsuarioController usuarioController;

	private static PracticaController instancia;

	private PracticaController() {
//		this.practicas = new ArrayList<Practica>();
//		this.practicas.add(new Practica("A00001", "Globulos blancos", null, EstadoPractica.HABILITADA, 8, false));
//		this.practicas.add(new Practica("A00002", "Globulos blancos", null, EstadoPractica.INHABILITADA, 10, true));
//		this.practicas.add(new Practica("A00003", "Globulos blancos", null, EstadoPractica.HABILITADA, 12, true));
//		guardarPracticas();

		usuarioController = UsuarioController.getInstancia();
		recuperarPracticasGuardadas();
		recuperarResultadosGuardados();
	}


	public static PracticaController getInstancia() {
		if (instancia == null) {
			instancia = new PracticaController();
		}
		return instancia;
	}

	public boolean validarRolUsuario(Operacion operacion) {
		return usuarioController.validarRolUsuario(operacion);
	}

	/********************************  PRACTICAS  ********************************/
	public boolean altaPractica(Practica practica) {
		if (!validarRolUsuario(Operacion.PRACTICA_ALTA) || getPractica(practica.getCodigoPractica()) != null) {
			return false;
		}
		practicas.add(practica);

		guardarPracticas();

		return true;
	}

	public boolean bajaPractica(int idPractica) {
		Practica practicaParaEliminar = getPractica(idPractica);
		if (!validarRolUsuario(Operacion.PRACTICA_BAJA) || practicaParaEliminar == null) {
			return false;
		}
		if(practicaParaEliminar.isEnUso()) { //TODO agregar check de peticion de inhabilitacion
			return inhabilitarPractica(practicaParaEliminar);
		}
		this.practicas.remove(practicaParaEliminar);

		guardarPracticas();

		return true;
	}

	private boolean inhabilitarPractica(Practica practica) {
		Practica practicaInhabilitada = getPractica(practica.getCodigoPractica());
		practicaInhabilitada.setEnUso(false);
		practicas.remove(practica);
		practicas.add(practicaInhabilitada);
		guardarPracticas();
		
		return false;
	}

	public boolean modificarPractica(Practica practicaModificada) {
		Practica practica = getPractica(practicaModificada.getCodigoPractica());
		if (!validarRolUsuario(Operacion.PRACTICA_MODIFICACION) || practica == null) {
			return false;
		}
		
		practicas.remove(practica);
		practicas.add(practicaModificada);
		guardarPracticas();
		
		return true;
	}


	private void guardarPracticas() {
		try (Writer writer = new FileWriter("src/json/practicas.json")) {
			Gson gson = new GsonBuilder().create();
			gson.toJson(this.practicas, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Practica getPractica(int codigo) {
		Practica practica = null;
		int i = 0;
		while (codigo != -1 && practica == null && i != practicas.size()){
			if(practicas.get(i).getCodigoPractica() == codigo){
				practica = practicas.get(i);
			}
			i++;
		}
		return practica;
	}

	public List<Practica> getPracticas() {
		return this.practicas;
	}

	public Practica getPracticaPorNombre(String nombre) {
		for (Practica pra : practicas) {
			if (pra.getNombrePractica().equals(nombre)) {
				return pra;
			}
		}
		return null;

	}

	private void recuperarPracticasGuardadas() {
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Practica>>() {
		}.getType();
		try (FileReader reader = new FileReader("src/json/practicas.json")) {
			this.practicas = new Gson().fromJson(reader, listType);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cargarDatosPracticas(int codigo, String nombre, List<Practica> grupo, EstadoPractica estado, int hs,
							boolean enUso) {
		boolean existing = false;
		for (Practica pra : practicas) {
			if (pra.getCodigoPractica() == codigo) {
				pra.editPractica(codigo, nombre, hs);
				existing = true;
			}
		}

		if (!existing) {
			practicas.add(new Practica(codigo, nombre, grupo, estado, hs, enUso));
		}
		guardarPracticas();

	}
	
	/********************************  RESULTADOS  ********************************/
	public boolean altaResultado(Resultado resultado) {
		if (!validarRolUsuario(Operacion.RESULTADO_ALTA) || getResultado(resultado.getIdResultado()) != null) {
			return false;
		}
		resultados.add(resultado);
		guardarResultados();
		
		return true;
	}
	
	public boolean bajaResultado(int idResultado) {
		Resultado resultadoParaEliminar = getResultado(idResultado);
		if (!validarRolUsuario(Operacion.RESULTADO_BAJA) || resultadoParaEliminar == null){
			return false;
		}
		this.resultados.remove(resultadoParaEliminar);
		guardarResultados();
		return true;
	}

	public boolean modificarResultado(Resultado resultadoModificado){
		Resultado resultado = getResultado(resultadoModificado.getIdResultado());
		if (!validarRolUsuario(Operacion.RESULTADO_MODIFICACION) || resultado == null){
			return false;
		}
		resultados.remove(resultado);
		resultados.add(resultadoModificado);
		guardarResultados();
		return true;
	}
	
	
	private Resultado getResultado(int idResultado) {
		Resultado resultado = null;
		int i = 0;
		while (idResultado != -1 && resultado == null && i != resultados.size()){
			if(resultados.get(i).getIdResultado() == idResultado){
				resultado = resultados.get(i);
			}
			i++;
		}
		return resultado;
	}

	private void guardarResultados() {
		try (Writer writer = new FileWriter("src/json/resultados.json")) {
			Gson gson = new GsonBuilder().create();
			gson.toJson(this.resultados, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void recuperarResultadosGuardados() {
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Practica>>() {
		}.getType();
		try (FileReader reader = new FileReader("src/json/resultados.json")) {
			this.resultados = new Gson().fromJson(reader, listType);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int generateIdPractica() {
		return this.practicas.size() + 1;
	}

}