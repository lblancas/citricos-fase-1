/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Albaran_detalle; 
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
 
public class AlbaranDetalleController  extends Conexion 
{
    String querySelect="SELECT ID,ID_ALBARAN,CALIBRE,POS_CALIBRE,PESO,FRUTA,PORCENTAJE FROM CC_ALBARAN_DETALLE";
    String queryMax="SELECT MAX(ID) ID FROM CC_ALBARAN_DETALLE";
    String querySelectOne="SELECT ID,ID_ALBARAN,CALIBRE,POS_CALIBRE,PESO,FRUTA,PORCENTAJE FROM CC_ALBARAN_DETALLE WHERE ID = ";
    String queryUpdate="UPDATE CC_ALBARAN_DETALLE SET ID_ALBARAN=?,CALIBRE=?,POS_CALIBRE=?,PESO=?,FRUTA=?,PORCENTAJE=? WHERE ID = ?";
    String queryDelete="DELETE FROM CC_ALBARAN_DETALLE WHERE ID = ? ";
    String queryCreate="INSERT INTO CC_ALBARAN_DETALLE (ID_ALBARAN,CALIBRE,POS_CALIBRE,PESO,FRUTA,PORCENTAJE) VALUES (?,?,?,?,?,?) ";
    
    public Albaran_detalle getMax()
    {
        Albaran_detalle bean=new Albaran_detalle();
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
    public Albaran_detalle getOne(int i)
    {
        Albaran_detalle bean=new Albaran_detalle();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+i);
            if(rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setId_albaran(rs.getInt("ID_ALBARAN"));
                bean.setCalibre(rs.getString("CALIBRE"));
                bean.setPos_calibre(rs.getString("POS_CALIBRE"));
                bean.setPeso(rs.getFloat("PESO"));
                bean.setFruta(rs.getFloat("FRUTA"));
                bean.setPorcentaje(rs.getFloat("PORCENTAJE"));
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
    public ArrayList<Albaran_detalle> getAll()
    {
        ArrayList<Albaran_detalle> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            try (ResultSet rs = st.executeQuery(querySelect)) {
                while(rs.next())
                {
                    Albaran_detalle bean=new Albaran_detalle();
                    bean.setId(rs.getInt("ID"));
                    bean.setId_albaran(rs.getInt("ID_ALBARAN"));
                    bean.setCalibre(rs.getString("CALIBRE"));
                    bean.setPos_calibre(rs.getString("POS_CALIBRE"));
                    bean.setPeso(rs.getFloat("PESO"));
                    bean.setFruta(rs.getFloat("FRUTA"));
                    bean.setPorcentaje(rs.getFloat("PORCENTAJE"));
                    l.add(bean);
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
     * Modificar Registro
     * @param id_albaran
     * @param calibre
     * @param pos_calibre
     * @param peso
     * @param fruta
     * @param porcentaje
     * @param id
     * @return 
     */
    public int updateRecord(int id_albaran,String calibre,String pos_calibre,float peso,float fruta,float porcentaje,int id)
     {  
        System.out.println("updateRecord("+id+","+ id_albaran+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            //ID_ALBARAN=?,CALIBRE=?,POS_CALIBRE=?,PESO=?,FRUTA=?,PORCENTAJE=? WHERE ID 
            ps.setInt   (1, id_albaran);
            ps.setString(2, calibre);
            ps.setString(3, pos_calibre);
            ps.setFloat (4, peso);
            ps.setFloat (5, fruta);
            ps.setFloat (6, porcentaje);
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
     * @param id
     * @return 
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
     * Insertar Detalle
     * @param id_albaran
     * @param calibre
     * @param pos_calibre
     * @param peso
     * @param fruta
     * @param porcentaje
     * @return 
     */
    public Albaran_detalle  insertRecord(int id_albaran,String calibre,String pos_calibre,float peso,float fruta,float porcentaje)
    {
        System.out.println("insertRecord("+id_albaran+")");
        int insertCont=0;
        Albaran_detalle bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreate);
            //INSERT INTO CC_ALBARAN_DETALLE (ID_ALBARAN,CALIBRE,POS_CALIBRE,PESO,FRUTA,PORCENTAJE)
            ps.setInt   (1, id_albaran);
            ps.setString(2, calibre);
            ps.setString(3, pos_calibre);
            ps.setFloat (4, peso);
            ps.setFloat (5, fruta);
            ps.setFloat (6, porcentaje);
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
