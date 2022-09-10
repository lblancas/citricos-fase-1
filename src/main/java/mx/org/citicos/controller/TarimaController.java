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
public class TarimaController extends Conexion
{ 
    String querySelect="SELECT ID, DESCRIPCION  FROM CC_CAT_TARIMA WHERE ID_ACTIVO=1 ";
    String querySelectTR="SELECT ID,ID_ACTIVO, DESCRIPCION  FROM CC_CAT_TARIMA   ";
    String querySelectTT="select  sum(ifnull(d.cajas,0)) CAJAS from cc_tarima_transporte tt,cc_tarima t, cc_tarima_desc d " + 
    		"  where tt.id = t.id_tt " + 
    		"  and   t.id =  d.id_tarima " + 
    		"  AND   TT.ID  = ";
    String queryMax="SELECT MAX(ID) ID FROM CC_CAT_TARIMA";
    String queryId=" SELECT *  FROM CC_TARIMA WHERE NUMERO ='";
    String querySelectOne="SELECT ID, DESCRIPCION  FROM CC_CAT_TARIMA  WHERE ID = ";
    String queryDelete="UPDATE CC_CAT_TARIMA   SET ID_ACTIVO =3 WHERE ID = ? ";
    String queryUpdate="UPDATE CC_CAT_TARIMA SET DESCRIPCION = ? WHERE ID = ?";
    String queryCreate="INSERT INTO CC_CAT_TARIMA (DESCRIPCION)  VALUES (?) ";
    String queryUpdateUsr="UPDATE CC_CAT_TARIMA SET DESCRIPCION = ?, MODIFICADOPOR =? WHERE ID = ?";
    String queryCreateUsr="INSERT INTO CC_CAT_TARIMA (DESCRIPCION,CREADOPOR,MODIFICADOPOR)  VALUES (?,?,?) ";
    public int getCajas(int id)
    {
        System.out.println("getCajas()");
        int cajas=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectTT+id);
            if(rs.next())
            { 
                cajas= rs.getInt("CAJAS");
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
        return cajas;
    }
    
    
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
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
    public Tarima  insertRecordUsr(String nombre,int usr)
    {
        System.out.println("insertRecord("+nombre+","+usr+")");
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
                    Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return bean;
    }
	public int getId(String num) 
	{
		int id=0;
		Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(queryId+num+"'");
            if(rs.next())
            {
                id = rs.getInt("ID"); 
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
        return id;
	}
}
