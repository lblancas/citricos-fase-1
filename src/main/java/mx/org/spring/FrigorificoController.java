package mx.org.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.org.citicos.controller.ActivoController;
import mx.org.citicos.controller.CDIController;
import mx.org.citicos.controller.CalibreController;
import mx.org.citicos.controller.CalidadController;
import mx.org.citicos.controller.MarcaController;
import mx.org.citicos.controller.PDF_Tarima;
import mx.org.citicos.controller.PalletController;
import mx.org.citicos.controller.PalletDescController;
import mx.org.citicos.controller.TarimaController;
import mx.org.citicos.controller.TransporteController;
import mx.org.citicos.controller.TransportistaController;
import mx.org.citricos.entity.Activo;
import mx.org.citricos.entity.Calibre;
import mx.org.citricos.entity.Calidad;
import mx.org.citricos.entity.Cdi;
import mx.org.citricos.entity.Marca;
import mx.org.citricos.entity.Pallet;
import mx.org.citricos.entity.Pallet_;
import mx.org.citricos.entity.Pallet_desc;
import mx.org.citricos.entity.Tarima;
import mx.org.citricos.entity.Transporte;
import mx.org.citricos.entity.Transportista;
@RestController
public class FrigorificoController 
{
	@RequestMapping("/frigo/transportes")
	public  List<Renglon> getTransportes()
	{
		List<Transporte> cat_transporte;
    	TransporteController ctra  = new TransporteController();
        cat_transporte = ctra.getAll();
        
		List<Renglon>  lista =  new ArrayList<Renglon>();
		for(Transporte rec : cat_transporte)
        	lista.add(new Renglon(rec.getNumero()+" "+getTransportista(rec.getIdtransportista())+" | "+rec.getId()));
        return lista;
	}
    private String getTransportista(int id)
	{
		TransportistaController controller= new TransportistaController();
		Transportista transportista = controller.getOne(id);
		return (transportista.getNombre() !=null ? transportista.getNombre() :  "");
	}
    @RequestMapping("/frigo/palletsT/{id}")
    public List<Renglon> getPalletsT(@PathVariable("id") int id)
    {
    	PalletController controller=new PalletController();
    	List<Pallet> todos =controller.getAllT(id);
    	List<Renglon>  lista =  new ArrayList<Renglon>();
		for(Pallet rec : todos)
        	lista.add(new Renglon(rec.getNumero()+" "+rec.getTarima()+" "+rec.getMarca()+" | "+rec.getId()));
    	return lista;
    }
    @RequestMapping("/frigo/pallets")
    public List<Renglon> getPallets()
    {
    	PalletController controller=new PalletController();
    	List<Pallet> todos =controller.getAll();
    	List<Renglon>  lista =  new ArrayList<Renglon>();
		for(Pallet rec : todos)
        	lista.add(new Renglon(rec.getNumero()+" "+rec.getTarima()+" "+rec.getMarca()+" | "+rec.getId()));
    	return lista;
    }
    @RequestMapping("/frigo/print/{id}/{usuario}")
    public String print(@PathVariable("id") int id,@PathVariable("usuario") int usuario)
    {
    	PDF_Tarima pdf = new PDF_Tarima(id,usuario);
    	pdf.run();
    	return "OK";
    }
    
