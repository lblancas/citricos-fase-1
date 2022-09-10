/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citricos.model;

/**
 *
 * @author luisa
 */ 
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List; 

/**
 * <p>DescripciÃ³n:</p>
 * POJO asociado a la entidad usuario
 */
public class Usuario implements  Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer usuarioPk;
    private String usuario;
    private String clave;
    private String correo;
    private Date fechaCreacion;
    private boolean cuentaNoExpirada;
    private boolean cuentaNoBloqueada;
    private boolean credencialNoExpirada;
    private boolean habilitado;
    private int contadorIntentosFallidos;
    private Date instanteDeBloqueo;
    private String preguntaSecreta;
    private String respuestaSecreta;
    private String idDeSeguridad;
    private Date ventanaParaIdSeguridad;
    private Date fechaUltimoAcceso;
    private Date fechaUltimoCambioClave;
    private List<Perfil> perfiles;

    /**
     * Constructor default de la clase.
     */
    public Usuario() {
    }

    /**
     * Constructor con los atributos que conforman la llave de la clase.
     * @param usuarioPk Llave primaria.
     */
    public Usuario(Integer usuarioPk) {
        this.usuarioPk = usuarioPk;
    }

    /**
     * Llave primaria.
     * @return Llave primaria.
     */
    public Integer getUsuarioPk() {
        return usuarioPk;
    }

    /**
     * Llave primaria.
     * @param usuarioPk Llave primaria.
     */
    public void setUsuarioPk(Integer usuarioPk) {
        this.usuarioPk = usuarioPk;
    }

    /**
     * Username (login.
     * @return Username (login.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Username (login.
     * @param usuario Username (login.
     */
    public void setUsuario(String usuario) {
        if (usuario == null) {
            this.usuario = null;
        } else {
            this.usuario = usuario.toLowerCase();
        }
    }

    /**
     * Password.
     * @return Password.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Password.
     * @param clave Password.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Correo electrÃ³nico (Ãºnico).
     * @return Correo electrÃ³nico (Ãºnico).
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Correo electrÃ³nico (Ãºnico).
     * @param correo Correo electrÃ³nico (Ãºnico).
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Fecha de creaciÃ³n del usuario.
     * @return Fecha de creaciÃ³n del usuario.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Fecha de creaciÃ³n del usuario.
     * @param fechaCreacion Fecha de creaciÃ³n del usuario.
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Cuenta no expirada.
     * @return Cuenta no expirada.
     */
    public boolean isCuentaNoExpirada() {
        return cuentaNoExpirada;
    }

    /**
     * Cuenta no expirada.
     * @param cuentaNoExpirada Cuenta no expirada.
     */
    public void setCuentaNoExpirada(boolean cuentaNoExpirada) {
        this.cuentaNoExpirada = cuentaNoExpirada;
    }

    /**
     * Cuenta no bloqueada.
     * @return Cuenta no bloqueada.
     */
    public boolean isCuentaNoBloqueada() {
        return cuentaNoBloqueada;
    }

    /**
     * Cuenta no bloqueada.
     * @param cuentaNoBloqueada Cuenta no bloqueada.
     */
    public void setCuentaNoBloqueada(boolean cuentaNoBloqueada) {
        this.cuentaNoBloqueada = cuentaNoBloqueada;
    }

    /**
     * Credencial no expirada.
     * @return Credencial no expirada.
     */
    public boolean isCredencialNoExpirada() {
        return credencialNoExpirada;
    }

    /**
     * Credencial no expirada.
     * @param credencialNoExpirada Credencial no expirada.
     */
    public void setCredencialNoExpirada(boolean credencialNoExpirada) {
        this.credencialNoExpirada = credencialNoExpirada;
    }

    /**
     * Usuario habilitado.
     * @return Usuario habilitado.
     */
    public boolean isHabilitado() {
        return habilitado;
    }

    /**
     * Usuario habilitado.
     * @param habilitado Usuario habilitado.
     */
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Contador de intentos fallidos de logueo.
     * @return Contador de intentos fallidos de logueo.
     */
    public int getContadorIntentosFallidos() {
        return contadorIntentosFallidos;
    }

    /**
     * Contador de intentos fallidos de logueo.
     * @param contadorIntentosFallidos Contador de intentos fallidos de logueo.
     */
    public void setContadorIntentosFallidos(int contadorIntentosFallidos) {
        this.contadorIntentosFallidos = contadorIntentosFallidos;
    }

    /**
     * Fecha de instante de bloqueo.
     * @return Fecha de instante de bloqueo.
     */
    public Date getInstanteDeBloqueo() {
        return instanteDeBloqueo;
    }

    /**
     * Fecha de instante de bloqueo.
     * @param instanteDeBloqueo Fecha de instante de bloqueo.
     */
    public void setInstanteDeBloqueo(Date instanteDeBloqueo) {
        this.instanteDeBloqueo = instanteDeBloqueo;
    }

    /**
     * Pregunta secreta.
     * @return Pregunta secreta.
     */
    public String getPreguntaSecreta() {
        return preguntaSecreta;
    }

    /**
     * Pregunta secreta.
     * @param preguntaSecreta Pregunta secreta.
     */
    public void setPreguntaSecreta(String preguntaSecreta) {
        this.preguntaSecreta = preguntaSecreta;
    }

    /**
     * Respuesta a la pregunta secreta.
     * @return Respuesta a la pregunta secreta.
     */
    public String getRespuestaSecreta() {
        return respuestaSecreta;
    }

    /**
     * Respuesta a la pregunta secreta.
     * @param respuestaSecreta Respuesta a la pregunta secreta.
     */
    public void setRespuestaSecreta(String respuestaSecreta) {
        this.respuestaSecreta = respuestaSecreta;
    }

    /**
     * Identificador de texto Ãºnico.
     * @return Identificador de texto Ãºnico.
     */
    public String getIdDeSeguridad() {
        return idDeSeguridad;
    }

    /**
     * Identificador de texto Ãºnico.
     * @param idDeSeguridad Identificador de texto Ãºnico.
     */
    public void setIdDeSeguridad(String idDeSeguridad) {
        this.idDeSeguridad = idDeSeguridad;
    }

    /**
     * Tiempo en que es vÃ¡lido el idSeguridad.
     * @return Tiempo en que es vÃ¡lido el idSeguridad.
     */
    public Date getVentanaParaIdSeguridad() {
        return ventanaParaIdSeguridad;
    }

    /**
     * Tiempo en que es vÃ¡lido el idSeguridad.
     * @param ventanaParaIdSeguridad Tiempo en que es vÃ¡lido el idSeguridad.
     */
    public void setVentanaParaIdSeguridad(Date ventanaParaIdSeguridad) {
        this.ventanaParaIdSeguridad = ventanaParaIdSeguridad;
    }

    /**
     * Fecha de Ãºltimo logueo.
     * @return Fecha de Ãºltimo logueo.
     */
    public Date getFechaUltimoAcceso() {
        return fechaUltimoAcceso;
    }

    /**
     * Fecha de Ãºltimo logueo.
     * @param fechaUltimoAcceso Fecha de Ãºltimo logueo.
     */
    public void setFechaUltimoAcceso(Date fechaUltimoAcceso) {
        this.fechaUltimoAcceso = fechaUltimoAcceso;
    }

    /**
     * Fecha de Ãºltimo cambio de password.
     * @return Fecha de Ãºltimo cambio de password.
     */
    public Date getFechaUltimoCambioClave() {
        return fechaUltimoCambioClave;
    }

    /**
     * Fecha de Ãºltimo cambio de password.
     * @param fechaUltimoCambioClave Fecha de Ãºltimo cambio de password.
     */
    public void setFechaUltimoCambioClave(Date fechaUltimoCambioClave) {
        this.fechaUltimoCambioClave = fechaUltimoCambioClave;
    }

    /**
     * Fecha de Ãºltimo cambio de password.
     * @return Fecha de Ãºltimo cambio de password.
     */
    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    /**
     * Lista de perfiles (roles) que el usuario tiene.
     * @param perfiles Lista de perfiles (roles) que el usuario tiene.
     */
    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }
 

    /**
     * Password.
     * @return Password.
     */ 
    public String getPassword() {
        return clave;
    }

    /**
     * Username (login).
     * @return Username (login).
     */ 
    public String getUsername() {
        return usuario;
    }

    /**
     * Cuenta no expirada.
     * @return Cuenta no expirada.
     */
 
    public boolean isAccountNonExpired() {
        return cuentaNoExpirada;
    }

    /**
     * Cuenta no bloqueada.
     * @return Cuenta no bloqueada.
     */
 
    public boolean isAccountNonLocked() {
        return cuentaNoBloqueada;
    }

    /**
     * Credenciales no expiradas.
     * @return Credenciales no expiradas.
     */
 
    public boolean isCredentialsNonExpired() {
        return credencialNoExpirada;
    }

    /**
     * Usuario habilitado.
     * @return Usuario habilitado.
     */
 
    public boolean isEnabled() {
        return habilitado;
    }
}
