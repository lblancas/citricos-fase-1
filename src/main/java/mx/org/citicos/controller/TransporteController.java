/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion; 
import mx.org.citricos.entity.Transporte;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author BID
 */
public class TransporteController extends Conexion
{
    String querySelect="SELECT ID,NO_TERMO,NO_SELLO,LECTOR_TEMP,LOTE,FECHA,ID_CLIENTE,CREADOPOR,MODIFICADOPOR,ID_ACTIVO,ID_TRANSPORTISTA,NUMERO FROM CC_TRANSPORTE  WHERE ID_ACTIVO =1";
    String querySelectTR="SELECT ID,NO_TERMO,NO_SELLO,LECTOR_TEMP,LOTE,FECHA,ID_CLIENTE,CREADOPOR,MODIFICADOPOR,ID_ACTIVO,ID_TRANSPORTISTA,NUMERO FROM CC_TRANSPORTE  WHERE   ID_TRANSPORTISTA =";
    String queryMax="SELECT MAX(ID) ID FROM CC_TRANSPORTE";
    String querySelectOne="SELECT  ID,NO_TERMO,NO_SELLO,LECTOR_TEMP,LOTE,FECHA,ID_CLIENTE,CREADOPOR,MODIFICADOPOR,ID_ACTIVO,ID_TRANSPORTISTA,NUMERO  FROM CC_TRANSPORTE  WHERE ID_ACTIVO =1 AND ID =  ";
    String queryUpdate="UPDATE CC_TRANSPORTE SET NO_TERMO=?,NO_SELLO=?,LECTOR_TEMP=?,LOTE=?,FECHA=?,ID_CLIENTE=?,MODIFICADOPOR=?, ID_TRANSPORTISTA=? WHERE ID = ? ";
    String queryUpdateNumber="UPDATE CC_TRANSPORTE SET  NUMERO = CONCAT(CONCAT('TTE',DATE_FORMAT(NOW(),'%Y%m%d')),LPAD(ID,9,'0')) WHERE ID = ? ";
    String queryDeleteUser="UPDATE CC_TRANSPORTE SET ID_ACTIVO = 3 , MODIFICADOPOR =? WHERE ID = ?  ";
    String queryCreateUser="INSERT INTO CC_TRANSPORTE(NO_TERMO,NO_SELLO,LECTOR_TEMP,LOTE,FECHA,ID_CLIENTE,CREADOPOR,MODIFICADOPOR,id_transportista) "
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    String queryTrans=" SELECT C.ID, " +
            " CONCAT(CONCAT(A.NOMBRE,' - '), " +
            " CONCAT(CONCAT(CONCAT(CONCAT(C.NO_TERMO,' - '),C.NO_SELLO),' - '),C.NUMERO)) DATO " +
            " FROM CC_TRANSPORTE C, CC_TRANSPORTISTA A " +
            " WHERE C.ID_ACTIVO =1 " +
            " AND   C.ID_TRANSPORTISTA =  A.ID";
     
