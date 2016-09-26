package com.umes.jeb.hww.eis.dto;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by elioth010 on 9/26/16.
 */

public class UsuarioDTO implements Serializable {
    private Integer id;
    private Integer rol;
    private String nombre;
    private String login;
    private String password;
    private String direccion;
    private Integer telefono;
    private Integer estado;

    public UsuarioDTO() {
        super();
    }

    public UsuarioDTO(Integer id, Integer rol, String nombre, String login, String password, String direccion, Integer telefono, Integer estado) {
        this.id = id;
        this.rol = rol;
        this.nombre = nombre;
        this.login = login;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
