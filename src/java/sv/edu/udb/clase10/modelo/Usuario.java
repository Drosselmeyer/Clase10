/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.clase10.modelo;

/**
 *
 * @author Drosselmeyer
 */
public class Usuario {
    
    //Propiedades
    private int idUsuario;
    private String nombre;
    private String email;
    private int idPais;

    public Usuario() {
    }

    public Usuario(String nombre, String email, int idPais) {
        this.nombre = nombre;
        this.email = email;
        this.idPais = idPais;
    }
    
    
    //Setter y getter
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }
    
    
}
