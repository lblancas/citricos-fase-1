
package mx.org.citricos.entity;

import java.io.Serializable;

/**
 *
 * @author BID
 */
public class Cdi  implements Serializable {
    private Integer    id;
    private String nombre;
    private Integer    activo;
    private String activos;

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

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getActivos() {
        return activos;
    }

    public void setActivos(String activos) {
        this.activos = activos;
    }

    @Override
    public String toString() {
        return "CDI " + "id=" + id + ", nombre=" + nombre + ", activo=" + activo + ", activos=" + activos + '}';
    }
    
}
