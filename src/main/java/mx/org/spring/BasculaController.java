package mx.org.spring;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.org.citicos.controller.FolioController;
import mx.org.citicos.controller.PDF;
import mx.org.citicos.controller.PDFGuardar;
import mx.org.citicos.controller.PDF_C;
import mx.org.citicos.controller.TrejaController;
import mx.org.citricos.entity.Folios;
import mx.org.citricos.entity.Tarima;

import org.springframework.web.bind.annotation.PathVariable;
@RestController
public class BasculaController
{
    @RequestMapping("/folios/{status}")
    public List<Renglon> getBasculas(@PathVariable("status") int status)
    {
    	FolioController controller=new FolioController();
    	List<Folios>  registros_basculas= controller.getAll(status);
    	List<Renglon>  lista =  new ArrayList<Renglon>();
        for(Folios folio : registros_basculas)
        	lista.add(new Renglon(folio.getProductor()+" , "+folio.getId()+", "+folio.getFolio()));
        return lista;
    }
    @RequestMapping("/basculas/{folio}")
    public Bascula getBasculas(@PathVariable("folio") String folio)
    {
    	FolioController controller=new FolioController();
    	Folios bascula =null;
    	try
    	{
    		bascula = controller.getOne(folio.trim());
    		if(bascula==null)
    			return new  Bascula(0,0.0,0.0,0.0,"","","",0,"Error.No existe Ticket Bascula[-1]:"+folio);
    	}
    	catch(Exception e) 
    	{
    		return  new  Bascula("No existe");
    	}
    	
		if(bascula!=null)
		{
			if(bascula.getId()!=null)
			{
	        	return new  Bascula(
	        		bascula.getId(),
	        		bascula.getPeso_bruto().doubleValue(), 
	        		bascula.getPeso_tara().doubleValue(),
	        		bascula.getPeso_neto().doubleValue(),
	        		bascula.getId_productor()+"-"+bascula.getProductor() ,
	        		folio,bascula.getEstatus().toString(),bascula.getEstatus(),"",bascula.getTicket());
	        	}
		}
		return  new  Bascula("No existe");
    }
    @RequestMapping("/basculasImpresion/{folio}/{opcion}/{usuario}/{fileName}")
    public Bascula getBasculasArchivo(@PathVariable("folio") String folio,@PathVariable("opcion") String opcion
    		,@PathVariable("usuario") String usuario,@PathVariable("fileName") String fileName)
    {
    	int v_usuario=0;
    	int v_opcion=0;
    	try { v_usuario= new Integer(usuario); }      catch(Exception e) { }
    	try { v_opcion= new Integer(opcion); }   catch(Exception e) {}
    	FolioController controller=new FolioController();
    	Folios bascula =null;
    	try
    	{
    		bascula = controller.getOne(folio);
    	}
    	catch(Exception e) 
    	{
    	}
    	
		if(bascula!=null)
		{
			if(bascula.getId()!=null)
			{
				PDFGuardar pdf=new PDFGuardar(bascula.getId(), v_opcion,v_usuario,fileName.replace("*","/"));
				pdf.run();
	        }
		}
		return  new  Bascula("No existe");
    }
    @RequestMapping("/basculasImpresion/{folio}/{opcion}/{usuario}")
    public Bascula getBasculasImpresion(@PathVariable("folio") String folio,@PathVariable("opcion") String opcion,@PathVariable("usuario") String usuario)
    {
    	int v_usuario=0;
    	int v_opcion=0;
    	try { v_usuario= new Integer(usuario); }      catch(Exception e) { }
    	try { v_opcion= new Integer(opcion); }   catch(Exception e) {}
    	FolioController controller=new FolioController();
    	Folios bascula =null;
    	try
    	{
    		bascula = controller.getOne(folio);
    		if(bascula==null)
    			return new  Bascula(0,0.0,0.0,0.0,"","","",0,"Error.No existe Ticket Bascula[-1]:"+folio);
    	}
    	catch(Exception e) 
    	{
    		return  new  Bascula("No existe");
    	}
    	
		if(bascula!=null)
		{
			if(bascula.getId()!=null)
			{
				if(v_opcion<=2)
				{
					PDF pdf=new PDF(bascula.getId(), v_opcion,v_usuario);
					pdf.run();
				}
				else
				{
					PDF_C pdf=new PDF_C(bascula.getId(),3,v_usuario);
	                pdf.run();
				}
	        }
		}
		return  new  Bascula("No existe");
    }
    @RequestMapping("/ticket/{folio}")
    public Integer getTicket(@PathVariable("folio") String folio)
    {
    	FolioController controller=new FolioController();
    	Folios tck =null;
    	try
    	{
    		tck = controller.getOne(folio);
    	}
    	catch(Exception e) 
    	{
    		return -2;
    	}
    	
		if(tck!=null)
		{
			switch(tck.getEstatus())
			{
				case 0:
				case 1:
					return tck.getProceso();
				case 2:
					return 3;
				case 3:
					return 4;
				case 4:
					return 5;
			}
		}
		return  -1;
    }
    @RequestMapping("/bascula/{folio}")
    public Bascula getBascula(@PathVariable("folio") String folio)
    {
    	FolioController controller=new FolioController();
    	Folios bascula =null;
    	try
    	{
    		bascula = controller.getOne(folio);
    		if(bascula==null)
    			return new  Bascula(0,0.0,0.0,0.0,"","","",0,"Error.No existe Ticket Bascula[-1]:"+folio);
    		if(bascula.getEstatus()!=0)
    			return new  Bascula(0,0.0,0.0,0.0,"","","",0,"Error.No existe Ticket Bascula["+bascula.getEstatus()+"]:"+folio);
    	}
    	catch(Exception e) 
    	{
    		return  new  Bascula("No existe");
    	}
    	
		if(bascula!=null)
		{
			if(bascula.getId()!=null)
			{
				String  posicion ="";
				if(bascula.getFechabas()==null && bascula.getFecharec()==null)
					posicion = "Inicio";
				else if(bascula.getFechabas()!=null && bascula.getFecharec()==null)
					posicion  = "Bascula";
				else if(bascula.getFechabas()!=null && bascula.getFecharec()!=null)
					posicion = "Bascula Confirma";
	        	return new  Bascula(
	        		bascula.getId(),
	        		bascula.getPeso_bruto().doubleValue(), 
	        		bascula.getPeso_tara().doubleValue(),
	        		bascula.getPeso_neto().doubleValue(),
	        		bascula.getProductor()+"|"+bascula.getId_productor(),
	        		folio,bascula.getEstatus().toString(),1,"",bascula.getTicket(),posicion);
	        	}
		}
		return  new  Bascula("No existe");
    }
    private Bascula getBasculaLocal(String folio)
    {
    	FolioController controller=new FolioController();
    	Folios bascula =null;
    	try
    	{
    		bascula = controller.getOne(folio); 
    	}
    	catch(Exception e) 
    	{
    		return  new  Bascula("No existe");
    	}
    	
		if(bascula!=null)
		{
			if(bascula.getId()!=null)
			{
	        	return new  Bascula(
	        		bascula.getId(),
	        		bascula.getPeso_bruto().doubleValue(), 
	        		bascula.getPeso_tara().doubleValue(),
	        		bascula.getPeso_neto().doubleValue(),
	        		bascula.getId_productor()+"-"+bascula.getProductor() ,
	        		folio,bascula.getEstatus().toString(),new Integer(bascula.getEstatus().toString()),"",bascula.getTicket());
	        	}
		}
		return  new  Bascula("No existe");
    }
    @RequestMapping("/bascula/guardar/{productor}/{bruto}/{tara}/{usuario}/{tck}")
    public Bascula setBasculaNew(
    		@PathVariable("productor") String productor,
    		@PathVariable("bruto") String bruto,
    		@PathVariable("tara") String tara,
    		@PathVariable("usuario") String usuario,
    		@PathVariable("tck") String tck)
    {
    	int    v_productor = 0;
    	double v_bruto     = 0.0d;
    	double v_tara      = 0.0d;
    	int    v_usuario   = 0;
    	int    error       = 0;
    	String msg         = "";
    	if(tck.equals("-")) tck ="";
    	try { v_productor  = new Integer(productor); } catch(Exception e) { error=1 ; msg ="Error en productor";}
    	try { v_bruto      = new Double(bruto); }     catch(Exception e) { error=1 ; msg ="Error en peso bruto";}
    	try { v_tara       = new Double(tara); }      catch(Exception e) { error=1 ; msg ="Error en peso tara";}
    	try { v_usuario    = new Integer(usuario); }   catch(Exception e) { error=1 ; msg ="Error en usuario";}
    	
    	
    	if(error==0)
    	{
	    	try
	        {
	    		
	            FolioController controller=new FolioController();
	            Folios bascula=controller.insertRecordcTCK(v_productor,v_bruto,v_tara,(v_bruto-v_tara),v_usuario,tck.trim());
	            return new  Bascula( 
	            		bascula.getId(),
	            		bascula.getPeso_bruto().doubleValue(), 
	            		bascula.getPeso_tara().doubleValue(),
	            		bascula.getPeso_neto().doubleValue(),
	            		bascula.getProductor(),
	            		bascula.getFolio(),bascula.getEstatus().toString(),1,"OK",bascula.getTicket());
	        }
	        catch (Exception ex) 
	        {
	        	return new  Bascula(0,0.0,0.0,0.0,"","","",0,"Error."+ex.getMessage());
	        }
    	}
    	else
    		return new  Bascula(0,0.0,0.0,0.0,"","","",0,"Error."+msg);
    }
    @RequestMapping("/bascula/editar/{productor}/{bruto}/{tara}/{usuario}/{opcion}/{id}/{tck}")
    public Bascula modifyBasculaNew(
    		@PathVariable("productor") String productor,
    		@PathVariable("bruto") String bruto,
    		@PathVariable("tara") String tara,
    		@PathVariable("usuario") String usuario,
    		@PathVariable("opcion") String opcion,
    		@PathVariable("id") String id,
    		@PathVariable("tck") String tck)
    {
    	Bascula bascula =  new Bascula(0,0.0,0.0,0.0,"","","",0,"Error.Problemas al guardar");
    	int    v_productor = 0;
    	double v_bruto     = 0.0d;
    	double v_tara      = 0.0d;
    	int    v_usuario   = 0;
    	int    error       = 0;
    	int    v_opcion     = 0;
    	int    v_id         = 0;
    	String msg         = "";
    	if(tck.equals("-")) tck ="";
    	try { v_productor  = new Integer(productor); } catch(Exception e) { error=1 ; msg ="Error en productor";}
    	try { v_bruto      = new Double(bruto); }      catch(Exception e) { error=1 ; msg ="Error en peso bruto";}
    	try { v_tara       = new Double(tara); }       catch(Exception e) { error=1 ; msg ="Error en peso tara";}
    	try { v_usuario    = new Integer(usuario); }   catch(Exception e) { error=1 ; msg ="Error en usuario";}
    	try { v_opcion     = new Integer(opcion); }    catch(Exception e) { error=1 ; msg ="Error en opcion";}
    	try { v_id         = new Integer(id); }        catch(Exception e) { error=1 ; msg ="Error en id";}
    	FolioController controller=new FolioController();
    	Folios valida = controller.getOne(v_id);
    	if(error==0)
    	{	
    		try
    		{
    			if(v_opcion==0)
    			{
    				editarMismoEstatus(v_bruto,v_tara,v_productor,v_id,0,v_usuario,tck.trim());
    			}
    			else
    			{
    				editarMismoEstatus(v_bruto,v_tara,v_productor,v_id,1,v_usuario,tck.trim());
	    			if(valida.getFecharec()==null)
	    			{
	    				System.out.println("BASCULA!   3 ");
	    				try
	    				{
	    					System.out.println("BASCULA!   4 ");
	    					PDF pdf=new PDF(v_id,0,v_usuario);
	    					pdf.run();
	    				}catch(Exception ex ) { ex.printStackTrace();};
	                	editarOtroEstatus(v_bruto,v_tara,v_productor,v_id,1,v_usuario,tck.trim());
	    			}
	    			else if(valida.getFechabas()!=null && valida.getFecharec()!=null)
	    			{
		    			bascula = editarOtroEstatus(v_bruto,v_tara,v_productor,v_id,2,v_usuario,tck.trim());	
		    		}
	    		}
	    	}
    		catch (Exception ex) 
	        {
    			System.out.println("++++++++200");
	        	return new  Bascula(0,0.0,0.0,0.0,"","","",0,"Error."+ex.getMessage());
	        }
    	}
    	else
    	{
    		bascula = new Bascula(0,0.0,0.0,0.0,"","","",0,"Error."+msg);
    	}
    	return bascula;
    } 
    private Bascula editarMismoEstatus
    (double bruto,double tara,Integer id_productor,Integer id,int opc,Integer usuario,String tck)
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
        controller.updateRecordFase1(id_productor,bruto,tara,(bruto-tara-(no_rejas* pesoPorCaja)),id,0,usuario,tck);
        return getBasculaLocal(folioTemporal.getFolio());
    }
	private Bascula editarOtroEstatus(double bruto,double tara,Integer id_productor,Integer id,int opc,Integer usuario,String tck)
    {
		if(tck.equals("-")) tck ="";
		System.out.println("BASCULA!   5.1 ");
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
        {
        	System.out.println("BASCULA!   5.2 ");
        	System.out.println("--->>>>>>>>>>>>>>>>>>>>>>>>  Modifica bascula en estatus 1");
        	controller.updateRecordFase1(id_productor,bruto,tara,(bruto-tara-(no_rejas* pesoPorCaja)),id,1,usuario,tck);
        }
        if(folioTemporal.getFechabas()!=null && folioTemporal.getFecharec()!=null)
        {
        	System.out.println("BASCULA!   5.3 ");
        	controller.updateRecordFase1(id_productor,bruto,tara,(bruto-tara-(no_rejas* pesoPorCaja)),id,2,usuario,tck);
        	controller.updateRecord(fechaN, 2, id);
        }
        System.out.println("BASCULA!   5.4 TERMINA ");
        return getBasculaLocal(folioTemporal.getFolio());
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
