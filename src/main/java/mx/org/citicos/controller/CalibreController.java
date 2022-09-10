/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Calibre; 
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
 * * @author Luis Adrian Blancas Bahena
 */
public class CalibreController   extends Conexion
{ 
    String querySelect="SELECT ID, DESCRIPCION  FROM CC_CALIBRE WHERE ID_ACTIVO =1 ";
    String querySelectTR="SELECT ID,ID_ACTIVO, DESCRIPCION  FROM CC_CALIBRE ";
    String queryMax="SELECT MAX(ID) ID FROM CC_CALIBRE";
    String querySelectOne="SELECT ID, DESCRIPCION  FROM CC_CALIBRE  WHERE ID = ";
    String queryDelete="UPDATE CC_CALIBRE   SET ID_ACTIVO =3, MODIFICADOPOR=? WHERE ID = ? ";
    String queryUpdate="UPDATE CC_CALIBRE SET DESCRIPCION = ? WHERE ID = ?";
    String queryCreate="INSERT INTO CC_CALIBRE (DESCRIPCION)  VALUES (?) ";
    String queryUpdateUsr="UPDATE CC_CALIBRE SET DESCRIPCION = ?, MODIFICADOPOR =? WHERE ID = ?";
    String queryCreateUsr="INSERT INTO CC_CALIBRE (DESCRIPCION,CREADOPOR,MODIFICADOPOR)  VALUES (?,?,?) ";
     
    String queryUpdateS="UPDATE CC_CALIBRE SET ID_SUBCALIBRE = ? WHERE ID = ?";
    
    public Calibre getMax()
    {
        Calibre bean=new Calibre();
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bean;
    }
    public Calibre getOne(int i)
    {
        Calibre bean=new Calibre();
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bean;
    }
    public ArrayList<Calibre> getAll()
    {
        ArrayList<Calibre> l=new ArrayList<Calibre>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                Calibre obj=new Calibre();
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    public ArrayList<Calibre> getAll(HashMap m)
    {
        ArrayList<Calibre> l=new ArrayList<Calibre>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectTR);
            while(rs.next())
            {
                Calibre obj=new Calibre();
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    /**
     * Proceso para la modificación de registro.
     * @return numero de registros dados de alta.
     */
    public int updateRecordsub(int sub,int id)
     {  
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdateS);
            ps.setInt(1, sub);
            ps.setInt(2, id);
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return updeteCont;
    }
    /**
     * Proceso para la modificación de registro.
     * @return numero de registros dados de alta.
     */
    public int updateRecordUsr(String nombre,int id,int usr)
     {  
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdateUsr);
            ps.setString(1, nombre);
            ps.setInt(2, id);
            ps.setInt(3, usr);
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
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
        {
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return deleteCont;   
    }
    /**
     * Proceso para el guardado de registro.
     * @return numero de registros dados de alta.
     */
    public Calibre  insertRecordUsr(String nombre,int usr)
    {
        int insertCont=0;
        Calibre bean=null;
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
                SubCalibreController sctr=new SubCalibreController();
                sctr.insertRecord(nombre, bean.getId());
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bean;
    }
}