    private Transporte getMax()
    {
        Transporte bean=new Transporte();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(queryMax);
            if(rs.next())
            { 
                bean = getOne(rs.getInt("ID"));
            }
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
        return bean;
    }
    /**
     * Proceso para la consulta de un cliente
     * @param id
     * @return 
     */
    public Transporte getOne(int id)
    {
        Transporte bean=new Transporte();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+id);
            if(rs.next())
            { 
                bean.setId(rs.getInt("ID"));
                bean.setNumerotermo(rs.getString("NO_TERMO"));
                bean.setNumerosello(rs.getString("NO_SELLO"));
                bean.setLector_temp(rs.getString("LECTOR_TEMP")); 
                bean.setLote(rs.getString("LOTE"));       
                bean.setFecha(rs.getString("FECHA"));       
                bean.setIdcliente(rs.getInt("ID_CLIENTE"));
                bean.setIdtransportista(rs.getInt("ID_TRANSPORTISTA"));
                bean.setNumero(rs.getString("NUMERO"));   
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date procesada = format.parse( rs.getString("FECHA"));

                bean.setFechat(procesada);
            }
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
        return bean;
    }
    public List<Transporte> getTransportes()
    {
        System.out.println("getTransportes()");
        List<Transporte> l=new ArrayList<Transporte>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(queryTrans);
            while(rs.next())
            {
                Transporte bean=new Transporte();
                bean.setId(rs.getInt("ID"));
                bean.setNumero(rs.getString("DATO"));    
                
                l.add(bean);
            }
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
        return l;
    }
    /**
     * Obtiene todos los transportes
     * @return 
     */
    public ArrayList<Transporte> getAll()
    {
        ArrayList<Transporte> l=new ArrayList<Transporte>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                Transporte bean=new Transporte();
                bean.setId(rs.getInt("ID"));
                bean.setNumerotermo(rs.getString("NO_TERMO"));
                bean.setNumerosello(rs.getString("NO_SELLO"));
                bean.setLector_temp(rs.getString("LECTOR_TEMP")); 
                bean.setLote(rs.getString("LOTE"));       
                bean.setFecha(rs.getString("FECHA"));       
                bean.setIdcliente(rs.getInt("ID_CLIENTE"));
                bean.setIdtransportista(rs.getInt("ID_TRANSPORTISTA"));
                bean.setNumero(rs.getString("NUMERO"));       
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date procesada = format.parse( rs.getString("FECHA"));
                bean.setFechat(procesada);
                l.add(bean);
            }
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
        return l;
    }
    /**
     * Obtiene todos los transportes
     * @param idTranportista
     * @return 
     */
    public ArrayList<Transporte> getAll(int idTranportista,HashMap m)
    {
        ArrayList<Transporte> l=new ArrayList<Transporte>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectTR + idTranportista);
            while(rs.next())
            {
                Transporte bean=new Transporte();
                bean.setId(rs.getInt("ID"));
                bean.setNumerotermo(rs.getString("NO_TERMO"));
                bean.setNumerosello(rs.getString("NO_SELLO"));
                bean.setLector_temp(rs.getString("LECTOR_TEMP")); 
                bean.setLote(rs.getString("LOTE"));       
                bean.setFecha(rs.getString("FECHA"));       
                bean.setIdcliente(rs.getInt("ID_CLIENTE"));
                bean.setIdtransportista(rs.getInt("ID_TRANSPORTISTA"));
                bean.setNumero(rs.getString("NUMERO"));       
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date procesada = format.parse( rs.getString("FECHA"));
                bean.setActivos((String) m.get((new Integer(rs.getInt("ID_ACTIVO")))));
                bean.setFechat(procesada);
                l.add(bean);
            }
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
        return l;
    }
    
    /**
     * Proceso para la modificación de registro.
     * @param no_termo
     * @param no_sello
     * @param lector_temp 
     * @param lote
     * @param fecha
     * @param id_cliente 
     * @param id_user
     * @param id
     * @return registro a modificar
     */
    public int updateRecord(String no_termo,String no_sello,String lector_temp,String lote,String fecha,int id_cliente,int id_transportista,int id_user,int id)
     {   
        System.out.println("updateRecord("+no_termo+","+no_sello+","+lector_temp+","+lote+","+fecha+","+id_cliente+","+id_user+","+id+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            //UPDATE CC_TRANSPORTE SET NO_TERMO=?,NO_SELLO=?,LECTOR_TEMP=?,LOTE=?,FECHA=?,ID_CLIENTE=?,MODIFICADOPOR=?, ID_TRANSPORTISTA=? WHERE ID = ?
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setString(1, no_termo);
            ps.setString(2, no_sello);
            ps.setString(3, lector_temp); 
            ps.setString(4, lote);
            ps.setString(5, fecha);
            ps.setInt(6, id_cliente); 
            ps.setInt(7, id_user);
            ps.setInt(8, id_transportista);
            ps.setInt(9, id);
            updeteCont = ps.executeUpdate();
            ps.close();
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
        return updeteCont;
    }
    /**
     * Proceso para la modificación de registro. 
     * @param id
     * @return numero de registros dados de alta.
     */
    private  int updateRecord(int id)
     {  
        System.out.println("updateRecord("+ id+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdateNumber); 
            ps.setInt(1, id);
            updeteCont = ps.executeUpdate();
            ps.close();
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
        return updeteCont;
    }
    /**
     * Proceso para el borrado de registro.
     * @param id
     * @param id_user
     * @return numero de registros dados de alta.
     */
    public int deleteRecord(int id,int id_user)
    {
        System.out.println("deleteRecord("+id+")");
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryDeleteUser);
            ps.setInt(1, id_user);
            ps.setInt(2, id); 
            deleteCont=ps.executeUpdate();
            ps.close();
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
        return deleteCont;   
    }
    /**
     * Proceso para la modificación de registro.
     * @param no_termo
     * @param no_sello
     * @param lector_temp 
     * @param lote
     * @param fecha
     * @param id_cliente 
     * @param id_user
     * @param id
     * @return numero de registros dados de alta.
     */
    public Transporte  insertRecord(String no_termo,String no_sello,String lector_temp,String lote,String fecha,int id_cliente,int id_user,int idtransporte)
     {
        System.out.println("insertRecord("+no_termo+","+no_sello+","+lector_temp+","+lote+","+fecha+","+id_cliente+","+id_user+")");
        int insertCont=0;
        Transporte bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreateUser);
            ps.setString(1, no_termo);
            ps.setString(2, no_sello);
            ps.setString(3, lector_temp); 
            ps.setString(4, lote);
            ps.setString(5, fecha);
            ps.setInt(6, id_cliente); 
            ps.setInt(7, id_user);
            ps.setInt(8, id_user);
            ps.setInt(9, idtransporte);
            insertCont=ps.executeUpdate();
            if(insertCont==1)
            {
                bean  =  getMax();
            }
            ps.close();
            int updateRecord = updateRecord(bean.getId());
            bean  =  getOne(bean.getId());
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
        return bean;
    }
}
