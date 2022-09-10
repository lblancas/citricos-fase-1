package mx.org.citricos.entity;

public class Pallet_ 
{
	private Integer    id=0;
	private Integer    cajas=0;
    private String     numero="";
    private Integer    idtarima=0;
    private Integer    idmarca=0;
    private Integer    idtransporte=0;
    private String     tarima="";
    private String     marca="";
    private String     transporte="";
    
	public Pallet_() 
	{
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCajas() {
		return cajas;
	}
	public void setCajas(Integer cajas) {
		this.cajas = cajas;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Integer getIdtarima() {
		return idtarima;
	}
	public void setIdtarima(Integer idtarima) {
		this.idtarima = idtarima;
	}
	public Integer getIdmarca() {
		return idmarca;
	}
	public void setIdmarca(Integer idmarca) {
		this.idmarca = idmarca;
	}
	public Integer getIdtransporte() {
		return idtransporte;
	}
	public void setIdtransporte(Integer idtransporte) {
		this.idtransporte = idtransporte;
	}
	public String getTarima() {
		return tarima;
	}
	public void setTarima(String tarima) {
		this.tarima = tarima;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTransporte() {
		return transporte;
	}
	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}
    
}
