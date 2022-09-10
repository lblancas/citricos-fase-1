package mx.org.citicos.controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.RepTarimaCabecera;
import mx.org.citricos.entity.RepTarimaDetalle;
import mx.org.citricos.entity.RepTarimaDetallePallet;
public class ReporteController extends Conexion
{
	String queryTarima="  select "  + 
			" cct.id id, "  +
			" cctd.id id_tarima  , "  + 
			" ccte.no_termo , "  + 
			" ccte.no_sello , "  + 
			" ccte.lector_temp , "  + 
			" ccte.lote, "  + 
			" ccta.nombre, "  + 
			" ccta.placas, "  + 
			" ccta.numero_economico, "  + 
			" cct.numero numeroTarima, "  + 
			" cct.id_cat_tarima, "  + 
			" ccct.descripcion tarima_desc, "  + 
			" cct.id_cat_marca, "  + 
			" cccm.descripcion marca_desc, "  + 
			" cctd.id_calibre, "  + 
			" ccclb.descripcion calibre_desc, "  + 
			" cctd.id_cat_calidad, "  + 
			" cccld.descripcion calidad_desc, "  + 
			" cctd.cajas, "  + 
			" cctd.cdi "  + 
			" from cc_tarima cct,cc_tarima_desc cctd,cc_transporte ccte,cc_transportista ccta, "  + 
			" cc_cat_marca cccm,cc_cat_tarima ccct,cc_calibre ccclb,cc_cat_calidad cccld "  + 
			" where cct.id = cctd.id_tarima "  + 
			" and   cct.id_transporte = ccte.id   "  + 
			" and   ccte.id_transportista = ccta.id "  + 
			" and   cct.id_cat_marca =  cccm.id "  + 
			" and   cct.id_cat_tarima = ccct.id "  + 
			" and   cctd.id_calibre =ccclb.id "  + 
			" and   cctd.id_cat_calidad = cccld.id "  + 
			" and   cct.id = "  ;
    String queryTarimaTransporte=" select "  + 
    		" cctt.id idTransporteTarima, "  + 
    		" cctt.numero numeroTransporteTarima, "  + 
    		" ccte.no_termo, " +
    		" cctd.id id_tarima , "  + 
    		" ccte.no_sello, "  + 
    		" ccte.lector_temp, "  + 
    		" ccte.lote, "  + 
    		" ccta.nombre, "  + 
    		" ccta.placas, "  + 
    		" cct.id idTarima , "  + 
    		" cctd.id idDetalle, "  + 
    		" ccta.numero_economico, "  + 
    		" cct.numero numeroTarima, "  + 
    		" cct.id_cat_tarima, "  + 
    		" ccct.descripcion tarima_desc, "  + 
    		" cct.id_cat_marca, "  + 
    		" cccm.descripcion marca_desc, "  + 
    		" cctd.id_calibre, "  + 
    		" ccclb.descripcion calibre_desc, "  + 
    		" cctd.id_cat_calidad, "  + 
    		" cccld.descripcion calidad_desc, "  + 
    		" cctd.cajas, "  + 
    		" cctd.cdi "  + 
    		" from cc_tarima_transporte cctt, cc_tarima cct, cc_tarima_desc cctd, cc_transporte ccte, cc_transportista ccta, "  + 
    		" cc_cat_marca cccm, cc_cat_tarima ccct, cc_calibre ccclb, cc_cat_calidad cccld  "  + 
    		" where cctt.id = cct.id_tt "  + 
    		" and   cctt.id_transporte = ccte.id   "  + 
    		" and   cctt.id_estatus =1 "  + 
    		" and   cct.id = cctd.id_tarima "  + 
    		" and   cct.id_cat_tarima = ccct.id "  + 
    		" and   cct.id_cat_marca =  cccm.id "  + 
    		" and   ccte.id_transportista = ccta.id "  + 
    		" and   cctd.id_calibre =ccclb.id "  + 
    		" and   cctd.id_cat_calidad = cccld.id "  + 
    		" and   cctt.id = " ;
    
