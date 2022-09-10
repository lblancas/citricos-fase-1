package mx.org.citricos.entity;

public class RepTarimaDetallePallet {

	private int id;
	private int id_calibre;
	private String  calibre_desc;
	private int id_cat_calidad;
	private String  calidad_desc;
	private int cajas;
	private String  cdi;
	
	public RepTarimaDetallePallet(int id, int id_calibre, String calibre_desc, int id_cat_calidad, String calidad_desc,
			int cajas, String cdi) {
		super();
		this.id = id;
		this.id_calibre = id_calibre;
		this.calibre_desc = calibre_desc;
		this.id_cat_calidad = id_cat_calidad;
		this.calidad_desc = calidad_desc;
		this.cajas = cajas;
		this.cdi = cdi;
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_calibre() {
		return id_calibre;
	}
	public void setId_calibre(int id_calibre) {
		this.id_calibre = id_calibre;
	}
	public String getCalibre_desc() {
		return calibre_desc;
	}
	public void setCalibre_desc(String calibre_desc) {
		this.calibre_desc = calibre_desc;
	}
	public int getId_cat_calidad() {
		return id_cat_calidad;
	}
	public void setId_cat_calidad(int id_cat_calidad) {
		this.id_cat_calidad = id_cat_calidad;
	}
	public String getCalidad_desc() {
		return calidad_desc;
	}
	public void setCalidad_desc(String calidad_desc) {
		this.calidad_desc = calidad_desc;
	}
	public int getCajas() {
		return cajas;
	}
	public void setCajas(int cajas) {
		this.cajas = cajas;
	}
	public String getCdi() {
		return cdi;
	}
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	
}
