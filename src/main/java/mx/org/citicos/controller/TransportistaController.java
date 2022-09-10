/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;  
import mx.org.citricos.entity.Transportista;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author BID
 */
public class TransportistaController extends Conexion
{
    
    String querySelect="SELECT  ID,NOMBRE,PLACAS,NUMERO_ECONOMICO,CREADOPOR,MODIFICADOPOR,ID_ACTIVO,NUMERO FROM CC_TRANSPORTISTA  WHERE ID_ACTIVO =1";
    String querySelectTR="SELECT  ID,NOMBRE,PLACAS,NUMERO_ECONOMICO,CREADOPOR,MODIFICADOPOR,ID_ACTIVO,NUMERO FROM CC_TRANSPORTISTA ";
    String queryMax="SELECT MAX(ID) ID FROM CC_TRANSPORTISTA";
    String querySelectOne="SELECT  ID,NOMBRE,PLACAS,NUMERO_ECONOMICO,CREADOPOR,MODIFICADOPOR,ID_ACTIVO,NUMERO FROM CC_TRANSPORTISTA  WHERE ID_ACTIVO =1 AND ID =  ";
    String queryUpdate="UPDATE CC_TRANSPORTISTA SET NOMBRE=?,PLACAS=?,NUMERO_ECONOMICO=?,MODIFICADOPOR=? WHERE ID = ? ";
    String queryUpdateNumber="UPDATE CC_TRANSPORTISTA SET  NUMERO = CONCAT(CONCAT('TTA',DATE_FORMAT(NOW(),'%Y%m%d')),LPAD(ID,9,'0')) WHERE ID = ? ";
    String queryDeleteUser="UPDATE CC_TRANSPORTISTA SET ID_ACTIVO = 3 , MODIFICADOPOR =? WHERE ID = ?  ";
    String queryCreateUser="INSERT INTO CC_TRANSPORTISTA(NOMBRE,PLACAS,NUMERO_ECONOMICO,CREADOPOR,MODIFICADOPOR) VALUES(?,?,?,?,?)";
    String queryUpdateTarimas =" update  cc_tarima  set id_tt = ? where  id= ? ";
    private Transportista getMax()
    {
        Transportista bean=new Transportista();
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
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
    public Transportista getOne(int id)
    {
        Transportista bean=new Transportista();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+id);
            if(rs.next())
            { 
                bean.setId(rs.getInt("ID"));
                bean.setNombre(rs.getString("NOMBRE"));
                bean.setPlacas(rs.getString("PLACAS"));
                bean.setNumero_economico(rs.getString("NUMERO_ECONOMICO")); 
                bean.setNumero(rs.getString("NUMERO"));                 
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bean;
    }
    public ArrayList<Transportista> getAll()
    {
        ArrayList<Transportista> l=new ArrayList<Transportista>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                Transportista bean=new Transportista();
                bean.setId(rs.getInt("ID"));
                bean.setNombre(rs.getString("NOMBRE"));
                bean.setPlacas(rs.getString("PLACAS"));
                bean.setNumero_economico(rs.getString("NUMERO_ECONOMICO")); 
                bean.setNumero(rs.getString("NUMERO"));       
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    public ArrayList<Transportista> getAll(HashMap m)
    {
        ArrayList<Transportista> l=new ArrayList<Transportista>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectTR);
            while(rs.next())
            {
                Transportista bean=new Transportista();
                bean.setId(rs.getInt("ID"));
                bean.setNombre(rs.getString("NOMBRE"));
                bean.setPlacas(rs.getString("PLACAS"));
                bean.setNumero_economico(rs.getString("NUMERO_ECONOMICO")); 
                bean.setNumero(rs.getString("NUMERO"));       
                bean.setActivos((String) m.get((new Integer(rs.getInt("ID_ACTIVO")))));
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    /**
     * Proceso para la modificación de registro.
     * @param nombre
     * @param placas
     * @param numero_economico 
     * @param id_user
     * @param id
     * @return numero de registros dados de alta.
     */
    public int updateAsignaTransporteTarimaATarima(int idTransporteTarima,int idTarima)
     {  
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdateTarimas); 
            ps.setInt(1, idTransporteTarima);
            ps.setInt(2, idTarima);
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return updeteCont;
    }
    /**
     * Proceso para la modificación de registro.
     * @param nombre
     * @param placas
     * @param numero_economico 
     * @param id_user
     * @param id
     * @return numero de registros dados de alta.
     */
    public int updateRecord(String nombre,String placas,String numero_economico,int id_user,int id)
     {  
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setString(1, nombre);
            ps.setString(2, placas);
            ps.setString(3, numero_economico); 
            ps.setInt(4, id_user);
            ps.setInt(5, id);
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return deleteCont;   
    }
    /**
     * Proceso para el guardado de registro.
     * @param nombre
     * @param placas
     * @param numero_economico 
     * @param id_user
     * @return numero de registros dados de alta.
     */
    public Transportista  insertRecord(String nombre,String placas,String numero_economico,int id_user)
     {  
        int insertCont=0;
        Transportista bean=null;
        Connection connection=null;
        try 
        { 
        	connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreateUser);
            ps.setString(1, nombre);
            ps.setString(2, placas);
            ps.setString(3, numero_economico); 
            ps.setInt   (4, id_user);
            ps.setInt   (5, id_user);
            insertCont=ps.executeUpdate();
            if(insertCont==1)
            {
                bean  =  getMax();
            }
            ps.close();
            updateRecord(bean.getId());
            bean= getOne(bean.getId());
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bean;
    }
}
