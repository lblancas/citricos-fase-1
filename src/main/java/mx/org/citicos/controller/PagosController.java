/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Pagos;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author BID
 */
public class PagosController   extends Conexion
{
   String queryTotales=" SELECT T.ID,T.ID_PRODUCTORS, SUM(MONTO)  MONTO FROM  (\n" +
                "SELECT  \n" +
                "P.ID_PRODUCTOR ID  , \n" +
                "concat(concat(concat(pr.nombre,' '),concat(pr.apellido_p,' ')),concat(pr.apellido_m,' ')) ID_PRODUCTORS, \n" +
                "(P.MONTO  * F.TIPO ) MONTO \n" +
                "FROM CC_PAGOS_ADEUDOS P , CC_CAT_TIPO_PAGO F ,CC_PRODUCTOR PR \n" +
                "WHERE F.ID =P.ID_TIPO_PAGO\n" +
                "AND  PR.ID =P.ID_PRODUCTOR \n" +
                "AND  P.ID_ACTIVO =1\n" +
                "union all \n" +
                "SELECT P.ID ,  \n" +
                "concat(concat(concat(P.nombre,' '),concat(P.apellido_p,' ')),concat(P.apellido_m,' ')) ID_PRODUCTORS,\n" +
                "CONVERT(\n" +
                "IF(CAST(REPLACE(PROMEDIO_ORIGINAL,',','') AS DECIMAL(10,2))=\n" +
                "CAST(REPLACE(PROMEDIO_MODIFICACION,',','') AS DECIMAL(10,2)),\n" +
                "CAST(REPLACE(SUMA14,',','') AS DECIMAL(10,2)) +   CAST(REPLACE(SUMA24,',','') AS DECIMAL(10,2)) ,\n" +
                "CONVERT((CAST(REPLACE(SUMA11,',','') AS DECIMAL(10,2))+ CAST(REPLACE(SUMA21,',','') AS DECIMAL(10,2)))* CAST(REPLACE(PROMEDIO_MODIFICACION,',','') AS DECIMAL(10,2)),DECIMAL(14,2))) \n" +
                ",DECIMAL(19,2))MONTO\n" +
                "FROM  CC_FOLIOS F,CC_CORRIDAS C, CC_PRODUCTOR P  WHERE F.STATUS =4 AND  F.ID =  C.ID_FOLIO AND F.ID_PRODUCTOR =  P.ID\n" +
                ")T\n" +
                "GROUP BY T.ID,T.ID_PRODUCTORS ORDER BY ID_PRODUCTORS	; ";
   String querySelect="SELECT \n" +
                " P.ID, \n" +
                " P.DESCRIPCION ,\n" +
                " P.ID_PRODUCTOR ,\n" +
                " concat(concat(concat(pr.nombre,' '),concat(pr.apellido_p,' ')),concat(pr.apellido_m,' ')) ID_PRODUCTORS,\n" +
                " FECHA,FECHA_PROMESA,\n" +
                " DATE_FORMAT( P.FECHA,'%Y-%m-%d')  FECHAS,\n" +
                " P.ID_TIPO_PAGO,\n" +
                " F.DESCRIPCION  ID_TIPO_PAGOS,\n" +
                " F.TIPO  TIPO,\n" +
                " P.MONTO ,\n" +
                " DATE_FORMAT(P.FECHA_PROMESA,'%Y-%m-%d') FECHA_PROMESAS ,\n" +
                " P.MONTO_PROMESA  ,\n" +
                " P.ID_ACTIVO ,\n" +
                " AA.NOMBRE   ID_ACTIVOS,\n" +
                " P.ID_INSUMO, \n" +
                " (SELECT M.CONCEPTO FROM CC_CAT_INSUMOS M WHERE M.ID =P.ID_INSUMO ) ID_INSUMOS\n" +
                " FROM CC_PAGOS_ADEUDOS P , CC_CAT_TIPO_PAGO F ,CC_PRODUCTOR PR,CC_ACTIVO AA\n" +
                " WHERE F.ID =P.ID_TIPO_PAGO\n" +
                " AND  PR.ID =P.ID_PRODUCTOR\n" +
                " AND AA.ID =P.ID_ACTIVO  \n" +
                " AND  p.ID_ACTIVO =1";
   String querySelectByProductor="SELECT  F.ID,  " +
                " 'Pago corrida' DESCRIPCION , " +
                " ID_PRODUCTOR ,  " +
                " concat(concat(concat(P.nombre,' '),concat(P.apellido_p,' ')),concat(P.apellido_m,' ')) ID_PRODUCTORS, " +
                " DATE_FORMAT(f.modificado,'%Y-%m-%d') FECHA , " +
                " DATE_FORMAT(f.modificado,'%Y-%m-%d') FECHA_PROMESA , " +
                " DATE_FORMAT(f.modificado,'%Y-%m-%d') FECHAS , " +
                " 8 ID_TIPO_PAGO, " +
                " concat('Pendiente a corrida : ',f.folio) ID_TIPO_PAGOS, " +
                " 1 TIPO, " +
                " IF(CAST(REPLACE(PROMEDIO_ORIGINAL,',','') AS DECIMAL(10,2))= " +
                " CAST(REPLACE(PROMEDIO_MODIFICACION,',','') AS DECIMAL(10,2)), " +
                " CAST(REPLACE(SUMA14,',','') AS DECIMAL(10,2))+CAST(REPLACE(SUMA24,',','') AS DECIMAL(10,2)) , " +
                " CONVERT((CAST(REPLACE(SUMA11,',','') AS DECIMAL(10,2)) +CAST(REPLACE(SUMA21,',','') AS DECIMAL(10,2)))* CAST(REPLACE(PROMEDIO_MODIFICACION,',','') AS DECIMAL(10,2)),DECIMAL(14,2))) MONTO, " +
                " DATE_FORMAT(f.modificado,'%Y-%m-%d') FECHA_PROMESAS , " +
                " 0  MONTO_PROMESA, " +
                " 1 ID_ACTIVO, " +
                " 'Activo' ID_ACTIVOS, " +
                " null ID_INSUMO, " +
                " null ID_INSUMOS, " +
                " 0 ACTIVO " +
                " FROM  CC_FOLIOS F,CC_CORRIDAS C, CC_PRODUCTOR P  WHERE F.STATUS =4 AND  F.ID =  C.ID_FOLIO AND F.ID_PRODUCTOR =  P.ID " +
                " AND  F.ID_PRODUCTOR =?  " +
                " union all  " +
                " SELECT " +
                " P.ID, " +
                " P.DESCRIPCION ," +
                " P.ID_PRODUCTOR ," +
                " concat(concat(concat(pr.nombre,' '),concat(pr.apellido_p,' ')),concat(pr.apellido_m,' ')) ID_PRODUCTORS," +
                " FECHA,FECHA_PROMESA," +
                " DATE_FORMAT( P.FECHA,'%Y-%m-%d')  FECHAS," +
                " P.ID_TIPO_PAGO," +
                " F.DESCRIPCION  ID_TIPO_PAGOS," +
                " F.TIPO  TIPO," +
                " P.MONTO ," +
                " DATE_FORMAT(P.FECHA_PROMESA,'%Y-%m-%d') FECHA_PROMESAS ," +
                " P.MONTO_PROMESA  ," +
                " P.ID_ACTIVO ," +
                " AA.NOMBRE   ID_ACTIVOS," +
                " P.ID_INSUMO, " +
                " (SELECT M.CONCEPTO FROM CC_CAT_INSUMOS M WHERE M.ID =P.ID_INSUMO ) ID_INSUMOS," +
                " 0 ACTIVO " +
                " FROM CC_PAGOS_ADEUDOS P , CC_CAT_TIPO_PAGO F ,CC_PRODUCTOR PR,CC_ACTIVO AA" +
                " WHERE F.ID =P.ID_TIPO_PAGO" +
                " AND  PR.ID =P.ID_PRODUCTOR" +
                " AND  AA.ID =P.ID_ACTIVO  " +
                " AND  p.ID_ACTIVO =1 " +
                " AND  P.ID_PRODUCTOR = ?";
   String querSelectByTypePos="SELECT  F.ID, \n" +
                "'Corrida' DESCRIPCION ,\n" +
                "ID_PRODUCTOR , \n" +
                "concat(concat(concat(P.nombre,' '),concat(P.apellido_p,' ')),concat(P.apellido_m,' ')) ID_PRODUCTORS,\n" +
                "DATE_FORMAT(f.modificado,'%Y-%m-%d') FECHA ,\n" +
                "DATE_FORMAT(f.modificado,'%Y-%m-%d') FECHA_PROMESA ,\n" +
                "DATE_FORMAT(f.modificado,'%Y-%m-%d') FECHAS ,\n" +
                "8 ID_TIPO_PAGO,\n" +
                "concat('Corrida : ',f.folio) ID_TIPO_PAGOS,\n" +
                "1 TIPO,\n" +
                "IF(CAST(REPLACE(PROMEDIO_ORIGINAL,',','') AS DECIMAL(10,2))=\n" +
                "CAST(REPLACE(PROMEDIO_MODIFICACION,',','') AS DECIMAL(10,2)),\n" +
                "CAST(REPLACE(SUMA14,',','') AS DECIMAL(10,2)) +   CAST(REPLACE(SUMA24,',','') AS DECIMAL(10,2)) ,\n" +
                "CONVERT((CAST(REPLACE(SUMA11,',','') AS DECIMAL(10,2))+ CAST(REPLACE(SUMA21,',','') AS DECIMAL(10,2)))* CAST(REPLACE(PROMEDIO_MODIFICACION,',','') AS DECIMAL(10,2)),DECIMAL(14,2))) MONTO,\n" +
                "DATE_FORMAT(f.modificado,'%Y-%m-%d') FECHA_PROMESAS ,\n" +
                "0  MONTO_PROMESA,\n" +
                "1 ID_ACTIVO,\n" +
                "'Activo' ID_ACTIVOS,\n" +
                "null ID_INSUMO,\n" +
                "null ID_INSUMOS,\n" +
                "0 ACTIVO\n" +
                "FROM  CC_FOLIOS F,CC_CORRIDAS C, CC_PRODUCTOR P  WHERE F.STATUS =4  " +
                " AND  F.ID =  C.ID_FOLIO AND F.ID_PRODUCTOR =  P.ID    "  +
                "union all \n" +
                "SELECT\n" +
                "P.ID,  \n" +
                "P.DESCRIPCION , \n" +
                "P.ID_PRODUCTOR ,\n" +
                "concat(concat(concat(pr.nombre,' '),concat(pr.apellido_p,' ')),concat(pr.apellido_m,' ')) ID_PRODUCTORS,\n" +
                "FECHA,FECHA_PROMESA, \n" +
                "DATE_FORMAT( P.FECHA,'%Y-%m-%d')  FECHAS, \n" +
                "P.ID_TIPO_PAGO,\n" +
                "F.DESCRIPCION  ID_TIPO_PAGOS,\n" +
                "F.TIPO  TIPO,\n" +
                "P.MONTO ,\n" +
                "DATE_FORMAT(P.FECHA_PROMESA,'%Y-%m-%d') FECHA_PROMESAS ,\n" +
                "P.MONTO_PROMESA  ,\n" +
                "P.ID_ACTIVO ,\n" +
                "AA.NOMBRE   ID_ACTIVOS,\n" +
                "P.ID_INSUMO, \n" +
                "(SELECT M.CONCEPTO FROM CC_CAT_INSUMOS M WHERE M.ID =P.ID_INSUMO ) ID_INSUMOS,\n" +
                "1 ACTIVO\n" +
                "FROM CC_PAGOS_ADEUDOS P , CC_CAT_TIPO_PAGO F ,CC_PRODUCTOR PR,CC_ACTIVO AA\n" +
                "WHERE F.ID =P.ID_TIPO_PAGO\n" +
                "AND  PR.ID =P.ID_PRODUCTOR\n" +
                "AND  AA.ID =P.ID_ACTIVO  \n" +
                "AND  p.ID_ACTIVO =1 \n" + 
                "AND F.TIPO =";
   String querySelectByTypeNeg="SELECT \n" +
                " P.ID, \n" +
                " P.DESCRIPCION ,\n" +
                " P.ID_PRODUCTOR ,\n" +
                " concat(concat(concat(pr.nombre,' '),concat(pr.apellido_p,' ')),concat(pr.apellido_m,' ')) ID_PRODUCTORS,\n" +
                " FECHA,FECHA_PROMESA,\n" +
                " DATE_FORMAT( P.FECHA,'%Y-%m-%d')  FECHAS,\n" +
                " P.ID_TIPO_PAGO,\n" +
                " F.DESCRIPCION  ID_TIPO_PAGOS,\n" +
                " F.TIPO  TIPO,\n" +
                " P.MONTO ,\n" +
                " DATE_FORMAT(P.FECHA_PROMESA,'%Y-%m-%d') FECHA_PROMESAS ,\n" +
                " P.MONTO_PROMESA  ,\n" +
                " P.ID_ACTIVO ,\n" +
                " AA.NOMBRE   ID_ACTIVOS,\n" +
                " P.ID_INSUMO, \n" +
                " (SELECT M.CONCEPTO FROM CC_CAT_INSUMOS M WHERE M.ID =P.ID_INSUMO ) ID_INSUMOS, \n" +
                " 0 ACTIVO\n" +
                " FROM CC_PAGOS_ADEUDOS P , CC_CAT_TIPO_PAGO F ,CC_PRODUCTOR PR,CC_ACTIVO AA\n" +
                " WHERE F.ID =P.ID_TIPO_PAGO\n" +
                " AND  PR.ID =P.ID_PRODUCTOR\n" +
                " AND  AA.ID =P.ID_ACTIVO  \n" +
                " AND  p.ID_ACTIVO =1 "
              + " AND  F.TIPO =";
        String querySelectOne="SELECT \n" +
                " P.ID, \n" +
                " P.DESCRIPCION ,\n" +
                " P.ID_PRODUCTOR ,\n" +
                " concat(concat(concat(pr.nombre,' '),concat(pr.apellido_p,' ')),concat(pr.apellido_m,' ')) ID_PRODUCTORS,\n" +
                " FECHA,FECHA_PROMESA,\n" +
                " DATE_FORMAT( P.FECHA,'%Y-%m-%d')  FECHAS,\n" +
                " P.ID_TIPO_PAGO,\n" +
                " F.DESCRIPCION  ID_TIPO_PAGOS,\n" +
                " F.TIPO  TIPO,\n" +
                " P.MONTO ,\n" +
                " DATE_FORMAT(P.FECHA_PROMESA,'%Y-%m-%d') FECHA_PROMESAS ,\n" +
                " P.MONTO_PROMESA  ,\n" +
                " P.ID_ACTIVO ,\n" +
                " AA.NOMBRE   ID_ACTIVOS,\n" +
                " P.ID_INSUMO, \n" +
                " (SELECT M.CONCEPTO FROM CC_CAT_INSUMOS M WHERE M.ID =P.ID_INSUMO ) ID_INSUMOS\n" +
                " FROM CC_PAGOS_ADEUDOS P , CC_CAT_TIPO_PAGO F ,CC_PRODUCTOR PR,CC_ACTIVO AA\n" +
                " WHERE F.ID =P.ID_TIPO_PAGO\n" +
                " AND  PR.ID =P.ID_PRODUCTOR\n" +
                " AND  AA.ID =P.ID_ACTIVO  \n" +
                " AND  p.ID_ACTIVO =1 "
                + " AND  P.ID= ";
        String queryMax="SELECT MAX(ID) ID FROM CC_PAGOS_ADEUDOS";
        String queryUpdatePagos ="UPDATE CC_PAGOS_ADEUDOS "
                + " SET DESCRIPCION=?,"
                + " ID_PRODUCTOR=?,"
                + " FECHA=?,"
                + " ID_TIPO_PAGO=?,"
                + " MONTO=?, "
                + " MODIFICADOPOR =? "
                + " WHERE ID = ? ";
        String queryUpdate="UPDATE CC_PAGOS_ADEUDOS "
                + " SET DESCRIPCION=?,"
                + " ID_PRODUCTOR=?,"
                + " FECHA=?,"
                + " ID_TIPO_PAGO=?,"
                + " MONTO=?,"
                + " FECHA_PROMESA=?,"
                + " MONTO_PROMESA=?,"
                + " MODIFICADOPOR =? "
                + " WHERE ID = ? ";
        String queryUpdateExtra="UPDATE CC_PAGOS_ADEUDOS "
                + " SET ID_INSUMO=? "
                + " WHERE ID = ? ";
        String queryDeleteUser="UPDATE CC_PAGOS_ADEUDOS SET ID_ACTIVO = 3 , MODIFICADOPOR =? WHERE ID = ?  ";
        String queryCreateUserSinPromesa="INSERT INTO CC_PAGOS_ADEUDOS"
                + "(DESCRIPCION,ID_PRODUCTOR,FECHA,ID_TIPO_PAGO,MONTO,MODIFICADOPOR,CREADOPOR) "
                + "VALUES(?,?,?,?,?,?,?)";
        String queryCreateUser="INSERT INTO CC_PAGOS_ADEUDOS"
                + "(DESCRIPCION,ID_PRODUCTOR,FECHA,ID_TIPO_PAGO,MONTO,FECHA_PROMESA,MONTO_PROMESA,MODIFICADOPOR,CREADOPOR) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        String queryCreatePago="INSERT INTO CC_PAGOS_ADEUDOS"
                + "(DESCRIPCION,ID_PRODUCTOR,FECHA,ID_TIPO_PAGO,MONTO,MODIFICADOPOR,CREADOPOR) "
                + "VALUES(?,?,?,?,?,?,?)";
    
