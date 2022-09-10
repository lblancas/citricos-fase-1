/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;

import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.citricos.entity.Conn;

/**
 *
 * @author BID
 */
public class UsuarioController extends Conexion
{ 
    public Usuario getUsuarioByLogin(String login)
    {
        Usuario user=new Usuario();
        Connection connection = null;
		try {
			connection = get_connection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try 
        {  
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(
                    " SELECT  " +
                    " CCP.ID,  " +
                    " CCU.NOMBRE, " +
                    " CCU.PASSWORD, " +
                    " CCU.APELLIDOS , " +
                    " CCU.ID_ACTIVO,  " +
                    " CCU.ID_PERFIL , " +
                    " CCP.NOMBRE PERFIL, " +
                    " CCU.LOGIN, " +
                    " CCA.NOMBRE ACTIVOSTR " +
                    "  FROM CC_USUARIO CCU, CC_PERFIL CCP ,CC_ACTIVO CCA " +
                    "  WHERE CCU.ID_PERFIL      =  CCP.ID  " +
                    "  AND   CCA.ID             =  CCP.ID_ACTIVO " +
                    "  AND   CCP.ID_ACTIVO      =  1 " +
                    "  AND   UPPER(CCU.LOGIN)   =  '"+ login.toUpperCase()  +"'");
            if(rs.next())
            {  
                user.setId(rs.getInt("ID"));
                user.setNombre(rs.getString("NOMBRE"));
                user.setApellidos(rs.getString("APELLIDOS"));
                user.setLogin(rs.getString("LOGIN")); 
                user.setPassword(rs.getString("PASSWORD"));
                user.setActivo(rs.getInt("ID_ACTIVO"));
                user.setActivostr(rs.getString("ACTIVOSTR"));
                user.setPerfil(rs.getInt("ID_PERFIL"));
                user.setPerfilstr(rs.getString("PERFIL"));
                user.setMsg("OK");
            }
            else
            {
                user.setMsg("El usuario no existe ["+login+"]");
            }
        }
        catch (Exception e) 
        {
            user.setMsg(e.getMessage());
             System.out.println(e);
             
        }
        finally
        {
             if(connection!=null){
                 try {
                     connection.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         return user;
    }
    public Usuario findControl(String usuario, String password)
    {
        Usuario user=new Usuario();
        Connection connection = null;
		try {
			connection = get_connection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try 
        {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(
                    " SELECT  " +
                    " CCU.ID,  " +
                    " CCU.NOMBRE, " +
                    " CCU.PASSWORD, " +
                    " CCU.APELLIDOS , " +
                    " CCU.ID_ACTIVO,  " +
                    " CCU.ID_PERFIL , " +
                    " CCP.NOMBRE PERFIL, " +
                    " CCU.LOGIN, " +
                    " CCP.PAGINA, " +
                    " CCA.NOMBRE ACTIVOSTR, " +
                    " CCU.IMPRESORA, CCU.IMPRIMIR" +
                    "  FROM CC_USUARIO CCU, CC_PERFIL CCP ,CC_ACTIVO CCA " +
                    "  WHERE CCU.ID_PERFIL      =  CCP.ID  " +
                    "  AND   CCA.ID             =  CCP.ID_ACTIVO " +
                    "  AND   CCP.ID_ACTIVO      =  1 " +
                    "  AND   UPPER(CCU.LOGIN)   =  '"+ usuario.toUpperCase()  +"' " +
                    "  AND   UPPER(CCU.PASSWORD)=  '"+ password.toUpperCase() +"'");
            if(rs.next())
            {  
                user.setId(rs.getInt("ID"));
                user.setNombre(rs.getString("NOMBRE"));
                user.setApellidos(rs.getString("APELLIDOS"));
                user.setLogin(rs.getString("LOGIN")); 
                user.setPassword(rs.getString("PASSWORD"));
                user.setPagina(rs.getString("PAGINA"));
                user.setActivo(rs.getInt("ID_ACTIVO"));
                user.setActivostr(rs.getString("ACTIVOSTR"));
                user.setPerfil(rs.getInt("ID_PERFIL"));
                user.setPerfilstr(rs.getString("PERFIL"));
                user.setImpresora(rs.getString("IMPRESORA"));
                user.setImprimir (rs.getBoolean("IMPRIMIR"));
                user.setMsg("OK");
            }
            else
            {
                user.setMsg("El usuario no existe ["+usuario+"]");
            }
        }
        catch (Exception e) 
        {
            user.setMsg(e.getMessage());
             System.out.println(e);
             
        }
        finally
        {
             if(connection!=null){
                 try {
                     connection.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         return user;
    }
    public List<Usuario> getll(HashMap m)
    {
        System.out.println("Se actualizaron datos");
        List<Usuario>  lista=new ArrayList<>() ;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(
                    " SELECT  " +
                    " CCU.ID,  " +
                    " CCU.NOMBRE, " +
                    " CCU.PASSWORD, " +
                    " CCU.APELLIDOS , " +
                    " CCU.ID_ACTIVO,  " +
                    " CCU.ID_PERFIL , " +
                    " CCP.NOMBRE PERFIL, " +
                    " CCU.LOGIN, " +
                    " CCA.NOMBRE ACTIVOSTR " +
                    "  FROM CC_USUARIO CCU, CC_PERFIL CCP ,CC_ACTIVO CCA " +
                    "  WHERE CCU.ID_PERFIL      =  CCP.ID  " +
                    "  AND   CCA.ID             =  CCP.ID_ACTIVO ");
            while(rs.next())
            {  
                Usuario user=new Usuario();
                user.setId(rs.getInt("ID"));
                user.setNombre(rs.getString("NOMBRE"));
                user.setApellidos(rs.getString("APELLIDOS"));
                user.setLogin(rs.getString("LOGIN"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setActivostr(rs.getString("ACTIVOSTR"));
                user.setActivo(rs.getInt("ID_ACTIVO"));
                user.setPerfil(rs.getInt("ID_PERFIL"));
                user.setPerfilstr(rs.getString("PERFIL"));  
                user.setActivostr((String) m.get((new Integer(rs.getInt("ID_ACTIVO")))));
                user.setCabecera("Editar datos del usuario ["+rs.getInt("ID")+"]");
                lista.add(user);
            }
        }
        catch (Exception e) 
        {
             System.out.println(e);
        }
        finally
        {
             if(connection!=null){
                 try {
                     connection.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         return lista;
     }
    public List<Usuario> getll()
    {
        List<Usuario>  lista=new ArrayList<>() ;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(
                    " SELECT  " +
                    " CCU.ID,  " +
                    " CCU.NOMBRE, " +
                    " CCU.PASSWORD, " +
                    " CCU.APELLIDOS , " +
                    " CCU.ID_ACTIVO,  " +
                    " CCU.ID_PERFIL , " +
                    " CCP.NOMBRE PERFIL, " +
                    " CCU.LOGIN, " +
                    " CCA.NOMBRE ACTIVOSTR " +
                    "  FROM CC_USUARIO CCU, CC_PERFIL CCP ,CC_ACTIVO CCA " +
                    "  WHERE CCU.ID_PERFIL      =  CCP.ID  " +
                    "  AND   CCA.ID             =  CCP.ID_ACTIVO "+
                    "  AND   CCU.ID_ACTIVO      =1");
            while(rs.next())
            {  
                Usuario user=new Usuario();
                user.setId(rs.getInt("ID"));
                user.setNombre(rs.getString("NOMBRE"));
                user.setApellidos(rs.getString("APELLIDOS"));
                user.setLogin(rs.getString("LOGIN"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setActivostr(rs.getString("ACTIVOSTR"));
                user.setActivo(rs.getInt("ID_ACTIVO"));
                user.setPerfil(rs.getInt("ID_PERFIL"));
                user.setPerfilstr(rs.getString("PERFIL"));  
                user.setCabecera("Editar datos del usuario ["+rs.getInt("ID")+"]");
                lista.add(user);
            }
        }
        catch (Exception e) 
        {
             e.printStackTrace();
        }
        finally
        {
             if(connection!=null){
                 try {
                     connection.close();
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                 }
             }
         }
         return lista;
     }

    public int borrarUsuario(int id)
    {
        System.out.println("borrarUsuario("+id+")");
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement("update cc_usuario set ID_ACTIVO=3 where id = "+ id); 
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

    public int creaUsuario(String nombre,String apellidos,String login,String password,Integer perfil, Integer activo) 
    {
        System.out.println("creaUsuario("+nombre+" "+apellidos +")");
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement("INSERT INTO CC_USUARIO"
                    + " (NOMBRE,APELLIDOS,LOGIN,PASSWORD,ID_ACTIVO,ID_PERFIL) "
                    + "  VALUES(?,?,?,?,?,?)");  
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, login);
            ps.setString(4, password);
            ps.setInt   (5, perfil);
            ps.setInt   (6, activo);
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
     * Modificar usuarios
     * @param nombre
     * @param apellidos
     * @param login
     * @param perfil
     * @param activo
     * @return 
     * Integer id, String nombre, String apellidos, String login, Integer perfil, Integer activo
     */
    public int modificaUsuario(int id, String nombre,String apellidos,String login,Integer perfil, Integer activo,String password) 
    {
        System.out.println("modificaUsuario("+nombre+" "+apellidos +")");
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(
                      " UPDATE CC_USUARIO"
                    + " SET NOMBRE=?,APELLIDOS=?,LOGIN=?,ID_PERFIL=? , ID_ACTIVO=? , PASSWORD =? "
                    + " WHERE ID=? ");  
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, login);
            ps.setInt   (4, perfil);
            ps.setInt   (5, activo);
            ps.setString(6, password);
            ps.setInt   (7, id);
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
    public int resetPassword(int id,String password) 
    {
        System.out.println("modificaResetear ("+id +")");
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(
                      " UPDATE CC_USUARIO"
                    + " SET PASSWORD=? "
                    + " WHERE ID=? ");  
            ps.setString(1, password);
            ps.setInt   (2, id);
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
	public String getNameUsuarioById(int usuario) {
		System.out.println("Se Consulta nombre de usuario actual >>");
        String nombreUsuario="";
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(
                    " SELECT   " +
                    " CCU.NOMBRE, " +
                    " CCU.APELLIDOS  " +
                    "  FROM CC_USUARIO CCU " +
                    "  WHERE CCU.ID  ="+usuario);
            if(rs.next())
            {  
            	nombreUsuario = (rs.getString("NOMBRE")+ " "+rs.getString("APELLIDOS")).trim().toUpperCase();
            }
        }
        catch (Exception e) 
        {
             System.out.println(e);
        }
        finally
        {
             if(connection!=null){
                 try {
                     connection.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         return nombreUsuario;
	}
 
}
