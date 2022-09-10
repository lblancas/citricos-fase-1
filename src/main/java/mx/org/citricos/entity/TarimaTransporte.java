package mx.org.citricos.entity;

public class TarimaTransporte {
	private Integer    id;
	private String    numero;
	private Integer    cajas;
	private Integer    idTransporte;
	private String    transporte;
	private Integer    idEstatus;
	//0 inactivo
	//1 activo
	//2 asignando
	//3 cerrado
	
	public Integer getId() {
		return id;
	}
	public TarimaTransporte()
	{
		super();
	}
	public TarimaTransporte(Integer id, String numero, Integer cajas, Integer idTransporte, Integer idEstatus) {
		super();
		this.id = id;
		this.numero = numero;
		this.cajas = cajas;
		this.idTransporte = idTransporte;
		this.idEstatus = idEstatus;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Integer getCajas() {
		return cajas;
	}
	public void setCajas(Integer cajas) {
		this.cajas = cajas;
	}
	public Integer getIdTransporte() {
		return idTransporte;
	}
	public void setIdTransporte(Integer idTransporte) {
		this.idTransporte = idTransporte;
	}
	public Integer getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}
	public String getTransporte() {
		return transporte;
	}
	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}
	
}
