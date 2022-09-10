/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Impresora; 
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
 
public class ImpresoraController  extends Conexion 
{ 
    String querySelect="select u.id,login,concat(concat(p.nombre,' '), apellidos)nombre, p.nombre perfil,\n" +
        "  (CASE WHEN impresora IS NULL THEN '' ELSE impresora END) impresora,\n" +
        "  (CASE WHEN imprimir IS NULL THEN false ELSE imprimir END) imprimir\n" +
        "  from cc_usuario u, cc_perfil p where u.id_Perfil = p.id";
    String queryUpdate="update cc_usuario set  impresora=?, imprimir=? where id =?";
    
    public ArrayList<Impresora> getAll()
    {
        ArrayList<Impresora> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            try (ResultSet rs = st.executeQuery(querySelect)) {
                while(rs.next())
                {
                    int id          = rs.getInt("id");
                    String login    = rs.getString("login");
                    String perfil   = rs.getString("perfil");
                    String nombre   = rs.getString("nombre");
                    String impresora= rs.getString("impresora");
                    boolean imprimir= rs.getBoolean("imprimir");
              //      System.out.println(
                //        ""+id+","+login+","+nombre+","+impresora+","+imprimir+","+perfil
                  //      );
                    l.add(new Impresora(id,login,nombre,impresora,imprimir,perfil));
                }
            }
            //System.out.println(".----------------------------------------------");
            
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
     * @param nombre
     * @param id
     * @return 
     */
    public int updateRecord(String impresora,boolean imprimir,int id)
     {  
         //update cc_usuario set  impresora=?, imprimir=? where id =?
        System.out.println("updateRecord("+impresora+","+ id+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setString(1, impresora);
            ps.setBoolean(2, imprimir);
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
