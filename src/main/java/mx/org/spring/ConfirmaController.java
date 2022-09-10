package mx.org.spring;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.org.citicos.controller.FolioController;
import mx.org.citicos.controller.PDF;
import mx.org.citicos.controller.TrejaController;
import mx.org.citricos.entity.Folios;
import mx.org.citricos.entity.Tarima;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
public class ConfirmaController
{
    @RequestMapping("/confirmacion/{folio}")
    public Confirma getFolio(@PathVariable("folio") String folio)
    {
    	FolioController controller=new FolioController();
    	Confirma con= new Confirma(0,0.0,0.0,0.0,folio,"No existe",0,"No existe Folio "+folio,0.0,0.0,0.0,0.0,0.0);
    	Folios b =null;
    	try
    	{
    		b = controller.getOne(folio);
    		if(b==null)
    		{
    			con.setMsg("Error.No existe Ticket Confirmacion[-1]:"+folio);
    			con.setOpc(0);
    	    	return con;
    		}
    		if(b.getEstatus()!=2)
    		{
    			con.setMsg("Error.No existe Ticket Confirmacion["+b.getEstatus()+"]:"+folio);
    			con.setOpc(0);
    	    	return con;
    		}
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    	
		if(b!=null)
		{
			if(b.getId()!=null)
			{

                con = new Confirma(b.getId(),b.getPeso_bruto(),b.getPeso_tara(),b.getPeso_neto(),b.getFolio(),
                		""+b.getEstatus(),1,"OK",
                		b.getJapon(),b.getSegundas(),b.getTerceras(),b.getTorreon(),b.getColeada());
	        }
		}
		return  con;
    } 
    private Confirma getFolioLocal(String folio)
    {
    	FolioController controller=new FolioController();
    	Confirma con= new Confirma(0,0.0,0.0,0.0,folio,"No existe",0,"No existe Folio "+folio,0.0,0.0,0.0,0.0,0.0);
    	Folios b =null;
    	try
    	{
    		b = controller.getOne(folio);
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    	
		if(b!=null)
		{
			if(b.getId()!=null)
			{

                con = new Confirma(b.getId(),b.getPeso_bruto(),b.getPeso_tara(),b.getPeso_neto(),b.getFolio(),
                		""+b.getEstatus(),1,"OK",
                		b.getJapon(),b.getSegundas(),b.getTerceras(),b.getTorreon(),b.getColeada());
	        }
		}
		return  con;
    }
    @RequestMapping("/setConfirma/{japon}/{segundas}/{terceras}/{torreon}/{coleada}/{id}/{opcion}/{usuario}")
    public Confirma setFolio(
    		@PathVariable ("japon")  double japon,
    		@PathVariable ("segundas")  double segundas,
    		@PathVariable ("terceras")  double terceras,
    		@PathVariable ("torreon")  double torreon,
    		@PathVariable ("coleada")  double coleada,
    	    @PathVariable ("id")  int    id,
    	    @PathVariable ("opcion")  int opcion,
    	    @PathVariable ("usuario")  int    usuario)
    {
    	Confirma con= new Confirma(id,0.0,0.0,0.0,"Problemas al guardar","Problemas al guardar",0,"Problemas al guardar "+id,0.0,0.0,0.0,0.0,0.0);
    	try
    	{
	    	FolioController controller=new FolioController();
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date d=new Date();
	        String fechaN=format.format(d);
	        Folios tmp =null;	    	try {tmp = controller.getOne(id);} catch(Exception e){ return  new  Confirma("No existe "+id);}
	    	double  pesoPorCaja =obtienePesoPorCaja(tmp.getTipo_limon());
	    	controller.updateRecord(
	    			tmp.getPeso_bruto(),tmp.getPeso_tara(),(tmp.getPeso_bruto()-tmp.getPeso_tara()-(tmp.getNo_rejas()* pesoPorCaja)),
	    			japon,segundas,terceras,torreon,coleada,
	    			id,opcion,usuario,tmp.getComprador(), tmp.getFacturar(),tmp.getFolio());
	        if(opcion==3)
	        {
	        	controller.updateRecord(fechaN, 3, id);
	        	PDF pdf=new PDF(id,3,usuario);
                pdf.run();
	        }
	        return getFolioLocal(tmp.getFolio());
    	}
        catch (Exception ex) 
        {
        	ex.printStackTrace();
        }
    	return con;
    }
	private double obtienePesoPorCaja(Integer tipo_limon) 
    {
    	List<Tarima> tamanos_reja;
        double pesoPorCaja=0;
        TrejaController ctrtr=new TrejaController();
        tamanos_reja=ctrtr.getAll();
        
        for(Tarima  elemento_tipo_limon: tamanos_reja)
        {
            System.out.println(elemento_tipo_limon+"-->"+tipo_limon);
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
