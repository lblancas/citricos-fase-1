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
public class Albaran  implements Serializable {
    private Integer    id;
    private Integer    id_folio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_folio() {
        return id_folio;
    }

    public void setId_folio(Integer id_folio) {
        this.id_folio = id_folio;
    }
    
    
}
