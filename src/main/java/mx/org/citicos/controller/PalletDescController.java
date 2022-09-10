/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.citricos.entity.Activo;
import mx.org.citricos.entity.Calibre;
import mx.org.citricos.entity.Calidad; 
import mx.org.citricos.entity.Pallet_desc; 
/**
 * @author BID
 */
public class PalletDescController extends Conexion
{
    private int idPallet;
    private final String querySelect="SELECT  ID,ID_CALIBRE,ID_CAT_CALIDAD,CAJAS,CDI,ID_TARIMA,ID_ACTIVO FROM CC_TARIMA_DESC  WHERE ID_ACTIVO =1  AND ID_TARIMA =  " ;
    private final String querySelectEdit = "SELECT  D.ID,D.ID_CALIBRE,D.ID_CAT_CALIDAD,D.CAJAS,D.CDI,D.ID_TARIMA,D.ID_ACTIVO, TAR.DESCRIPCION TARIMA,MAR.DESCRIPCION MARCA  " +
                        " FROM CC_TARIMA_DESC D,   CC_TARIMA T, CC_CAT_TARIMA TAR, CC_CAT_MARCA MAR " +
                        " WHERE  T.ID   = D.ID_TARIMA " +
                        " AND    TAR.ID = T.ID_CAT_TARIMA " +
                        " AND    MAR.ID = T.ID_CAT_TARIMA " +
                        " AND    D.ID_ACTIVO =1   " +
                        " AND    D.ID = ";
    private final String querySelectTR="SELECT  ID,ID_CALIBRE,ID_CAT_CALIDAD,CAJAS,CDI,ID_TARIMA,ID_ACTIVO FROM CC_TARIMA_DESC  WHERE  ID_TARIMA =  " ;
    private final String querySelectOne="SELECT  ID,ID_CALIBRE,ID_CAT_CALIDAD,CAJAS,CDI,ID_TARIMA,ID_ACTIVO FROM CC_TARIMA_DESC  WHERE ID_ACTIVO =1 AND ID = " ;
    private final String queryMax="SELECT MAX(ID) ID FROM CC_TARIMA_DESC ";
    private final String queryDeleteUser0000="UPDATE CC_TARIMA_DESC SET ID_ACTIVO = 3 , MODIFICADOPOR =?, MODIFICADO=CURDATE() WHERE ID = ?  ";
    private final String queryDeleteUser="DELETE from CC_TARIMA_DESC WHERE ID = ?  ";
    private final String queryUpdate="UPDATE CC_TARIMA_DESC SET CAJAS =? ,ID_CALIBRE =? ,ID_CAT_CALIDAD =?,CDI =? ,ID_ACTIVO =?, MODIFICADOPOR =?, MODIFICADO=CURDATE() WHERE ID = ?  ";    
    private final String queryCreateUser="INSERT INTO CC_TARIMA_DESC  (CAJAS,ID_CALIBRE,ID_CAT_CALIDAD,CDI,ID_TARIMA,MODIFICADOPOR,CREADO,MODIFICADO,ID_ACTIVO) VALUES(?,?,?,?,?,?,NOW(),NOW(),1)";
    
    public PalletDescController()
    {
    }
    
    public PalletDescController(int i)
    {
        this.idPallet=i;
    }
     