    @RequestMapping("/frigo/palletNum/{id}")
    public Pallet_ getPalletNum(@PathVariable("id") String id)
    {
    	PalletController controller=new PalletController();
    	Pallet pallet = null;
    	try
    	{
    		pallet =controller.getNumero(id);
    	}
    	catch(Exception e)
    	{}
    	Pallet_ pallet_ = new Pallet_();
    	if(pallet!=null)
    	{
    		if(pallet.getId()!=null)
    		{
    			int cajas=0;
    			PalletDescController detalle =new PalletDescController(pallet.getId());
    			ArrayList<Pallet_desc> detalles = detalle.getAll(pallet.getId());
    			for(Pallet_desc rec : detalles)
    	        	 cajas= cajas + rec.getCajas();
    			pallet_.setId(pallet.getId());
		    	pallet_.setCajas(cajas);
		    	pallet_.setIdmarca(pallet.getIdmarca());
		    	pallet_.setIdtarima(pallet.getIdtarima());
		    	pallet_.setIdtransporte(pallet.getIdtransporte());
		    	pallet_.setNumero(pallet.getNumero());
		    	pallet_.setMarca(pallet.getIdmarca()==0?"":(pallet.getMarca()+"|"+pallet.getIdmarca()));
		    	pallet_.setTarima(pallet.getIdtarima()==0?"":(pallet.getTarima()+"|"+pallet.getIdtarima()));
		    	pallet_.setTransporte(pallet.getIdtransporte()==0?"":(pallet.getTransporte()+"|"+pallet.getIdtransporte()));
    		}
    	}
    	return pallet_;
    }
    @RequestMapping("/frigo/pallets/{id}")
    public Pallet_ getPallet(@PathVariable("id") int id)
    {
    	PalletController controller=new PalletController();
    	Pallet pallet = null;
    	try
    	{
    		pallet =controller.getOne(id);
    	}
    	catch(Exception e)
    	{}
    	Pallet_ pallet_ = new Pallet_();
    	if(pallet!=null)
    	{
    		if(pallet.getId()!=null)
    		{
    			int cajas=0;
    			PalletDescController detalle =new PalletDescController(id);
    			ArrayList<Pallet_desc> detalles = detalle.getAll(id);
    			for(Pallet_desc rec : detalles)
    	        	 cajas= cajas + rec.getCajas();
    			pallet_.setId(pallet.getId());
		    	pallet_.setCajas(cajas);
		    	pallet_.setIdmarca(pallet.getIdmarca());
		    	pallet_.setIdtarima(pallet.getIdtarima());
		    	pallet_.setIdtransporte(pallet.getIdtransporte());
		    	pallet_.setNumero(pallet.getNumero());
		    	pallet_.setMarca(pallet.getIdmarca()==0?"":(pallet.getMarca()+"|"+pallet.getIdmarca()));
		    	pallet_.setTarima(pallet.getIdtarima()==0?"":(pallet.getTarima()+"|"+pallet.getIdtarima()));
		    	pallet_.setTransporte(pallet.getIdtransporte()==0?"":(pallet.getTransporte()+"|"+pallet.getIdtransporte()));
    		}
    	}
    	return pallet_;
    }
    @RequestMapping("/frigo/detallePallet/{id}")
    public List<Renglon> getDetallePallet(@PathVariable("id") int id)
    {
    	PalletDescController detalle =new PalletDescController(id);
    	ArrayList<Pallet_desc> lista = detalle.getAll(id);
    	List<Renglon>  listaREC =  new ArrayList<Renglon>();
		for(Pallet_desc rec : lista)
        	listaREC.add(new Renglon(rec.getCajas()+"-"+rec.getCalibre()+"/"+rec.getCalidad()+"{"+rec.getCdi()+"}"+"|"+rec.getId()));
    	return listaREC;
    }
    @RequestMapping("/frigo/informacionDetallePallet/{id}/{idDetalle}")
    public Pallet_desc getInformacionDetallePallet(@PathVariable("id") int id,@PathVariable("idDetalle") int idDetalle)
    {
    	PalletDescController detalle =new PalletDescController(id);
    	Pallet_desc informacion= detalle.getOne(idDetalle,id);
    	return informacion;
    }
    @RequestMapping("/frigo/tarimas")
    public  List<Renglon> getTarimas()
    {
    	List<Tarima> cat_tarima;
    	TarimaController ctar  = new TarimaController();
        cat_tarima = ctar.getAll();

        List<Renglon>  lista =  new ArrayList<Renglon>();
		for(Tarima rec : cat_tarima)
			lista.add(new Renglon((rec.getNombre()!=null?rec.getNombre():"")+"|"+rec.getId()));
        return lista;
        
    }
    @RequestMapping("/frigo/marcas")
    public  List<Renglon>  getMarcas()
	{
		List<Marca>  cat_marca;
		MarcaController cmar  = new MarcaController();
        cat_marca = cmar.getAll();

        List<Renglon>  lista =  new ArrayList<Renglon>();
		for(Marca rec : cat_marca)
			lista.add(new Renglon((rec.getNombre()!=null?rec.getNombre():"")+"|"+rec.getId()));
        return lista;
	}
    @RequestMapping("/frigo/calibres")
    public  List<Renglon>  getCalibres()
	{
		List<Calibre> cat_calibre;
		CalibreController cCal  = new CalibreController();
        cat_calibre = cCal.getAll();

        List<Renglon>  lista =  new ArrayList<Renglon>();
		for(Calibre rec : cat_calibre)
			lista.add(new Renglon((rec.getNombre()!=null?rec.getNombre():"")+"|"+rec.getId()));
        return lista;
	}
    @RequestMapping("/frigo/calidades")
    public  List<Renglon>   getCalidades()
	{
		List<Calidad>  cat_calidad;
		CalidadController cCalid  = new CalidadController();
        cat_calidad = cCalid.getAll();

        List<Renglon>  lista =  new ArrayList<Renglon>();
		for(Calidad rec : cat_calidad)
			lista.add(new Renglon((rec.getNombre()!=null?rec.getNombre():"")+"|"+rec.getId()));
        return lista;
	}
    @RequestMapping("/frigo/cdis")
    public  List<Renglon>   getCDI()
	{
		List<Cdi>  cat_cdi;
		CDIController cCDI  = new CDIController();
        cat_cdi = cCDI.getAll();

        List<Renglon>  lista =  new ArrayList<Renglon>();
		for(Cdi rec : cat_cdi)
			lista.add(new Renglon((rec.getNombre()!=null?rec.getNombre():"")+"|"+rec.getId()));
        return lista;
	}
    @RequestMapping("/frigo/activos")
    public  List<Renglon>  getActivos()
	{
		List<Activo> cat_activo;
		ActivoController conActivo =new ActivoController();
        cat_activo = conActivo.getAll();

        List<Renglon>  lista =  new ArrayList<Renglon>();
		for(Activo rec : cat_activo)
        	lista.add(new Renglon((rec.getNombre()!=null?rec.getNombre():"")+"|"+rec.getId()));
        return lista;
	}

