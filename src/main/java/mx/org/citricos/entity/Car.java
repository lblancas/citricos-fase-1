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
 * @author luisa
 */
public class Car  implements Serializable{
    
    private String id;
    private String randomid;
    private String randombrand;
    private Integer randomyear;
    private String randomcolor;
    private Integer randomprice;
    private boolean randomsoldstate;
    private int cajas;
    private String cdi;
     

    public Car(int i,String randomid, String randombrand, int randomyear, String randomcolor, int randomprice, boolean randomsoldstate,int cajas,String cdi) {
        this.id=""+i;
        this.randomid = randomid;
        this.randombrand = randombrand;
        this.randomyear = randomyear;
        this.randomcolor = randomcolor;
        this.randomprice = randomprice;
        this.randomsoldstate = randomsoldstate;
        this.cajas= cajas;
        this.cdi=cdi;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRandomid() {
        return randomid;
    }

    public void setRandomid(String randomid) {
        this.randomid = randomid;
    }

    public String getRandombrand() {
        return randombrand;
    }

    public void setRandombrand(String randombrand) {
        this.randombrand = randombrand;
    }

    public Integer getRandomyear() {
        return randomyear;
    }

    public void setRandomyear(Integer randomyear) {
        this.randomyear = randomyear;
    }

    public String getRandomcolor() {
        return randomcolor;
    }

    public void setRandomcolor(String randomcolor) {
        this.randomcolor = randomcolor;
    }

    public Integer getRandomprice() {
        return randomprice;
    }

    public void setRandomprice(Integer randomprice) {
        this.randomprice = randomprice;
    }

    public boolean isRandomsoldstate() {
        return randomsoldstate;
    }

    public void setRandomsoldstate(boolean randomsoldstate) {
        this.randomsoldstate = randomsoldstate;
    }

    public int getCajas() {
        return cajas;
    }

    public void setCajas(int cajas) {
        this.cajas = cajas;
    }

    public String getCdi() {
        return cdi;
    }

    public void setCdi(String cdi) {
        this.cdi = cdi;
    }

    
    
}
