/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citricos.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author BID
 */
public class Pallet  implements Serializable{
    private Integer    id;
    private String     qr;
    private String     numero;
    private Integer    cajas;
    private Integer    idactivo;
    private Integer    idtarima;
    private Integer    idmarca;
    private Integer    idtransporte;
    private String     tarima;
    private String     marca;
    private String     transporte;
    private String     valor1;
    private String     valor2;
    private String     valor3; 
    private String     activos;
    private List<Pallet_desc> detalle; 
    private byte[]     tr_arreglo;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getCajas() {
        return cajas;
    }

    public void setCajas(Integer cajas) {
        this.cajas = cajas;
    }

    public Integer getIdactivo() {
        return idactivo;
    }

    public void setIdactivo(Integer idactivo) {
        this.idactivo = idactivo;
    }

    public Integer getIdtarima() {
        return idtarima;
    }

    public void setIdtarima(Integer idtarima) {
        this.idtarima = idtarima;
    }

    public Integer getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Integer idmarca) {
        this.idmarca = idmarca;
    }

    public Integer getIdtransporte() {
        return idtransporte;
    }

    public void setIdtransporte(Integer idtransporte) {
        this.idtransporte = idtransporte;
    }

    @Override
    public String toString() {
        return "Pallet{" + "id=" + id + ", qr=" + qr + "\n, numero=" + numero + ", cajas=" + cajas + ", idactivo=" + idactivo + ", idtarima=" + idtarima + ", idmarca=" + idmarca + ", idtransporte=" + idtransporte + '}';
    }

    public String getTarima() {
        return tarima;
    }

    public void setTarima(String tarima) {
        this.tarima = tarima;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getValor1() {
        return valor1;
    }

    public void setValor1(String valor1) {
        this.valor1 = valor1;
    }

    public String getValor2() {
        return valor2;
    }

    public void setValor2(String valor2) {
        this.valor2 = valor2;
    }

    public String getValor3() {
        return valor3;
    }

    public void setValor3(String valor3) {
        this.valor3 = valor3;
    }

    public byte[] getTr_arreglo() {
        return tr_arreglo;
    }

    public void setTr_arreglo(byte[] tr_arreglo) {
        this.tr_arreglo = tr_arreglo;
    }
    public String getActivos() {
        return activos;
    }

    public void setActivos(String activos) {
        this.activos = activos;
    }

    public List<Pallet_desc> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Pallet_desc> detalle) {
        this.detalle = detalle;
    }
    
    
}
