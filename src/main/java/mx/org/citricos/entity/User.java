package mx.org.citricos.entity;

public class User {

	private int id;
	private String login;
	private String nombre;
	private String impresora;
	private int imprimir;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getImpresora() {
		return impresora;
	}
	public void setImpresora(String impresora) {
		this.impresora = impresora;
	}
	public int getImprimir() {
		return imprimir;
	}
	public void setImprimir(int imprimir) {
		this.imprimir = imprimir;
	}
	
}
