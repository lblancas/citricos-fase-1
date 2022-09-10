/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citricos.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author luisa
 */
public class Pagos implements Serializable{
   private Integer id;
   private String descripcion;
   private Integer idproductor;
   private String productor;
   private String fecha;
   private Date   fechat;
   private Integer idtipopago;
   private String idtipopagos;
   private float monto;
   private float montopromesa;
   private String fechapromesa;
   private Date   fechapromesat;
   private Integer idactivo;
   private String idactivos;
   private Integer idinsumo;
   private Integer activo;
   private String idinsumos;
   private int tipo;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdproductor() {
        return idproductor;
    }

    public void setIdproductor(Integer idproductor) {
        this.idproductor = idproductor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Date getFechat() {
        return fechat;
    }

    public void setFechat(Date fechat) {
        this.fechat = fechat;
    }

    public Integer getIdtipopago() {
        return idtipopago;
    }

    public void setIdtipopago(Integer idtipopago) {
        this.idtipopago = idtipopago;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getFechapromesa() {
        return fechapromesa;
    }

    public void setFechapromesa(String fechapromesa) {
        this.fechapromesa = fechapromesa;
    }

    public Date getFechapromesat() {
        return fechapromesat;
    }

    public void setFechapromesat(Date fechapromesat) {
        this.fechapromesat = fechapromesat;
    }

    public Integer getIdactivo() {
        return idactivo;
    }

    public void setIdactivo(Integer idactivo) {
        this.idactivo = idactivo;
    }

    public String getIdactivos() {
        return idactivos;
    }

    public void setIdactivos(String idactivos) {
        this.idactivos = idactivos;
    }

    public Integer getIdinsumo() {
        return idinsumo;
    }

    public void setIdinsumo(Integer idinsumo) {
        this.idinsumo = idinsumo;
    }

    public String getIdinsumos() {
        return idinsumos;
    }

    public void setIdinsumos(String idinsumos) {
        this.idinsumos = idinsumos;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getIdtipopagos() {
        return idtipopagos;
    }

    public void setIdtipopagos(String idtipopagos) {
        this.idtipopagos = idtipopagos;
    }

    public float getMontopromesa() {
        return montopromesa;
    }

    public void setMontopromesa(float montopromesa) {
        this.montopromesa = montopromesa;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Pagos{" + "id=" + id + ", descripcion=" + descripcion + ", idproductor=" + idproductor + ", productor=" + productor + ", fecha=" + fecha + ", fechat=" + fechat + ", idtipopago=" + idtipopago + ", idtipopagos=" + idtipopagos + ", monto=" + monto + ", montopromesa=" + montopromesa + ", fechapromesa=" + fechapromesa + ", fechapromesat=" + fechapromesat + ", idactivo=" + idactivo + ", idactivos=" + idactivos + ", idinsumo=" + idinsumo + ", idinsumos=" + idinsumos + '}';
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
   
    
}
