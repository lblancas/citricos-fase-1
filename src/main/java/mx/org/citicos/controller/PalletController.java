package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Pallet;
import mx.org.citricos.entity.Precios;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.citricos.entity.Marca;
import mx.org.citricos.entity.Tarima;
import mx.org.citricos.entity.Transporte;
public class PalletController extends Conexion
{
    TarimaController     tarimaC     = new TarimaController();
    MarcaController      marcaC      = new MarcaController();
    TransporteController transporteC = new TransporteController();
    private final String querySelect="SELECT  ID,QR,NUMERO,CAJAS,ID_ACTIVO,ID_CAT_TARIMA,ID_CAT_MARCA,ID_TRANSPORTE FROM CC_TARIMA  WHERE ID_ACTIVO =1  ORDER BY ID DESC";
    private final String querySelect_T=
    		"SELECT  ID,QR,NUMERO,CAJAS,ID_ACTIVO,ID_CAT_TARIMA,ID_CAT_MARCA,ID_TRANSPORTE FROM CC_TARIMA  WHERE ID_ACTIVO = 1 AND   id_tt=  "; 
    private final String querySelectTR="SELECT  ID,QR,NUMERO,CAJAS,ID_ACTIVO,ID_CAT_TARIMA,ID_CAT_MARCA,ID_TRANSPORTE FROM CC_TARIMA";
    private final String queryMax="SELECT MAX(ID) ID FROM CC_TARIMA";
    private final String queryPalletDescFinal="SELECT ID_TARIMA FROM CC_TARIMA_DESC WHERE ID =";
    private final String querySelectOne=
    		" SELECT  C.ID,C.QR,C.NUMERO,C.CAJAS,C.ID_ACTIVO,C.ID_CAT_TARIMA,C.ID_CAT_MARCA,C.ID_TRANSPORTE,M.DESCRIPCION MARCAX, T.DESCRIPCION TARIMAX , " + 
    		" CONCAT(CONCAT('[',CTT.NUMERO),CONCAT('] ',	UPPER(CTTA.NOMBRE)))  " +  
    		" TRANSPORTE, " + 
    		" CTT.ID_TRANSPORTISTA ,  CTTA.ID " + 
    		" FROM CC_TARIMA C , CC_CAT_MARCA M, CC_CAT_TARIMA T ,CC_TRANSPORTE CTT, CC_TRANSPORTISTA CTTA " + 
    		" WHERE C.ID_ACTIVO =1  " + 
    		" AND C.ID_CAT_TARIMA =T.ID  " + 
    		" AND  C.ID_CAT_MARCA =  M.ID " + 
    		" AND  C.ID_TRANSPORTE = CTT.ID  " + 
    		" AND  CTT.ID_TRANSPORTISTA =  CTTA.ID " + 
    		" AND   C.ID =  ";
    private final String querySelectOneNum=
    		" SELECT  C.ID,C.QR,C.NUMERO,C.CAJAS,C.ID_ACTIVO,C.ID_CAT_TARIMA,C.ID_CAT_MARCA,C.ID_TRANSPORTE,M.DESCRIPCION MARCAX, T.DESCRIPCION TARIMAX , " + 
    		" CONCAT(CTT.NUMERO,' ',	UPPER(CTTA.NOMBRE)) TRANSPORTE,  CTT.ID_TRANSPORTISTA ,  CTTA.ID  " + 
    		" FROM CC_TARIMA C , CC_CAT_MARCA M, CC_CAT_TARIMA T ,CC_TRANSPORTE CTT, CC_TRANSPORTISTA CTTA " + 
    		" WHERE C.ID_ACTIVO =1  " + 
    		" AND C.ID_CAT_TARIMA =T.ID  " + 
    		" AND  C.ID_CAT_MARCA =  M.ID " + 
    		" AND  C.ID_TRANSPORTE = CTT.ID  " + 
    		" AND  CTT.ID_TRANSPORTISTA =  CTTA.ID " + 
    		" AND   C.NUMERO =  ";
    private final String queryUpdate="UPDATE CC_TARIMA SET CAJAS=?,ID_CAT_TARIMA=?,ID_CAT_MARCA=?, MODIFICADOPOR=?, MODIFICADO=NOW() WHERE ID = ? ";
    private final String queryUpdateTrans="UPDATE CC_TARIMA SET CAJAS=?,ID_CAT_TARIMA=?,ID_CAT_MARCA=?, MODIFICADOPOR=?, MODIFICADO=NOW(),id_transporte=? WHERE ID = ? ";
    private final String queryUpdateTransporte="UPDATE CC_TARIMA SET  ID_TRANSPORTE=?,MODIFICADOPOR=? , MODIFICADO=NOW() WHERE ID = ? ";
    //private final String queryUpdateQR="UPDATE CC_TARIMA SET QR =? , MODIFICADO=NOW() WHERE ID = ? ";
    private final String queryUpdateNumber="UPDATE CC_TARIMA SET  NUMERO = CONCAT(CONCAT('PLL',DATE_FORMAT(NOW(),'%Y%m%d')),LPAD(ID,9,'0')) , MODIFICADO=NOW() WHERE ID = ? ";
    private final String queryDeleteUser0001="UPDATE CC_TARIMA SET ID_ACTIVO = 3 , MODIFICADOPOR =?, MODIFICADO=CURDATE() WHERE ID = ?  ";
    private final String queryDeleteUser="DELETE from CC_TARIMA  WHERE ID = ? ";
    private final String queryCreateUser="INSERT INTO CC_TARIMA  (CAJAS,ID_CAT_TARIMA,ID_CAT_MARCA,CREADOPOR,MODIFICADOPOR,CREADO,MODIFICADO) VALUES(?,?,?,?,?,NOW(),NOW())";
    private final String queryCreateUserTrans="INSERT INTO CC_TARIMA  (CAJAS,ID_CAT_TARIMA,ID_CAT_MARCA,CREADOPOR,MODIFICADOPOR,CREADO,MODIFICADO,id_transporte) VALUES(?,?,?,?,?,NOW(),NOW(),?)";
    private final String queryUpdateRecordBox="UPDATE CC_TARIMA  SET CAJAS = ?   WHERE ID = ?  ";
     
