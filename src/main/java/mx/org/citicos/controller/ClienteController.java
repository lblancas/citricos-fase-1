/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Cliente;
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
public class ClienteController extends Conexion
{
    
    String querySelect="SELECT  ID,PAIS,ESTADO,NOMBRE,APELLIDOS,ID_ACTIVO,NUMERO FROM CC_CLIENTES  WHERE ID_ACTIVO =1";
    String querySelectTR="SELECT  ID,PAIS,ESTADO,NOMBRE,APELLIDOS,ID_ACTIVO,NUMERO FROM CC_CLIENTES  ";
    String queryMax="SELECT MAX(ID) ID FROM CC_CLIENTES";
    String querySelectOne="SELECT  ID,PAIS,ESTADO,NOMBRE,APELLIDOS,ID_ACTIVO,NUMERO FROM CC_CLIENTES  WHERE ID_ACTIVO =1 AND ID =  ";
    String queryUpdate="UPDATE CC_CLIENTES SET PAIS=?,ESTADO=?,NOMBRE=?,APELLIDOS=?,MODIFICADOPOR=? WHERE ID = ? ";
    String queryUpdateNumber="UPDATE CC_CLIENTES SET  NUMERO = CONCAT(CONCAT('CTE',DATE_FORMAT(NOW(),'%Y%m%d')),LPAD(ID,9,'0')) WHERE ID = ? ";
    String queryDeleteUser="UPDATE CC_CLIENTES SET ID_ACTIVO = 3,MODIFICADOPOR=?   WHERE ID = ?  ";
    String queryCreateUser="INSERT INTO CC_CLIENTES  (PAIS,ESTADO,NOMBRE,APELLIDOS,CREADOPOR,MODIFICADOPOR) VALUES(?,?,?,?,?,?)";
     
    private Cliente getMax()
    {
        Cliente bean=new Cliente();
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
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
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
    public Cliente getOne(int id)
    {
        Cliente bean=new Cliente();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+id);
            if(rs.next())
            { 
                bean.setId(rs.getInt("ID"));
                bean.setPais(rs.getString("PAIS"));
                bean.setEstado(rs.getString("ESTADO"));
                bean.setNombre(rs.getString("NOMBRE"));
                bean.setApellidos(rs.getString("APELLIDOS"));
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
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bean;
    }
    public ArrayList<Cliente> getAll()
    {
        ArrayList<Cliente> l=new ArrayList<Cliente>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                Cliente obj=new Cliente();
                obj.setId(rs.getInt("ID"));
                obj.setPais(rs.getString("PAIS"));
                obj.setEstado(rs.getString("ESTADO"));
                obj.setNombre(rs.getString("NOMBRE"));
                obj.setApellidos(rs.getString("APELLIDOS"));
                obj.setNumero(rs.getString("NUMERO")); 
                l.add(obj);
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
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    public ArrayList<Cliente> getAll(HashMap m)
    {
        ArrayList<Cliente> l=new ArrayList<Cliente>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectTR);
            while(rs.next())
            {
                Cliente obj=new Cliente();
                obj.setId(rs.getInt("ID"));
                obj.setPais(rs.getString("PAIS"));
                obj.setEstado(rs.getString("ESTADO"));
                obj.setNombre(rs.getString("NOMBRE"));
                obj.setApellidos(rs.getString("APELLIDOS"));
                obj.setNumero(rs.getString("NUMERO")); 
                obj.setActivos((String) m.get((new Integer(rs.getInt("ID_ACTIVO")))));
                l.add(obj);
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
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    /**
     * Proceso para la modificación de registro.
     * @param pais
     * @param estado
     * @param nombre
     * @param apellidos
     * @param id_user
     * @param id
     * @return numero de registros dados de alta.
     */
    public int updateRecord(String pais,String estado,String nombre,String apellidos,int id_user,int id)
     {  
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setString(1, pais);
            ps.setString(2, estado);
            ps.setString(3, nombre);
            ps.setString(4, apellidos);
            ps.setInt(5, id_user);
            ps.setInt(6, id);
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
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
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
    public int deleteRecord(int id,int usuario)
    {
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryDeleteUser);
            ps.setInt(1, usuario);
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
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return deleteCont;   
    }
    /**
     * Proceso para el guardado de registro.
     * @param pais
     * @param estado
     * @param nombre
     * @param apellidos
     * @param idUsuario
     * @return numero de registros dados de alta.
     */
    public Cliente  insertRecord(String pais,String estado,String nombre,String apellidos,int idUsuario)
    {
        int insertCont=0;
        Cliente bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreateUser);
            ps.setString(1, pais);
            ps.setString(2, estado);
            ps.setString(3, nombre);
            ps.setString(4, apellidos);
            ps.setInt   (5, idUsuario);
            ps.setInt   (6, idUsuario);
            insertCont=ps.executeUpdate();
            if(insertCont==1)
            {
                bean  =  getMax();
            }
            ps.close();
            updateRecord(bean.getId());
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
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bean;
    }
}
