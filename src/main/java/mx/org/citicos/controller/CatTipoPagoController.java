/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.CatTipoPago;

/**
 *
 * @author luisa
 */
public class CatTipoPagoController extends Conexion
{
    String querySelect="SELECT ID, DESCRIPCION,TIPO  FROM CC_CAT_TIPO_PAGO WHERE ID_ACTIVO = 1 ";
    String querySelectTipo="SELECT ID, DESCRIPCION,TIPO  FROM CC_CAT_TIPO_PAGO WHERE ID_ACTIVO = 1 AND TIPO =";
    String queryMax="SELECT MAX(ID) ID FROM CC_CAT_TIPO_PAGO";
    String querySelectOne="SELECT ID, DESCRIPCION,TIPO  FROM CC_CAT_TIPO_PAGO  WHERE ID = ";
    String queryDelete="UPDATE CC_CAT_TIPO_PAGO   SET ID_ACTIVO =3, MODIFICADOPOR=?  WHERE ID = ? ";  
    String queryUpdateUsr="UPDATE CC_CAT_TIPO_PAGO SET DESCRIPCION = ?,TIPO=?, MODIFICADOPOR =? WHERE ID = ?";
    String queryCreateUsr="INSERT INTO CC_CAT_TIPO_PAGO (DESCRIPCION,TIPO,CREADOPOR,MODIFICADOPOR)  VALUES (?,?,?,?) ";
    

    
    public CatTipoPago getMax()
    {
        CatTipoPago bean=new CatTipoPago();
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
     * Metodo para obtener un tipo pago
     * @param i
     * @return 
     */
    public CatTipoPago getOne(int i)
    {
        CatTipoPago bean=new CatTipoPago();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            //SELECT ID, DESCRIPCION,TIPO  FROM CC_CAT_TIPO_PAGO  WHERE ID =
            ResultSet rs=st.executeQuery(querySelectOne+i);
            if(rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setNombre(rs.getString("DESCRIPCION"));
                bean.setTipo(rs.getInt("TIPO"));
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
    public ArrayList<CatTipoPago> getAll(int tipo)
    {
        ArrayList<CatTipoPago> l=new ArrayList<CatTipoPago>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectTipo+tipo);
            while(rs.next())
            {
                CatTipoPago obj=new CatTipoPago();
                obj.setId(rs.getInt("ID"));
                obj.setNombre(rs.getString("DESCRIPCION"));
                obj.setTipo(rs.getInt("TIPO"));
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
     * get All.
     * @return ARRAYlIST 
     */
    public ArrayList<CatTipoPago> getAll()
    {
        ArrayList<CatTipoPago> l=new ArrayList<CatTipoPago>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                CatTipoPago obj=new CatTipoPago();
                obj.setId(rs.getInt("ID"));
                obj.setNombre(rs.getString("DESCRIPCION"));
                obj.setTipo(rs.getInt("TIPO"));
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
    public int updateRecord(String nombre,int tipo,int id,int usr)
     {  
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdateUsr);
            ps.setString(1, nombre);
            ps.setInt(2, tipo);
            ps.setInt(3, usr);
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
    * Metodo para insertar
    * @param nombre
    * @param usr
    * @param tipo
    * @return 
    */
    public CatTipoPago  insertRecord(String nombre,int tipo,int usr)
    {
        int insertCont=0;
        CatTipoPago bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreateUsr);
            ps.setString(1, nombre);
            ps.setInt(2,tipo);
            ps.setInt(3, usr);
            ps.setInt(4, usr);
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
