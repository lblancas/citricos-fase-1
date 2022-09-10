/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.citricos.dao.Conexion;
/**
 *
 * @author Luis Adrian Blancas Bahena
 */
 
public class MenuCorridasController  extends Conexion 
{ 
    String queryNombre="SELECT NOMBRE TXT FROM PRODUCTORES WHERE upper(NOMBRE) like upper('%";
    String queryFolios="SELECT FOLIO  TXT FROM CC_FOLIOS   WHERE upper(FOLIO)  like upper('%";
    public ArrayList<String> getAll(int opc,String contenido)
    {
        ArrayList<String> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            String querySelect = (opc ==1 ? queryNombre :queryFolios) +  contenido + "%') ORDER BY 1;" ;
            System.out.println("QUERY :: " + querySelect);
            try (ResultSet rs = st.executeQuery(querySelect)) {
                while(rs.next())
                {
                    l.add(rs.getString("TXT"));
                    System.out.println("TXT :: " + rs.getString("TXT"));
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
}
