package com.example.trabajofinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {
    EditText NC,EMAIL,PAS,PHONE;
    FirebaseAuth fAuth;
    Button Registro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        NC= findViewById(R.id.etEmail);
        EMAIL= findViewById(R.id.mail);
        PAS= findViewById(R.id.password);
        PHONE= findViewById(R.id.phone);
        Registro = findViewById(R.id.LoginBtn);

        fAuth = FirebaseAuth.getInstance();


        //conexion
        if (fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), MenuPrincipal.class));
        }

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EMAIL.getText().toString().trim();
                String password = PAS.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    EMAIL.setError("Email  es requido");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    PAS.setError("Password es requerido");
                    return;
                }
                if(password.length()<6){
                    PAS.setError("La contraseña tiene que tener mas de 6 caracteres");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Registro.this,"Usuario creado exitosamente",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }else{
                            Toast.makeText(Registro.this,"Se produjo un error  usuario no creado"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });



    }
}