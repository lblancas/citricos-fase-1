package mx.org.spring;

import java.io.Serializable;

public class User  implements Serializable
{
    private String username="";
    private String nombre="";
    private String role="";
    private String pagina="";
    private int    codigo=0;
    private String msg="";
    private int    id=0;
    public User( int id,String user,String nom,String role,String pag) 
   {
   		this.id = id;
        this.username = user;
        this.nombre =  nom;
        this.role =role;
        this.pagina = pag;
        this.codigo =1;
        this.msg = "Ok.Exito";
    }
    public User( int estatus,String msg) 
   {
        this.codigo =estatus;
        this.msg =msg;
    }
    public String getUsername() {
        return username;
    }
    public String getNombre() {
        return nombre;
    }
    public String getRole() {
        return role;
    }
    public String getPagina() {
        return pagina;
    }
    public int getId() {
        return id;
    }
    public int getCodigo() {
        return codigo;
    }
    public String getMsg() {
        return msg;
    }
}
