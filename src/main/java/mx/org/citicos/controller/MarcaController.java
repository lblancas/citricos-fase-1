/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Marca;
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
public class MarcaController   extends Conexion
{ 
    String querySelect="SELECT ID, DESCRIPCION  FROM CC_CAT_MARCA WHERE ID_ACTIVO =1";
    String querySelectTR="SELECT ID, DESCRIPCION,ID_ACTIVO  FROM CC_CAT_MARCA ";
    String queryMax="SELECT MAX(ID) ID FROM CC_CAT_MARCA";
    String querySelectOne="SELECT ID, DESCRIPCION  FROM CC_CAT_MARCA  WHERE ID = ";
    String queryDelete="UPDATE CC_CAT_MARCA   SET ID_ACTIVO =3 , MODIFICADOPOR=? WHERE ID = ? ";
    String queryUpdate="UPDATE CC_CAT_MARCA SET DESCRIPCION = ? WHERE ID = ?";
    String queryCreate="INSERT INTO CC_CAT_MARCA (DESCRIPCION)  VALUES (?) ";
    String queryUpdateUsr="UPDATE CC_CAT_MARCA SET DESCRIPCION = ?, MODIFICADOPOR =? WHERE ID = ?";
    String queryCreateUsr="INSERT INTO CC_CAT_MARCA (DESCRIPCION,CREADOPOR,MODIFICADOPOR)  VALUES (?,?,?) ";
    

    
    public Marca getMax()
    {
        Marca bean=new Marca();
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
    public Marca getOne(int i)
    {
        Marca bean=new Marca();
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bean;
    }
    public ArrayList<Marca> getAll()
    {
        ArrayList<Marca> l=new ArrayList<Marca>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                Marca obj=new Marca();
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
    public ArrayList<Marca> getAll(HashMap m)
    {
        ArrayList<Marca> l=new ArrayList<Marca>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectTR);
            while(rs.next())
            {
                Marca obj=new Marca();
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    } 
    /**
     * Proceso para la modificaci??n de registro.
     * @return numero de registros dados de alta.
     */
    public int updateRecordUsr(String nombre,int id,int usr)
     {
        System.out.println("updateRecordUsr("+nombre+","+ id+","+ usr+ ")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdateUsr);
            ps.setString(1, nombre);
            ps.setInt(2, usr);
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return deleteCont;   
    }
     
    /**
     * Proceso para el guardado de registro.
     * @return numero de registros dados de alta.
     */
    public Marca  insertRecordUsr(String nombre,int usr)
    {
        System.out.println("insertRecord("+nombre+","+usr+")");
        int insertCont=0;
        Marca bean=null;
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bean;
    }
}
