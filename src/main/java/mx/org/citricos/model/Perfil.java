/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citricos.model;
import java.io.Serializable;
/**
 *
 * @author luisa
 */
public class Perfil implements  Serializable {
     private static final long serialVersionUID = 1L;
    
    private Integer perfilPk; 
    private String descripcionPerfil;

    /**
     * Constructor default de la clase.
     */
    public Perfil() {
    }

    /**
     * Constructor con los atributos que conforman la llave de la clase.
     * @param perfilPk Llave primaria.
     */
    public Perfil(Integer perfilPk) {
        this.perfilPk = perfilPk;
    }

    /**
     * Llave primaria.
     * @return Llave primaria.
     */
    public Integer getPerfilPk() {
        return perfilPk;
    }

    /**
     * Llave primaria.
     * @param perfilPk Llave primaria.
     */
    public void setPerfilPk(Integer perfilPk) {
        this.perfilPk = perfilPk;
    } 
    /**
     * DescripciÃ³n breve.
     * @return DescripciÃ³n breve.
     */
    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    /**
     * DescripciÃ³n breve.
     * @param descripcionPerfil DescripciÃ³n breve.
     */
    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }
 
}