    public RepTarimaCabecera getTarima(int idTarima)
    {
        System.out.println("queryTarima("+idTarima+")");
        RepTarimaCabecera cabecera =null;
        RepTarimaDetalle tarima = null;
        List<RepTarimaDetalle> tarimas=new ArrayList<RepTarimaDetalle>();
        List<RepTarimaDetallePallet> pallets=new ArrayList<RepTarimaDetallePallet>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            String query =" select  cct.id id,  cctd.id id_tarima  ,  ccte.no_termo ,  ccte.no_sello , " + 
            		" ccte.lector_temp ,  ccte.lote,  ccta.nombre,  ccta.placas,  ccta.numero_economico,  cct.numero numeroTarima,  " + 
            		" cct.id_cat_tarima,  ccct.descripcion tarima_desc,  cct.id_cat_marca,  cccm.descripcion marca_desc,  cctd.id_calibre, " + 
            		" ccclb.descripcion calibre_desc,  cctd.id_cat_calidad,  cccld.descripcion calidad_desc,  cctd.cajas,  cctd.cdi  " + 
            		" from cc_tarima cct,cc_tarima_desc cctd,cc_transporte ccte,cc_transportista ccta,  cc_cat_marca cccm,cc_cat_tarima ccct, " + 
            		" cc_calibre ccclb,cc_cat_calidad cccld  where cct.id = cctd.id_tarima  and   cct.id_transporte = ccte.id    " + 
            		" and   ccte.id_transportista = ccta.id  and   cct.id_cat_marca =  cccm.id  and   cct.id_cat_tarima = ccct.id  " + 
            		" and   cctd.id_calibre =ccclb.id  and   cctd.id_cat_calidad = cccld.id  " +
            		" and   cct.id = " + idTarima + 
            		" union" + 
            		" select  cct.id id,  0 id_tarima  ,  ccte.no_termo ,  ccte.no_sello , " + 
            		" ccte.lector_temp ,  ccte.lote,  ccta.nombre,  ccta.placas,  ccta.numero_economico,  cct.numero numeroTarima, " + 
            		" cct.id_cat_tarima,  ccct.descripcion tarima_desc,  cct.id_cat_marca,  cccm.descripcion marca_desc,  0 id_calibre, " + 
            		" '-' calibre_desc,  0 id_cat_calidad,  '-' calidad_desc,  0 cajas,  '-' cdi  " + 
            		" from cc_tarima cct, cc_transporte ccte,cc_transportista ccta,  cc_cat_marca cccm,cc_cat_tarima ccct " + 
            		" where cct.id_transporte = ccte.id    " +
            		" and   cct.id  not in (select cctd.id_tarima from cc_tarima_desc cctd ) " + 
            		" and   ccte.id_transportista = ccta.id  " + 
            		" and   cct.id_cat_marca =  cccm.id  " + 
            		" and   cct.id_cat_tarima = ccct.id    " + 
            		" and   cct.id =  " + idTarima +  
            		" order by  id";
            System.out.println(">>>>>>>>>>>>>>>>> "+query);
            ResultSet rs=st.executeQuery(query);
            int cajasTotales=0;
            while(rs.next())
            {
            	if(cabecera==null)
            	{
	                String  no_termo =rs.getString("no_termo");
	                String  no_sello =rs.getString("no_sello");
	                String  lector_temp =rs.getString("lector_temp");
	                String  lote =rs.getString("lote");
	                String  nombre =rs.getString("nombre");
	                String  placas =rs.getString("placas");
	                String  numero_economico =rs.getString("numero_economico");
	                int id =rs.getInt("id");
	                String  numeroTarima =rs.getString("numeroTarima");
	                cabecera =new RepTarimaCabecera(id,no_termo,no_sello,lector_temp,lote,nombre,placas,numero_economico);
	                
	                int id_cat_tarima =rs.getInt("id_cat_tarima");
	                String  tarima_desc =rs.getString("tarima_desc");
	                int id_cat_marca =rs.getInt("id_cat_marca");
	                String  marca_desc =rs.getString("marca_desc");
	                tarima=new RepTarimaDetalle(id,numeroTarima,id_cat_tarima,tarima_desc,id_cat_marca,marca_desc);
	                
            	}
                int id_detalle =rs.getInt("id_tarima");
                int id_calibre =rs.getInt("id_calibre");
                String  calibre_desc =rs.getString("calibre_desc");
                int id_cat_calidad =rs.getInt("id_cat_calidad");
                String  calidad_desc =rs.getString("calidad_desc");
                int cajas =rs.getInt("cajas");
                cajasTotales =  cajasTotales + cajas;
                String  cdi =rs.getString("cdi");
                pallets.add(new RepTarimaDetallePallet(id_detalle,id_calibre,calibre_desc,id_cat_calidad,calidad_desc,cajas,cdi));
            }
            tarima.setPallets(pallets);
            tarima.setCajas(cajasTotales);
            tarimas.add(tarima);
            cabecera.setTarimas(tarimas);
            rs.close();
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
        finally
        {
            if(connection!=null)
            {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransporteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return cabecera;
    }
    
    public RepTarimaCabecera getTransporteTarima(int transporteTarima)
    {
        System.out.println("queryTarimaTransporte("+ transporteTarima+")");
        RepTarimaCabecera cabecera =null;
        RepTarimaDetalle tarima = null;
        List<RepTarimaDetalle> tarimas=new ArrayList<RepTarimaDetalle>();
        List<RepTarimaDetallePallet> pallets=new ArrayList<RepTarimaDetallePallet>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            String query  = "	select  cctt.id idTransporteTarima,  cctt.numero numeroTransporteTarima,  ccte.no_termo,  cctd.id id_tarima ,  ccte.no_sello, " + 
            		"	ccte.lector_temp,  ccte.lote,  ccta.nombre,  ccta.placas,  cct.id idTarima ,  cctd.id idDetalle,  ccta.numero_economico,  " + 
            		"	cct.numero numeroTarima,  cct.id_cat_tarima,  ccct.descripcion tarima_desc,  cct.id_cat_marca,  cccm.descripcion marca_desc,  " + 
            		"	cctd.id_calibre,  ccclb.descripcion calibre_desc,  cctd.id_cat_calidad,  cccld.descripcion calidad_desc,  cctd.cajas,  cctd.cdi  " + 
            		"	from cc_tarima_transporte cctt, cc_tarima cct, cc_tarima_desc cctd, cc_transporte ccte, cc_transportista ccta,  " + 
            		"	cc_cat_marca cccm, cc_cat_tarima ccct, cc_calibre ccclb, cc_cat_calidad cccld   " + 
            		"	where cctt.id = cct.id_tt  and   cctt.id_transporte = ccte.id    and   cctt.id_estatus =1  " + 
            		"	and   cct.id = cctd.id_tarima  and   cct.id_cat_tarima = ccct.id  and   cct.id_cat_marca =  cccm.id  " + 
            		"	and   ccte.id_transportista = ccta.id  " + 
            		"	and   cctd.id_calibre =ccclb.id  and   cctd.id_cat_calidad = cccld.id" +
            		"   and   cctt.id = " + transporteTarima+ " \r\n"+
            		"	union  \r\n" + 
            		"	select  cctt.id idTransporteTarima,  cctt.numero numeroTransporteTarima,  ccte.no_termo,  0 id_tarima ,  ccte.no_sello, " + 
            		"	ccte.lector_temp,  ccte.lote,  ccta.nombre,  ccta.placas,  cct.id idTarima , 0 idDetalle,  ccta.numero_economico, " + 
            		"	cct.numero numeroTarima,  cct.id_cat_tarima,  ccct.descripcion tarima_desc,  cct.id_cat_marca,  cccm.descripcion marca_desc,  " + 
            		"	0 id_calibre, '-' calibre_desc,  0 id_cat_calidad,  '-' calidad_desc,  0 cajas, '-' cdi  " + 
            		"	from cc_tarima_transporte cctt, cc_tarima cct , cc_transporte ccte, cc_transportista ccta,  " + 
            		"	cc_cat_marca cccm, cc_cat_tarima ccct " + 
            		"	where cctt.id = cct.id_tt   " + 
            		"	and   cct.id  not in (select cctd.id_tarima from cc_tarima_desc cctd ) " + 
            		"	and   cctt.id_transporte = ccte.id     " + 
            		"	and   cctt.id_estatus =1  " + 
            		"	and   cct.id_cat_tarima = ccct.id   " + 
            		"	and   cct.id_cat_marca =  cccm.id  " + 
            		"	and   ccte.id_transportista = ccta.id " + 
            		"	and   cctt.id =  " + transporteTarima+ "\n\r"+ 
            		"	union  \r\n" +  
            		"	select  cctt.id idTransporteTarima,  cctt.numero numeroTransporteTarima,  ccte.no_termo,  0 id_tarima ,  ccte.no_sello, " + 
            		"	ccte.lector_temp,  ccte.lote,  ccta.nombre,  ccta.placas,  0 idTarima , 0 idDetalle,  ccta.numero_economico,  " + 
            		"	'-' numeroTarima,  0 id_cat_tarima,  '-' tarima_desc,  0 id_cat_marca,  '-' marca_desc,  " + 
            		"	0 id_calibre, '-' calibre_desc,  0 id_cat_calidad,  '-' calidad_desc,  0 cajas, '-' cdi  " + 
            		"	from cc_tarima_transporte cctt,  cc_transporte ccte, cc_transportista ccta " + 
            		"	where cctt.id_transporte = ccte.id    " + 
            		"	and   cctt.id_estatus =1  " + 
            		"	and   cctt.id  not in  (select cct.id_tt from cc_tarima cct) " + 
            		"	and   ccte.id_transportista = ccta.id " + 
            		"	and   cctt.id =  "  + transporteTarima+ 
            		"	order by idTarima, id_tarima ";
            System.out.println(">>>>>>>>>>>>>>>>>  "+ query);
            ResultSet rs=st.executeQuery(query);
            int cajasTotales=0;
            int idTarima=0;
            while(rs.next())
            {
            	if(cabecera==null)
            	{
            		int idTransporteTarima = rs.getInt("idTransporteTarima");
            		String  numeroTransporteTarima =rs.getString("numeroTransporteTarima");
	                String  no_termo =rs.getString("no_termo");
	                String  no_sello =rs.getString("no_sello");
	                String  lector_temp =rs.getString("lector_temp");
	                String  lote =rs.getString("lote");
	                String  nombre =rs.getString("nombre");
	                String  placas =rs.getString("placas");
	                String  numero_economico =rs.getString("numero_economico");
	                cabecera =new RepTarimaCabecera(idTransporteTarima,numeroTransporteTarima,idTarima,no_termo,no_sello,lector_temp,lote,nombre,placas,numero_economico);
            	}
            	int idTarimaActual =rs.getInt("idTarima");
            	if(idTarima != idTarimaActual)
            	{
            		String  numeroTarima =rs.getString("numeroTarima");
	                int id_cat_tarima =rs.getInt("id_cat_tarima");
	                String  tarima_desc =rs.getString("tarima_desc");
	                int id_cat_marca =rs.getInt("id_cat_marca");
	                String  marca_desc =rs.getString("marca_desc");
	                if(pallets.size()>0)
	                {
		                tarima.setPallets(pallets);
		                tarima.setCajas(cajasTotales);
		                tarimas.add(tarima);
		                pallets=new ArrayList<RepTarimaDetallePallet>();
	                }
	                idTarima = idTarimaActual;
	                tarima=new RepTarimaDetalle(idTarima,numeroTarima,id_cat_tarima,tarima_desc,id_cat_marca,marca_desc);	                
            	}
            	if(idTarima == idTarimaActual) 
            	{
	                int id_detalle =rs.getInt("id_tarima");
	                int id_calibre =rs.getInt("id_calibre");
	                String  calibre_desc =rs.getString("calibre_desc");
	                int id_cat_calidad =rs.getInt("id_cat_calidad");
	                String  calidad_desc =rs.getString("calidad_desc");
	                int cajas =rs.getInt("cajas");
	                cajasTotales =  cajasTotales + cajas;
	                String  cdi =rs.getString("cdi");
	                pallets.add(new RepTarimaDetallePallet(id_detalle,id_calibre,calibre_desc,id_cat_calidad,calidad_desc,cajas,cdi));
            	}
            }
            if(pallets.size()>0)
            {
                tarima.setPallets(pallets);
                tarima.setCajas(cajasTotales);
                tarimas.add(tarima);
            }
            cabecera.setTarimas(tarimas);
            rs.close();
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
        finally
        {
            if(connection!=null)
            {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransporteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return cabecera;
    }
}
