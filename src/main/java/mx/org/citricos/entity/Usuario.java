/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citricos.entity;

import java.io.Serializable;

/**
 *
 * @author BID
 */
public class Usuario implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String  nombre;
    private String  apellidos;
    private String  login; 
    private String  password;
    private Integer perfil;
    private Integer activo;
    private String  perfilstr;
    private String  activostr;
    private String  cabecera;
    private String  msg;
    private String  pagina;
    private String  impresora;
    private boolean imprimir;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPerfil() {
        return perfil;
    }

    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getPerfilstr() {
        return perfilstr;
    }

    public void setPerfilstr(String perfilstr) {
        this.perfilstr = perfilstr;
    }

    public String getActivostr() {
        return activostr;
    }

    public void setActivostr(String activostr) {
        this.activostr = activostr;
    }

    public String getCabecera() {
        return cabecera;
    }

    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public String getImpresora() {
        return impresora;
    }

    public void setImpresora(String impresora) {
        this.impresora = impresora;
    }

    public boolean isImprimir() {
        return imprimir;
    }

    public void setImprimir(boolean imprimir) {
        this.imprimir = imprimir;
    }
     
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", login=" + login + ", password=" + password + ", perfil=" + perfil + ", activo=" + activo + ", perfilstr=" + perfilstr + ", activostr=" + activostr + '}';
    }
    
}
