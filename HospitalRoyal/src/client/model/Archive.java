package client.model;

/**
 * 
 * Ejemplo rápido de objeto que se necesita para la vista del cliente
 *
 */
public class Archive{

	private String nombre, ultFechaModificacion, direccion;

	public Archive(String nombre, String ultFechaModificacion, String direccion) {
		this.nombre = nombre;
		this.ultFechaModificacion = ultFechaModificacion;
		this.direccion = direccion;
	}

	public String getExtension() {
		String extension = "";
		try {
			extension = nombre.split("\\.")[1];
		}
		catch(Exception e){
			extension = "folder";
		}
		
		return extension;
	}
	
	public String getName() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUltFechaModificacion() {
		return ultFechaModificacion;
	}

	public void setUltFechaModificacion(String ultFechaModificacion) {
		this.ultFechaModificacion = ultFechaModificacion;
	}


	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
