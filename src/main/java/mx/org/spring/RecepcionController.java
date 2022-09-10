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
public class RecepcionController
{
    @RequestMapping("/recepcion/{folio}")
    public Recepcion getFolio(@PathVariable("folio") String folio)
    {
    	FolioController controller=new FolioController();
    	Recepcion recepcion = new Recepcion(0,folio,0.0d,0.0d,0.0d,0.0,"","", "", "","", "", "", 0,"0");
    	recepcion.setMsg("Error al guardar Recepcion");
    	Folios b =null;
    	try
    	{
    		b = controller.getOne(folio);
    		if(b==null)
    		{
    			recepcion.setMsg("Error.No existe Ticket Recepcion[-1]:"+folio);
    	    	recepcion.setOpc(0);
    	    	return recepcion;
    		}
    		if(b.getEstatus()!=1)
    		{
    			recepcion.setMsg("Error.No existe Ticket Recepcion["+b.getEstatus()+"]:"+folio);
    	    	recepcion.setOpc(0);
    	    	return recepcion;
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

                recepcion = new Recepcion(b.getId(),b.getFolio(),b.getPeso_bruto(),b.getPeso_tara(),b.getPeso_neto(),
                		b.getNo_rejas(),b.getComprador(),b.getFacturar(),
                		b.getTipos_rejas()+"|"+b.getTipo_rejas(),
                		b.getTipos_limones()+"|"+b.getTipo_limon(),
                		b.getAgronomo()+"|"+b.getId_agronomo(),
                		b.getTicket(),b.getObservaciones(),b.getDejo(),""+b.getEstatus());
	        	}
		}
		return  recepcion;
    } 
    private  Recepcion getFolioLocal(String folio)
    {
    	FolioController controller=new FolioController();
    	Recepcion recepcion = new Recepcion(0,folio,0.0d,0.0d,0.0d,0.0,"","", "", "","", "", "", 0,"0");
    	recepcion.setMsg("Error al guardar Recepcion");
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

                recepcion = new Recepcion(b.getId(),b.getFolio(),b.getPeso_bruto(),b.getPeso_tara(),b.getPeso_neto(),
                		b.getNo_rejas(),b.getComprador(),b.getFacturar(),b.getTipo_rejas()+"-"+b.getTipos_rejas(),b.getTipo_limon()+"-"+b.getTipos_limones(),
                		b.getId_agronomo()+"-"+b.getAgronomo(),b.getTicket(),b.getObservaciones(),b.getDejo(),""+b.getEstatus());
	        	}
		}
		return  recepcion;
    }
    @RequestMapping("/setRecepccion/{norejas}/{comprador}/{facturar}/{tamanorejas}/{tipolimon}/{agronomo}/{observaciones}/{dejorejas}/{ticket}/{id}/{opcion}/{usuario}/{bruto}/{tara}")
    public Recepcion setFolio(
    		@PathVariable ("norejas")  double norejas,
    	    @PathVariable ("comprador")  String comprador,
    	    @PathVariable ("facturar")  String facturar,
    	    @PathVariable ("tamanorejas")  int tamanorejas,
    	    @PathVariable ("tipolimon")  int tipolimon,
    	    @PathVariable ("agronomo")  int agronomo,
    	    @PathVariable ("observaciones")  String observaciones,
    	    @PathVariable ("dejorejas")  int dejorejas,
    	    @PathVariable ("ticket")  String ticket,
    	    @PathVariable ("id")  int    id,
    	    @PathVariable ("opcion")  int opcion,
    	    @PathVariable ("usuario")  int    usuario,
    	    @PathVariable ("bruto")  double bruto,
    	    @PathVariable ("tara")  double tara)
    {
    	
    	observaciones= cambiarCaracter(observaciones,"&","*0*");
    	observaciones= cambiarCaracter(observaciones,"\"","*1*");
        observaciones= cambiarCaracter(observaciones,"/","*2*");
        observaciones= cambiarCaracter(observaciones,"'","*3*");
        observaciones= cambiarCaracter(observaciones,"?","*4*");
        observaciones= cambiarCaracter(observaciones,"¿","*5*");
        observaciones= cambiarCaracter(observaciones,"~","*6*");
        observaciones= cambiarCaracter(observaciones,":","*7*");
        observaciones= cambiarCaracter(observaciones,"|","*8*");
        observaciones= cambiarCaracter(observaciones,"%","*9*");
        
    	Recepcion recepcion = new Recepcion(id,"",0.0d,0.0d,0.0d,
    			norejas,comprador,facturar, ""+tamanorejas, ""+tipolimon,""+agronomo, ticket, observaciones, dejorejas,""+opcion);
    	recepcion.setMsg("Error al guardar Recepcion");
    	recepcion.setOpc(0);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d=new Date();
        String fechaN=format.format(d);
        FolioController controller=new FolioController();
        Folios tmp =null;
        
        if(comprador.equals("-"))     comprador=("");
        if(facturar.equals("-"))      facturar=("");
        if(observaciones.equals("-")) observaciones=("");
        if(ticket.equals("-"))        ticket=("");
        
    	try {tmp = controller.getOne(id);} catch(Exception e){ return  new  Recepcion("No existe "+id);}
    	try
        {
    		double pesoPorCaja=0;
    		double pesoRestante=0;
            if(dejorejas==1)
            {
                pesoPorCaja=obtienePesoPorCaja(tamanorejas);
                if(norejas>0)
                	pesoRestante =(pesoPorCaja*norejas );
            }
            if((bruto-tara)>=0)
            {
            	
                controller.updateRecord(norejas,tamanorejas,
                		tipolimon,agronomo,dejorejas,
                observaciones,id,opcion,usuario,tipolimon,
                bruto,tara, (bruto-tara)-pesoRestante,
                comprador,facturar,ticket);
                if(opcion==0)
                {
                    PDF pdf=new PDF(id,2,usuario);
                    pdf.run();
                    controller.updateRecord(fechaN, 2, id);
                }
                Folios b=null;
                return getFolioLocal(tmp.getFolio());    			
            }
        }
        catch (Exception ex) 
        {
        	ex.printStackTrace();
        }
    	return recepcion;
    }

	private String cambiarCaracter(String observaciones, String remplazarPor, String remplazar) 
	{
		if(observaciones.indexOf(remplazar)>0)
            observaciones= observaciones.replaceAll(remplazar,remplazarPor);
        return observaciones;
	}
	private void editarOtroEstatus(double bruto,double tara,Integer id_productor,Integer id,int opc,Integer usuario)
    {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Date d=new Date();
        String fechaN=format.format(d);
        double pesoPorCaja=0;
        FolioController controller=new FolioController();
        Folios folioTemporal = controller.getOne(id);
        if(folioTemporal.getDejo() == 1)
            pesoPorCaja=obtienePesoPorCaja(folioTemporal.getTipo_rejas());
        double no_rejas=  folioTemporal.getNo_rejas();
        if(folioTemporal.getFechabas()!=null && folioTemporal.getFecharec()==null)
        	controller.updateRecordFase1(id_productor,bruto,tara,(bruto-tara-(no_rejas* pesoPorCaja)),id,1,usuario);
        if(folioTemporal.getFechabas()!=null && folioTemporal.getFecharec()!=null)
        {
        	controller.updateRecordFase1(id_productor,bruto,tara,(bruto-tara-(no_rejas* pesoPorCaja)),id,2,usuario);
        	controller.updateRecord(fechaN, 2, id);
        }
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
