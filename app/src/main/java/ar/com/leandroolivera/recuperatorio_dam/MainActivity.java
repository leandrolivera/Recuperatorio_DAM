package ar.com.leandroolivera.recuperatorio_dam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

import ar.com.leandroolivera.recuperatorio_dam.model.Datos;
import ar.com.leandroolivera.recuperatorio_dam.room.AppDatabase;
import ar.com.leandroolivera.recuperatorio_dam.room.DatosDao;

public class MainActivity extends AppCompatActivity {

    TextView tvTitulo;
    EditText etNombre, etApellido, etCalle, etNumero;
    ToggleButton toggleGuardar;
    Button btnTitulo, btnBorrar, btnSaludo, btnGuardar, btnMostrar;
    ListView listViewDatos;
    String nuevoTitulo, tituloOriginal;
    List<String> listaRecuperados = null;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitulo = findViewById(R.id.tvTitulo);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCalle = findViewById(R.id.etCalle);
        etNumero = findViewById(R.id.etNumero);
        toggleGuardar = findViewById(R.id.toggleGuardar);
        btnTitulo = findViewById(R.id.btnTitulo);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnSaludo = findViewById(R.id.btnSaludar);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnMostrar = findViewById(R.id.btnMostrar);
        listViewDatos = findViewById(R.id.listViewDatos);
        nuevoTitulo = "Gracias por cambiar de título.";
        tituloOriginal = tvTitulo.getText().toString();



        toggleGuardar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnMostrar.setEnabled(toggleGuardar.isChecked());
            }
        });

        btnTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvTitulo.getText().toString().equals(tituloOriginal)){
                    tvTitulo.setText(nuevoTitulo);
                }
                else{
                    tvTitulo.setText(tituloOriginal);
                }
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitulo.setText(tituloOriginal);
                etNombre.setText("");
                etApellido.setText("");
                etCalle.setText("");
                etNumero.setText("");
                toggleGuardar.setChecked(false);

            }
        });

        btnSaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNombre.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Por favor, ingrese su nombre", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Hola! Gracias por elegirnos "+etNombre.getText().toString()+"!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNombre.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Por favor, ingrese su nombre", Toast.LENGTH_LONG).show();
                }
                else if (etApellido.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Por favor, ingrese su apellido", Toast.LENGTH_LONG).show();
                }
                else if (etCalle.getText().toString().isEmpty() || etNumero.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Por favor, complete los datos de su domicilio", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Datos guardados en la base de datos exitosamente", Toast.LENGTH_LONG).show();
                    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                            AppDatabase.class, "recuperatorio-db").build();
                    Datos datos = new Datos(etNombre.getText().toString(), etApellido.getText().toString(),
                            etCalle.getText().toString(), etNumero.getText().toString());
                    db.datosDao().insertar(datos);
                    etNombre.setText("");
                    etApellido.setText("");
                    etCalle.setText("");
                    etNumero.setText("");
                }
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "recuperatorio-db").build();
                List<Datos> listaDatos = db.datosDao().buscarDatos();
                String datosRecuperados;
                for (int i = 0; i < listaDatos.size(); i++){
                    datosRecuperados = "Nombre: "+listaDatos.get(i).getNombre()+" "+"Apellido: "+listaDatos.get(i).getApellido()+" "+"Direccion: "+listaDatos.get(i).getCalle()+" "+listaDatos.get(i).getNumero();
                    listaRecuperados.add(datosRecuperados);
                }
                adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listaRecuperados);
                listViewDatos.setAdapter(adapter);
            }
        });

    }
}