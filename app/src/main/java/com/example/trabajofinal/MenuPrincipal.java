package com.example.trabajofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuPrincipal extends AppCompatActivity {

    ImageButton btntrabajador,btnnosotros,btnmanual, btnubicacion, btnsalir,RegistroT ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        btntrabajador =findViewById(R.id.btnMaletin);
        btnnosotros =findViewById(R.id.btnNosotros);
        btnmanual =findViewById(R.id.btnManual);
        btnubicacion =findViewById(R.id.btnUbicacion);
        btnsalir =findViewById(R.id.btnSalir);
        RegistroT =findViewById(R.id.btnRegistroTR);

        RegistroT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, RegistroTrabajador.class);
                startActivity(intent);
            }
        });

        btnnosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Nosotros.class);
                startActivity(intent);
            }
        });

        btnmanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Manual.class);
                startActivity(intent);

            }
        });

        btnubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Ubicacion.class);
                startActivity(intent);

            }
        });


        btntrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, ListadoTrabajadores.class);
                startActivity(intent);
            }
        });
        //salir
        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

}