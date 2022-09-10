/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion; 
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import mx.org.citricos.entity.Precios;
import mx.org.citricos.entity.Productores;
/**
 *
 * @author BID
 */
public class ProductoresController  extends Conexion
{
    String querySelect="select id ,  PAIS,  ESTADO,upper(concat(NOMBRE,' ',apellido_p,' ',apellido_m)) nombre FROM cc_productor  ";
    String queryMax="SELECT MAX(ID) ID FROM CC_PRODUCTOR ";
    String querySelectOne="SELECT ID, PAIS,ESTADO,NOMBRE,APELLIDO_P,APELLIDO_M,NUMERO  FROM CC_PRODUCTOR WHERE ACTIVO  =1  AND ID =  ";
    String queryDelete="UPDATE CC_PRODUCTOR SET MODIFICADO =NOW(), ID_ACTIVO =3, MODIFICADOPOR=? WHERE ID = ? ";
    String queryUpdate="UPDATE CC_PRODUCTOR SET MODIFICADO =NOW(),PAIS=?,ESTADO=?,NOMBRE=?,APELLIDO_P=?,APELLIDO_M=?,MODIFICADOPOR=? WHERE ID = ?";
    String queryCreate="INSERT INTO CC_PRODUCTOR"
            + "(ID, PAIS,ESTADO,NOMBRE,APELLIDO_P,APELLIDO_M,NUMERO,MODIFICADO,CREADO,MODIFICADOPOR,CREADOPOR)  "
            + "VALUES (?,?,?,?,?,?,?,now(),NOW(),?,?) ";    
    public Productores getMax()
    {
        Productores bean=new Productores();
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
    public Productores getOne(int i)
    {
        Productores bean=new Productores();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+i);
            if(rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setPais(rs.getString("PAIS"));
                bean.setEstado(rs.getString("ESTADO"));
                bean.setNombre(rs.getString("NOMBRE"));
                bean.setPaterno(rs.getString("APELLIDO_P"));
                bean.setMaterno(rs.getString("APELLIDO_M"));
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
    public ArrayList<Productores> getAll()
    {
        ArrayList<Productores> l=new ArrayList<Productores>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                Productores bean=new Productores();
                bean.setId(rs.getInt("ID"));
                bean.setNombre(rs.getString("NOMBRE"));
                bean.setPais(rs.getString("PAIS"));
                bean.setEstado(rs.getString("ESTADO"));  
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
    public ArrayList<Productores> getAll(String prd)
    {
        ArrayList<Productores> l=new ArrayList<Productores>();
        Connection connection=null;
        try 
        {
            String condicion = "and  concat(concat(concat(NOMBRE,' '),concat(APELLIDO_P,' ')),APELLIDO_M) like '%"+prd+"%'"; 
            connection=get_connection();
            Statement st=connection.createStatement();
            String querySelect="SELECT ID, PAIS,ESTADO,NOMBRE,APELLIDO_P,APELLIDO_M,NUMERO  FROM CC_PRODUCTOR WHERE ACTIVO  =1  " +
                    (prd.length()==0 ? "" : prd);
            System.out.println("querySelect>>>> "+querySelect);
            ResultSet rs=st.executeQuery(querySelect );
            while(rs.next())
            {
                Productores bean=new Productores();
                bean.setId(rs.getInt("ID"));
                bean.setNombre(rs.getString("NOMBRE"));
                bean.setPais(rs.getString("PAIS"));
                bean.setEstado(rs.getString("ESTADO"));
                bean.setPaterno(rs.getString("APELLIDO_P"));
                bean.setMaterno(rs.getString("APELLIDO_M"));
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
    
    public ArrayList<Productores> getAllProductor(String prd)
    {
        ArrayList<Productores> l=new ArrayList<Productores>();
        Connection connection=null;
        try 
        {
            prd =  prd.trim();
            prd =  prd.replaceAll(" ","%");
            String condicion = "where upper(concat(concat(concat(NOMBRE,' '),concat(apellido_p,' ')),concat(apellido_m))) like upper('%"+prd+"%')"; 
            connection=get_connection();
            Statement st=connection.createStatement();
            String querySelect="select id ,  PAIS,  ESTADO,upper(concat(concat(concat(NOMBRE,' '),concat(apellido_p,' ')),concat(apellido_m))) nombre FROM cc_productor " +
                    (prd.length()==0 ? "" : condicion);
            ResultSet rs=st.executeQuery(querySelect );
            while(rs.next())
            {
                Productores bean=new Productores();
                bean.setId(rs.getInt("ID"));
                bean.setNombre(rs.getString("NOMBRE")); 
                bean.setPais(rs.getString("PAIS"));
                bean.setEstado(rs.getString("ESTADO"));
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
    
    public int updateRecord(String pais,String estado,String nombre,String apellido_p,String apellido_m,int id,int usuario)
     { 
        System.out.println("updateRecord("+nombre+","+ id+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setString(1, pais);
            ps.setString(2, estado);
            ps.setString(3, nombre);
            ps.setString(4, apellido_p);
            ps.setString(5, apellido_m);
            ps.setInt   (6, usuario);
            ps.setInt   (7, id);
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
    public static String leftPad(String originalString, int length,
         char padCharacter) {
      String paddedString = originalString;
      while (paddedString.length() < length) {
         paddedString = padCharacter + paddedString;
      }
      return paddedString;
   }
    public Productores insertRecord(String pais,String estado,String nombre,String apellido_p,String apellido_m,int usuario)
    {
        System.out.println("insertRecord("+nombre+")");
        int insertCont=0;
        Productores bean=null;
        Connection connection=null;
        try 
        {
            bean  =  getMax();
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreate);
            ps.setInt(1,bean.getId() +1);
            ps.setString(2, pais);
            ps.setString(3, estado);
            ps.setString(4, nombre);
            ps.setString(5, apellido_p);
            ps.setString(6, apellido_m);
            ps.setString(7, "PRODC"+ leftPad((""+(bean.getId() +1)) ,7, '0') );
            ps.setInt(8, usuario);
            ps.setInt(9,usuario);
            insertCont=ps.executeUpdate();
            if(insertCont==1)
            {
                
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
	public String getDireccion(int id_productor) {
		Connection connection=null;
		String direccion="";
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            String querySelect="select concat(trim(colonia),',',trim(municipio),',',trim(estado),'.') direccion from  cc_productor where id = "+id_productor;
            System.out.println("querySelect>>>> "+querySelect);
            ResultSet rs=st.executeQuery(querySelect );
            if(rs.next())
            {
            	direccion =  rs.getString("direccion"); 
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
        return  direccion;
	}
     
}
