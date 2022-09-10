/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;

import mx.org.citricos.dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BID
 */
public class SubCalibreController extends Conexion{
   /**
     * Proceso para el guardado de registro.
     * @return numero de registros dados de alta.
     */
    String queryCreate="INSERT INTO CC_SUBCALIBRE (DESCRIPCION,ID)  VALUES (?,?) ";
    public int  insertRecord(String nombre,int i)
    {
        System.out.println("insertRecord("+nombre+","+i+")");
        int insertCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreate);
            ps.setString(1, nombre);
            ps.setInt(2,i);
            insertCont=ps.executeUpdate(); 
            System.out.println(">> SubCalibre ["+nombre+","+i+"]");
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
        return insertCont;
    } 
}
