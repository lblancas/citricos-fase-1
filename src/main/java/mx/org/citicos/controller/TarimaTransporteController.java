package mx.org.citicos.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.TarimaTransporte;

public class TarimaTransporteController extends Conexion
{
	String querySelect="SELECT  ID,NUMERO,ID_TRANSPORTE,ID_ESTATUS FROM CC_TARIMA_TRANSPORTE WHERE ID_ESTATUS <> 0 "; 
    String queryMax="SELECT MAX(ID) ID FROM CC_TARIMA_TRANSPORTE";
    String querySelectOne="SELECT  ID,NUMERO,ID_TRANSPORTE,ID_ESTATUS FROM CC_TARIMA_TRANSPORTE WHERE ID = ";
    String querySelectOneforNum="SELECT  ID,NUMERO,ID_TRANSPORTE,ID_ESTATUS FROM CC_TARIMA_TRANSPORTE WHERE NUMERO = ";
    String queryDelete="UPDATE CC_TARIMA_TRANSPORTE   SET ID_ESTATUS =0,MODIFICADO=now(),MODIFICADOPOR=? WHERE ID = ? ";
    String queryUpdateNumber="UPDATE CC_TARIMA_TRANSPORTE SET  NUMERO = CONCAT(CONCAT('TARTTE',DATE_FORMAT(NOW(),'%Y%m%d')),LPAD(ID,9,'0')) WHERE ID = ? ";
    String queryUpdate="UPDATE CC_TARIMA_TRANSPORTE SET ID_TRANSPORTE=?,MODIFICADO=now(),MODIFICADOPOR=? WHERE ID = ?";
    String queryCreate="INSERT INTO CC_TARIMA_TRANSPORTE (ID,NUMERO,ID_TRANSPORTE,ID_ESTATUS,CREADO,MODIFICADO,CREADOPOR,MODIFICADOPOR) "
    		+ " VALUES (?,?,?,1,NOW(),NOW(),?,?) ";		
    public int getMaximo()
    { 
        Connection connection=null;
        int maximo =0;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(queryMax);
            if(rs.next())
            { 
            	maximo=rs.getInt("ID");
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
        return maximo;
    }
    public TarimaTransporte getMax()
    { 
        int maximo  = getMaximo();
        return getOne(maximo);
    }
    private  int updateRecord(int id)
    {  
       System.out.println("updateRecord("+ id+")");
       int updeteCont=0;
       Connection connection=null;
       try 
       { 
           connection=get_connection();
           PreparedStatement ps=connection.prepareStatement(queryUpdateNumber); 
           ps.setInt(1, id);
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
    public ArrayList<TarimaTransporte> getAll()
    {
        ArrayList<TarimaTransporte> l=new ArrayList<TarimaTransporte>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
            	TarimaTransporte bean=new TarimaTransporte();
                bean.setId(rs.getInt("ID"));
                bean.setNumero(rs.getString("NUMERO"));
                bean.setIdTransporte(rs.getInt("ID_TRANSPORTE"));
                bean.setIdEstatus(rs.getInt("ID_ESTATUS")); 
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
    public String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);
     
        return sb.toString();
    }
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
    public TarimaTransporte  modificar(int id,int transporte,int usuario)
    {
        System.out.println("queryUpdate TarimaTransporte ("+transporte+","+usuario+")");
        int insertCont=0;
        TarimaTransporte bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection(); 	
            
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setInt(1, transporte); 
            ps.setInt(2, usuario);
            ps.setInt(3, id);
            insertCont=ps.executeUpdate();
            if(insertCont==1)
            {
                bean  =  getOne(id);
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
    public TarimaTransporte  insertRecord(int transporte,int usuario)
    {
        int insertCont=0;
        TarimaTransporte bean=null;
        Connection connection=null;
        Date date =  new Date();
		String fecha= 
				date.getYear()+ 
				(date.getMonth()>=10?(""+date.getMonth()):("0"+date.getMonth()))+
				(date.getDay()>=10?(""+date.getDay()):("0"+date.getDay()));
		int maximo  =0;
        try 
        {
        	 maximo  = getMaximo()+1;
        	String numero="TARTTE"+maximo;
        	
            connection=get_connection(); 	
            PreparedStatement ps=connection.prepareStatement(queryCreate);
            ps.setInt(1, maximo);
            ps.setString(2, numero);
            ps.setInt(3, transporte); 
            ps.setInt(4, usuario);
            ps.setInt(5, usuario); 
            insertCont=ps.executeUpdate();
            
            if(insertCont==1)
            {
                updateRecord(maximo);
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
        bean =  getOne(maximo);
        System.out.println("insertRecord TarimaTransporte ("+maximo+","+bean.getNumero()+")");
        return bean;
    }
    public TarimaTransporte getOne(String numero)
    {
        TarimaTransporte bean=new TarimaTransporte();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOneforNum +"'"+ numero+"'");
            if(rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setNumero(rs.getString("NUMERO"));
                bean.setIdTransporte(rs.getInt("ID_TRANSPORTE"));
                bean.setIdEstatus(rs.getInt("ID_ESTATUS")); 
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
    public TarimaTransporte getOne(int i)
    {
        TarimaTransporte bean=new TarimaTransporte();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+i);
            if(rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setNumero(rs.getString("NUMERO"));
                bean.setIdTransporte(rs.getInt("ID_TRANSPORTE"));
                bean.setIdEstatus(rs.getInt("ID_ESTATUS")); 
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
}
