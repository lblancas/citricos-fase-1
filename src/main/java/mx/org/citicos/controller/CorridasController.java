package mx.org.citicos.controller;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Corrida;
import mx.org.citricos.entity.CorridaFolio;

// Referenced classes of package mx.org.citicos.controller:
//            TransportistaController

public class CorridasController extends Conexion
{
    String    queryUnaCorrida="SELECT f.id,CONCAT(CONCAT(P.PAIS,','),P.ESTADO) DIRECCION,\n" +
                " F.CALIDAD_EMPAQUE, F.ID,F.STATUS, F.FOLIO,F.FECHA,F.ID_PRODUCTOR,\n" +
                " F.PESO_BRUTO,F.PESO_TARA,F.PESO_NETO, F.NO_REJAS,F.ID_REJAS,\n" +
                " F.ID_TLIMON,F.ID_AGRONOMO,F.DEJO,F.OBSERVACIONES,F.SEGUNDAS,\n" +
                " F.TERCERAS,F.TORREON,F.COLEADA,F.JAPON,  (SELECT A.DESCRIPCION \n" +
                " FROM CC_AGRONOMO A WHERE F.ID_AGRONOMO  = A.ID)AGRONOMO , \n" +
                " concat(concat(concat(p.nombre,' '),concat(p.apellido_p,' ')),concat(p.apellido_m,' ')) PRODUCTOR, \n" +
                " (SELECT R.DESCRIPCION FROM CC_CAT_REJA R \n" +
                " WHERE F.ID_REJAS     = R.ID)TIPO_REJAS, \n" +
                " (SELECT L.DESCRIPCION FROM CC_CAT_TAMANO_LIMON L \n" +
                " WHERE F.ID_TLIMON    = L.ID)TIPO_LIMON ,F.COMPRADOR,F.FACTURAR \n" +
                " FROM CC_FOLIOS F, CC_PRODUCTOR P \n" +
                " WHERE  F.ID_PRODUCTOR = P.ID  AND f.id = ";
    String    querySelectCo = " SELECT f.id,CONCAT(CONCAT(P.PAIS,','),P.ESTADO) DIRECCION,\n" +
                "F.CALIDAD_EMPAQUE, F.ID,F.STATUS, F.FOLIO,F.FECHA,F.ID_PRODUCTOR,\n" +
                "F.PESO_BRUTO,F.PESO_TARA,F.PESO_NETO, F.NO_REJAS,F.ID_REJAS,\n" +
                "F.ID_TLIMON,F.ID_AGRONOMO,F.DEJO,F.OBSERVACIONES,F.SEGUNDAS,\n" +
                "F.TERCERAS,F.TORREON,F.COLEADA,F.JAPON,  (SELECT A.DESCRIPCION \n" +
                "FROM CC_AGRONOMO A WHERE F.ID_AGRONOMO  = A.ID)AGRONOMO , \n" +
                "concat(concat(concat(p.nombre,' '),concat(p.apellido_p,' ')),concat(p.apellido_m,' ')) PRODUCTOR, \n" +
                "(SELECT R.DESCRIPCION FROM CC_CAT_REJA R \n" +
                "WHERE F.ID_REJAS     = R.ID)TIPO_REJAS, \n" +
                "(SELECT L.DESCRIPCION FROM CC_CAT_TAMANO_LIMON L \n" +
                "WHERE F.ID_TLIMON    = L.ID)TIPO_LIMON ,F.COMPRADOR,F.FACTURAR \n" +
                "FROM CC_FOLIOS F, CC_PRODUCTOR P \n" +
                "WHERE  F.ID_PRODUCTOR = P.ID  AND F.ID_ACTIVO =1 AND STATUS =  ";
    String    querySelect = " SELECT C.ID, C.ID_FOLIO,ALBARAN ,\n" +
                "(select  japon from cc_folios where id = C.ID_FOLIO) VERDE_JAPON"
                + ",C.VERDE_110,C.VERDE_150,C.VERDE_175,C.VERDE_200,C.VERDE_230,C.VERDE_250," +
                "C.EMPAQUE_110,C.EMPAQUE_150,C.EMPAQUE_175,C.EMPAQUE_200,C.EMPAQUE_230,C.EMPAQUE_250," +
                "C.EUR_110,C.EUR_150,C.EUR_175,C.EUR_200,C.EUR_230,C.EUR_250 ,C.PROMEDIO1,C.PROMEDIO2, " +
                "C.SEGUNDAS,C.TERCERAS,C.TORREON,C.COLEADA, C.COMPRADOR,C.FACTURAR,  C.IDCOMPRADOR,C.IDFACTURAR,C.IDTIPO," +
                "C.PROMEDIO_ORIGINAL,C.FECHA_MODIFICACION,C.PROMEDIO_MODIFICACION, C.SUMA11,C.SUMA21," +
                "(SELECT  CONCAT(CONCAT(P.PAIS,','),P.ESTADO)DIRECCION FROM CC_FOLIOS F, CC_PRODUCTOR P  WHERE F.ID_PRODUCTOR = P.ID AND  F.ID = C.ID_FOLIO) DIRECCION " +
                "FROM CC_CORRIDAS C WHERE C.ID_FOLIO =  ";
    String    querySelectFolio = "SELECT ID, ID_FOLIO,"
                + " (select  japon from cc_folios where id = C.ID_FOLIO) VERDE_JAPON ,VERDE_110,VERDE_150,VERDE_175,VERDE_200,VERDE_230,VERDE_250,  "
                + "EMPAQUE_110,EMPAQUE_150,EMPAQUE_175,EMPAQUE_200,EMPAQUE_230,EMPAQUE_250,"
                + "EUR_110,EUR_150,EUR_175,EUR_200,EUR_230,EUR_250, "
                + "SEGUNDAS,TERCERAS,TORREON,COLEADA, COMPRADOR,FACTURAR,  IDCOMPRADOR,IDFACTURAR,IDTIPO,   "
                + "promedio_original,fecha_modificacion,promedio_modificacion ,ALBARAN   "
                + "FROM CC_CORRIDAS C WHERE ID = ";
    String    querySelectCorridas = "SELECT ID,ID_folio, "
                + "(select  japon from cc_folios where id = C.ID_FOLIO) VERDE_JAPON,VERDE_110,VERDE_150,VERDE_175,VERDE_200,VERDE_230,VERDE_250,  "
                + "EMPAQUE_110,EMPAQUE_150,EMPAQUE_175,EMPAQUE_200,EMPAQUE_230,EMPAQUE_250,"
                + "EUR_110,EUR_150,EUR_175,EUR_200,EUR_230,EUR_250, "
                + "SEGUNDAS,TERCERAS,TORREON,COLEADA, COMPRADOR,FACTURAR,  IDCOMPRADOR,IDFACTURAR,IDTIPO,"
                + "promedio_original,fecha_modificacion,promedio_modificacion    "
                + "FROM CC_CORRIDAS WHERE ID_folio = ";
    String   queryMax = "SELECT MAX(ID) ID FROM CC_CORRIDAS";
    String   queryCreate = "INSERT INTO CC_CORRIDAS (  "
                + "VERDE_JAPON,VERDE_110,VERDE_150,VERDE_175,VERDE_200,VERDE_230,VERDE_250,  "
                + "EMPAQUE_110,EMPAQUE_150,EMPAQUE_175,EMPAQUE_200,EMPAQUE_230,EMPAQUE_250, "
                + "SEGUNDAS,TERCERAS,TORREON,COLEADA,COMPRADOR,FACTURAR, IDCOMPRADOR,IDFACTURAR,IDTIPO,ID_FOLIO,ID)  "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,?,?)";
    String    queryUpdate = "UPDATE CC_CORRIDAS SET "
                + "VERDE_JAPON= ?,VERDE_110= ?,VERDE_150= ?,VERDE_175= ?,VERDE_200= ?,VERDE_230= ?,VERDE_250= ?,  "
                + "EMPAQUE_110= ?,EMPAQUE_150= ?,EMPAQUE_175= ?,EMPAQUE_200= ?,EMPAQUE_230= ?,EMPAQUE_250= ?, "
                + "EUR_110= ?,EUR_150= ?,EUR_175= ?,EUR_200= ?,EUR_230= ?,EUR_250= ?, "
                + "SEGUNDAS= ?,TERCERAS= ?,TORREON= ?,COLEADA= ?,COMPRADOR= ?,FACTURAR= ?, IDCOMPRADOR= ?,IDFACTURAR= ? ,ALBARAN= ?"
                + "WHERE ID_folio =  ? ";
    String    queryUpdateFolio = " UPDATE CC_FOLIOS SET COMPRADOR= ?,FACTURAR= ?  WHERE ID =  ? ";
    String    updatePromedioOrigen = "UPDATE CC_CORRIDAS SET "
                + "promedio_original=?,fecha_modificacion=DATE_FORMAT(NOW(),'%a %d de %M del %Y %h:%m.%s'),"
                + "promedio_modificacion=?,modificado=?,creado=?  "
                + "WHERE ID_folio =  ? ";
    String     updatePromedioModificado = "UPDATE CC_CORRIDAS SET "
                + "fecha_modificacion=DATE_FORMAT(NOW(),'%a %d de %M del %Y %h:%m.%s'),"
                + "promedio_modificacion=?,modificado=?  "
                + "WHERE ID_folio =  ? ";
    public CorridasController()
    {
    } 
    public Corrida getMax()
    {
        Corrida bean=null;
        try {
            
            Connection connection;
            bean = new Corrida();
            connection = null;
            try {
                connection = get_connection();
                
            } catch (Exception ex) {
                Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(queryMax);
            if(rs.next())
            {
                try {
                    bean = getOnebyXFolio(rs.getInt("ID"));
                } catch (Exception ex) {
                    Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            rs.close();
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
    public Corrida getOnebyXFolio(int i)
    {
        Corrida bean=null;
        try {
            
            boolean encontro;
            Connection connection;
            bean = null;
            encontro = false;
            connection = null;
            connection = get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery((new StringBuilder()).append(querySelectFolio).append(i).toString());
            if(rs.next())
            {
                bean = new Corrida();
                bean.setFolio((rs.getInt("ID_FOLIO")));
                bean.setVerde_japon((rs.getDouble("VERDE_JAPON")));
                bean.setVerde_110((rs.getDouble("VERDE_110")));
                bean.setVerde_150((rs.getDouble("VERDE_150")));
                bean.setVerde_175((rs.getDouble("VERDE_175")));
                bean.setVerde_200((rs.getDouble("VERDE_200")));
                bean.setVerde_230((rs.getDouble("VERDE_230")));
                bean.setVerde_250((rs.getDouble("VERDE_250")));
                bean.setEmpaque_110((rs.getDouble("EMPAQUE_110")));
                bean.setEmpaque_150((rs.getDouble("EMPAQUE_150")));
                bean.setEmpaque_175((rs.getDouble("EMPAQUE_175")));
                bean.setEmpaque_200((rs.getDouble("EMPAQUE_200")));
                bean.setEmpaque_230((rs.getDouble("EMPAQUE_230")));
                bean.setEmpaque_250((rs.getDouble("EMPAQUE_250")));
                
                bean.setEur_110((rs.getDouble("EUR_110")));
                bean.setEur_150((rs.getDouble("EUR_150")));
                bean.setEur_175((rs.getDouble("EUR_175")));
                bean.setEur_200((rs.getDouble("EUR_200")));
                bean.setEur_230((rs.getDouble("EUR_230")));
                bean.setEur_250((rs.getDouble("EUR_250")));
                
                bean.setSegundas((rs.getDouble("SEGUNDAS")));
                bean.setTerceras((rs.getDouble("TERCERAS")));
                bean.setTorreon((rs.getDouble("TORREON")));
                bean.setColeada((rs.getDouble("COLEADA")));
                bean.setComprador(rs.getString("COMPRADOR"));
                bean.setFacturar(rs.getString("FACTURAR"));
                bean.setIdcomprador((rs.getInt("IDCOMPRADOR")));
                bean.setIdfacturar((rs.getInt("IDFACTURAR")));
                bean.setIdtipo((rs.getInt("IDTIPO")));
                bean.setAlbaran((rs.getDouble("ALBARAN")));
            }
            rs.close();
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }

            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
    

    public Corrida getOne(int i)
    {
        
        Corrida bean=null;
        try 
        {
            Connection connection;
            bean = null;
            connection = null;
            connection = get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery((new StringBuilder()).append(querySelect).append(i).toString());
            if(rs.next())
            {
                bean = new Corrida();
                bean.setFolio((rs.getInt("ID_FOLIO")));
                bean.setVerde_japon((rs.getDouble("VERDE_JAPON")));
                bean.setVerde_110((rs.getDouble("VERDE_110")));
                bean.setVerde_150((rs.getDouble("VERDE_150")));
                bean.setVerde_175((rs.getDouble("VERDE_175")));
                bean.setVerde_200((rs.getDouble("VERDE_200")));
                bean.setVerde_230((rs.getDouble("VERDE_230")));
                bean.setVerde_250((rs.getDouble("VERDE_250")));
                bean.setEmpaque_110((rs.getDouble("EMPAQUE_110")));
                bean.setEmpaque_150((rs.getDouble("EMPAQUE_150")));
                bean.setEmpaque_175((rs.getDouble("EMPAQUE_175")));
                bean.setEmpaque_200((rs.getDouble("EMPAQUE_200")));
                bean.setEmpaque_230((rs.getDouble("EMPAQUE_230")));
                bean.setEmpaque_250((rs.getDouble("EMPAQUE_250")));
                bean.setDireccion(rs.getString("DIRECCION"));
                bean.setEur_110((rs.getDouble("EUR_110")));
                bean.setEur_150((rs.getDouble("EUR_150")));
                bean.setEur_175((rs.getDouble("EUR_175")));
                bean.setEur_200((rs.getDouble("EUR_200")));
                bean.setEur_230((rs.getDouble("EUR_230")));
                bean.setEur_250((rs.getDouble("EUR_250")));
                
                bean.setSegundas((rs.getDouble("SEGUNDAS")));
                bean.setTerceras((rs.getDouble("TERCERAS")));
                bean.setTorreon((rs.getDouble("TORREON")));
                bean.setColeada((rs.getDouble("COLEADA")));
                bean.setComprador(rs.getString("COMPRADOR"));
                bean.setFacturar(rs.getString("FACTURAR"));
                bean.setIdcomprador((rs.getInt("IDCOMPRADOR")));
                bean.setIdfacturar((rs.getInt("IDFACTURAR")));
                bean.setIdtipo((rs.getInt("IDTIPO")));
                bean.setPromedio1(rs.getDouble("promedio1"));
                bean.setPromedio2(rs.getDouble("promedio2"));
                bean.setAlbaran(rs.getDouble("albaran"));
                bean.setSubsuma1(new Double (rs.getString("suma11").replaceAll(",","" )));
                bean.setSubsuma2(new Double(rs.getString("suma21").replaceAll(",","" )));
                bean.setAlbaran((rs.getDouble("ALBARAN")));
            }
            rs.close();
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
    
    public Corrida insertaDatoVacio(int id_folio,String comprador,String facturar)
    {
        Corrida c=null;
        try {
            c= insertRecord(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 
                    0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 
                    comprador, facturar, "", 0, 0, 0, id_folio);
        } catch (Exception ex) {
            Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public Corrida insertRecord(double verde_japon, double verde_110, double verde_150, double verde_175, double verde_200, double verde_230, double verde_250, 
            double empaque_110, double empaque_150, double empaque_175, double empaque_200, double empaque_230, double empaque_250, double segundas, 
            double terceras, double torreon, double coleada, String comprador, 
            String facturar, String tipo, int idcomprador, int idfacturar, int idtipo, int id_folio)
    {
        Corrida bean=null;
        try
        {    
            Connection connection;
            int insertCont = 0;
            bean = null;
            connection = null;
            connection = get_connection();
            /*
            "INSERT INTO CC_CORRIDAS (  "
                + "VERDE_JAPON,VERDE_110,VERDE_150,VERDE_175,VERDE_200,VERDE_230,VERDE_250,  "
                + "EMPAQUE_110,EMPAQUE_150,EMPAQUE_175,EMPAQUE_200,EMPAQUE_230,EMPAQUE_250, "
                + "SEGUNDAS,TERCERAS,TORREON,COLEADA,COMPRADOR,FACTURAR, IDCOMPRADOR,IDFACTURAR,IDTIPO,ID_FOLIO,ID)  "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,?,?)"
            */
            PreparedStatement ps = connection.prepareStatement(queryCreate);
            ps.setDouble(1, verde_japon);
            ps.setDouble(2, verde_110);
            ps.setDouble(3, verde_150);
            ps.setDouble(4, verde_175);
            ps.setDouble(5, verde_200);
            ps.setDouble(6, verde_230);
            ps.setDouble(7, verde_250);
            ps.setDouble(8, empaque_110);
            ps.setDouble(9, empaque_150);
            ps.setDouble(10, empaque_175);
            ps.setDouble(11, empaque_200);
            ps.setDouble(12, empaque_230);
            ps.setDouble(13, empaque_250);
            ps.setDouble(14, segundas);
            ps.setDouble(15, terceras);
            ps.setDouble(16, torreon);
            ps.setDouble(17, coleada);
            ps.setString(18, comprador);
            ps.setString(19, facturar);
            ps.setInt   (20, idcomprador);
            ps.setInt   (21, idfacturar);
            ps.setInt   (22, id_folio);
            ps.setInt   (23, id_folio);
            insertCont = ps.executeUpdate();
            if(insertCont == 1)
            {
                bean = getOnebyXFolio(id_folio);
            }
            ps.close();
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }

            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
    
    public ArrayList getCorridas(int id)
    {
        ArrayList l = new ArrayList();
        try
        {
            Connection connection;
            connection = null;
            ResultSet rs;
            Throwable throwable;
            connection = get_connection();
            Statement st = connection.createStatement();
            rs = st.executeQuery((new StringBuilder()).append(querySelectCo).append(id).append(" order by F.FOLIO desc ").toString());
            throwable = null;
            try
            {
                CorridaFolio bean;
                for(; rs.next(); l.add(bean))
                {
                    bean = new CorridaFolio();
                    bean.setId_folio(rs.getInt("ID"));
                    bean.setFolio(rs.getString("FOLIO"));
                    bean.setEstatus((rs.getInt("STATUS")));
                    bean.setCodigo((bean.getFolio()));
                    bean.setFecha(rs.getString("FECHA"));
                    
                    bean.setComprador(rs.getString("COMPRADOR"));
                    bean.setFacturar(rs.getString("FACTURAR"));
                    bean.setFacturar(rs.getString("DIRECCION"));
                    bean.setCalidad_empaque((rs.getInt("CALIDAD_EMPAQUE")));
                    bean.setId_productor((rs.getInt("ID_PRODUCTOR")));
                    bean.setPeso_bruto(rs.getDouble("PESO_BRUTO"));
                    bean.setPeso_tara(rs.getDouble("PESO_TARA"));
                    bean.setPeso_neto(rs.getDouble("PESO_NETO"));
                    bean.setNo_rejas(rs.getDouble("NO_REJAS"));
                    bean.setTipo_rejas((rs.getInt("ID_REJAS")));
                    bean.setTipo_limon((rs.getInt("ID_TLIMON")));
                    bean.setId_agronomo((rs.getInt("ID_AGRONOMO")));
                    bean.setDejo((rs.getInt("DEJO")));
                    if(bean.getDejo().intValue() == 1)
                    {
                        bean.setIsdejo(true);
                    } else
                    {
                        bean.setIsdejo(false);
                    }
                    bean.setObservaciones(rs.getString("OBSERVACIONES"));
                    bean.setSegundas((rs.getDouble("SEGUNDAS")));
                    bean.setTerceras((rs.getDouble("TERCERAS")));
                    bean.setTorreon((rs.getDouble("TORREON")));
                    bean.setColeada((rs.getDouble("COLEADA")));
                    bean.setJapon((rs.getDouble("JAPON")));
                    bean.setProductor(rs.getString("PRODUCTOR"));
                    bean.setAgronomo(rs.getString("AGRONOMO"));
                    bean.setTipos_rejas(rs.getString("TIPO_REJAS"));
                    bean.setTipos_limones(rs.getString("TIPO_LIMON"));
                    CorridasController corridasCTR = new CorridasController();
                    Corrida corrida = corridasCTR.getOne(rs.getInt("id"));
                    bean.setVerde_japon(corrida.getVerde_japon());
                    bean.setVerde_110(corrida.getVerde_110());
                    bean.setVerde_150(corrida.getVerde_150());
                    bean.setVerde_175(corrida.getVerde_175());
                    bean.setVerde_200(corrida.getVerde_200());
                    bean.setVerde_230(corrida.getVerde_230());
                    bean.setVerde_250(corrida.getVerde_250());
                    bean.setEmpaque_110(corrida.getEmpaque_110());
                    bean.setEmpaque_150(corrida.getEmpaque_150());
                    bean.setEmpaque_175(corrida.getEmpaque_175());
                    bean.setEmpaque_200(corrida.getEmpaque_200());
                    bean.setEmpaque_230(corrida.getEmpaque_230());
                    bean.setEmpaque_250(corrida.getEmpaque_250());
                    bean.setEur_110(corrida.getEur_110());
                    bean.setEur_150(corrida.getEur_150());
                    bean.setEur_175(corrida.getEur_175());
                    bean.setEur_200(corrida.getEur_200());
                    bean.setEur_230(corrida.getEur_230());
                    bean.setEur_250(corrida.getEur_250());
                    bean.setSig_segundas(corrida.getSegundas());
                    bean.setSig_terceras(corrida.getTerceras());
                    bean.setSig_torreon(corrida.getTorreon());
                    bean.setSig_coleada(corrida.getColeada());
                    bean.setComprador(rs.getString("COMPRADOR"));
                    bean.setFacturar(rs.getString("FACTURAR"));
                    bean.setIdtipo(corrida.getIdtipo());
                    bean.setVerde_suma(getVerdesuma(corrida));
                    bean.setEmpaque_suma(getEmpaquesuma(corrida));
                    bean.setCorrida_suma(getCorridasuma(corrida));
                }
                
            }
            catch(Throwable throwable2)
            {
                throwable = throwable2;
                throw throwable2;
            }
            
            if(rs != null)
            {
                if(throwable != null)
                {
                    try
                    {
                        rs.close();
                    }
                    catch(Throwable throwable1)
                    {
                        throwable.addSuppressed(throwable1);
                    }
                } else
                {
                    rs.close();
                }
            }

            if(throwable != null)
            {
                try
                {
                    rs.close();
                }
                catch(Throwable throwable3)
                {
                    throwable.addSuppressed(throwable3);
                }
            } else
            {
                rs.close();
            }
            
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }

            
            
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            
            
        }
        catch(Exception ex)
        {
            Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public CorridaFolio getCorridaUnica(int id)
    {
        CorridaFolio bean=new CorridaFolio();
        ArrayList l = new ArrayList();
        try
        {
            Connection connection;
            connection = null;
            ResultSet rs;
            connection = get_connection();
            Statement st = connection.createStatement();
            rs = st.executeQuery(queryUnaCorrida + id);
            
            if( rs.next())
            {
                bean = new CorridaFolio();
                bean.setId_folio(rs.getInt("ID"));
                bean.setFolio(rs.getString("FOLIO"));
                bean.setEstatus((rs.getInt("STATUS")));
                bean.setCodigo((bean.getFolio()));
                bean.setFecha(rs.getString("FECHA"));

                bean.setComprador(rs.getString("COMPRADOR"));
                bean.setFacturar(rs.getString("FACTURAR"));
                bean.setFacturar(rs.getString("DIRECCION"));
                bean.setCalidad_empaque((rs.getInt("CALIDAD_EMPAQUE")));
                bean.setId_productor((rs.getInt("ID_PRODUCTOR")));
                bean.setPeso_bruto(rs.getDouble("PESO_BRUTO"));
                bean.setPeso_tara(rs.getDouble("PESO_TARA"));
                bean.setPeso_neto(rs.getDouble("PESO_NETO"));
                bean.setNo_rejas(rs.getDouble("NO_REJAS"));
                bean.setTipo_rejas((rs.getInt("ID_REJAS")));
                bean.setTipo_limon((rs.getInt("ID_TLIMON")));
                bean.setId_agronomo((rs.getInt("ID_AGRONOMO")));
                bean.setDejo((rs.getInt("DEJO")));
                if(bean.getDejo().intValue() == 1)
                {
                    bean.setIsdejo(true);
                } else
                {
                    bean.setIsdejo(false);
                }
                bean.setObservaciones(rs.getString("OBSERVACIONES"));
                bean.setSegundas((rs.getDouble("SEGUNDAS")));
                bean.setTerceras((rs.getDouble("TERCERAS")));
                bean.setTorreon((rs.getDouble("TORREON")));
                bean.setColeada((rs.getDouble("COLEADA")));
                bean.setJapon((rs.getDouble("JAPON")));
                bean.setProductor(rs.getString("PRODUCTOR"));
                bean.setAgronomo(rs.getString("AGRONOMO"));
                bean.setTipos_rejas(rs.getString("TIPO_REJAS"));
                bean.setTipos_limones(rs.getString("TIPO_LIMON"));
                CorridasController corridasCTR = new CorridasController();
                Corrida corrida = corridasCTR.getOne(rs.getInt("id"));
                bean.setVerde_japon(corrida.getVerde_japon());
                bean.setVerde_110(corrida.getVerde_110());
                bean.setVerde_150(corrida.getVerde_150());
                bean.setVerde_175(corrida.getVerde_175());
                bean.setVerde_200(corrida.getVerde_200());
                bean.setVerde_230(corrida.getVerde_230());
                bean.setVerde_250(corrida.getVerde_250());
                bean.setEmpaque_110(corrida.getEmpaque_110());
                bean.setEmpaque_150(corrida.getEmpaque_150());
                bean.setEmpaque_175(corrida.getEmpaque_175());
                bean.setEmpaque_200(corrida.getEmpaque_200());
                bean.setEmpaque_230(corrida.getEmpaque_230());
                bean.setEmpaque_250(corrida.getEmpaque_250());
                bean.setEur_110(corrida.getEur_110());
                bean.setEur_150(corrida.getEur_150());
                bean.setEur_175(corrida.getEur_175());
                bean.setEur_200(corrida.getEur_200());
                bean.setEur_230(corrida.getEur_230());
                bean.setEur_250(corrida.getEur_250());
                bean.setSig_segundas(corrida.getSegundas());
                bean.setSig_terceras(corrida.getTerceras());
                bean.setSig_torreon(corrida.getTorreon());
                bean.setSig_coleada(corrida.getColeada());
                bean.setComprador(rs.getString("COMPRADOR"));
                bean.setFacturar(rs.getString("FACTURAR"));
                bean.setIdtipo(corrida.getIdtipo());
                bean.setVerde_suma(getVerdesuma(corrida));
                bean.setEmpaque_suma(getEmpaquesuma(corrida));
                bean.setCorrida_suma(getCorridasuma(corrida));
            } 
            if(rs != null)
            {
                try
                {
                    rs.close();
                    connection.close();
                    
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        catch(Exception ex)
        {
            Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }

    public Double getVerdesuma(Corrida corrida)
    {
        double verde_suma = corrida.getVerde_japon().doubleValue() + corrida.getVerde_110().doubleValue() + corrida.getVerde_150().doubleValue() + corrida.getVerde_175().doubleValue() + corrida.getVerde_200().doubleValue() + corrida.getVerde_230().doubleValue() + corrida.getVerde_250().doubleValue();
        return (verde_suma);
    }

    public Double getEmpaquesuma(Corrida corrida)
    {
        double empaque_suma = corrida.getEmpaque_110().doubleValue() + corrida.getEmpaque_150().doubleValue() + corrida.getEmpaque_175().doubleValue() + corrida.getEmpaque_200().doubleValue() + corrida.getEmpaque_230().doubleValue() + corrida.getEmpaque_250().doubleValue();
        return (empaque_suma);
    }

    public Double getCorridasuma(Corrida corrida)
    {
        double corrida_suma = corrida.getSegundas().doubleValue() + corrida.getTerceras().doubleValue() + corrida.getTorreon().doubleValue() + corrida.getColeada().doubleValue();
        return (corrida_suma);
    }
    
    public void UpdateRecordFolio(String comprador,String facturar,int id)
    {
        try {
            Connection connection;
            connection = null;
            connection = get_connection();
            PreparedStatement ps = connection.prepareStatement(queryUpdateFolio);
            ps.setString(1, comprador);
            ps.setString(2, facturar); 
            ps.setInt(3, id);
            int updet = ps.executeUpdate();
            ps.close(); 
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 
    public void UpdateRecord(double verde_japon, 
    		double verde_110, double verde_150, double verde_175, double verde_200, double verde_230, double verde_250, 
            double empaque_110, double empaque_150, double empaque_175, double empaque_200, double empaque_230, double empaque_250,  
            double eur_110,double eur_150,double eur_175,double eur_200,double eur_230,double eur_250,
            double segundas,double terceras, double torreon, double coleada, 
            String comprador,String facturar, 
            int idcomprador, int idfacturar, 
            int idtipo, int id,double albaran)
    {
        try {
            Connection connection;
            connection = null;
            connection = get_connection();
            PreparedStatement ps = connection.prepareStatement(queryUpdate);
            ps.setDouble(1, verde_japon);
            ps.setDouble(2, verde_110);
            ps.setDouble(3, verde_150);
            ps.setDouble(4, verde_175); 
            ps.setDouble(5, verde_200);
            ps.setDouble(6, verde_230);
            ps.setDouble(7, verde_250);
            ps.setDouble(8, empaque_110);
            ps.setDouble(9, empaque_150);
            ps.setDouble(10, empaque_175);
            ps.setDouble(11, empaque_200);
            ps.setDouble(12, empaque_230);
            ps.setDouble(13, empaque_250);
            ps.setDouble(14, eur_110);
            ps.setDouble(15, eur_150);
            ps.setDouble(16, eur_175);
            ps.setDouble(17, eur_200);
            ps.setDouble(18, eur_230);
            ps.setDouble(19, eur_250);
            ps.setDouble(20, segundas);
            ps.setDouble(21, terceras);
            ps.setDouble(22, torreon);
            ps.setDouble(23, coleada);
            ps.setString(24, comprador);
            ps.setString(25, facturar);
            ps.setInt(26, idcomprador);
            ps.setInt(27, idfacturar);
            ps.setDouble(28, albaran);
            ps.setInt(29, id);
            int updet = ps.executeUpdate();
            ps.close(); 
            UpdateRecordFolio (comprador,facturar,id);
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void UpdatePromedioModificado(int id, double promedio,int usuario)
    {
        try {
            Connection connection=null;
            connection = null;
            try
            {
                connection = get_connection();
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            PreparedStatement ps = connection.prepareStatement(updatePromedioModificado);
            ps.setDouble(1, promedio);
            ps.setInt(2, usuario);
            ps.setInt(3, id);
            int updet = ps.executeUpdate();
            ps.close(); 
            
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    System.out.println(ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void UpdatePromedioOrigen(int id, double promedio,int usuario)
    {
        try {
            Connection connection=null;
            connection = null;
            try
            {
                connection = get_connection();
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            PreparedStatement ps = connection.prepareStatement(updatePromedioOrigen);
            ps.setDouble(1, promedio);
            ps.setDouble(2, promedio);
            ps.setInt(3, usuario);
            ps.setInt(4, usuario);
            ps.setInt(5, id);
            int updet = ps.executeUpdate();
            ps.close(); 
            
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    System.out.println(ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void UpdateRecord(int id, int estatus)
    {
        try 
        {
            Connection connection=null;
            connection = null;
            try
            {
                connection = get_connection();
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            String queryUpdate = "UPDATE CC_FOLIOS SET  STATUS= ?  WHERE ID =  (select  id_folio  from cc_corridas" +
                    " where id_folio = ?) "
                    ;
            PreparedStatement ps = connection.prepareStatement(queryUpdate);
            ps.setInt(1, estatus);
            ps.setInt(2, id);
            int updet = ps.executeUpdate();
            ps.close(); 
            
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException ex)
                {
                    System.out.println(ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CorridasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private String getToken(String cadena)
    {
        cadena = cadena.substring(3, 15);
        String pww = "";
        if(cadena.length() % 2 == 0)
        {
            for(int i = 0; i < cadena.length() / 2; i++)
            {
                String tok = cadena.substring(2 * i, 2 * (i + 1));
                pww = (new StringBuilder()).append(pww).append(sexty(tok)).toString();
            }

        }
        return pww;
    }

    private String sexty(String to)
    {
        int cod = (new Integer(to)).intValue();
        switch(cod)
        {
        case 0: // '\0'
            return "0";

        case 1: // '\001'
            return "1";

        case 2: // '\002'
            return "2";

        case 3: // '\003'
            return "3";

        case 4: // '\004'
            return "4";

        case 5: // '\005'
            return "5";

        case 6: // '\006'
            return "6";

        case 7: // '\007'
            return "7";

        case 8: // '\b'
            return "8";

        case 9: // '\t'
            return "9";

        case 10: // '\n'
            return "A";

        case 11: // '\013'
            return "B";

        case 12: // '\f'
            return "C";

        case 13: // '\r'
            return "D";

        case 14: // '\016'
            return "E";

        case 15: // '\017'
            return "F";

        case 16: // '\020'
            return "G";

        case 17: // '\021'
            return "H";

        case 18: // '\022'
            return "I";

        case 19: // '\023'
            return "J";

        case 20: // '\024'
            return "K";

        case 21: // '\025'
            return "L";

        case 22: // '\026'
            return "M";

        case 23: // '\027'
            return "N";

        case 24: // '\030'
            return "O";

        case 25: // '\031'
            return "P";

        case 26: // '\032'
            return "Q";

        case 27: // '\033'
            return "R";

        case 28: // '\034'
            return "S";

        case 29: // '\035'
            return "T";

        case 30: // '\036'
            return "U";

        case 31: // '\037'
            return "V";

        case 32: // ' '
            return "W";

        case 33: // '!'
            return "X";

        case 34: // '"'
            return "Y";

        case 35: // '#'
            return "Z";

        case 36: // '$'
            return "*";

        case 37: // '%'
            return "#";

        case 38: // '&'
            return "$";

        case 39: // '\''
            return "%";

        case 40: // '('
            return "&";

        case 41: // ')'
            return "/";

        case 42: // '*'
            return "(";

        case 43: // '+'
            return ")";

        case 44: // ','
            return "=";

        case 45: // '-'
            return "!";

        case 46: // '.'
            return "+";

        case 47: // '/'
            return "{";

        case 48: // '0'
            return "}";

        case 49: // '1'
            return "[";

        case 50: // '2'
            return "]";

        case 51: // '3'
            return "-";

        case 52: // '4'
            return ".";

        case 53: // '5'
            return ",";

        case 54: // '6'
            return ";";

        case 55: // '7'
            return ":";

        case 56: // '8'
            return "<";

        case 57: // '9'
            return ">";

        case 58: // ':'
            return "@";

        case 59: // ';'
            return "?";
        }
        return " ";
    }
}