    /**
     * Proceso para el borrado de registro.
     * @param id
     * @param cajas
     * @return numero de registros dados de alta.
     */
    public int updateRecordBox(int id,int cajas)
    {
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdateRecordBox);
            ps.setInt(1, cajas);
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
    private Pallet getMax()
    {
        Pallet bean=new Pallet();
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
    public int getIdPallet(int idPalletDesc)
    {
        System.out.println("getIdPallet("+ idPalletDesc+")");
        int idPallet=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(queryPalletDescFinal + idPalletDesc);
            if(rs.next())
            { 
                idPallet = rs.getInt("ID");
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
        return idPallet;
    }
    /**
     * Proceso para la consulta de un cliente
     * @param id
     * @return 
     */
    public Pallet getOne(int id)
    {
        Pallet bean=new Pallet();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+id);
            if(rs.next())
            { 
                bean.setId(rs.getInt("ID"));
                bean.setQr(rs.getString("QR"));
                bean.setCajas(rs.getInt("CAJAS"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO"));
                bean.setIdtarima(rs.getInt("ID_CAT_TARIMA"));
                bean.setIdmarca(rs.getInt("ID_CAT_MARCA"));
                bean.setIdtransporte(rs.getInt("ID_TRANSPORTE"));
                bean.setTransporte(rs.getString("TRANSPORTE"));
                bean.setNumero(rs.getString("NUMERO"));
                bean.setMarca(rs.getString("MARCAX"));
                bean.setTarima(rs.getString("TARIMAX"));
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
    public Pallet getNumero(String id)
    {
        Pallet bean=new Pallet();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOneNum+"'"+id+"'");
            if(rs.next())
            { 
                bean.setId(rs.getInt("ID"));
                bean.setQr(rs.getString("QR"));
                bean.setCajas(rs.getInt("CAJAS"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO"));
                bean.setIdtarima(rs.getInt("ID_CAT_TARIMA"));
                bean.setIdmarca(rs.getInt("ID_CAT_MARCA"));
                bean.setIdtransporte(rs.getInt("ID_TRANSPORTE"));
                bean.setTransporte(rs.getString("TRANSPORTE"));
                bean.setNumero(rs.getString("NUMERO"));
                bean.setMarca(rs.getString("MARCAX"));
                bean.setTarima(rs.getString("TARIMAX"));
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
    public Precios getPrecio()
    {
        System.out.println("getPrecios()");
        Precios p=new Precios();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery("select " + 
            		" id,Verde_Japon,Verde_110,Verde_150,Verde_175,Verde_200,Verde_230,Verde_250 " + 
            		" ,Empaque_110,Empaque_150,Empaque_175,Empaque_200,Empaque_230,Empaque_250, " + 
            		" segundas,terceras,torreon,coleada " + 
            		" from cc_precios where id  = (select max(id) from cc_precios)");
            while(rs.next())
            {
                p.setId(rs.getInt("id"));
                p.setEmpaque_110(rs.getDouble("empaque_110"));
                p.setEmpaque_150(rs.getDouble("empaque_150"));
                p.setEmpaque_175(rs.getDouble("empaque_175"));
                p.setEmpaque_200(rs.getDouble("empaque_200"));
                p.setEmpaque_230(rs.getDouble("empaque_230"));
                p.setEmpaque_250(rs.getDouble("empaque_250"));
                p.setVerde_110(rs.getDouble("verde_110"));
                p.setVerde_150(rs.getDouble("verde_150"));
                p.setVerde_175(rs.getDouble("verde_175"));
                p.setVerde_200(rs.getDouble("verde_200"));
                p.setVerde_230(rs.getDouble("verde_230"));
                p.setVerde_250(rs.getDouble("verde_250"));
                p.setTerceras(rs.getDouble("terceras"));
                p.setSegundas(rs.getDouble("segundas"));
                p.setTorreon(rs.getDouble("torreon"));
                p.setVerde_japon(rs.getDouble("verde_japon"));
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
        return p;
    }
    public List<String> getProductor()
    {
        System.out.println("getPrecios()");
        List<String> lista=new ArrayList<String>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(" select concat('',u.id,'.-','[',u.login,'] ',u.nombre,' ',u.apellidos,'->',p.nombre ) productor from cc_usuario u , cc_perfil p\r\n" + 
            		"where u.id_Perfil =  p.id ");
            while(rs.next())
            {
                String precio = rs.getString("precio");
                lista.add(precio);
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
        return lista;
    }
    public List<String> getPrecios()
    {
        System.out.println("getPrecios()");
        List<String> lista=new ArrayList<String>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery("select " + 
            		" concat (id,'.-', " + 
            		" DATE_FORMAT(modificado, \"%Y-%m-%d\"), " + 
            		" ' V$ ',FORMAT((Verde_japon +Verde_110+Verde_150+Verde_175+Verde_200+Verde_230+Verde_250)/7,2), " + 
            		" ' E$ ',FORMAT((Empaque_110+Empaque_150+Empaque_175+Empaque_200+Empaque_230+Empaque_250)/6,2), " + 
            		" ' S$ ',segundas, " + 
            		" ' T$ ',terceras, " + 
            		" ' R$ ',torreon, " + 
            		" ' C$ ',coleada) precio" + 
            		" from cc_precios order by id desc ;");
            while(rs.next())
            {
                String precio = rs.getString("precio");
                lista.add(precio);
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
        return lista;
    }   
    public ArrayList<Pallet> getAll()
    {
        ArrayList<Pallet> l=new ArrayList<Pallet>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                Pallet bean=new Pallet();
                bean.setId(rs.getInt("ID"));
                bean.setQr(rs.getString("QR"));
                bean.setCajas(rs.getInt("CAJAS"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO"));
                bean.setIdtarima(rs.getInt("ID_CAT_TARIMA"));
                bean.setIdmarca(rs.getInt("ID_CAT_MARCA"));
                bean.setIdtransporte(rs.getInt("ID_TRANSPORTE"));
                bean.setNumero(rs.getString("NUMERO"));
                bean.setTarima(((Tarima)tarimaC.getOne(bean.getIdtarima())).getNombre());
                bean.setMarca(((Marca)marcaC.getOne(bean.getIdmarca())).getNombre());
                bean.setTransporte(((Transporte)transporteC.getOne(bean.getIdtransporte())).getNumero());
                PalletDescController detController=new PalletDescController(rs.getInt("ID"));
                bean.setDetalle(detController.getAll());
        
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
    public ArrayList<Pallet> getAllT(int id)
    {
        ArrayList<Pallet> l=new ArrayList<Pallet>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect_T+id+ " ORDER BY ID DESC");
            while(rs.next())
            {
                Pallet bean=new Pallet();
                bean.setId(rs.getInt("ID"));
                bean.setQr(rs.getString("QR"));
                bean.setCajas(rs.getInt("CAJAS"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO"));
                bean.setIdtarima(rs.getInt("ID_CAT_TARIMA"));
                bean.setIdmarca(rs.getInt("ID_CAT_MARCA"));
                bean.setIdtransporte(rs.getInt("ID_TRANSPORTE"));
                bean.setNumero(rs.getString("NUMERO"));
                bean.setTarima(((Tarima)tarimaC.getOne(bean.getIdtarima())).getNombre());
                bean.setMarca(((Marca)marcaC.getOne(bean.getIdmarca())).getNombre());
                bean.setTransporte(((Transporte)transporteC.getOne(bean.getIdtransporte())).getNumero());
                PalletDescController detController=new PalletDescController(rs.getInt("ID"));
                bean.setDetalle(detController.getAll());
        
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
    public ArrayList<Pallet> getAll(HashMap m)
    {
        ArrayList<Pallet> l=new ArrayList<Pallet>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectTR);
            while(rs.next())
            {
                Pallet bean=new Pallet();
                bean.setId(rs.getInt("ID"));
                bean.setQr(rs.getString("QR"));
                bean.setCajas(rs.getInt("CAJAS"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO"));
                bean.setIdtarima(rs.getInt("ID_CAT_TARIMA"));
                bean.setIdmarca(rs.getInt("ID_CAT_MARCA"));
                bean.setIdtransporte(rs.getInt("ID_TRANSPORTE"));
                bean.setNumero(rs.getString("NUMERO"));
                bean.setActivos((String) m.get((new Integer(rs.getInt("ID_ACTIVO")))));
                bean.setTarima(((Tarima)tarimaC.getOne(bean.getIdtarima())).getNombre());
                bean.setMarca(((Marca)marcaC.getOne(bean.getIdmarca())).getNombre());
                bean.setTransporte(((Transporte)transporteC.getOne(bean.getIdtransporte())).getNumero());
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
    /**
     * Proceso para la modificación de registro.  
     * @param transporte
     * @param usuario
     * @param id
     * @return numero de registros dados de alta.
     */
    public int updateRecordTransporte(int transporte,int usuario,int id)
     {   
        System.out.println("updateRecord("+transporte+","+usuario+","+id+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdateTransporte);
            ps.setInt(1, transporte);
            ps.setInt(2, usuario);
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
    /**
     * Proceso para la modificación de registro. 
     * @param cajas
     * @param tarima
     * @param marca
     * @param usuariom
     * @param id
     * @return numero de registros dados de alta.
     */
    public int updateRecord(int cajas,int tarima,int marca,int usuariom,int id)
     {   
        System.out.println("updateRecord("+cajas+","+tarima+","+marca+","+id+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            //UPDATE CC_TARIMA SET CAJAS=?,ID_CAT_TARIMA=?,ID_CAT_MARCA=?, MODIFICADOPOR=? WHERE ID = ?
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setInt(1, cajas);
            ps.setInt(2, tarima);
            ps.setInt(3, marca); 
            ps.setInt(4, usuariom);            
            ps.setInt(5, id);
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
     * Proceso para la modificación de registro. 
     * @param cajas
     * @param tarima
     * @param marca
     * @param usuariom
     * @param id
     * @return numero de registros dados de alta.
     */
    public int updateRecord(int cajas,int tarima,int marca,int usuariom,int id,int idTransporte)
     {   
        System.out.println("updateRecord("+cajas+","+tarima+","+marca+","+id+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            //UPDATE CC_TARIMA SET CAJAS=?,ID_CAT_TARIMA=?,ID_CAT_MARCA=?, MODIFICADOPOR=? WHERE ID = ?
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdateTrans);
            ps.setInt(1, cajas);
            ps.setInt(2, tarima);
            ps.setInt(3, marca); 
            ps.setInt(4, usuariom);
            ps.setInt(5, idTransporte);
            ps.setInt(6, id);
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
     * Proceso para la modificación de registro. 
     * @param id
     * @return numero de registros dados de alta.
     */
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
    /**
     * Proceso para el borrado de registro.
     * @param id
     * @param id_user
     * @return numero de registros dados de alta.
     */
    public int deleteRecord(int id,int id_user)
    {
        System.out.println("deleteRecord("+id+")");
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryDeleteUser);
            ps.setInt(1, id_user);
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
    /**
     * Proceso para el borrado de registro.
     * @param id
     * @param id_user
     * @return numero de registros dados de alta.
     */
    public int deleteRecordPallet(int id,int id_user)
    {
        System.out.println("deleteRecord("+id+")");
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryDeleteUser);
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
     * @param cajas
     * @param tarima
     * @param marca
     * @return numero de registros dados de alta.
     * CAJAS,ID_CAT_TARIMA,ID_CAT_MARCA
     */
    public Pallet  insertRecord(int cajas,int tarima,int marca,int idUsuarioi, int idUsuariom)
    {
        //CAJAS,ID_CAT_TARIMA,ID_CAT_MARCA,CREADOPOR,MODIFICADOPOR
        System.out.println("insertRecord("+cajas+","+tarima+","+marca+","+idUsuarioi+","+idUsuariom+")");
        int insertCont=0;
        Pallet bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreateUser);
            ps.setInt(1, cajas);
            ps.setInt(2, tarima);
            ps.setInt(3, marca);
            ps.setInt(4, idUsuarioi);
            ps.setInt(5, idUsuariom); 
            insertCont=ps.executeUpdate();
            if(insertCont==1)
            {
                bean  =  getMax();
            }
            ps.close();
            updateRecord(bean.getId());
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
     * Proceso para el guardado de registro.
     * @param cajas
     * @param tarima
     * @param marca
     * @return numero de registros dados de alta.
     * CAJAS,ID_CAT_TARIMA,ID_CAT_MARCA
     */
    public Pallet  insertRecord(int cajas,int tarima,int marca,int idUsuarioi, int idUsuariom,int idTransporte)
    {
        //CAJAS,ID_CAT_TARIMA,ID_CAT_MARCA,CREADOPOR,MODIFICADOPOR
        System.out.println("insertRecord("+cajas+","+tarima+","+marca+","+idUsuarioi+","+idUsuariom+")");
        int insertCont=0;
        Pallet bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreateUserTrans);
            ps.setInt(1, cajas);
            ps.setInt(2, tarima);
            ps.setInt(3, marca);
            ps.setInt(4, idUsuarioi);
            ps.setInt(5, idUsuariom);
            ps.setInt(6, idTransporte); 
            insertCont=ps.executeUpdate();
            
            if(insertCont==1)
            {
                bean  =  getMax();
            }
            ps.close();
            updateRecord(bean.getId());
            bean  =  getMax();
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