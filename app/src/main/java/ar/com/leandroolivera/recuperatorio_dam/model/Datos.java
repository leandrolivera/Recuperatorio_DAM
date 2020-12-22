package ar.com.leandroolivera.recuperatorio_dam.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Datos {
    @PrimaryKey(autoGenerate = true)
    Long id;
    String nombre;
    String apellido;
    String calle;
    String numero;

    public Datos(String nombre, String apellido, String calle, String numero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.calle = calle;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
