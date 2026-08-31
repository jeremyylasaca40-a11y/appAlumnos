package com.example.appalumnos;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.appalumnos.DbHelper;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnCrearBD;
    private CardView cardConfirmacion;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        btnCrearBD = findViewById(R.id.btnCrearBD);
        cardConfirmacion = findViewById(R.id.cardConfirmacion);

        // Inicializar DbHelper
        dbHelper = new DbHelper(this);

        // Evento del botón
        btnCrearBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearBaseDeDatos();
            }
        });
    }

    private void crearBaseDeDatos() {
        try {
            // Obtener la base de datos (esto la crea si no existe)
            dbHelper.getWritableDatabase();

            // Mostrar confirmación
            cardConfirmacion.setVisibility(View.VISIBLE);

            // Deshabilitar botón temporalmente
            btnCrearBD.setEnabled(false);
            btnCrearBD.setAlpha(0.6f);

            // Toast de confirmación
            Toast.makeText(this, "✅ Base de datos 'alumnos.db' creada exitosamente",
                    Toast.LENGTH_LONG).show();

            // Rehabilitar botón después de 3 segundos
            btnCrearBD.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnCrearBD.setEnabled(true);
                    btnCrearBD.setAlpha(1.0f);
                    cardConfirmacion.setVisibility(View.GONE);
                }
            }, 3000);

        } catch (Exception e) {
            Toast.makeText(this, "❌ Error: " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}