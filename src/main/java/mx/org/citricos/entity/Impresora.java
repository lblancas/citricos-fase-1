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
public class Impresora  implements Serializable
{
    private int     id;
    private String  login;
    private String  nombre;
    private String  impresora;
    private boolean imprimir;
    private String  perfil;
    public Impresora()
    {
    }
    public Impresora(int id, String login, String nombre, String impresora, boolean imprimir,String perfil) {
        this.id = id;
        this.login = login;
        this.nombre = nombre;
        this.impresora = impresora;
        this.imprimir = imprimir;
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
}
