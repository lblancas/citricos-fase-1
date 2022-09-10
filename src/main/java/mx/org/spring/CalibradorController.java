package mx.org.spring;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.org.citicos.controller.CorridasController;
import mx.org.citicos.controller.FolioController;
import mx.org.citicos.controller.PDFCalibradorG;
import mx.org.citicos.controller.PDF_C;
import mx.org.citicos.controller.PreciosController;
import mx.org.citicos.controller.TrejaController;
import mx.org.citricos.entity.Corrida;
import mx.org.citricos.entity.Folios;
import mx.org.citricos.entity.Precios;
import mx.org.citricos.entity.Tarima;
@RestController
public class CalibradorController
{
    @RequestMapping("/calibrador/{folio}")
    public Calibrador getFolio(@PathVariable("folio") String folio)
    {
    	Corrida corrida= null;
    	FolioController controller=new FolioController();
    	CorridasController  corridasController = new CorridasController ();
    	Calibrador con= new Calibrador(
    		    		0,
    		    		0.0,0.0,0.0,
    		    		folio,"No existe Folio "+folio,0,"No existe Folio "+folio,"", 
    		    		0.0,0.0,0.0,0.0,0.0,0.0,
    		    		0.0,0.0,0.0,0.0,0.0,0.0,
    		    		0.0,0.0,0.0,0.0,0.0,0.0,
    		    		0.0,0.0,0.0);
    	Folios b =null;
    	try
    	{
    		b = controller.getOne(folio);
    		if(b==null)
    		{
    			con.setMsg("Error.No existe Ticket Calibrador[-1]:"+folio);
    			con.setOpc(0);
    	    	return con;
    		}
    		if(b.getEstatus()!=3)
    		{
    			con.setMsg("Error.No existe Ticket Calibrador["+b.getEstatus()+"]:"+folio);
    			con.setOpc(0);
    	    	return con;
    		}
    		corrida= corridasController.getOnebyXFolio(b.getId());
    		if(corrida!=null)
    			b.setCorrida(corrida);
    		else
    		{
    			con.setMsg("Error.No existe Ticket Calibrador["+b.getEstatus()+"]:"+folio);
    			con.setOpc(0);
    			return con;
    		}
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    	
		if(b!=null)
			if(b.getId()!=null)
				if(b.getCorrida()!=null)
				{
					con = new 
                		Calibrador(
                	    		b.getId(),b.getPeso_bruto(),b.getPeso_tara(),b.getPeso_neto(),b.getFolio(),""+b.getEstatus(),1,"OK",
                	    		b.getId_productor()+"-"+b.getProductor(), 
                	    		b.getCorrida().getVerde_110(),b.getCorrida().getVerde_150(),b.getCorrida().getVerde_175(),b.getCorrida().getVerde_200(),b.getCorrida().getVerde_230(),b.getCorrida().getVerde_250(),
                	    		b.getCorrida().getEmpaque_110(),b.getCorrida().getEmpaque_150(),b.getCorrida().getEmpaque_175(),b.getCorrida().getEmpaque_200(),b.getCorrida().getEmpaque_230(),b.getCorrida().getEmpaque_250(),
                	    		b.getCorrida().getEur_110(),b.getCorrida().getEur_150(),b.getCorrida().getEur_175(),b.getCorrida().getEur_200(),b.getCorrida().getEur_230(),b.getCorrida().getEur_250(),
                	    		b.getCorrida().getSegundas(),b.getJapon(),b.getCorrida().getAlbaran());
				}
				else
					con= new Calibrador(
    		    		0,
    		    		0.0,0.0,0.0,
    		    		folio,"Problemas con la creacion de corridas en  "+folio,0,"Problemas con la creacion de corridas en  "+folio,"", 
    		    		0.0,0.0,0.0,0.0,0.0,0.0,
    		    		0.0,0.0,0.0,0.0,0.0,0.0,
    		    		0.0,0.0,0.0,0.0,0.0,0.0,
    		    		0.0,0.0,0.0);
		return  con;
    }
    private  Calibrador getFolioLocal(String folio)
    {
    	Corrida corrida= null;
    	FolioController controller=new FolioController();
    	CorridasController  corridasController = new CorridasController ();
    	Calibrador con= new Calibrador(0,0.0,0.0,0.0,folio,"Error. no existe folio : "+ folio,0,"Error. no existe folio : "+ folio); 
    	Folios b =null;
    	try
    	{
    		b = controller.getOne(folio);
    		corrida= corridasController.getOnebyXFolio(b.getId());
    		if(corrida!=null)
    			b.setCorrida(corrida);
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    	
		if(b!=null)
		{
			if(b.getId()!=null)
			{

                con = new 
                		Calibrador(
                	    		b.getId(),
                	    		b.getPeso_bruto(),b.getPeso_tara(),b.getPeso_neto(),
                	    		b.getFolio(),""+b.getEstatus(),
                	    		1,
                	    		"OK",
                	    		b.getId_productor()+"-"+b.getProductor(), 
                	    		b.getCorrida().getVerde_110(),b.getCorrida().getVerde_150(),b.getCorrida().getVerde_175(),b.getCorrida().getVerde_200(),b.getCorrida().getVerde_230(),b.getCorrida().getVerde_250(),
                	    		b.getCorrida().getEmpaque_110(),b.getCorrida().getEmpaque_150(),b.getCorrida().getEmpaque_175(),b.getCorrida().getEmpaque_200(),b.getCorrida().getEmpaque_230(),b.getCorrida().getEmpaque_250(),
                	    		b.getCorrida().getEur_110(),b.getCorrida().getEur_150(),b.getCorrida().getEur_175(),b.getCorrida().getEur_200(),b.getCorrida().getEur_230(),b.getCorrida().getEur_250(),
                	    		b.getCorrida().getSegundas(),b.getJapon(),b.getCorrida().getAlbaran());
	        }
		}
		return  con;
    }
    @RequestMapping("/setEstatus/{id}/{opcion}/{usuario}")
    public Boolean setEstatus(
    	    @PathVariable ("id")       int    id,
    	    @PathVariable ("opcion")   int    opcion,
    	    @PathVariable ("usuario")  int    usuario)
    {
    	try
    	{
	    	FolioController controller=new FolioController();
	        Folios tmp =null; 
	        try {
	        	tmp = controller.getOne(id);
	        	modificaEstatusDeFolio(tmp.getId(), opcion);
	        } 
	        catch(Exception e)
	        { 
	        	return  false;
	        }
    	}
        catch (Exception ex) 
        {
        	ex.printStackTrace();
        	return  false;
        }
    	return  true;
    }
    @RequestMapping("/setCalibrador"
    		+ "/{c110}/{c150}/{c175}/{c200}/{c230}/{c250}"
    		+ "/{u110}/{u150}/{u175}/{u200}/{u230}/{u250}"
    		+ "/{e110}/{e150}/{e175}/{e200}/{e230}/{e250}"
    		+ "/{segundas}/{japon}"
    		+ "/{id}/{opcion}/{usuario}/{albaran}")
    public Calibrador setCalibrador(
    		@PathVariable ("c110")     double c110,
    		@PathVariable ("c150")     double c150,
    		@PathVariable ("c175")     double c175,
    		@PathVariable ("c200")     double c200,
    		@PathVariable ("c230")     double c230,
    		@PathVariable ("c250")     double c250,
    		
    		@PathVariable ("u110")     double u110,
    		@PathVariable ("u150")     double u150,
    		@PathVariable ("u175")     double u175,
    		@PathVariable ("u200")     double u200,
    		@PathVariable ("u230")     double u230,
    		@PathVariable ("u250")     double u250,
    		
    		@PathVariable ("e110")     double e110,
    		@PathVariable ("e150")     double e150,
    		@PathVariable ("e175")     double e175,
    		@PathVariable ("e200")     double e200,
    		@PathVariable ("e230")     double e230,
    		@PathVariable ("e250")     double e250,
    		
    		@PathVariable ("segundas") double segundas,
    		@PathVariable ("japon")    double japon,
    	    @PathVariable ("id")       int    id,
    	    @PathVariable ("opcion")   int    opcion,
    	    @PathVariable ("usuario")  int    usuario,
    	    @PathVariable ("albaran")  double albaran)
    {
    	Calibrador con= new Calibrador(id,0.0,0.0,0.0,"","Error. no existe id : "+ id,0,"Error. no existe id : "+ id);
    	try
    	{
	    	FolioController controller=new FolioController();
	    	CorridasController  corridas = new CorridasController ();
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date d=new Date();
	        String fechaN=format.format(d);
	        Folios tmp =null; try {tmp = controller.getOne(id);} catch(Exception e){ return  new  Calibrador(""+id,"No existe "+id,0);}
	    	corridas.UpdateRecord
	    	(
            japon,
            c110,
            c150,
            c175,
            c200,
            c230,
            c250,
            u110,
            u150,
            u175,
            u200,
            u230,
            u250,
            e110,
            e150,
            e175,
            e200,
            e230,
            e250,	
            segundas,
            tmp.getTerceras().doubleValue(),
            tmp.getTorreon().doubleValue(),
            tmp.getColeada().doubleValue(),
            tmp.getComprador(),
            tmp.getFacturar(),
            0,0,
            opcion,id,albaran);	
	        if(opcion==4)
	        {
	        	modificaEstatusDeFolio(id, 3);
	        	PDF_C pdf=new PDF_C(id,3,usuario);
                pdf.run();
                modificaEstatusDeFolio(id, 4);
	        }
	        return getFolioLocal(tmp.getFolio());
    	}
        catch (Exception ex) 
        {
        	ex.printStackTrace();
        }
    	return con;
    }
    @RequestMapping("/setPrecios"
    		+ "/{empaque_110}/{empaque_150}/{empaque_175}/{empaque_200}/{empaque_230}/{empaque_250}"
    		+ "/{verde_110}/{verde_150}/{verde_175}/{verde_200}/{verde_230}/{verde_250}/{verde_japon}"
    		+ "/{segundas}/{coleada}/{terceras}/{torreon}"
    		+ "/{usuario}")
    public Precios setPrecios(
    		@PathVariable ("empaque_110")   double empaque_110,
    		@PathVariable ("empaque_150")   double empaque_150,
    		@PathVariable ("empaque_175")   double empaque_175,
    		@PathVariable ("empaque_200")   double empaque_200,
    		@PathVariable ("empaque_230")   double empaque_230,
    		@PathVariable ("empaque_250")   double empaque_250,
    		@PathVariable ("verde_110")     double verde_110,
    		@PathVariable ("verde_150")     double verde_150,
    		@PathVariable ("verde_175")     double verde_175,
    		@PathVariable ("verde_200")     double verde_200,
    		@PathVariable ("verde_230")     double verde_230,
    		@PathVariable ("verde_250")     double verde_250,
    		@PathVariable ("verde_japon")   double verde_japon,
    		@PathVariable ("segundas")      double segundas,
    		@PathVariable ("coleada")       double coleada,
    		@PathVariable ("terceras")      double terceras,
    		@PathVariable ("torreon")       double torreon,
    	    @PathVariable ("usuario")       int    usuario)
    {
    	PreciosController preciosController= new PreciosController(); 
    	Precios b = preciosController.getMax();
        b.setEmpaque_110(empaque_110);
        b.setEmpaque_150(empaque_150);
        b.setEmpaque_175(empaque_175);
        b.setEmpaque_200(empaque_200);
        b.setEmpaque_230(empaque_230);
        b.setEmpaque_250(empaque_250);
        
        b.setVerde_110(verde_110);
        b.setVerde_150(verde_150);
        b.setVerde_175(verde_175);
        b.setVerde_200(verde_200);
        b.setVerde_230(verde_230);
        b.setVerde_250(verde_250);
        b.setVerde_japon(verde_japon);
        
        b.setColeada(coleada);
        b.setSegundas(segundas);
        b.setTerceras(terceras);
        b.setTorreon(torreon);
        
        preciosController.insertRecordBean(usuario, b);
        return b;
    }
    @RequestMapping("/getImpresoras")
    public List<Renglon> getImpresora()
    {
    	List<Renglon> renglones = new ArrayList<Renglon>();
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) 
        {
            String impresora = printService.getName();
            renglones.add(new Renglon(impresora));
        }
        return renglones;
    }
    @RequestMapping("/imprime/{id}/{idf}/{usu}")
    public String imprime(
    		@PathVariable ("id")       int    id,
    		@PathVariable ("idf")       int    idf,
    		@PathVariable ("usu")       int    usu)
    {
    	PDF_C pdf=new PDF_C(id,3,usu);
    	pdf.run();
    	return "OK";
    }
    @RequestMapping("/imprimeG/{id}/{usu}/{filename}")
    public String imprimeGuardar(
    		@PathVariable ("id")       int    id,
    		@PathVariable ("usu")      int    usu,
    		@PathVariable ("filename") String filename)
    {
    	PDFCalibradorG pdf=new PDFCalibradorG(id,3,usu,filename.replace("*","/"));
    	pdf.run();
    	return "OK";
    }
    @RequestMapping("/getUsuarioImpresoras")
    public List<Renglon> getUserIm() 
    {
    	PreciosController preciosController= new PreciosController();
    	return  preciosController.getUsuariosImpresoras();
    }
    @RequestMapping("/getUsuarioImpresora/{usuario}")
    public mx.org.citricos.entity.User getUsuarioImpresora(@PathVariable ("usuario")int usuario) 
    {
    	PreciosController preciosController= new PreciosController();
    	return  preciosController.getUsuarioImpresora(usuario);
    }
    @RequestMapping("/setUsuarioImpresora/{usuario}/{imprimir}/{impresora}")
    public mx.org.citricos.entity.User setUserIm(@PathVariable ("usuario")int usuario,@PathVariable ("imprimir")int imprimir,@PathVariable ("impresora")String impresora) 
    {
    	PreciosController preciosController= new PreciosController(); 
    	preciosController.setUsuarioImpresora(usuario,imprimir,impresora);
    	return getUsuarioImpresora(usuario);
    }
	@RequestMapping("/getPrecio")
    public Precios getPrecio()
    {
		PreciosController preciosController= new PreciosController();
		return preciosController.getMax();
    }
	@RequestMapping("/getPrecio/{id}")
    public Precios getPrecioUnitario(@PathVariable ("id")int id)
    {
		PreciosController preciosController= new PreciosController();
		return preciosController.getOne(id);
    }
	@RequestMapping("/getPrecios")
    public List<Renglon> getPrecios()
    {
		PreciosController preciosController= new PreciosController();
		List<Precios> lista =  preciosController.getAll();
		List<Renglon> renglones =  new ArrayList<Renglon>();
		for(Precios p:lista)
		{
			renglones.add(new Renglon(p.getFecha()+"_"+p.getUsuario()+"|"+p.getId()));
		}
		return renglones;
    }
    private void modificaEstatusDeFolio(int idFolio, int idEstatus)
    {
    	FolioController controller=new FolioController();
    	controller.rechazar(idFolio,idEstatus);
    }
    private double obtienePesoPorCaja(Integer tipo_limon) 
    {
    	List<Tarima> tamanos_reja;
        double pesoPorCaja=0;
        TrejaController ctrtr=new TrejaController();
        tamanos_reja=ctrtr.getAll();
        
        for(Tarima  elemento_tipo_limon: tamanos_reja)
        {
            if(elemento_tipo_limon.getId() == tipo_limon)
            {
                String[] tokens=elemento_tipo_limon.getNombre().split(" ");
                if(tokens.length==3)
                {
                    try
                    {
                        pesoPorCaja= new Double(tokens[1]); break;
                    }
                    catch(Exception e)
                    {
                        System.out.println("Problemas al parsear  pesoPorCaja= new Double("+tokens[1]+")");
                    }
                }
            }
        }
        return pesoPorCaja;
    }
}
