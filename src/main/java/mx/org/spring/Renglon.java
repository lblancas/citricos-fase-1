package mx.org.spring;

import java.io.Serializable;

public class Renglon  implements Serializable 
{
    private String field="";

    public Renglon () {
    }

    public Renglon (String f) 
    {
    	f= f.replaceAll("  "," ");
        this.field=(f).trim();
    }

    public String getField() {
        return field;
    }

    public void setField(String f) {
        this.field= f;
    }}
