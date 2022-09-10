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
public class Perfil  implements Serializable {
    private Integer     id;
    private String  nombre;
    private String  activos;
    private boolean activo;
    private String  pagina;
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

    public String getActivos() {
        return activos;
    }

    public void setActivos(String activos) {
        this.activos = activos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
    

    @Override
    public String toString() {
        return "Perfil{" + "id=" + id + ", nombre=" + nombre + ", activos=" + activos + ", activo=" + activo + '}';
    }
    
}
