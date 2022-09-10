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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Precios;
import mx.org.citricos.entity.User;
import mx.org.spring.Renglon;

/**
 *
 * @author luisa
 */
public class PreciosController  extends Conexion 
{
    String querySelect="SELECT P.ID,   VERDE_JAPON,VERDE_110,VERDE_150,VERDE_175,VERDE_200,VERDE_230,VERDE_250,  " + 
    		"EMPAQUE_110,EMPAQUE_150,EMPAQUE_175,EMPAQUE_200,EMPAQUE_230,EMPAQUE_250,	 " + 
    		"SEGUNDAS,TERCERAS,TORREON,COLEADA,   " + 
    		"DATE_FORMAT(P.MODIFICADO,'%Y-%M-%D') FECHA_ACT,DATE_FORMAT(P.MODIFICADO,'%Y-%M-%D %H:%I') FECHA ,  " + 
    		"CONCAT ('[',LOGIN,']') USUARIO " + 
    		"FROM CC_PRECIOS P, CC_USUARIO U " + 
    		"WHERE P.MODIFICADOPOR = U.ID " + 
    		"ORDER BY P.ID  DESC ";
    
    String querySeleccionaMasActual="SELECT ID, "
            + " VERDE_JAPON,VERDE_110,VERDE_150,VERDE_175,VERDE_200,VERDE_230,VERDE_250, " 
            + " EMPAQUE_110,EMPAQUE_150,EMPAQUE_175,EMPAQUE_200,EMPAQUE_230,EMPAQUE_250,	" 
            + " SEGUNDAS,TERCERAS,TORREON,COLEADA,"
            + " DATE_FORMAT(MODIFICADO,'%Y-%m-%d') FECHA_ACT,DATE_FORMAT(MODIFICADO,'%Y-%m-%d %H:%i') FECHA"
            + ",DATE_FORMAT(now(),'%Y-%m-%d') NOWF   " 
            + " FROM CC_PRECIOS " 
            + " ORDER BY FECHA DESC ";
    String queryMax="SELECT MAX(ID) ID FROM CC_PRECIOS";
    String querySelectOne="SELECT P.ID,   VERDE_JAPON,VERDE_110,VERDE_150,VERDE_175,VERDE_200,VERDE_230,VERDE_250,     " + 
    		" EMPAQUE_110,EMPAQUE_150,EMPAQUE_175,EMPAQUE_200,EMPAQUE_230,EMPAQUE_250,	  " + 
    		" SEGUNDAS,TERCERAS,TORREON,COLEADA,  " + 
    		" DATE_FORMAT(P.MODIFICADO,'%Y-%M-%D') FECHA_ACT,DATE_FORMAT(P.MODIFICADO,'%Y-%M-%D %H:%I') FECHA , " + 
    		" CONCAT ('[',LOGIN,']') USUARIO " + 
    		" FROM CC_PRECIOS P, CC_USUARIO U  " + 
    		" WHERE P.MODIFICADOPOR = U.ID " +
    		 " AND P.ID = "; 
    String queryPrecioProducto="SELECT P.ID, P.VERDE_JAPON,P.VERDE_110,P.VERDE_150,P.VERDE_175,P.VERDE_200,P.VERDE_230,P.VERDE_250, " + 
    		"    		 P.EMPAQUE_110,P.EMPAQUE_150,P.EMPAQUE_175,P.EMPAQUE_200,P.EMPAQUE_230,P.EMPAQUE_250," + 
    		"    		 P.SEGUNDAS,P.TERCERAS,P.TORREON,P.COLEADA,  " + 
    		"    		 DATE_FORMAT(f.MODIFICADO,'%Y-%M-%D') FECHA_ACT,DATE_FORMAT(f.MODIFICADO,'%Y-%M-%D %H:%I') FECHA , " + 
    		"    		 CONCAT ('[',LOGIN,']') USUARIO  " + 
    		"    		 FROM CC_PRECIO_producto P, CC_USUARIO U , cc_corridas C , cc_folios f " + 
    		"    		 WHERE P.ID = C.id " + 
    		"    		 AND   f.MODIFICADOPOR = U.ID  " + 
    		"    		 AND   F.ID =C.ID " + 
    		"    		 AND   C.ID =   "; 
    String queryCreate="INSERT INTO CC_PRECIOS ("
            + " VERDE_JAPON,VERDE_110,VERDE_150,VERDE_175,VERDE_200,VERDE_230,VERDE_250, " 
            + " EMPAQUE_110,EMPAQUE_150,EMPAQUE_175,EMPAQUE_200,EMPAQUE_230,EMPAQUE_250,	" 
            + " SEGUNDAS,TERCERAS,TORREON,COLEADA,"
            + "MODIFICADOPOR,CREADOPOR)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
    
