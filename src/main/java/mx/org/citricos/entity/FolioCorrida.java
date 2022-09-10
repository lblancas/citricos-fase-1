/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citricos.entity;

import java.io.Serializable;

/**
 *
 * @author luisa
 */
public class FolioCorrida implements Serializable
{
    private Integer idfolio=0;    
    private Double  verde_cantidad=0d;
    private Double  verde_porcentaje=0d;
    private Double  verde_precio=0d;
    private Double  verde_total=0d;
    private Double  empaque_cantidad=0d;
    private Double  empaque_porcentaje=0d;
    private Double  empaque_precio=0d;
    private Double  empaque_total=0d;
    private Double  desechos_cantidad=0d;
    private Double  desechos_porcentaje=0d;
    private Double  desechos_precio=0d;
    private Double  desechos_total=0d;
    private Double  suma11=0d;
    private Double  suma12=0d;
    private Double  suma14=0d;
    private Double  suma21=0d;
    private Double  suma22=0d;
    private Double  suma24=0d;
    private Double  promedio1=0d;
    private Double  promedio2=0d;
    private String  productor;
    private String  agronomo;
    private String  folio;
    private String  comprador;
    private String  facturar;
    private String  tamano;
    private String  codigo;
    private String  mensaje;
    private int     status;
    private boolean disableSt0=false;
    private boolean disableSt1=false;
    private boolean disableSt2=false;
    private boolean disableSt3=false;
    private boolean disableSt4=false;
    private boolean disableSt5=false;
    private boolean disableSt6=false;
    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getAgronomo() {
        return agronomo;
    }

    public void setAgronomo(String agronomo) {
        this.agronomo = agronomo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getFacturar() {
        return facturar;
    }

    public void setFacturar(String facturar) {
        this.facturar = facturar;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    public Double getVerde_cantidad() {
        return verde_cantidad;
    }

    public void setVerde_cantidad(Double verde_cantidad) {
        this.verde_cantidad = verde_cantidad;
    }

    public Double getVerde_porcentaje() {
        return verde_porcentaje;
    }

    public void setVerde_porcentaje(Double verde_porcentaje) {
        this.verde_porcentaje = verde_porcentaje;
    }

    public Double getVerde_precio() {
        return verde_precio;
    }

    public void setVerde_precio(Double verde_precio) {
        this.verde_precio = verde_precio;
    }

    public Double getVerde_total() {
        return verde_total;
    }

    public void setVerde_total(Double verde_total) {
        this.verde_total = verde_total;
    }

    public Double getEmpaque_cantidad() {
        return empaque_cantidad;
    }

    public void setEmpaque_cantidad(Double empaque_cantidad) {
        this.empaque_cantidad = empaque_cantidad;
    }

    public Double getEmpaque_porcentaje() {
        return empaque_porcentaje;
    }

    public void setEmpaque_porcentaje(Double empaque_porcentaje) {
        this.empaque_porcentaje = empaque_porcentaje;
    }

    public Double getEmpaque_precio() {
        return empaque_precio;
    }

    public void setEmpaque_precio(Double empaque_precio) {
        this.empaque_precio = empaque_precio;
    }

    public Double getEmpaque_total() {
        return empaque_total;
    }

    public void setEmpaque_total(Double empaque_total) {
        this.empaque_total = empaque_total;
    }

    public Double getDesechos_cantidad() {
        return desechos_cantidad;
    }

    public void setDesechos_cantidad(Double desechos_cantidad) {
        this.desechos_cantidad = desechos_cantidad;
    }

    public Double getDesechos_porcentaje() {
        return desechos_porcentaje;
    }

    public void setDesechos_porcentaje(Double desechos_porcentaje) {
        this.desechos_porcentaje = desechos_porcentaje;
    }

    public Double getDesechos_precio() {
        return desechos_precio;
    }

    public void setDesechos_precio(Double desechos_precio) {
        this.desechos_precio = desechos_precio;
    }

    public Double getDesechos_total() {
        return desechos_total;
    }

    public void setDesechos_total(Double desechos_total) {
        this.desechos_total = desechos_total;
    }

    public Double getSuma11() {
        return suma11;
    }

    public void setSuma11(Double suma11) {
        this.suma11 = suma11;
    }

    public Double getSuma12() {
        return suma12;
    }

    public void setSuma12(Double suma12) {
        this.suma12 = suma12;
    }

    public Double getSuma14() {
        return suma14;
    }

    public void setSuma14(Double suma14) {
        this.suma14 = suma14;
    }

    public Double getSuma21() {
        return suma21;
    }

    public void setSuma21(Double suma21) {
        this.suma21 = suma21;
    }

    public Double getSuma22() {
        return suma22;
    }

    public void setSuma22(Double suma22) {
        this.suma22 = suma22;
    }

    public Double getSuma24() {
        return suma24;
    }

    public void setSuma24(Double suma24) {
        this.suma24 = suma24;
    }

    public Integer getIdfolio() {
        return idfolio;
    }

    public void setIdfolio(Integer idfolio) {
        this.idfolio = idfolio;
    }

    public Double getPromedio1() {
        return promedio1;
    }

    public void setPromedio1(Double promedio1) {
        this.promedio1 = promedio1;
    }

    public Double getPromedio2() {
        return promedio2;
    }
    public void setPromedio2(Double promedio2) {
        this.promedio2 = promedio2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isDisableSt0() {
        return disableSt0;
    }

    public void setDisableSt0(boolean disableSt0) {
        this.disableSt0 = disableSt0;
    }

    public boolean isDisableSt1() {
        return disableSt1;
    }

    public void setDisableSt1(boolean disableSt1) {
        this.disableSt1 = disableSt1;
    }

    public boolean isDisableSt2() {
        return disableSt2;
    }

    public void setDisableSt2(boolean disableSt2) {
        this.disableSt2 = disableSt2;
    }

    public boolean isDisableSt3() {
        return disableSt3;
    }

    public void setDisableSt3(boolean disableSt3) {
        this.disableSt3 = disableSt3;
    }

    public boolean isDisableSt4() {
        return disableSt4;
    }

    public void setDisableSt4(boolean disableSt4) {
        this.disableSt4 = disableSt4;
    }

    public boolean isDisableSt5() {
        return disableSt5;
    }

    public void setDisableSt5(boolean disableSt5) {
        this.disableSt5 = disableSt5;
    }

    public boolean isDisableSt6() {
        return disableSt6;
    }

    public void setDisableSt6(boolean disableSt6) {
        this.disableSt6 = disableSt6;
    }

    
}
