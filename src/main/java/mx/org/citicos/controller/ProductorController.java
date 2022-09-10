/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Productor;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author BID
 */
public class ProductorController  extends Conexion
{
    String querySelect="SELECT A.ID,A.RFC,A.NOMBRE,A.DIRECCION,A.TELEFONO1,A.TELEFONO2,A.TIPO, B.NOMBRE TIPOS  "
            + "FROM PRODUCTORES A, CC_TIPO_PRODUCTOR B WHERE  A.TIPO = B.ID  AND ID_ACTIVO =1  ";
    String queryMax="SELECT MAX(ID) ID FROM CC_CAT_CALIDAD";
    String querySelectOne="SELECT A.ID,A.RFC,A.NOMBRE,A.DIRECCION,A.TELEFONO1,A.TELEFONO2,A.TIPO, B.NOMBRE TIPOS  "
            + "FROM PRODUCTORES A, CC_TIPO_PRODUCTOR B WHERE  A.TIPO = B.ID  AND ID_ACTIVO =1 AND A.ID = ";
    String queryDelete="UPDATE PRODUCTORES   SET ID_ACTIVO =3, MODIFICADOPOR=? WHERE ID = ? ";
    String queryUpdate="UPDATE PRODUCTORES SET RFC=?,NOMBRE=?,DIRECCION=?,TELEFONO1=?,TELEFONO2=?,TIPO=?,MODIFICADOPOR = ? WHERE ID = ?";
    String queryCreate="INSERT INTO PRODUCTORES (RFC,NOMBRE,DIRECCION,TELEFONO1,TELEFONO2,TIPO,MODIFICADOPOR)  VALUES (?,?,?,?,?,?,?) ";    
    public Productor getMax()
    {
        Productor bean=new Productor();
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
    public Productor getOne(int i)
    {
        Productor bean=new Productor();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+i);
            if(rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setRfc(rs.getString("RFC"));
                bean.setNombre(rs.getString("NOMBRE"));
                bean.setDireccion(rs.getString("DIRECCION"));
                bean.setTelefono1(rs.getString("TELEFONO1"));
                bean.setTelefono2(rs.getString("TELEFONO2"));
                bean.setTipo(rs.getInt("TIPO"));
                bean.setRfc( rs.getString("RFC"));
                bean.setTipos(rs.getString("TIPOS"));
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
    public ArrayList<Productor> getAll()
    {
        ArrayList<Productor> l=new ArrayList<Productor>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                Productor bean=new Productor();
                bean.setId(rs.getInt("ID"));
                bean.setRfc(rs.getString("RFC"));
                bean.setNombre(rs.getString("NOMBRE"));
                bean.setDireccion(rs.getString("DIRECCION"));
                bean.setTelefono1(rs.getString("TELEFONO1"));
                bean.setTelefono2(rs.getString("TELEFONO2"));
                bean.setTipo(rs.getInt("TIPO"));
                bean.setTipos(rs.getString("TIPOS"));
                bean.setRfc( rs.getString("RFC"));
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
     * Metodo para modificar registro
     * @param rfc
     * @param nombre
     * @param direccion
     * @param telefono1
     * @param telefono2
     * @param tipo
     * @param usuario
     * @param id
     * @return 
     */
    //UPDATE PRODUCTORES SET RFC=?,NOMBRE=?,DIRECCION=?,TELEFONO1=?,TELEFONO2=?,TIPO=?,MODIFICADOPOR = ? WHERE ID = ?  
    public int updateRecord(String rfc,String nombre,String direccion,String telefono1,String telefono2,int tipo,int usuario,int id)
     {  
        System.out.println("updateRecord("+nombre+","+ id+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setString(1, rfc);
            ps.setString(2, nombre);
            ps.setString(3, direccion);
            ps.setString(4, telefono1);
            ps.setString(5, telefono2);
            ps.setInt(6, tipo);
            ps.setInt(7, usuario);
            ps.setInt(8, id);
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
     * Metodo para insertar regitro de productor
     * @param rfc
     * @param nombre
     * @param direccion
     * @param telefono1
     * @param telefono2
     * @param tipo
     * @param usuario
     * @return 
     * INSERT INTO PRODUCTORES (RFC,NOMBRE,DIRECCION,TELEFONO1,TELEFONO2,TIPO,MODIFICADOPOR)  VALUES (?,?,?,?,?,?,?) 
     */
    public Productor  insertRecord(String rfc,String nombre,String direccion,String telefono1,String telefono2,int tipo,int usuario,int agronomo)
    {
        System.out.println("insertRecord("+nombre+")");
        int insertCont=0;
        Productor bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreate);
            ps.setString(1, rfc);
            ps.setString(2, nombre);
            ps.setString(3, direccion);
            ps.setString(4, telefono1);
            ps.setString(5, telefono2);
            ps.setInt(6, tipo);
            ps.setInt(7,usuario);
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
