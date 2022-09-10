/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citicos.controller;

import mx.org.citricos.entity.Precios;

/**
 *
 * @author luisa
 */
public class UtilidadesController 
{
    public String getActivoSeleccionado()
    {
        PreciosController controller= new PreciosController();
        Precios precio  =  controller.getSeleccionMasActual();
        if(!precio.isActivo())
            return "/mobile/menu.xhtml?faces-redirect=true";
        else
            return "/mobile/preciosd.xhtml?faces-redirect=true";
    }

    public String function(String pagina) 
    {
        if(pagina.indexOf("getActivoSeleccionado")>0)
            return getActivoSeleccionado();
        return "/mobile/menu.xhtml?faces-redirect=true"; 
    }
}
