package ar.com.leandroolivera.recuperatorio_dam.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ar.com.leandroolivera.recuperatorio_dam.model.Datos;

@Database(entities = {Datos.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatosDao datosDao();
}
