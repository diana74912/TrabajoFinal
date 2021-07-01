package com.example.trabajofinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class Login extends AppCompatActivity {

   private Button CrearCuenta,ingresar;
   private EditText usuario,contraseña;
   private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CrearCuenta = findViewById(R.id.LoginBtn);
        ingresar = findViewById(R.id.login);
        usuario=findViewById(R.id.etEmail);
        contraseña=findViewById(R.id.etPassword);

        mAuth = FirebaseAuth.getInstance();



        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String email= usuario.getText().toString().trim();
              String password=contraseña.getText().toString().trim();

              if (email.isEmpty()){
                  usuario.setError("Se requiere el correo");
                  usuario.requestFocus();
                  return;
              }
              if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                  usuario.setError("Ingrese el correo");
                  usuario.requestFocus();
                  return;
              }
              if (password.isEmpty()){
                  contraseña.setError("se requiere la contraseña");
                  contraseña.requestFocus();
                  return;
              }
              if(password.length()<6){
                  contraseña.setError("La contraseña debe ser de 6 caracteres");
                  contraseña.requestFocus();
                  return;
              }
              mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          startActivity(new Intent(Login.this, MenuPrincipal.class));

                      }else{
                          Toast.makeText(Login.this,"Fallo el inicio de secion contraseña o usuario invalidos",Toast.LENGTH_SHORT).show();
                      }

                  }
              });


            }
        });
        CrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(Login.this, Registro.class);
                startActivity(inten);
            }
        });
    }
}