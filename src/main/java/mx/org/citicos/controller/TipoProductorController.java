/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Tipop;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap; 
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author BID
 */
public class TipoProductorController  extends Conexion
{ 
    String querySelect="SELECT ID, NOMBRE  FROM CC_TIPO_PRODUCTOR  "; 
    String queryMax="SELECT MAX(ID) ID FROM CC_TIPO_PRODUCTOR";
    String querySelectOne="SELECT ID, NOMBRE  FROM CC_TIPO_PRODUCTOR  WHERE ID = ";
    String queryDelete="DELETE CC_TIPO_PRODUCTOR  WHERE ID = ? ";
    String queryUpdate="UPDATE CC_TIPO_PRODUCTOR SET NOMBRE = ? WHERE ID = ?";
    String queryCreate="INSERT INTO CC_TIPO_PRODUCTOR (NOMBRE)  VALUES (?) "; 
    

    
    public Tipop getMax()
    {
        Tipop bean=new Tipop();
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
    public Tipop getOne(int i)
    {
        Tipop bean=new Tipop();
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
    public ArrayList<Tipop> getAll()
    {
        ArrayList<Tipop> l=new ArrayList<Tipop>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                Tipop obj=new Tipop();
                obj.setId(rs.getInt("ID"));
                obj.setNombre(rs.getString("NOMBRE"));
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
     */
    public int updateRecord(String nombre,int id)
     {  
        System.out.println("updateRecord("+nombre+","+ id+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setString(1, nombre);
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
    public int deleteRecord(int id)
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
     */
    public Tipop  insertRecord(String nombre)
    {
        System.out.println("insertRecord("+nombre+")");
        int insertCont=0;
        Tipop bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreate);
            ps.setString(1, nombre);
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