    public Precios getMax()
    {
        Precios bean=new Precios();
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
    public Precios getSeleccionMasActual()
    {
        Precios bean=new Precios();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            System.out.println(querySeleccionaMasActual);
            ResultSet rs=st.executeQuery(querySeleccionaMasActual);
            if(rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setVerde_japon(rs.getDouble("VERDE_JAPON"));
                bean.setVerde_110(rs.getDouble("VERDE_110"));
                bean.setVerde_150(rs.getDouble("VERDE_150"));
                bean.setVerde_175(rs.getDouble("VERDE_175"));
                bean.setVerde_200(rs.getDouble("VERDE_200"));
                bean.setVerde_230(rs.getDouble("VERDE_230"));
                bean.setVerde_250(rs.getDouble("VERDE_250")); 
        
        
                bean.setEmpaque_110(rs.getDouble("EMPAQUE_110"));
                bean.setEmpaque_150(rs.getDouble("EMPAQUE_150"));
                bean.setEmpaque_175(rs.getDouble("EMPAQUE_175"));
                bean.setEmpaque_200(rs.getDouble("EMPAQUE_200"));
                bean.setEmpaque_230(rs.getDouble("EMPAQUE_230"));
                bean.setEmpaque_250(rs.getDouble("EMPAQUE_250")); 
                bean.setUsuario(rs.getString("usuario"));
                bean.setSegundas(rs.getDouble("SEGUNDAS"));
                bean.setTerceras(rs.getDouble("TERCERAS"));
                bean.setTorreon(rs.getDouble("TORREON"));
                bean.setColeada(rs.getDouble("COLEADA"));  
                
                bean.setFecha(rs.getString("FECHA"));
                bean.setFecha_act(rs.getString("FECHA_ACT"));
                String now = (rs.getString("NOWF"));
                bean.setConfirmar("Crear Precios");
                bean.setActivo(false);
                if(now.equals(bean.getFecha_act()))
                {
                    bean.setConfirmar("Crear Precios");
                    bean.setActivo(false);
                }
                else
                {
                    bean.setConfirmar("Confirmar precios");
                    bean.setActivo(true);
                }
                System.out.println("ID:: "+ bean.getId());
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
    public Precios getPrecioProProducto(int i)
    {
        Precios bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(queryPrecioProducto+i);
            if(rs.next())
            {
                bean=new Precios();
                bean.setId(rs.getInt("ID"));
                bean.setVerde_japon(rs.getDouble("VERDE_JAPON"));
                bean.setVerde_110(rs.getDouble("VERDE_110"));
                bean.setVerde_150(rs.getDouble("VERDE_150"));
                bean.setVerde_175(rs.getDouble("VERDE_175"));
                bean.setVerde_200(rs.getDouble("VERDE_200"));
                bean.setVerde_230(rs.getDouble("VERDE_230"));
                bean.setVerde_250(rs.getDouble("VERDE_250")); 
        
        
                bean.setEmpaque_110(rs.getDouble("EMPAQUE_110"));
                bean.setEmpaque_150(rs.getDouble("EMPAQUE_150"));
                bean.setEmpaque_175(rs.getDouble("EMPAQUE_175"));
                bean.setEmpaque_200(rs.getDouble("EMPAQUE_200"));
                bean.setEmpaque_230(rs.getDouble("EMPAQUE_230"));
                bean.setEmpaque_250(rs.getDouble("EMPAQUE_250")); 
                
                bean.setSegundas(rs.getDouble("SEGUNDAS"));
                bean.setTerceras(rs.getDouble("TERCERAS"));
                bean.setTorreon(rs.getDouble("TORREON"));
                bean.setColeada(rs.getDouble("COLEADA"));
                
                bean.setFecha(rs.getString("FECHA"));
                bean.setFecha_act(rs.getString("FECHA_ACT"));
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
    public Precios getOne(int i)
    {
        Precios bean=new Precios();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+i);
            if(rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setVerde_japon(rs.getDouble("VERDE_JAPON"));
                bean.setVerde_110(rs.getDouble("VERDE_110"));
                bean.setVerde_150(rs.getDouble("VERDE_150"));
                bean.setVerde_175(rs.getDouble("VERDE_175"));
                bean.setVerde_200(rs.getDouble("VERDE_200"));
                bean.setVerde_230(rs.getDouble("VERDE_230"));
                bean.setVerde_250(rs.getDouble("VERDE_250")); 
        
        
                bean.setEmpaque_110(rs.getDouble("EMPAQUE_110"));
                bean.setEmpaque_150(rs.getDouble("EMPAQUE_150"));
                bean.setEmpaque_175(rs.getDouble("EMPAQUE_175"));
                bean.setEmpaque_200(rs.getDouble("EMPAQUE_200"));
                bean.setEmpaque_230(rs.getDouble("EMPAQUE_230"));
                bean.setEmpaque_250(rs.getDouble("EMPAQUE_250")); 
                
                bean.setSegundas(rs.getDouble("SEGUNDAS"));
                bean.setTerceras(rs.getDouble("TERCERAS"));
                bean.setTorreon(rs.getDouble("TORREON"));
                bean.setColeada(rs.getDouble("COLEADA"));
                
                bean.setFecha(rs.getString("FECHA"));
                bean.setFecha_act(rs.getString("FECHA_ACT"));
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
    
    public ArrayList<Precios> getAll()
    {
        ArrayList<Precios> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            try (ResultSet rs = st.executeQuery(querySelect)) {
                while(rs.next())
                {
                    Precios bean=new Precios();
                    bean.setId(rs.getInt("ID"));
                    bean.setVerde_japon(rs.getDouble("VERDE_JAPON"));
                    bean.setVerde_110(rs.getDouble("VERDE_110"));
                    bean.setVerde_150(rs.getDouble("VERDE_150"));
                    bean.setVerde_175(rs.getDouble("VERDE_175"));
                    bean.setVerde_200(rs.getDouble("VERDE_200"));
                    bean.setVerde_230(rs.getDouble("VERDE_230"));
                    bean.setVerde_250(rs.getDouble("VERDE_250")); 
                    bean.setUsuario(rs.getString("usuario"));

                    bean.setEmpaque_110(rs.getDouble("EMPAQUE_110"));
                    bean.setEmpaque_150(rs.getDouble("EMPAQUE_150"));
                    bean.setEmpaque_175(rs.getDouble("EMPAQUE_175"));
                    bean.setEmpaque_200(rs.getDouble("EMPAQUE_200"));
                    bean.setEmpaque_230(rs.getDouble("EMPAQUE_230"));
                    bean.setEmpaque_250(rs.getDouble("EMPAQUE_250")); 

                    bean.setSegundas(rs.getDouble("SEGUNDAS"));
                    bean.setTerceras(rs.getDouble("TERCERAS"));
                    bean.setTorreon(rs.getDouble("TORREON"));
                    bean.setColeada(rs.getDouble("COLEADA"));

                    bean.setFecha(rs.getString("FECHA"));
                    bean.setFecha_act(rs.getString("FECHA_ACT"));
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
    /***
     * Creacion de registro de precios
     * @param verde_japon
     * @param verde_110
     * @param verde_150
     * @param verde_175
     * @param verde_200
     * @param verde_230
     * @param verde_250
     * @param empaque_110
     * @param empaque_150
     * @param empaque_175
     * @param empaque_200
     * @param empaque_230
     * @param empaque_250
     * @param segundas
     * @param terceras
     * @param torreon
     * @param coleada
     * @param creadopor
     * @return toma el maximo y regresa el precio creado 
     */
    public Precios  insertRecordBean(
            int    creadopor,
            Precios precios
            )
    {
        System.out.println("insertRecord()");
        int insertCont=0;
        Precios bean=null;
        Connection connection=null;
        try 
        {
            String queryCreate12="INSERT INTO CC_PRECIOS ("
            + " VERDE_JAPON,VERDE_110,VERDE_150,VERDE_175,VERDE_200,VERDE_230,VERDE_250, " 
            + " EMPAQUE_110,EMPAQUE_150,EMPAQUE_175,EMPAQUE_200,EMPAQUE_230,EMPAQUE_250,	" 
            + " SEGUNDAS,TERCERAS,TORREON,COLEADA,"
            + "MODIFICADOPOR,CREADOPOR,ID)  VALUES ("
                    + "?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,"
                    + "?,?,?,?,"
                    + "?,?,"
                    + "?) ";
            
            connection=get_connection();
            precios.setId(precios.getId()+1);
            PreparedStatement ps=connection.prepareStatement(queryCreate12);
            ps.setDouble(1,  precios.getVerde_japon());
            ps.setDouble(2,  precios.getVerde_110());
            ps.setDouble(3,  precios.getVerde_150());
            ps.setDouble(4,  precios.getVerde_175());
            ps.setDouble(5,  precios.getVerde_200());
            ps.setDouble(6,  precios.getVerde_230());
            ps.setDouble(7,  precios.getVerde_250());

            ps.setDouble(8,  precios.getEmpaque_110());
            ps.setDouble(9,  precios.getEmpaque_150());
            ps.setDouble(10, precios.getEmpaque_175());
            ps.setDouble(11, precios.getEmpaque_200());
            ps.setDouble(12, precios.getEmpaque_230());
            ps.setDouble(13, precios.getEmpaque_250());	

            ps.setDouble(14, precios.getSegundas());
            ps.setDouble(15, precios.getTerceras());
            ps.setDouble(16, precios.getTorreon());
            ps.setDouble(17, precios.getColeada());
            
            ps.setInt   (18, creadopor);
            ps.setInt   (19, creadopor);
            ps.setInt   (20, precios.getId());
            insertCont=ps.executeUpdate();
            if(insertCont==1)
            {
                bean  =  getOne(precios.getId());
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
    /***
     * Creacion de registro de precios
     * @param verde_japon
     * @param verde_110
     * @param verde_150
     * @param verde_175
     * @param verde_200
     * @param verde_230
     * @param verde_250
     * @param empaque_110
     * @param empaque_150
     * @param empaque_175
     * @param empaque_200
     * @param empaque_230
     * @param empaque_250
     * @param segundas
     * @param terceras
     * @param torreon
     * @param coleada
     * @param creadopor
     * @return toma el maximo y regresa el precio creado 
     */
    public Precios  insertRecord(
            double verde_japon,
            double verde_110,
            double verde_150,
            double verde_175,
            double verde_200,
            double verde_230,
            double verde_250,
            double empaque_110,
            double empaque_150,
            double empaque_175,
            double empaque_200,
            double empaque_230,
            double empaque_250,	
            double segundas,
            double terceras ,
            double torreon,
            double coleada,
            int    creadopor
            )
    {
        System.out.println("insertRecord()");
        int insertCont=0;
        Precios bean=null;
        Connection connection=null;
        try 
        {
            String queryCreate12="INSERT INTO CC_PRECIOS ( "
            + " VERDE_JAPON,VERDE_110,VERDE_150,VERDE_175,VERDE_200,VERDE_230,VERDE_250, " 
            + " EMPAQUE_110,EMPAQUE_150,EMPAQUE_175,EMPAQUE_200,EMPAQUE_230,EMPAQUE_250,	" 
            + " SEGUNDAS,TERCERAS,TORREON,COLEADA,"
            + "MODIFICADOPOR,CREADOPOR)  VALUES ("
                    + "?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,"
                    + "?,?,?,?,"
                    + "?,?) ";
            
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreate);
            ps.setDouble(1,  verde_japon);
            ps.setDouble(2,  verde_110);
            ps.setDouble(3,  verde_150);
            ps.setDouble(4,  verde_175);
            ps.setDouble(5,  verde_200);
            ps.setDouble(6,  verde_230);
            ps.setDouble(7,  verde_250);

            ps.setDouble(8,  empaque_110);
            ps.setDouble(9,  empaque_150);
            ps.setDouble(10, empaque_175);
            ps.setDouble(11, empaque_200);
            ps.setDouble(12, empaque_230);
            ps.setDouble(13, empaque_250);	

            ps.setDouble(14, segundas);
            ps.setDouble(15, terceras);
            ps.setDouble(16, torreon);
            ps.setDouble(17, coleada);
            
            ps.setInt   (18, creadopor);
            ps.setInt   (19, creadopor);
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
	public List<Renglon> getUsuariosImpresoras() {
		System.out.println("getUsuariosImpresoras()");
        ArrayList<Renglon> l=new ArrayList<Renglon>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            try (ResultSet rs = st.executeQuery(" SELECT  CONCAT(ID,'.-',LOGIN,'_',IMPRESORA) IMPRESORA FROM CC_USUARIO ORDER BY ID ")) 
            {
                while(rs.next())
                    l.add(new Renglon(rs.getString("IMPRESORA")));
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
	
	public mx.org.citricos.entity.User getUsuarioImpresora(int usuario) 
	{
		System.out.println("getUsuarioImpresora()");
		mx.org.citricos.entity.User u=new mx.org.citricos.entity.User();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            try (ResultSet rs = st.executeQuery(" SELECT  ID, LOGIN, CONCAT(NOMBRE,' ',APELLIDOS) NOMBRE,IMPRESORA,IMPRIMIR FROM CC_USUARIO WHERE ID  = "+usuario)) 
            {
                if(rs.next())
                {
                    u.setId(rs.getInt("ID"));
                    u.setLogin(rs.getString("LOGIN"));
                    u.setNombre(rs.getString("NOMBRE"));
                    u.setImpresora(rs.getString("IMPRESORA"));
                    u.setImprimir(rs.getInt("IMPRIMIR"));
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
        return u;
	}
	public void setUsuarioImpresora(int usuario, int imprimir, String impresora) 
	{
        System.out.println("setUsuarioImpresora ("+usuario+","+","+imprimir+","+impresora+")");
        int insertCont=0;
        Precios bean=null;
        Connection connection=null;
        try 
        {
            String queryCreate12=" UPDATE CC_USUARIO SET IMPRIMIR =?, IMPRESORA =? WHERE ID  =?  ";
            
            connection=get_connection(); 
            PreparedStatement ps=connection.prepareStatement(queryCreate12);
            ps.setInt   (1,  imprimir);
            ps.setString(2,  impresora);
            ps.setInt   (3,  usuario);
            insertCont=ps.executeUpdate();
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
    }
}
