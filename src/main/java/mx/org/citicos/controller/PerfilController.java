/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Perfil;
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
public class PerfilController   extends Conexion
{ 
    String querySelect="SELECT ID, NOMBRE, PAGINA  FROM CC_PERFIL WHERE ID_ACTIVO =1";
    String querySelectTR="SELECT ID, NOMBRE,ID_ACTIVO, PAGINA  FROM CC_PERFIL";
    String querySelectActivo="SELECT CP.ID, CP.NOMBRE, PAGINA FROM CC_PERFIL CP,CC_ACTIVO CA WHERE CP.ID_ACTIVO = CA.ID AND UPPER(CA.NOMBRE)='ACTIVO'";
    String queryMax="SELECT MAX(ID) ID FROM CC_PERFIL";
    String querySelectOne="SELECT ID, NOMBRE, PAGINA  FROM CC_PERFIL  WHERE ID = ";
    String queryDelete="UPDATE CC_PERFIL   SET ID_ACTIVO =3 WHERE ID = ? ";
    String queryUpdate="UPDATE CC_PERFIL SET NOMBRE = ?,  PAGINA =? WHERE ID = ?";
    String queryCreate="INSERT INTO CC_PERFIL (NOMBRE,PAGINA)  VALUES (?,?) ";
    String queryUpdateUsr="UPDATE CC_PERFIL SET NOMBRE = ?,  PAGINA =?  WHERE ID = ?";
    String queryCreateUsr="INSERT INTO CC_PERFIL (NOMBRE,  PAGINA )  VALUES (?,?) ";
    

    
    public Perfil getMax()
    {
        Perfil bean=new Perfil();
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
    public Perfil getOne(int i)
    {
        Perfil bean=new Perfil();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+i);
            if(rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setNombre(rs.getString("NOMBRE"));
                bean.setPagina(rs.getString("PAGINA"));
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
    
    public ArrayList<Perfil> getAllActivo()
    {
        ArrayList<Perfil> l=new ArrayList<Perfil>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectActivo);
            while(rs.next())
            {
                Perfil obj=new Perfil();
                obj.setId(rs.getInt("ID"));
                obj.setNombre(rs.getString("NOMBRE"));
                obj.setPagina(rs.getString("PAGINA"));
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    /**
     * Servicio para obtener los perfiles
     * @return 
     */
    
    public ArrayList<Perfil> getAll()
    {
        ArrayList<Perfil> l=new ArrayList<Perfil>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                Perfil obj=new Perfil();
                obj.setId(rs.getInt("ID"));
                obj.setNombre(rs.getString("NOMBRE"));
                obj.setPagina(rs.getString("PAGINA"));
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    public ArrayList<Perfil> getAll(HashMap m)
    {
        ArrayList<Perfil> l=new ArrayList<Perfil>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectTR);
            while(rs.next())
            {
                Perfil obj=new Perfil();
                obj.setId(rs.getInt("ID"));
                obj.setNombre(rs.getString("NOMBRE"));
                obj.setPagina(rs.getString("PAGINA"));
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    
    /**
     * Proceso para la modificación de registro.
     * @return numero de registros dados de alta.
     * @param nombre
     * @param pagina
     * @param id
     * @param usr
     * @return valor
     */
    public int updateRecordUsr(String nombre,String pagina,int id,int usr)
     {  
        System.out.println("updateRecordUsr("+nombre+","+ id+","+ usr+ ")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdateUsr);
            ps.setString(1, nombre);
            ps.setString(2, pagina);
            ps.setInt(3, id);
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
     * @return numero de registros dados de alta.
     */
    public int deleteRecord(int id,int usuario)
    {
        System.out.println("deleteRecord("+id+")");
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryDelete);
            ps.setInt(1, id);
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
     * @return numero de registros dados de alta.
     * @param nombre
     * @param pagina
     * @param usr
     */
    public Perfil  insertRecordUsr(String nombre,String pagina,int usr)
    {
        System.out.println("insertRecord("+nombre+","+usr+")");
        int insertCont=0;
        Perfil bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreateUsr);
            ps.setString(1, nombre);
            ps.setString(2, pagina);
            insertCont=ps.executeUpdate();
            if(insertCont==1)
            {
                bean  =  getMax();
            }
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
        return bean;
    }
}
 