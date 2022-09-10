/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Tarima;
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
public class AgronomoController extends Conexion
{ 
    String querySelect="SELECT ID, DESCRIPCION  FROM CC_AGRONOMO WHERE ID_ACTIVO=1 ";
    String querySelectTR="SELECT ID,ID_ACTIVO, DESCRIPCION  FROM CC_AGRONOMO   ";
    String queryMax="SELECT MAX(ID) ID FROM CC_AGRONOMO";
    String querySelectOne="SELECT ID, DESCRIPCION  FROM CC_AGRONOMO  WHERE ID = ";
    String queryDelete="UPDATE CC_AGRONOMO   SET ID_ACTIVO =3,  modificadopor =?  WHERE  ID = ? ";
    String queryUpdate="UPDATE CC_AGRONOMO SET DESCRIPCION = ? WHERE ID = ?";
    String queryCreate="INSERT INTO CC_AGRONOMO (DESCRIPCION)  VALUES (?) ";
    String queryUpdateUsr="UPDATE CC_AGRONOMO SET DESCRIPCION = ?, MODIFICADOPOR =? WHERE ID = ?";
    String queryCreateUsr="INSERT INTO CC_AGRONOMO (DESCRIPCION,CREADOPOR,MODIFICADOPOR)  VALUES (?,?,?) ";
       
    public Tarima getMax()
    {
        Tarima bean=new Tarima();
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
    public Tarima getOne(int i)
    {
        Tarima bean=new Tarima();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+i);
            if(rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setNombre(rs.getString("DESCRIPCION"));
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
                    Logger.getLogger(AgronomoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bean;
    }
    public ArrayList<Tarima> getAll()
    {
        ArrayList<Tarima> l=new ArrayList<Tarima>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                Tarima obj=new Tarima();
                obj.setId(rs.getInt("ID"));
                obj.setNombre(rs.getString("DESCRIPCION"));
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
    public ArrayList<Tarima> getAll(HashMap m)
    {
        ArrayList<Tarima> l=new ArrayList<Tarima>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectTR);
            while(rs.next())
            {
                Tarima obj=new Tarima();
                obj.setId(rs.getInt("ID"));
                obj.setNombre(rs.getString("DESCRIPCION"));
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
                    Logger.getLogger(AgronomoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    /**
     * Proceso para la modificación de registro.
     * @param nombre
     * @param id
     * @param usr
     * @return numero de registros dados de alta.
     */
    public int updateRecordUsr(String nombre,int id,int usr)
     {  
        int updeteCont=0;
        Connection connection=null;
        try 
        { //UPDATE CC_AGRONOMO SET DESCRIPCION = ?, MODIFICADOPOR =? WHERE ID = ?
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdateUsr);
            ps.setString(1, nombre);
            ps.setInt(2, usr );
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
                    Logger.getLogger(AgronomoController.class.getName()).log(Level.SEVERE, null, ex);
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
        int deleteCont=0;
        Connection connection=null;
        try 
        {//UPDATE CC_AGRONOMO   SET ID_ACTIVO =3 WHERE modificadopor =?, ID = ? 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryDelete);
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
                    Logger.getLogger(AgronomoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return deleteCont;   
    }
    public Tarima  insertRecordUsr(String nombre,int usr)
    {
        int insertCont=0;
        Tarima bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreateUsr);
            ps.setString(1, nombre);
            ps.setInt(2, usr);
            ps.setInt(3, usr);
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
                    Logger.getLogger(AgronomoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bean;
    }
}
