package ar.com.leandroolivera.recuperatorio_dam.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ar.com.leandroolivera.recuperatorio_dam.model.Datos;

@Dao
public interface DatosDao {

    @Query("SELECT * FROM datos")
    List<Datos> buscarDatos();

    @Query("SELECT * FROM datos WHERE nombre LIKE :nombre LIMIT 1")
    Datos buscarNombre(String nombre);

    @Insert
    void insertar(Datos datos);
}
