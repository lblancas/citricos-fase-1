/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger ;
import mx.org.citricos.entity.CorridaR;
/**
 *
 * @author Luis Adrian Blancas Bahena
 */
 
public class CorridaRController  extends Conexion 
{ 
    String querySelectOne="select  " +
    " id " +
    ",id_folio " +
    ",verde_japon " +
    ",verde_110 " +
    ",verde_150 " +
    ",verde_175 " +
    ",verde_200 " +
    ",verde_230 " +
    ",verde_250 " +
    ",empaque_110 " +
    ",empaque_150 " +
    ",empaque_175 " +
    ",empaque_200 " +
    ",empaque_230 " +
    ",empaque_250 " +
    ",eur_110 " +
    ",eur_150 " +
    ",eur_175 " +
    ",eur_200 " +
    ",eur_230 " +
    ",eur_250 " +
    ",segundas " +
    ",terceras " +
    ",torreon " +
    ",coleada " +
    ",promedio_original " +
    ",fecha_modificacion " +
    ",promedio_modificacion " +
    ",modificado " +
    ",creado " +
    ",comprador " +
    ",facturar" +
    ",(select descripcion from cc_cat_tamano_limon  where id   = idtipo) tipo from cc_corridas WHERE ID_FOLIO = ";
    String queryUpdate="UPDATE cc_cat_tamano_limon "
            + " SET promedio_modificacion = ?,"
            + " fecha_modificacion = now() ,"
            + " modificado=? "
            + " WHERE ID_folio = ?";
    
    public CorridaR getOne(int i)
    {
        CorridaR bean=new CorridaR();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+i);
            if(rs.next())
            {
                bean.setId_folio(rs.getInt("ID_FOLIO"));
                bean.setVerde_japon(rs.getDouble("verde_japon"));
                bean.setVerde_110(rs.getDouble("verde_110"));
                bean.setVerde_150(rs.getDouble("verde_150"));
                bean.setVerde_175(rs.getDouble("verde_175"));
                bean.setVerde_200(rs.getDouble("verde_200"));
                bean.setVerde_230(rs.getDouble("verde_230"));
                bean.setVerde_250(rs.getDouble("verde_250"));
                
                bean.setEmpaque_110(rs.getDouble("empaque_110"));
                bean.setEmpaque_150(rs.getDouble("empaque_150"));
                bean.setEmpaque_175(rs.getDouble("empaque_175"));
                bean.setEmpaque_200(rs.getDouble("empaque_200"));
                bean.setEmpaque_230(rs.getDouble("empaque_230"));
                bean.setEmpaque_250(rs.getDouble("empaque_250"));
                
                bean.setEur_110(rs.getDouble("eur_110"));
                bean.setEur_150(rs.getDouble("eur_150"));
                bean.setEur_175(rs.getDouble("eur_175"));
                bean.setEur_200(rs.getDouble("eur_200"));
                bean.setEur_230(rs.getDouble("eur_230"));
                bean.setEur_250(rs.getDouble("eur_250"));
                
                bean.setSegundas(rs.getDouble("segundas"));
                bean.setTerceras(rs.getDouble("terceras"));
                bean.setTorreon (rs.getDouble("torreon"));
                bean.setColeada (rs.getDouble("coleada"));
                
                bean.setPromedio_original    (rs.getDouble("promedio_original"));
                bean.setPromedio_modificacion(rs.getDouble("promedio_modificacion"));
                
                bean.setFecha_modificacion(rs.getString("fecha_modificacion"));
                
                bean.setModificado(rs.getInt("MODIFICADO"));
                bean.setCreado(rs.getInt("CREADO"));
                bean.setTipostr(rs.getString("TIPO"));
                
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
     * Modificar datos
     * @param nombre
     * @param id
     * @return 
     */
    public int updateRecord(int id,int modificado,double promedio)
     {  
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setDouble(1, promedio);
            ps.setInt(2, modificado);
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
}
