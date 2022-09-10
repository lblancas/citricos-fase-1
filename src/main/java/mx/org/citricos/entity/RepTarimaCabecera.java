package mx.org.citricos.entity;

import java.util.List;

public class RepTarimaCabecera 
{
	private int id_transporte_tarima;
	private String  numero_transporte_tarima;
	private int id;
	private String  no_termo;
	private String  no_sello;
	private String  lector_temp;
	private String  lote;
	private String  nombre;
	private String  placas;
	private String  numero_economico;
	private List<RepTarimaDetalle> tarimas;
	
	public RepTarimaCabecera(int id, String no_termo,
			String no_sello, String lector_temp, String lote, String nombre, String placas, String numero_economico) {
		super();
		this.id_transporte_tarima=0;
		this.numero_transporte_tarima="pendiente";
		this.id = id;
		this.no_termo = no_termo;
		this.no_sello = no_sello;
		this.lector_temp = lector_temp;
		this.lote = lote;
		this.nombre = nombre;
		this.placas = placas;
		this.numero_economico = numero_economico;
	}
	
	public RepTarimaCabecera(int id_transporte_tarima, String numero_transporte_tarima, int id, String no_termo,
			String no_sello, String lector_temp, String lote, String nombre, String placas, String numero_economico)
	{
		super();
		this.id_transporte_tarima = id_transporte_tarima;
		this.numero_transporte_tarima = numero_transporte_tarima;
		this.id = id;
		this.no_termo = no_termo;
		this.no_sello = no_sello;
		this.lector_temp = lector_temp;
		this.lote = lote;
		this.nombre = nombre;
		this.placas = placas;
		this.numero_economico = numero_economico;
	}
	public int getId_transporte_tarima() {
		return id_transporte_tarima;
	}
	public void setId_transporte_tarima(int id_transporte_tarima) {
		this.id_transporte_tarima = id_transporte_tarima;
	}
	public String getNumero_transporte_tarima() {
		return numero_transporte_tarima;
	}
	public void setNumero_transporte_tarima(String numero_transporte_tarima) {
		this.numero_transporte_tarima = numero_transporte_tarima;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo_termo() {
		return no_termo;
	}
	public void setNo_termo(String no_termo) {
		this.no_termo = no_termo;
	}
	public String getNo_sello() {
		return no_sello;
	}
	public void setNo_sello(String no_sello) {
		this.no_sello = no_sello;
	}
	public String getLector_temp() {
		return lector_temp;
	}
	public void setLector_temp(String lector_temp) {
		this.lector_temp = lector_temp;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPlacas() {
		return placas;
	}
	public void setPlacas(String placas) {
		this.placas = placas;
	}
	public String getNumero_economico() {
		return numero_economico;
	}
	public void setNumero_economico(String numero_economico) {
		this.numero_economico = numero_economico;
	}

	public List<RepTarimaDetalle> getTarimas() {
		return tarimas;
	}

	public void setTarimas(List<RepTarimaDetalle> tarimas) {
		this.tarimas = tarimas;
	}
	
	
}