	    @RequestMapping("/frigo/setPallet"
	    		+ "/{cajas}"
	    		+ "/{idTarima}"
	    		+ "/{idMarca}"
	    		+ "/{idUsuario}"
	    		+ "/{idPallet}"
			    + "/{idTransporte}"
	    		+ "/{opcion}"
    		)
    public Renglon setPallet(
    		@PathVariable("cajas") int cajas,
    		@PathVariable("idTarima") int idTarima,
    		@PathVariable("idMarca") int idMarca,
    		@PathVariable("idUsuario") int idUsuario,
    		@PathVariable("idPallet") int idPallet,
		@PathVariable("idTransporte") int idTransporte,
    		@PathVariable("opcion") int opcion
    		)
	{
	    System.out.println("http://localhost:9080/frigo/setPallet/"+cajas+"/"+idTarima+"/"+idMarca+"/"+idUsuario+"/"+idPallet+"/"+idTransporte+"/"+opcion);
	    System.out.println("opcion  {"+opcion+"}");
    	System.out.println("opcion,cajas,idTarima,idMarca,idUsuario,idPallet->opcion {"+","+cajas+","+idTarima+","+idMarca+","+idUsuario+","+idPallet+","+idTransporte+","+opcion+"}");
		if(opcion==1) // Alta
			return new Renglon(createPallet(cajas,idTarima,idMarca,idUsuario,idPallet,idTransporte));
		if(opcion==2) // Modifica
			updatePallet         (cajas,idTarima,idMarca,idUsuario,idPallet,idTransporte);
		if(opcion==3) // Borrar
			deletePallet         (cajas,idTarima,idMarca,idUsuario,idPallet,idTransporte);
		System.out.println("idPallet  {"+idPallet+"}");
		return new Renglon(""+idPallet);
	}
    
	
	private  String createPallet(int cajas,int idTarima,int idMarca,int idUsuario,int idPallet,int idTransporte)
    {
	    PalletController controller=new PalletController();
	    Pallet pallet= controller.insertRecord(cajas,idTarima,idMarca,idUsuario, idUsuario ,idTransporte);
	    return pallet.getNumero()+"|"+pallet.getId();
    }
	