    private int getMax()
    {
        int maximo=0;
        Connection connection=null;
        try 
        {   
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(queryMax);
            if(rs.next())
            { 
                maximo =(rs.getInt("ID"));
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
    /**
     * Proceso para la consulta de un cliente
     * @return 
     */ 
    public ArrayList<Pallet_desc> getAll()
    {
        int cajas  =0;
        ArrayList<Pallet_desc> l=new ArrayList<Pallet_desc>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect+this.idPallet);
            
            while(rs.next())
            {
                Pallet_desc bean=new Pallet_desc();
                bean.setId(rs.getInt("ID"));
                bean.setIdx(rs.getInt("ID"));
                bean.setIdcalibre(rs.getInt("ID_CALIBRE"));
                bean.setIdcalidad(rs.getInt("ID_CAT_CALIDAD"));
                bean.setCajas(rs.getInt("CAJAS"));
                bean.setCdi(rs.getString("CDI"));
                bean.setIdtarima(rs.getInt("ID_TARIMA"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO")); 
                cajas  = + rs.getInt("CAJAS");
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
        PalletController ctr=new PalletController();
        ctr.updateRecordBox(this.idPallet, cajas);
        return l;
    }
    /**
     * Proceso para la consulta de un clientev
     * @return 
     */ 
    public ArrayList<Pallet_desc> getAll(int i, HashMap m)
    {
        int cajas  =0;
        ArrayList<Pallet_desc> l=new ArrayList<Pallet_desc>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectTR+i);
            
            while(rs.next())
            {
                Pallet_desc bean=new Pallet_desc();
                bean.setId(rs.getInt("ID"));
                bean.setIdx(rs.getInt("ID"));
                bean.setIdcalibre(rs.getInt("ID_CALIBRE"));
                bean.setIdcalidad(rs.getInt("ID_CAT_CALIDAD"));
                bean.setCajas(rs.getInt("CAJAS"));
                bean.setCdi(rs.getString("CDI"));
                bean.setIdtarima(rs.getInt("ID_TARIMA"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO")); 
                bean.setActivo((String) m.get((new Integer(rs.getInt("ID_ACTIVO")))));
                CalidadController cdc=new CalidadController();
                CalibreController cec=new CalibreController();
                bean.setCalibre(((Calibre)cec.getOne(bean.getIdcalibre())).getNombre());
                bean.setCalidad(((Calidad)cdc.getOne(bean.getIdcalidad())).getNombre());
                cajas  = + rs.getInt("CAJAS");
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
        PalletController ctr=new PalletController();
        ctr.updateRecordBox(this.idPallet, cajas);
        return l;
    }
    /**
     * Proceso para la consulta de un cliente
     * @param id
     * @return 
     */ 
    public ArrayList<Pallet_desc> getAll(int id)
    {
        int cajas  =0;
        
        ArrayList<Pallet_desc> l=new ArrayList<Pallet_desc>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect+id);
            
            while(rs.next())
            {
                Pallet_desc bean=new Pallet_desc();
                bean.setId(rs.getInt("ID"));
                bean.setIdx(rs.getInt("ID"));
                bean.setIdcalibre(rs.getInt("ID_CALIBRE"));
                bean.setIdcalidad(rs.getInt("ID_CAT_CALIDAD"));
                bean.setCajas(rs.getInt("CAJAS"));
                bean.setCdi(rs.getString("CDI"));
                bean.setIdtarima(rs.getInt("ID_TARIMA"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO")); 
                cajas  = + rs.getInt("CAJAS");
                CalidadController calidadCTL=new CalidadController();
                Calidad cld=(calidadCTL.getOne(rs.getInt("ID_CAT_CALIDAD")));
                CalibreController calibreCTL=new CalibreController();
                Calibre clb=(calibreCTL.getOne(rs.getInt("ID_CALIBRE")));
                ActivoController activoCTL=new ActivoController();
                Activo cla=(activoCTL.getOne(rs.getInt("ID_ACTIVO")));
                if(clb.getId()!=null)
                	bean.setCalibre(clb.getId()+".-"+ clb.getNombre());
                if(cld.getId()!=null)
                	bean.setCalidad(cld.getId()+".-"+ cld.getNombre());
                if(cla.getId()!=null)
                	bean.setActivo(cla.getId()+".-"+ cla.getNombre());
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
        PalletController ctr=new PalletController();
        ctr.updateRecordBox(this.idPallet, cajas);
        return l;
    }
    
    /**
     * Proceso para la consulta de un cliente
     * @param id
     * @param tarima
     * @return 
     */ 
    public Pallet_desc getOne(int id,int tarima)
    {
        Pallet_desc bean=new Pallet_desc();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne +(""+id + " AND ID_TARIMA ="+tarima ));
            if (rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setIdx(rs.getInt("ID"));
                bean.setIdcalibre(rs.getInt("ID_CALIBRE"));
                bean.setIdcalidad(rs.getInt("ID_CAT_CALIDAD"));
                bean.setCajas(rs.getInt("CAJAS"));
                bean.setCdi(rs.getString("CDI"));
                bean.setIdtarima(rs.getInt("ID_TARIMA"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO"));
                CalidadController calidadCTL=new CalidadController();
                Calidad cld=(calidadCTL.getOne(rs.getInt("ID_CAT_CALIDAD")));
                CalibreController calibreCTL=new CalibreController();
                Calibre clb=(calibreCTL.getOne(rs.getInt("ID_CALIBRE")));
                ActivoController activoCTL=new ActivoController();
                Activo cla=(activoCTL.getOne(rs.getInt("ID_ACTIVO")));
                if(clb.getId()!=null)
                	bean.setCalibre(clb.getNombre()+"|"+clb.getId());
                if(cld.getId()!=null)
                	bean.setCalidad(cld.getNombre()+"|"+cld.getId());
                if(cla.getId()!=null)
                	bean.setActivo(cla.getNombre()+"|"+cla.getId());
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
     * Proceso para la consulta de un cliente
     * @param id
     * @param tarima
     * @return 
     */ 
    public Pallet_desc getOneEdit(int id)
    {
        Pallet_desc bean=new Pallet_desc();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectEdit + id );
            if (rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setIdx(rs.getInt("ID"));
                bean.setIdcalibre(rs.getInt("ID_CALIBRE"));
                bean.setIdcalidad(rs.getInt("ID_CAT_CALIDAD"));
                bean.setCajas(rs.getInt("CAJAS"));
                bean.setCdi(rs.getString("CDI"));
                bean.setIdtarima(rs.getInt("ID_TARIMA"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO"));
                if(rs.getString("TARIMA")!=null)
                	bean.setTarima(rs.getString("TARIMA"));
                else
                	bean.setTarima("");
                if(rs.getString("MARCA")!=null)
                	bean.setMarca (rs.getString("MARCA"));
                else
                	bean.setMarca("");
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
     * Proceso para el borrado de registro.
     * @param id
     * @param cajas
     * @param calibre
     * @param calidad
     * @param cdi
     * @param usuario
     * @param activo
     * @return numero de registros dados de alta.
     * id.intValue(), cajas.intValue(), calibreInt, calidadInt, cdi,activoInt, 1
     */
    public int updateRecor(int id,int cajas,int calibre,int calidad, String cdi ,int activo,int usuario )
    {
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setInt(1, cajas);
            ps.setInt(2, calibre); 
            ps.setInt(3, calidad); 
            ps.setString(4, cdi); 
            ps.setInt(5, activo);
            ps.setInt(6, usuario);
            ps.setInt(7, id); 
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
    public int deleteRecord(int id,int id_user)
    {
        System.out.println("deleteRecord("+id+")");
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryDeleteUser);
            //ps.setInt(1, id_user);
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
     * @param idUsuario
     * @return numero de registros dados de alta.
     * CAJAS,ID_CALIBRE,ID_CAT_CALIDAD,CDI,ID_TARIMA,CREADOPOR,MODIFICADOPOR,CREADO,MODIFICADO
     */
    public int  insertRecord(int cajas,int calibre,int calidad,String cdi,int tarima,int idUsuarioi)
    {
        //CAJAS,ID_CALIBRE,ID_CAT_CALIDAD,CDI,ID_TARIMA,CREADOPOR,MODIFICADOPOR,CREADO,MODIFICADO
        System.out.println("insertRecord("+cajas+","+tarima+","+cdi+","+idUsuarioi+", )");
        int insertCont=0;
        int maximo=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreateUser);
            ps.setInt(1, cajas);
            ps.setInt(2, calibre);
            ps.setInt(3, calidad);
            ps.setString(4, cdi);
            ps.setInt(5, tarima);
            ps.setInt(6, idUsuarioi); 
            insertCont=ps.executeUpdate();
            if(insertCont==1)
            {
                maximo  =  getMax();
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
        return maximo;
    }

    public int getIdPallet() {
        return idPallet;
    }

    public void setIdPallet(int idPallet) {
        this.idPallet = idPallet;
    }

    public Pallet_desc creaNuevo(int idPalet) 
    {
        CalibreController calibres=new CalibreController();
        CalidadController calidades=new CalidadController();
        Calibre calibre = calibres.getAll().get(0);
        Calidad calidad = calidades.getAll().get(0);
        int cajas=0;
        String cdi="";
        int tarima=idPallet;
        int idUsuarioi=1;
        int palletD= insertRecord(cajas,calibre.getId(),calidad.getId(),cdi,tarima,idUsuarioi);
        Pallet_desc desc= getOne(palletD,idPallet);
        return desc;
    }
    
    
}
