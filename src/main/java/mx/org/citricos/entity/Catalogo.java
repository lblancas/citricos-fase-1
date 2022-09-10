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
public class Catalogo  implements Serializable {
    private String id;
    private String impresora;

    public Catalogo(String id, String impresora) {
        this.id = id;
        this.impresora = impresora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImpresora() {
        return impresora;
    }

    public void setImpresora(String impresora) {
        this.impresora = impresora;
    }
    
}
