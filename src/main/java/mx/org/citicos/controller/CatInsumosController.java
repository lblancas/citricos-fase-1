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
import mx.org.citricos.entity.CatInsumos;

/**
 *
 * @author luisa
 */
public class CatInsumosController   extends Conexion
{
    String querySelect="SELECT ID,CONCEPTO,MONTO,CANTIDAD,CLAVE FROM CC_CAT_INSUMOS  WHERE ID_ACTIVO = 1 ";
    String queryMax="SELECT MAX(ID) ID FROM CC_CAT_INSUMOS";
    String querySelectOne="SELECT ID,CONCEPTO,MONTO,CANTIDAD,CLAVE FROM CC_CAT_INSUMOS  WHERE ID = ";
    String queryDelete="UPDATE CC_CAT_INSUMOS   SET ID_ACTIVO =3, MODIFICADO_POR=? WHERE ID = ? ";
    String queryUpdate="UPDATE CC_CAT_INSUMOS SET CONCEPTO=?,MONTO=?,CANTIDAD=?,CLAVE=?,MODIFICADO_POR = ? WHERE ID = ?";
    String queryCreateUsr="INSERT INTO CC_CAT_INSUMOS (CONCEPTO,MONTO,CANTIDAD,CLAVE,CREADO_POR,MODIFICADO_POR)  VALUES (?,?,?,?,?,?) ";
    

    
    public CatInsumos getMax()
    {
        CatInsumos bean=new CatInsumos();
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
    public CatInsumos getOne(int i)
    {
        CatInsumos obj=new CatInsumos();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+i);
            if(rs.next())
            {
                obj.setId(rs.getInt("ID"));
                obj.setConcepto(rs.getString("CONCEPTO"));
                obj.setClave(rs.getString("CLAVE"));
                obj.setMonto(rs.getFloat("MONTO"));
                obj.setCantidad(rs.getInt("CANTIDAD"));
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
        return obj;
    }
    public ArrayList<CatInsumos> getAll()
    {
        ArrayList<CatInsumos> l=new ArrayList<CatInsumos>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                CatInsumos obj=new CatInsumos();
                obj.setId(rs.getInt("ID"));
                obj.setConcepto(rs.getString("CONCEPTO"));
                obj.setClave(rs.getString("CLAVE"));
                obj.setMonto(rs.getFloat("MONTO"));
                obj.setCantidad(rs.getInt("CANTIDAD")); 
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
     * Metodo que modifica insumos
     * @param concepto
     * @param monto
     * @param cantidad
     * @param clave
     * @param usuario
     * @param id
     * @return 
     */
    public int updateRecordUsr(String concepto,float monto,int cantidad,String clave,int usuario,int id)
     {
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setString(1, concepto);
            ps.setFloat(2, monto);
            ps.setInt(3, cantidad);
            ps.setString(4, clave);            
            ps.setInt(5, usuario);
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return updeteCont;
    }
    
    /**
     * Metodo par borrar insumo
     * @param id
     * @return 
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
     * Metodo para inserte insumos
     * @param concepto
     * @param monto
     * @param cantidad
     * @param clave
     * @param usuario
     * @return 
     */
    public CatInsumos  insertRecordUsr(String concepto,float monto,int cantidad,String clave,int usuario)
    {
        int insertCont=0;
        CatInsumos bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreateUsr);
            ps.setString(1, concepto);
            ps.setFloat(2, monto);
            ps.setInt(3, cantidad);
            ps.setString(4, clave);
            ps.setInt(5, usuario);
            ps.setInt(6, usuario);
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