    public Pagos getMax()
    {
        Pagos bean=new Pagos();
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
    public Pagos getOne(int i)
    {
        Pagos bean=new Pagos();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelectOne+i);
            if(rs.next())
            {
                bean.setId(rs.getInt("ID"));
                bean.setDescripcion(rs.getString("DESCRIPCION"));
                bean.setIdproductor(rs.getInt("ID_PRODUCTOR")); 
                bean.setProductor(rs.getString("ID_PRODUCTORS")); 
                bean.setFechat(rs.getDate("FECHAS")); 
                bean.setFecha(rs.getString("FECHA"));       
                bean.setIdtipopago(rs.getInt("ID_TIPO_PAGO")); 
                bean.setIdtipopagos(rs.getString("ID_TIPO_PAGOS"));  
                bean.setMonto(rs.getFloat("MONTO"));
                bean.setFechapromesat(rs.getDate("FECHA_PROMESA"));
                bean.setFechapromesa(rs.getString("FECHA_PROMESAS"));
                bean.setMontopromesa(rs.getFloat("MONTO_PROMESA"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO"));
                bean.setIdactivos(rs.getString("ID_ACTIVOS")); 
                bean.setIdinsumo(rs.getInt("ID_ACTIVO"));
                bean.setIdinsumos(rs.getString("ID_INSUMOS")); 
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
    public ArrayList<Pagos> getTotaly(String condicion)
    {
        System.out.println("getAll()");
        ArrayList<Pagos> l=new ArrayList<Pagos>();
        Connection connection=null;
        String queryTotalesf=" SELECT ID,ID_PRODUCTORS, SUM(MONTO)  MONTO FROM  (\n" +
                "SELECT  \n" +
                "P.ID_PRODUCTOR ID  , \n" +
                "concat(concat(concat(pr.nombre,' '),concat(pr.apellido_p,' ')),concat(pr.apellido_m,' ')) ID_PRODUCTORS, \n" +
                " round((P.MONTO  * F.TIPO ),2) MONTO \n" +
                "FROM CC_PAGOS_ADEUDOS P , CC_CAT_TIPO_PAGO F ,CC_PRODUCTOR PR \n" +
                "WHERE F.ID =P.ID_TIPO_PAGO\n" +
                "AND  PR.ID =P.ID_PRODUCTOR \n" +
                "AND  P.ID_ACTIVO =1   " +condicion +
                "  union all  "
                + "select id, ID_PRODUCTORS, sum(monto) monto  from ( " +
                " SELECT Pr.ID ,  \n" +
                " concat(concat(concat(PR.nombre,' '),concat(PR.apellido_p,' ')),concat(PR.apellido_m,' ')) ID_PRODUCTORS,\n" +
                " CONVERT(\n" +
                " IF(CAST(REPLACE(PROMEDIO_ORIGINAL,',','') AS DECIMAL(10,2))=\n" +
                " CAST(REPLACE(PROMEDIO_MODIFICACION,',','') AS DECIMAL(10,2)),\n" +
                "CAST(REPLACE(SUMA14,',','') AS DECIMAL(10,2)) +   CAST(REPLACE(SUMA24,',','') AS DECIMAL(10,2)) ,\n" +
                "CONVERT((CAST(REPLACE(SUMA11,',','') AS DECIMAL(10,2))+ CAST(REPLACE(SUMA21,',','') AS DECIMAL(10,2)))* CAST(REPLACE(PROMEDIO_MODIFICACION,',','') AS DECIMAL(10,2)),DECIMAL(14,2))) \n" +
                ",DECIMAL(19,2))MONTO " +
                "FROM  CC_FOLIOS F,CC_CORRIDAS C, CC_PRODUCTOR PR  WHERE F.STATUS =4 AND  F.ID =  C.ID_FOLIO AND F.ID_PRODUCTOR =  PR.ID  " +
                condicion +
                ")T " +
                "GROUP BY ID,ID_PRODUCTORS )  f group by id, ID_PRODUCTORS  ORDER BY ID_PRODUCTORS	 ";
                System.out.println("----------------------------->" +queryTotalesf);
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(queryTotalesf);
            while(rs.next())
            {
                Pagos bean=new Pagos();
                bean.setId(rs.getInt("ID")); 
                bean.setProductor(rs.getString("ID_PRODUCTORS"));  
                bean.setMonto(rs.getFloat("MONTO"));
                float montof=  rs.getFloat("MONTO");
                if(montof<0.001)
                    montof=0.0f;
                bean.setMonto(montof);
                System.out.println("Monto2 "+bean.getMonto());
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
    public ArrayList<Pagos> getAll()
    {
        ArrayList<Pagos> l=new ArrayList<Pagos>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(querySelect +"  ORDER  BY P.FECHA,P.ID_PRODUCTOR DESC  ");
            while(rs.next())
            {
                Pagos bean=new Pagos();
                bean.setId(rs.getInt("ID"));
                bean.setDescripcion(rs.getString("DESCRIPCION"));
                bean.setIdproductor(rs.getInt("ID_PRODUCTOR")); 
                bean.setProductor(rs.getString("ID_PRODUCTORS")); 
                bean.setFechat(rs.getDate("FECHAS")); 
                bean.setFecha(rs.getString("FECHA"));       
                bean.setIdtipopago(rs.getInt("ID_TIPO_PAGO")); 
                bean.setIdtipopagos(rs.getString("ID_TIPO_PAGOS"));  
                bean.setMonto(rs.getFloat("MONTO"));
                bean.setFechapromesat(rs.getDate("FECHA_PROMESA"));
                bean.setFechapromesa(rs.getString("FECHA_PROMESAS"));
                bean.setMontopromesa(rs.getFloat("MONTO_PROMESA"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO"));
                bean.setIdactivos(rs.getString("ID_ACTIVOS")); 
                bean.setIdinsumo(rs.getInt("ID_ACTIVO"));
                bean.setIdinsumos(rs.getString("ID_INSUMOS")); 
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
    public ArrayList<Pagos> getAll(int tipopago,String condicion)
    {
        ArrayList<Pagos> l=new ArrayList<Pagos>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement(); 
            ResultSet rs=null;
            String querySelect = "SELECT  F.ID, \n" +
                "'Corrida' DESCRIPCION ,\n" +
                "ID_PRODUCTOR , \n" +
                "concat(concat(concat(PR.nombre,' '),concat(PR.apellido_p,' ')),concat(PR.apellido_m,' ')) ID_PRODUCTORS,\n" +
                "DATE_FORMAT(f.modificado,'%Y-%m-%d') FECHA ,\n" +
                "DATE_FORMAT(f.modificado,'%Y-%m-%d') FECHA_PROMESA ,\n" +
                "DATE_FORMAT(f.modificado,'%Y-%m-%d') FECHAS ,\n" +
                "8 ID_TIPO_PAGO,\n" +
                "concat('Corrida : ',f.folio) ID_TIPO_PAGOS,\n" +
                "1 TIPO,\n" +
                "IF(CAST(REPLACE(PROMEDIO_ORIGINAL,',','') AS DECIMAL(10,2))=\n" +
                "CAST(REPLACE(PROMEDIO_MODIFICACION,',','') AS DECIMAL(10,2)),\n" +
                "CAST(REPLACE(SUMA14,',','') AS DECIMAL(10,2)) +   CAST(REPLACE(SUMA24,',','') AS DECIMAL(10,2)) ,\n" +
                "CONVERT((CAST(REPLACE(SUMA11,',','') AS DECIMAL(10,2))+ CAST(REPLACE(SUMA21,',','') AS DECIMAL(10,2)))* CAST(REPLACE(PROMEDIO_MODIFICACION,',','') AS DECIMAL(10,2)),DECIMAL(14,2))) MONTO,\n" +
                "DATE_FORMAT(f.modificado,'%Y-%m-%d') FECHA_PROMESAS ,\n" +
                "0  MONTO_PROMESA,\n" +
                "1 ID_ACTIVO,\n" +
                "'Activo' ID_ACTIVOS,\n" +
                "null ID_INSUMO,\n" +
                "null ID_INSUMOS,\n" +
                "0 ACTIVO\n" +
                "FROM  CC_FOLIOS F,CC_CORRIDAS C, CC_PRODUCTOR PR  WHERE F.STATUS =4  " +
                " AND  F.ID =  C.ID_FOLIO AND F.ID_PRODUCTOR =  PR.ID    " + condicion +
                "  union all \n" +
                "SELECT\n" +
                "P.ID,  \n" +
                "P.DESCRIPCION , \n" +
                "P.ID_PRODUCTOR ,\n" +
                "concat(concat(concat(pr.nombre,' '),concat(pr.apellido_p,' ')),concat(pr.apellido_m,' ')) ID_PRODUCTORS,\n" +
                "FECHA,FECHA_PROMESA, \n" +
                "DATE_FORMAT( P.FECHA,'%Y-%m-%d')  FECHAS, \n" +
                "P.ID_TIPO_PAGO,\n" +
                "F.DESCRIPCION  ID_TIPO_PAGOS,\n" +
                "F.TIPO  TIPO,\n" +
                "P.MONTO ,\n" +
                "DATE_FORMAT(P.FECHA_PROMESA,'%Y-%m-%d') FECHA_PROMESAS ,\n" +
                "P.MONTO_PROMESA  ,\n" +
                "P.ID_ACTIVO ,\n" +
                "AA.NOMBRE   ID_ACTIVOS,\n" +
                "P.ID_INSUMO, \n" +
                "(SELECT M.CONCEPTO FROM CC_CAT_INSUMOS M WHERE M.ID =P.ID_INSUMO ) ID_INSUMOS,\n" +
                "1 ACTIVO\n" +
                "FROM CC_PAGOS_ADEUDOS P , CC_CAT_TIPO_PAGO F ,CC_PRODUCTOR PR,CC_ACTIVO AA\n" +
                "WHERE F.ID =P.ID_TIPO_PAGO\n" +
                "AND  PR.ID =P.ID_PRODUCTOR\n" +
                "AND  AA.ID =P.ID_ACTIVO  \n" +
                "AND  p.ID_ACTIVO =1    " +
                condicion +
                "  AND F.TIPO =";
            if(tipopago<0)
               querySelect=  "SELECT\n" +
                "P.ID,  \n" +
                "P.DESCRIPCION , \n" +
                "P.ID_PRODUCTOR ,\n" +
                "concat(concat(concat(pr.nombre,' '),concat(pr.apellido_p,' ')),concat(pr.apellido_m,' ')) ID_PRODUCTORS,\n" +
                "FECHA,FECHA_PROMESA, \n" +
                "DATE_FORMAT( P.FECHA,'%Y-%m-%d')  FECHAS, \n" +
                "P.ID_TIPO_PAGO,\n" +
                "F.DESCRIPCION  ID_TIPO_PAGOS,\n" +
                "F.TIPO  TIPO,\n" +
                "P.MONTO ,\n" +
                "DATE_FORMAT(P.FECHA_PROMESA,'%Y-%m-%d') FECHA_PROMESAS ,\n" +
                "P.MONTO_PROMESA  ,\n" +
                "P.ID_ACTIVO ,\n" +
                "AA.NOMBRE   ID_ACTIVOS,\n" +
                "P.ID_INSUMO, \n" +
                "(SELECT M.CONCEPTO FROM CC_CAT_INSUMOS M WHERE M.ID =P.ID_INSUMO ) ID_INSUMOS,\n" +
                "1 ACTIVO\n" +
                "FROM CC_PAGOS_ADEUDOS P , CC_CAT_TIPO_PAGO F ,CC_PRODUCTOR PR,CC_ACTIVO AA\n" +
                "WHERE F.ID =P.ID_TIPO_PAGO\n" +
                "AND  PR.ID =P.ID_PRODUCTOR\n" +
                "AND  AA.ID =P.ID_ACTIVO  \n" +
                "AND  p.ID_ACTIVO =1    " +
                condicion +
                "  AND F.TIPO =";
            //System.out.println("--------------------1--------->" +querySelect +tipopago +"  ORDER  BY ID_PRODUCTORS,FECHA DESC ");
            rs=st.executeQuery(querySelect+tipopago +"  ORDER  BY  ID_PRODUCTORS,FECHA DESC ");
            while(rs.next())
            {
                Pagos bean=new Pagos();
                bean.setId(rs.getInt("ID"));
                bean.setDescripcion(rs.getString("DESCRIPCION"));
                bean.setIdproductor(rs.getInt("ID_PRODUCTOR")); 
                bean.setProductor(rs.getString("ID_PRODUCTORS")); 
                bean.setFechat(rs.getDate("FECHAS")); 
                bean.setFecha(rs.getString("FECHA"));       
                bean.setIdtipopago(rs.getInt("ID_TIPO_PAGO")); 
                bean.setIdtipopagos(rs.getString("ID_TIPO_PAGOS"));  
                bean.setMonto(rs.getFloat("MONTO"));
                bean.setFechapromesat(rs.getDate("FECHA_PROMESA"));
                bean.setFechapromesa(rs.getString("FECHA_PROMESAS"));
                bean.setMontopromesa(rs.getFloat("MONTO_PROMESA"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO"));
                bean.setIdactivos(rs.getString("ID_ACTIVOS")); 
                bean.setIdinsumo(rs.getInt("ID_ACTIVO"));
                bean.setIdinsumos(rs.getString("ID_INSUMOS")); 
                bean.setActivo(rs.getInt("ACTIVO"));
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
    public ArrayList<Pagos> getAllbyProductor(int prod)
    {
        ArrayList<Pagos> l=new ArrayList<Pagos>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement(); 
            PreparedStatement preparedStatement = connection.prepareStatement(querySelectByProductor);
            preparedStatement.setInt(1, prod);
            preparedStatement.setInt(2, prod); 
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next())
            {
                Pagos bean=new Pagos();
                bean.setId(rs.getInt("ID"));
                bean.setTipo(rs.getInt("TIPO"));
                bean.setDescripcion(rs.getString("DESCRIPCION"));
                bean.setIdproductor(rs.getInt("ID_PRODUCTOR")); 
                bean.setProductor(rs.getString("ID_PRODUCTORS")); 
                bean.setFechat(rs.getDate("FECHAS")); 
                bean.setFecha(rs.getString("FECHA"));       
                bean.setIdtipopago(rs.getInt("ID_TIPO_PAGO")); 
                bean.setIdtipopagos(rs.getString("ID_TIPO_PAGOS"));  
                float montof=  rs.getFloat("MONTO");
                if(montof<0.001)
                    montof=0.0f;
                bean.setMonto(montof);
                System.out.println("Monto2 "+bean.getMonto());
                bean.setFechapromesat(rs.getDate("FECHA_PROMESA"));
                bean.setFechapromesa(rs.getString("FECHA_PROMESAS"));
                bean.setMontopromesa(rs.getFloat("MONTO_PROMESA"));
                bean.setIdactivo(rs.getInt("ID_ACTIVO"));
                bean.setIdactivos(rs.getString("ID_ACTIVOS")); 
                bean.setIdinsumo(rs.getInt("ID_ACTIVO"));
                bean.setIdinsumos(rs.getString("ID_INSUMOS")); 
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
    public int updateRecord(int insumo,int id)
     {  
         /**
          * String queryUpdate="UPDATE CC_PAGOS_ADEUDOS SET  ID_INSUMO=? WHERE ID = ? ";
          */
        System.out.println("updateRecord * ("+id+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdateExtra);
            ps.setInt(1, insumo);
            ps.setInt(2, id); 
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
     * Modificacion de registro
     * @param descripcion
     * @param productor
     * @param fecha
     * @param tipo_pago
     * @param monto
     * @param id
     * @param usuario
     * @return ed.getDescripcion()  ,ed.getIdproductor(),fechaN,idtipopago, ed.getMonto(),ed.getId(),1
 
     */
    public int updateRecord(String descripcion,int productor,String fecha,int tipo_pago,
            float monto,int id,int usuario)
     {  
        System.out.println("updateRecord ++ ("+descripcion+","+tipo_pago+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdatePagos);
            ps.setString(1, descripcion);
            ps.setInt(2, productor);
            ps.setString(3, fecha);
            ps.setInt(4, tipo_pago);
            ps.setFloat(5, monto);
            ps.setInt(6, usuario); 
            ps.setInt(7, id); 
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
     * Modifcar registro
     * @param descripcion
     * @param productor
     * @param fecha
     * @param tipo_pago
     * @param monto
     * @param fpromesa
     * @param mpromesa
     * @param insumo
     * @param id
     * @param usuario
     * @return 
     */
    public int updateRecord(String descripcion,int productor,String fecha,int tipo_pago,
            float monto,String fpromesa,float mpromesa,int insumo,int id,int usuario)
     {
        int v=0;
        if(mpromesa>0) 
        {
            v=updateRecordConPromesa( descripcion, productor, fecha, tipo_pago,
              monto, fpromesa, mpromesa,  id, usuario);
        }
        else
        {
            v=updateRecord(descripcion,productor,fecha,tipo_pago,monto,id,usuario);
        }
        if(insumo>0)
            updateRecord(insumo,id);
        return v;
     }
    /**
     * Modifcar registro
     * @param descripcion
     * @param productor
     * @param fecha
     * @param tipo_pago
     * @param monto
     * @param fpromesa
     * @param mpromesa
     * @param insumo
     * @param id
     * @param usuario
     * @return 
     */
    public int updateRecordConPromesa(String descripcion,int productor,String fecha,int tipo_pago,
            float monto,String fpromesa,float mpromesa, int id,int usuario)
     {  
        System.out.println("updateRecord ++ ("+id+")");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryUpdate);
            ps.setString(1, descripcion);
            ps.setInt(2, productor);
            ps.setString(3, fecha);
            ps.setInt(4, tipo_pago);
            ps.setFloat(5, monto);
            ps.setString(6, fpromesa);
            ps.setFloat(7, mpromesa);
            ps.setInt(8, usuario); 
            ps.setInt(9, id); 
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
     * @param usuario
     * @return numero de registros dados de alta.
     */
    public int deleteRecord(int id,int usuario)
    {
        System.out.println("deleteRecord("+id+")");
        int deleteCont=0;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryDeleteUser);
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
    /**
     * 
     * Proceso para el guardado de registro.
     * @return numero de registros dados de alta
     * @param descripcion
     * @param productor
     * @param fecha
     * @param tipo_pago
     * @param monto
     * @param usuario
     * @return Pagos
     */
    public Pagos  insertRecord(String descripcion,int productor,String fecha,int tipo_pago,
            float monto,int usuario)
    {
        System.out.println("insertRecord(*pagos*)");
        int insertCont=0;
        Pagos bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreatePago);
            ps.setString(1, descripcion);
            ps.setInt(2, productor);
            ps.setString(3, fecha);
            ps.setInt(4, tipo_pago);
            ps.setFloat(5, monto);
            ps.setInt(6, usuario); 
            ps.setInt(7, usuario); 
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
    /**
     * 
     * Proceso para el guardado de registro.
     * @return numero de registros dados de alta
     * @param descripcion
     * @param productor
     * @param fecha
     * @param tipo_pago
     * @param monto
     * @param fpromesa
     * @param mpromesa
     * @param insumo
     * @param usuario
     * @return Pagos
     */
    public Pagos  insertRecord(String descripcion,int productor,String fecha,int tipo_pago,
            float monto,String fpromesa,float mpromesa,int insumo,int usuario)
    {
        if(mpromesa>0 && fpromesa!=null)
            return insertRecordConPromesa( descripcion, productor, fecha, tipo_pago,
             monto, fpromesa, mpromesa, insumo, usuario);
        else
            return insertRecordSinPromesa( descripcion, productor, fecha, tipo_pago,
             monto, insumo, usuario);
    
    }
    /**
     * 
     * Proceso para el guardado de registro.
     * @return numero de registros dados de alta
     * @param descripcion
     * @param productor
     * @param fecha
     * @param tipo_pago
     * @param monto
     * @param fpromesa
     * @param mpromesa
     * @param insumo
     * @param usuario
     * @return Pagos
     */
    public Pagos  insertRecordConPromesa(String descripcion,int productor,String fecha,int tipo_pago,
            float monto,String fpromesa,float mpromesa,int insumo,int usuario)
    {
        System.out.println("insertRecord(**)");
        int insertCont=0;
        Pagos bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreateUser);
            ps.setString(1, descripcion);
            ps.setInt(2, productor);
            ps.setString(3, fecha);
            ps.setInt(4, tipo_pago);
            ps.setFloat(5, monto);
            ps.setString(6, fpromesa);
            ps.setFloat(7, mpromesa);
            ps.setInt(8, usuario); 
            ps.setInt(9, usuario); 
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
        if(insumo>0)
            updateRecord(insumo,usuario);
        return bean;
    }
    /**
     * 
     * Proceso para el guardado de registro.
     * @return numero de registros dados de alta
     * @param descripcion
     * @param productor
     * @param fecha
     * @param tipo_pago
     * @param monto
     * @param insumo
     * @param usuario
     * @return Pagos
     */
    public Pagos  insertRecordSinPromesa(String descripcion,int productor,String fecha,int tipo_pago,
            float monto,int insumo,int usuario)
    {
        System.out.println("insertRecord(**)");
        int insertCont=0;
        Pagos bean=null;
        Connection connection=null;
        try 
        {
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(queryCreateUserSinPromesa);
            ps.setString(1, descripcion);
            ps.setInt(2, productor);
            ps.setString(3, fecha);
            ps.setInt(4, tipo_pago);
            ps.setFloat(5, monto);
            ps.setInt(6, usuario); 
            ps.setInt(7, usuario); 
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
        if(insumo>0)
            updateRecord(insumo,usuario);
        return bean;
    }
}
