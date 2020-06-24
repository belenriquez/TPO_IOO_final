package controllers;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import enums.Operacion;
import enums.Rol;
import model.Sucursal;
import model.Usuario;

public class UsuarioController {
	
	private List<Usuario> usuarios;
	private static UsuarioController instancia;
	private Usuario usuarioActivo;
	private List<Operacion> operacionesLaboratorista = new ArrayList<Operacion>();
	private List<Operacion> operacionesRecepcion = new ArrayList<Operacion>();

	private UsuarioController(){
		
		this.usuarios = new ArrayList<Usuario>();
		this.usuarios.add(new Usuario(1, "admin", "admin@admin.com", "admin", "Administrador", Rol.ADMINISTRADOR, 11111111, "Manuel Castro", new Date()));
		this.usuarios.add(new Usuario(2, "laboratorista", "laboratorista@laboratorista.com", "lab", "Laboratorista", Rol.LABORATORISTA, 2222222, "Castro", new Date()));
		this.usuarios.add(new Usuario(3, "recepcionista", "recepcionista@recepcionista.com", "recep", "Recepcionista", Rol.RECEPCION, 33333333, "Calle falsa 123", new Date()));

		this.guardarUsuarios();

		obtenerUsuariosGuardados();
		cargarListasPermisos();
	}

	public static UsuarioController getInstancia(){
    	if (instancia == null) {
    		instancia = new UsuarioController()	;
    	}
    	return instancia;
    }

	public boolean login(String userName, String password) {
		for (Usuario usuario : usuarios){
			if (usuario.getNombreUsuario().equals(userName) && usuario.getPassword().equals(password)){
				this.usuarioActivo = usuario;
				return true;
			}
		}
		return false;

	}

	public boolean validarRolUsuario(Operacion operacion){
		boolean tienePermiso = false;
		switch (usuarioActivo.getRol()){
			case ADMINISTRADOR:
				tienePermiso = true;
				break;
			case LABORATORISTA:
				tienePermiso = operacionesLaboratorista.contains(operacion);
				break;
			case RECEPCION:
				tienePermiso = operacionesRecepcion.contains(operacion);
				break;
		}
		return tienePermiso;
	}

	private void cargarListasPermisos(){
		operacionesLaboratorista.add(Operacion.RESULTADO_ALTA);
		operacionesLaboratorista.add(Operacion.RESULTADO_MODIFICACION);
		operacionesLaboratorista.add(Operacion.MUESTRA_PROCESAR);

		operacionesRecepcion.add(Operacion.PACIENTE_ALTA);
		operacionesRecepcion.add(Operacion.PACIENTE_MODIFICACION);
		operacionesRecepcion.add(Operacion.PETICION_ALTA);
		operacionesRecepcion.add(Operacion.PETICION_MODIFICACION);
	}


	//--------------------------------------------------

	private void obtenerUsuariosGuardados() {
		Type listType = new TypeToken<ArrayList<Usuario>>(){}.getType();

		try (FileReader fileReader = new FileReader("src/json/usuarios.json")) {
			this.usuarios = new Gson().fromJson(fileReader , listType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void guardarUsuarios(){
		try (Writer writer = new FileWriter("src/json/usuarios.json")) {
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(this.usuarios, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public Usuario getUsuario(int idUsuario) {
		Usuario usuario = null;
		int i = 0;
		while (idUsuario != -1 && usuario == null && i != usuarios.size()){
			if(usuarios.get(i).getIdUsuario() == idUsuario){
				usuario = usuarios.get(i);
			}
			i++;
		}
		return usuario;
	}

	public boolean altaUsuario(Usuario usuario) {
		if(!validarRolUsuario(Operacion.USUARIO_ALTA) || getUsuario(usuario.getIdUsuario()) != null){
			return false;
		}
		usuarios.add(usuario);
		guardarUsuarios();
		return true;
	}

	public boolean bajaUsuario(int idUser) {
		Usuario usuarioParaEliminar = getUsuario(idUser);
		if(!validarRolUsuario(Operacion.USUARIO_BAJA) || usuarioParaEliminar == null){
			return false;
		}
		usuarios.remove(usuarioParaEliminar);
		guardarUsuarios();
		return true;
	}

	public boolean modificarUsuario(Usuario usuarioCambiado){
		Usuario usuarioParaModificar = getUsuario(usuarioCambiado.getIdUsuario());
		if(!validarRolUsuario(Operacion.USUARIO_MODIFICACION) || usuarioParaModificar == null){
			return false;
		}
		usuarios.remove(usuarioParaModificar);
		usuarios.add(usuarioCambiado);
		guardarUsuarios();
		return true;
	}

	public Usuario getUsuarioActivo() {
		return usuarioActivo;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public int generateIdUsuario() {
		Usuario aux = this.getUsuario(this.usuarios.size());
		while(aux != null) {
			aux = this.getUsuario(this.usuarios.size() + 1);
		}
		return this.usuarios.size() + 1;
	}
}