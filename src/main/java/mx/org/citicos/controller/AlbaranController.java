/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Albaran; 
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger ;
/**
 *
 * @author Luis Adrian Blancas Bahena
 */
 
public class AlbaranController  extends Conexion 
{
    String querySelect="SELECT ID,ID_FOLIO FROM CC_ALBARAN";
    String queryMax="SELECT MAX(ID) ID FROM CC_ALBARAN";
    String querySelectOne="SELECT ID,ID_FOLIO FROM CC_ALBARAN WHERE ID = ";
    String queryUpdate="UPDATE CC_ALBARAN SET ID_FOLIO= ? WHERE ID = ?";
    String queryDelete="DELETE FROM CC_ALBARAN WHERE ID = ? ";
    String queryCreate="INSERT INTO CC_ALBARAN (ID_FOLIO)  VALUES (?) ";
    
    public Albaran getMax()
    {
        Albaran bean=new Albaran();
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
    public Albaran getOne(int i)
    {
        Albaran bean=new Albaran();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+i);
            if(rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setId_folio(rs.getInt("ID_FOLIO"));
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
    public ArrayList<Albaran> getAll()
    {
        ArrayList<Albaran> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            try (ResultSet rs = st.executeQuery(querySelect)) {
                while(rs.next())
                {
                    Albaran obj=new Albaran();
                    obj.setId(rs.getInt("ID"));
                    obj.setId_folio(rs.getInt("ID_FOLIO"));
                    l.add(obj);
                }
            }
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
     * Modificar datos
     * @param id_folio
     * @param id
     * @return 
     */
    public int updateRecord(int id_folio,int id)
     {  
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setInt(1, id_folio);
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
     * @param id
     * @return 
     */
    public int deleteRecord(int id)
    {
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
     * @param id_folio
     * @return 
     */
    public Albaran  insertRecord(int id_folio)
    {
        int insertCont=0;
        Albaran bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreate);
            ps.setInt(1, id_folio);
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
