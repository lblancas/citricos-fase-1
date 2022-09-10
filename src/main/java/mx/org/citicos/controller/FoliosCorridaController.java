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
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.FolioCorrida;
import mx.org.citricos.entity.Precios;
/**
 *
 * @author luisa
 */
public class FoliosCorridaController extends Conexion
{
    String query=   "select f.id, f.id id_folio,  f.status,\n" +
                    " (select concat(concat(concat(p.nombre,' '),concat(p.apellido_p,' ')),concat(p.apellido_m,' ')) from cc_productor p where p.id = f.id_productor )productor,\n" +
                    " (select p.descripcion from cc_agronomo p where p.id = f.id_agronomo   ) agronomo ,\n" +
                    " f.folio,c.comprador,c.facturar,\n" +
                    " (select descripcion from cc_cat_tamano_limon where id  = f.id_tlimon)calidad_limon,\n" +
                    " verde_cantidad,verde_porcentaje, \n" +
                    " verde_precio, verde_total, \n" +
                    " empaque_cantidad,empaque_porcentaje, \n" +
                    " empaque_precio, empaque_total, \n" +
                    " desechos_cantidad,desechos_porcentaje, \n" +
                    " desechos_precio, desechos_total, \n" +
                    " suma11, suma12, suma14, suma21,suma22, suma24,\n" +
                    " promedio_original,promedio_modificacion \n" +
                    " from cc_corridas  c ,cc_folios f\n" +
                    " where   f.id = c.id_folio and f.`status` >2\n" +
                    " union all \n" +
                    " select f.id, f.id id_folio,  f.status,\n" +
                    " (select concat(concat(concat(p.nombre,' '),concat(p.apellido_p,' ')),concat(p.apellido_m,' ')) from cc_productor p where p.id = f.id_productor )productor,\n" +
                    " (select p.descripcion from cc_agronomo p where p.id = f.id_agronomo   ) agronomo ,\n" +
                    " f.folio,'-'comprador,'-' facturar,\n" +
                    " (select descripcion from cc_cat_tamano_limon where id  = f.id_tlimon)calidad_limon,\n" +
                    " 0 verde_cantidad,0 verde_porcentaje, \n" +
                    " 0 verde_precio,0 verde_total, \n" +
                    " 0 empaque_cantidad,0 empaque_porcentaje, \n" +
                    " 0 empaque_precio,0 empaque_total, \n" +
                    " 0 desechos_cantidad,0 desechos_porcentaje, \n" +
                    " 0 desechos_precio,0 desechos_total, \n" +
                    " 0 suma11,0 suma12,0 suma14,0 suma21,0 suma22,0 suma24,\n" +
                    " 0 promedio_original,0 promedio_modificacion,id_rejas  \n" +
                    " from cc_folios f\n" +
                    " where   f.`status` <=2";
    String updateVerde    ="update cc_corridas set \n" +
                    "verde_cantidad    =?,\n" +
                    "verde_porcentaje  =?,\n" +
                    "verde_precio      =?,\n" +
                    "verde_total       =?,\n" +
                    "id_precios        =? where id_folio =?";
    String updateEmpaque  ="update cc_corridas set \n" +
                    "empaque_cantidad    =?,\n" +
                    "empaque_porcentaje  =?,\n" +
                    "empaque_precio      =?,\n" +
                    "empaque_total       =?   where id_folio =?";
    String updateDesechos ="update cc_corridas set \n" +
                    "desechos_cantidad   =?,\n" +
                    "desechos_porcentaje =?,\n" +
                    "desechos_precio     =?,\n" +
                    "desechos_total      =?   where id_folio =?";
    String updateSuma1="update cc_corridas set \n" +
                    "suma11 =?,\n" +
                    "suma12 =?,\n" +
                    "suma14 =? where id_folio = ?";
    String updateSuma2="update cc_corridas set \n" +
                    "suma21 =?,\n" +
                    "suma22 =?,\n" +
                    "suma24 =? where id_folio = ?";
    String updatePromedio ="update cc_corridas set \n" +
                    "promedio_modificacion =?,\n" +
                    "fecha_modificacion =now() where id_folio = ?";
    public ArrayList<FolioCorrida> getAllWithCondition(String condition)
    {
        ArrayList<FolioCorrida> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            String queryWithCondition=   "select f.id, f.id id_folio,  f.status,\n" +
                    " (select concat(concat(concat(p.nombre,' '),concat(p.apellido_p,' ')),concat(p.apellido_m,' ')) from cc_productor p where p.id = f.id_productor )productor,\n" +
                    " (select p.descripcion from cc_agronomo p where p.id = f.id_agronomo   ) agronomo ,\n" +
                    " f.folio,c.comprador,c.facturar,\n" +
                    " (select descripcion from cc_cat_tamano_limon where id  = f.id_tlimon)calidad_limon,\n" +
                    " verde_cantidad,verde_porcentaje, \n" +
                    " verde_precio, verde_total, \n" +
                    " empaque_cantidad,empaque_porcentaje, \n" +
                    " empaque_precio, empaque_total, \n" +
                    " desechos_cantidad,desechos_porcentaje, \n" +
                    " desechos_precio, desechos_total, \n" +
                    " suma11, suma12, suma14, suma21,suma22, suma24,\n" +
                    " promedio_original,promedio_modificacion \n" +
                    " from cc_corridas  c ,cc_folios f\n" +
                    " where   f.id = c.id_folio and  " +condition+
                    " union all \n" +
                    " select f.id, f.id id_folio,  f.status,\n" +
                    " (select concat(concat(concat(p.nombre,' '),concat(p.apellido_p,' ')),concat(p.apellido_m,' ')) from cc_productor p where p.id = f.id_productor )productor,\n" +
                    " (select p.descripcion from cc_agronomo p where p.id = f.id_agronomo   ) agronomo ,\n" +
                    " f.folio,'-'comprador,'-' facturar,\n" +
                    " (select descripcion from cc_cat_tamano_limon where id  = f.id_tlimon)calidad_limon,\n" +
                    " 0 verde_cantidad,0 verde_porcentaje, \n" +
                    " 0 verde_precio,0 verde_total, \n" +
                    " 0 empaque_cantidad,0 empaque_porcentaje, \n" +
                    " 0 empaque_precio,0 empaque_total, \n" +
                    " 0 desechos_cantidad,0 desechos_porcentaje, \n" +
                    " 0 desechos_precio,0 desechos_total, \n" +
                    " 0 suma11,0 suma12,0 suma14,0 suma21,0 suma22,0 suma24,\n" +
                    " 0 promedio_original,0 promedio_modificacion ,id_rejas\n" +
                    " from cc_folios f\n" +
                    " where   " +condition ;
            ResultSet rs=st.executeQuery(queryWithCondition);
            //System.out.println("queryWithCondition>> "+queryWithCondition);
            while(rs.next())
            {
                FolioCorrida obj=new FolioCorrida();
                obj.setIdfolio(rs.getInt("id_folio"));
                obj.setStatus(rs.getInt("status"));
                obj.setVerde_cantidad(new Double((rs.getString("Verde_cantidad")).replace(",","")));
                obj.setVerde_porcentaje(new Double((rs.getString("Verde_porcentaje")).replace(",","")));
                obj.setVerde_precio(new Double((rs.getString("Verde_precio")).replace(",","")));
                obj.setVerde_total(new Double((rs.getString("Verde_total")).replace(",","")));
                obj.setEmpaque_cantidad(new Double((rs.getString("Empaque_cantidad")).replace(",","")));
                obj.setEmpaque_porcentaje(new Double((rs.getString("Empaque_porcentaje")).replace(",","")));
                obj.setEmpaque_precio(new Double((rs.getString("Empaque_precio")).replace(",","")));
                obj.setEmpaque_total(new Double((rs.getString("Empaque_total")).replace(",","")));
                obj.setDesechos_cantidad(new Double((rs.getString("Desechos_cantidad")).replace(",","")));
                obj.setDesechos_porcentaje(new Double((rs.getString("Desechos_porcentaje")).replace(",","")));
                obj.setDesechos_precio(new Double((rs.getString("Desechos_precio")).replace(",","")));
                obj.setDesechos_total(new Double((rs.getString("Desechos_total")).replace(",","")));
                obj.setSuma11(new Double((rs.getString("Suma11")).replace(",","")));
                obj.setSuma12(new Double((rs.getString("Suma12")).replace(",","")));
                obj.setSuma14(new Double((rs.getString("Suma14")).replace(",","")));
                obj.setSuma21(new Double((rs.getString("Suma21")).replace(",","")));
                obj.setSuma22(new Double((rs.getString("Suma22")).replace(",","")));
                obj.setSuma24(new Double((rs.getString("Suma24")).replace(",","")));
                obj.setProductor(rs.getString("productor"));
                obj.setAgronomo(rs.getString("agronomo"));
                obj.setFolio(rs.getString("folio"));
                obj.setComprador(rs.getString("comprador"));
                obj.setFacturar(rs.getString("facturar"));
                obj.setTamano(rs.getString("calidad_limon"));
                obj.setPromedio1(rs.getDouble("promedio_original"));
                obj.setPromedio2(rs.getDouble("promedio_modificacion"));
                switch (obj.getStatus())
                {
                    case 0:
                        obj.setMensaje(" En Bascula "); 
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(true);
                        obj.setDisableSt2(true);
                        obj.setDisableSt3(true);
                        obj.setDisableSt4(true);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                         break;
                    case 1:
                        obj.setMensaje(" En Recepción ");
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(false);
                        obj.setDisableSt2(true);
                        obj.setDisableSt3(true);
                        obj.setDisableSt4(true);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                         break;
                    case 2:
                        obj.setMensaje(" En Confirmación ");
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(false);
                        obj.setDisableSt2(false);
                        obj.setDisableSt3(true);
                        obj.setDisableSt4(true);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                        break;
                    case 3:
                        obj.setMensaje(" En Calibrador ");
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(false);
                        obj.setDisableSt2(false);
                        obj.setDisableSt3(false);
                        obj.setDisableSt4(true);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                        break;
                    case 4:
                        obj.setMensaje(" Autorizado ["+obj.getPromedio1()+"]");
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(false);
                        obj.setDisableSt2(false);
                        obj.setDisableSt3(false);
                        obj.setDisableSt4(false);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                        if(obj.getPromedio1().doubleValue() == obj.getPromedio2().doubleValue())
                        {
                            obj.setDisableSt6(true);
                        }
                        else
                        {
                            obj.setMensaje(" Autorizado con cambio de promedio ["+obj.getPromedio1()+" -> "+obj.getPromedio2()+"]");
                            obj.setDisableSt5(false);
                            obj.setStatus(5);
                            obj.setDisableSt6(true);
                        }
                        break;
                }
                l.add(obj);
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    public ArrayList<FolioCorrida> getAllWithConditionMobil(String condition)
    {
        ArrayList<FolioCorrida> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            String queryWithCondition=   "select f.id, f.id id_folio,  f.status,\n" +
                    " (select concat(concat(concat(p.nombre,' '),concat(p.apellido_p,' ')),concat(p.apellido_m,' ')) from cc_productor p where p.id = f.id_productor )productor,\n" +
                    " (select p.descripcion from cc_agronomo p where p.id = f.id_agronomo   ) agronomo ,\n" +
                    " f.folio,c.comprador,c.facturar,\n" +
                    " (select descripcion from cc_cat_tamano_limon where id  = f.id_tlimon)calidad_limon,\n" +
                    " verde_cantidad,verde_porcentaje, \n" +
                    " verde_precio, verde_total, \n" +
                    " empaque_cantidad,empaque_porcentaje, \n" +
                    " empaque_precio, empaque_total, \n" +
                    " desechos_cantidad,desechos_porcentaje, \n" +
                    " desechos_precio, desechos_total, \n" +
                    " suma11, suma12, suma14, suma21,suma22, suma24,\n" +
                    " promedio_original,promedio_modificacion \n" +
                    " from cc_corridas  c ,cc_folios f\n" +
                    " where   f.id = c.id_folio and f.`status` >=3 \n " +condition+
                    " union all \n" +
                    " select f.id, f.id id_folio,  f.status,\n" +
                    " (select concat(concat(concat(p.nombre,' '),concat(p.apellido_p,' ')),concat(p.apellido_m,' ')) from cc_productor p where p.id = f.id_productor )productor,\n" +
                    " (select p.descripcion from cc_agronomo p where p.id = f.id_agronomo   ) agronomo ,\n" +
                    " f.folio,'-'comprador,'-' facturar,\n" +
                    " (select descripcion from cc_cat_tamano_limon where id  = f.id_tlimon)calidad_limon,\n" +
                    " 0 verde_cantidad,0 verde_porcentaje, \n" +
                    " 0 verde_precio,0 verde_total, \n" +
                    " 0 empaque_cantidad,0 empaque_porcentaje, \n" +
                    " 0 empaque_precio,0 empaque_total, \n" +
                    " 0 desechos_cantidad,0 desechos_porcentaje, \n" +
                    " 0 desechos_precio,0 desechos_total, \n" +
                    " 0 suma11,0 suma12,0 suma14,0 suma21,0 suma22,0 suma24,\n" +
                    " 0 promedio_original,0 promedio_modificacion \n" +
                    " from cc_folios f\n" +
                    " where   f.`status` <3" +condition ;
            ResultSet rs=st.executeQuery(queryWithCondition);
           // System.out.println("queryWithCondition>> "+queryWithCondition);
            while(rs.next())
            {
                FolioCorrida obj=new FolioCorrida();
                obj.setIdfolio(rs.getInt("id_folio"));
                obj.setStatus(rs.getInt("status"));
                obj.setVerde_cantidad(new Double((rs.getString("Verde_cantidad")).replace(",","")));
                obj.setVerde_porcentaje(new Double((rs.getString("Verde_porcentaje")).replace(",","")));
                obj.setVerde_precio(new Double((rs.getString("Verde_precio")).replace(",","")));
                obj.setVerde_total(new Double((rs.getString("Verde_total")).replace(",","")));
                obj.setEmpaque_cantidad(new Double((rs.getString("Empaque_cantidad")).replace(",","")));
                obj.setEmpaque_porcentaje(new Double((rs.getString("Empaque_porcentaje")).replace(",","")));
                obj.setEmpaque_precio(new Double((rs.getString("Empaque_precio")).replace(",","")));
                obj.setEmpaque_total(new Double((rs.getString("Empaque_total")).replace(",","")));
                obj.setDesechos_cantidad(new Double((rs.getString("Desechos_cantidad")).replace(",","")));
                obj.setDesechos_porcentaje(new Double((rs.getString("Desechos_porcentaje")).replace(",","")));
                obj.setDesechos_precio(new Double((rs.getString("Desechos_precio")).replace(",","")));
                obj.setDesechos_total(new Double((rs.getString("Desechos_total")).replace(",","")));
                obj.setSuma11(new Double((rs.getString("Suma11")).replace(",","")));
                obj.setSuma12(new Double((rs.getString("Suma12")).replace(",","")));
                obj.setSuma14(new Double((rs.getString("Suma14")).replace(",","")));
                obj.setSuma21(new Double((rs.getString("Suma21")).replace(",","")));
                obj.setSuma22(new Double((rs.getString("Suma22")).replace(",","")));
                obj.setSuma24(new Double((rs.getString("Suma24")).replace(",","")));
                obj.setProductor(rs.getString("productor"));
                obj.setAgronomo(rs.getString("agronomo"));
                obj.setFolio(rs.getString("folio"));
                obj.setComprador(rs.getString("comprador"));
                obj.setFacturar(rs.getString("facturar"));
                obj.setTamano(rs.getString("calidad_limon"));
                obj.setPromedio1(rs.getDouble("promedio_original"));
                obj.setPromedio2(rs.getDouble("promedio_modificacion"));
                switch (obj.getStatus())
                {
                    case 0:
                        obj.setMensaje(" En Bascula "); 
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(true);
                        obj.setDisableSt2(true);
                        obj.setDisableSt3(true);
                        obj.setDisableSt4(true);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                         break;
                    case 1:
                        obj.setMensaje(" En Recepción ");
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(false);
                        obj.setDisableSt2(true);
                        obj.setDisableSt3(true);
                        obj.setDisableSt4(true);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                         break;
                    case 2:
                        obj.setMensaje(" En Confirmación ");
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(false);
                        obj.setDisableSt2(false);
                        obj.setDisableSt3(true);
                        obj.setDisableSt4(true);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                        break;
                    case 3:
                        obj.setMensaje(" En Calibrador ");
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(false);
                        obj.setDisableSt2(false);
                        obj.setDisableSt3(false);
                        obj.setDisableSt4(true);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                        break;
                    case 4:
                        obj.setMensaje(" Autorizado ["+obj.getPromedio1()+"]");
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(false);
                        obj.setDisableSt2(false);
                        obj.setDisableSt3(false);
                        obj.setDisableSt4(false);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                        if(obj.getPromedio1().doubleValue() == obj.getPromedio2().doubleValue())
                        {
                            obj.setDisableSt6(true);
                        }
                        else
                        {
                            obj.setMensaje(" Autorizado con cambio de promedio ["+obj.getPromedio1()+" -> "+obj.getPromedio2()+"]");
                            obj.setDisableSt5(false);
                            obj.setStatus(5);
                            obj.setDisableSt6(true);
                        }
                        break;
                }
                l.add(obj);
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    public ArrayList<FolioCorrida> getAll()
    {
        //System.out.println("getAll()");
        ArrayList<FolioCorrida> l=new ArrayList<>();
        Connection connection=null;
        try 
        {
            connection=get_connection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                FolioCorrida obj=new FolioCorrida();
                obj.setIdfolio(rs.getInt("id_folio"));
                obj.setStatus(rs.getInt("status"));
                obj.setVerde_cantidad(new Double((rs.getString("Verde_cantidad")).replace(",","")));
                obj.setVerde_porcentaje(new Double((rs.getString("Verde_porcentaje")).replace(",","")));
                obj.setVerde_precio(new Double((rs.getString("Verde_precio")).replace(",","")));
                obj.setVerde_total(new Double((rs.getString("Verde_total")).replace(",","")));
                obj.setEmpaque_cantidad(new Double((rs.getString("Empaque_cantidad")).replace(",","")));
                obj.setEmpaque_porcentaje(new Double((rs.getString("Empaque_porcentaje")).replace(",","")));
                obj.setEmpaque_precio(new Double((rs.getString("Empaque_precio")).replace(",","")));
                obj.setEmpaque_total(new Double((rs.getString("Empaque_total")).replace(",","")));
                obj.setDesechos_cantidad(new Double((rs.getString("Desechos_cantidad")).replace(",","")));
                obj.setDesechos_porcentaje(new Double((rs.getString("Desechos_porcentaje")).replace(",","")));
                obj.setDesechos_precio(new Double((rs.getString("Desechos_precio")).replace(",","")));
                obj.setDesechos_total(new Double((rs.getString("Desechos_total")).replace(",","")));
                obj.setSuma11(new Double((rs.getString("Suma11")).replace(",","")));
                obj.setSuma12(new Double((rs.getString("Suma12")).replace(",","")));
                obj.setSuma14(new Double((rs.getString("Suma14")).replace(",","")));
                obj.setSuma21(new Double((rs.getString("Suma21")).replace(",","")));
                obj.setSuma22(new Double((rs.getString("Suma22")).replace(",","")));
                obj.setSuma24(new Double((rs.getString("Suma24")).replace(",","")));
                obj.setProductor(rs.getString("productor"));
                obj.setAgronomo(rs.getString("agronomo"));
                obj.setFolio(rs.getString("folio"));
                obj.setComprador(rs.getString("comprador"));
                obj.setFacturar(rs.getString("facturar"));
                obj.setTamano(rs.getString("calidad_limon"));
                obj.setPromedio1(rs.getDouble("promedio_original"));
                obj.setPromedio2(rs.getDouble("promedio_modificacion"));
                switch (obj.getStatus())
                {
                    case 0:
                        obj.setMensaje(" En Bascula "); 
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(true);
                        obj.setDisableSt2(true);
                        obj.setDisableSt3(true);
                        obj.setDisableSt4(true);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                         break;
                    case 1:
                        obj.setMensaje(" En Recepción ");
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(false);
                        obj.setDisableSt2(true);
                        obj.setDisableSt3(true);
                        obj.setDisableSt4(true);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                         break;
                    case 2:
                        obj.setMensaje(" En Confirmación ");
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(false);
                        obj.setDisableSt2(false);
                        obj.setDisableSt3(true);
                        obj.setDisableSt4(true);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                        break;
                    case 3:
                        obj.setMensaje(" En Calibrador ");
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(false);
                        obj.setDisableSt2(false);
                        obj.setDisableSt3(false);
                        obj.setDisableSt4(true);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                        break;
                    case 4:
                        obj.setMensaje(" Autorizado ["+obj.getPromedio1()+"]");
                        obj.setDisableSt0(false);
                        obj.setDisableSt1(false);
                        obj.setDisableSt2(false);
                        obj.setDisableSt3(false);
                        obj.setDisableSt4(false);
                        obj.setDisableSt5(true);
                        obj.setDisableSt6(true);
                        if(obj.getPromedio1().doubleValue() == obj.getPromedio2().doubleValue())
                        {
                            obj.setDisableSt6(true);
                        }
                        else
                        {
                            obj.setMensaje(" Autorizado con cambio de promedio ["+obj.getPromedio1()+" -> "+obj.getPromedio2()+"]");
                            obj.setDisableSt5(false);
                            obj.setStatus(5);
                            obj.setDisableSt6(true);
                        }
                        break;
                }
                l.add(obj);
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return l;
    }
    public int updatePromedio(int id,double promedio)
    {  
        //System.out.println("updatePromedio "+promedio+"  >> " + id);
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(updatePromedio);
            ps.setDouble(1, promedio);
            ps.setInt   (2, id);
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return updeteCont;
    }
    public int updateSuma1(int id,String s11,String s12,String s14)
    {  
        System.out.println("updateSuma1 "+updateSuma1+"  >> " + id);
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(updateSuma1);
            ps.setString(1, s11);
            ps.setString(2, s12);
            ps.setString(3, s14);
            ps.setInt   (4, id);
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return updeteCont;
    }
    public int updateSuma2(int id,String s21,String s22,String s24)
    {  
        System.out.println("updateSuma2  "+updateSuma2+"  >> " + id);
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(updateSuma2);
            ps.setString(1, s21);
            ps.setString(2, s22);
            ps.setString(3, s24);
            ps.setInt   (4, id);
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return updeteCont;
    }
    public int updatePromedioFinal(int id,String promedio)
    {  
        System.out.println("updatePromedio  "+id+"  >> " + promedio);
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement("update cc_corridas set promedio1= "+promedio+", promedio2="+promedio+" where id_folio = "+id);
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
                    Logger.getLogger(FoliosCorridaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return updeteCont;
    }
    public int updateDesechos(int id,String cantidad,String porcentaje,String precio,String total)
    {  
        System.out.println("updateDesechos  "+updateDesechos+"  >> " + id);
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(updateDesechos);
            ps.setString(1, cantidad);
            ps.setString(2, porcentaje);
            ps.setString(3, precio);
            ps.setString(4, total);
            ps.setInt   (5, id);
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return updeteCont;
    }
    public int updateEmpaque(int id,String cantidad,String porcentaje,String precio,String total)
    {  
        System.out.println("updateEmpaque  "+updateEmpaque+"  >> " + id);
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(updateEmpaque);
            ps.setString(1, cantidad);
            ps.setString(2, porcentaje);
            ps.setString(3, precio);
            ps.setString(4, total);
            ps.setInt   (5, id);
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
                    Logger.getLogger(CalibreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return updeteCont;
    }
    public int updateVerde(int id,String cantidad,String porcentaje,String precio,String total,int id_precio)
    {  
        System.out.println("updateVerde  "+updateVerde+"  >> " + id);
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(updateVerde);
            ps.setString(1, cantidad);
            ps.setString(2, porcentaje);
            ps.setString(3, precio);
            ps.setString(4, total);
            ps.setInt   (5, id_precio);
            ps.setInt   (6, id);
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
                    Logger.getLogger(FoliosCorridaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return updeteCont;
    }
	public int insertPrecioCorrida(Precios p,int id) 
	{
		System.out.println("insert  "+id +" >> ");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(
            		"insert into cc_precio_producto\r\n"+
            		"(" + 
            		"id,idprecio,Verde_Japon,Verde_110,Verde_150,"+
            		"Verde_175,Verde_200,Verde_230,Verde_250,Empaque_110," + 
            		"Empaque_150,Empaque_175,Empaque_200,Empaque_230,Empaque_250," + 
            		"segundas,terceras,torreon,coleada)\r\n  values"  + 
            		"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  ") ;
            ps.setInt(1,id);
            ps.setInt(2,p.getId());
            ps.setDouble(3,p.getVerde_japon());
            ps.setDouble(4,p.getVerde_110());
            ps.setDouble(5,p.getVerde_150());
            ps.setDouble(6,p.getVerde_175());
            ps.setDouble(7,p.getVerde_200());
            ps.setDouble(8,p.getVerde_230());
            ps.setDouble(9,p.getVerde_250());
            ps.setDouble(10,p.getEmpaque_110());
            ps.setDouble(11,p.getEmpaque_150());
            ps.setDouble(12,p.getEmpaque_175());
            ps.setDouble(13,p.getEmpaque_200());
            ps.setDouble(14,p.getEmpaque_230());
            ps.setDouble(15,p.getEmpaque_250());
            ps.setDouble(16,p.getSegundas());
            ps.setDouble(17,p.getTerceras());
            ps.setDouble(18,p.getTorreon());
            ps.setDouble(19,p.getColeada());
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
                    Logger.getLogger(FoliosCorridaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return updeteCont;
	}
	public int deletePrecioCorrida(Precios p,int id) 
	{
		System.out.println("insert  "+id +" >> ");
        int updeteCont=0;
        Connection connection=null;
        try 
        { 
            connection=get_connection();
            PreparedStatement ps=connection.prepareStatement(
            		"delete from  cc_precio_producto where id =? and idprecio = ? ") ;
            ps.setInt(1,id);
            ps.setInt(2,p.getId());
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
                    Logger.getLogger(FoliosCorridaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return updeteCont;
	}
}
