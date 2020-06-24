package model;

public class Sucursal {

	private int idSucursal;
	private int numeroSucursal;
	private String direccion;
	private Usuario responsableTecnico;

	
	public boolean derivarPeticionesActivas(int codSucDestino) {
		return false;
	}

	public boolean tienePeticionesActivas() {
//		if (!this.peticiones.isEmpty()) {
//			return true;
//		}
		return false;
	}
	
	
	public Sucursal(int id, int num, String direccion, Usuario rt) {
		this.idSucursal = id;
		this.numeroSucursal = num;
		this.direccion = direccion;
		this.responsableTecnico = rt;

	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public int getNumeroSucursal() {
		return numeroSucursal;
	}

	public void setNumeroSucursal(int numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Usuario getResponsableTecnico() {
		return responsableTecnico;
	}

	public void setResponsableTecnico(Usuario responsableTecnico) {
		this.responsableTecnico = responsableTecnico;
	}

}
