
package mx.org.citicos.controller.bascula;

public class Bascula {
	private int id=0;
	private int opcion=0;
	private int usuario=0;
	private String fileName;
	public Bascula(int id, int opcion, int usuario,String fileName){
		super();
		this.id = id;
		this.opcion = opcion;
		this.usuario = usuario;
		this.fileName = fileName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOpcion() {
		return opcion;
	}
	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