	private  int updatePallet(int cajas,int idTarima,int idMarca,int idUsuario,int idPallet,int idTransporte)
    {
	    PalletController controller=new PalletController();
	    int cont=controller.updateRecord(cajas,idTarima,idMarca,idUsuario,idPallet , idTransporte);
	    return cont;
    }
	private  int deletePallet(int cajas,int idTarima,int idMarca,int idUsuario,int idPallet,int idTransporte)
    {
	    PalletController controller=new PalletController();
	    int cont=controller.deleteRecordPallet(idPallet,idUsuario);
	    return cont;
    }
	@RequestMapping("/frigo/setDetalle"
    		+ "/{idPallet}"
    		+ "/{idDetalle}"
    		+ "/{cajas}"
    		+ "/{idCalibre}"
    		+ "/{idCalidad}"
    		+ "/{cdi}"
    		+ "/{idActivo}"
    		+ "/{idUsuario}"
    		+ "/{opcion}"
    		)
    public Renglon setDetallePallet(
    		@PathVariable("idPallet") int idPallet,
    		@PathVariable("idDetalle") int idDetalle,
    		@PathVariable("cajas") int cajas,
    		@PathVariable("idCalibre") int idCalibre,
    		@PathVariable("idCalidad") int idCalidad,
    		@PathVariable("cdi") String cdi,
    		@PathVariable("idActivo") int idActivo,
    		@PathVariable("idUsuario") int idUsuario,
    		@PathVariable("opcion") int opcion)
	{
    	System.out.println("idPallet,cajas,idCalibre,idCalidad,cdi,idActivo,idUsuario {"+opcion+"<>"+idPallet+"<>"+cajas+"<>"+idCalibre+"<>"
    									+idCalidad+"<>"+cdi+"<>"+idActivo+"<>"+idUsuario+"}");
		if(opcion==1) // Alta
			idDetalle=createDetalle(idPallet,idDetalle,cajas,idCalibre,idCalidad,cdi,idActivo,idUsuario);
		if(opcion==2) // Modifica
			updateDetail          (idPallet,idDetalle,cajas,idCalibre,idCalidad,cdi,idActivo,idUsuario);
		if(opcion==3) // Borrar
			deleteDetail          (idPallet,idDetalle,cajas,idCalibre,idCalidad,cdi,idActivo,idUsuario);
		return new Renglon(""+idDetalle);
	}
	private int  createDetalle(int idPallet,int idDetalle,int cajas,int idCalibre,int idCalidad, String cdi ,int idActivo,int idUsuario)
    {
        PalletDescController detalle =new PalletDescController(idPallet);
        int idDetalleN = detalle.insertRecord(cajas,idCalibre,idCalidad,cdi,idPallet,idUsuario);
        return idDetalleN;
    }
	private int updateDetail(int idPallet,int idDetalle,int cajas,int idCalibre,int idCalidad, String cdi ,int idActivo,int idUsuario)
	{
		PalletDescController detalle =new PalletDescController(idPallet);
		int cont =detalle.updateRecor(idDetalle, cajas, idCalibre, idCalidad, cdi, idActivo, idUsuario);
		return cont;
	}
	private int deleteDetail(int idPallet,int idDetalle,int cajas,int idCalibre,int idCalidad, String cdi ,int idActivo,int idUsuario)
	{
		PalletDescController detalle =new PalletDescController(idPallet);
		int cont =detalle.deleteRecord(idDetalle, idUsuario);
		return cont;
	}
	
}
