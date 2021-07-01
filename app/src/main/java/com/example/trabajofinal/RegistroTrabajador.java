package com.example.trabajofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabajofinal.Modelo.Trabajador;
import com.example.trabajofinal.Persistencia.TrabajadorDAO;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistroTrabajador extends AppCompatActivity {

    CircleImageView imgT;
    EditText nombreT,oficioT,telefonoT,descripcionT;
    Button registrar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private ImagePicker imagePicker;
    private Uri FOTO_TRABAJADOR_URL;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_trabajador);

        mAuth = FirebaseAuth.getInstance();
        InicializarFirebase();

        imgT=findViewById(R.id.imagenT);
        nombreT=findViewById(R.id.txtNombreT);
        oficioT=findViewById(R.id.txtOficioT);
        telefonoT=findViewById(R.id.txtTelefonoT);
        descripcionT=findViewById(R.id.txtDescripcionT);
        registrar=findViewById(R.id.btnRegistrarTrabajador);

        imagePicker=new ImagePicker(RegistroTrabajador.this);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        String n=nombreT.getText().toString().trim();
                        String o=oficioT.getText().toString().trim();
                        String d=descripcionT.getText().toString().trim();
                        String t=telefonoT.getText().toString().trim();
                        Trabajador trabajador =new Trabajador();
                        trabajador.setFoto(databaseReference.push().getKey());
                        trabajador.setNombre(n);
                        trabajador.setOficio(o);
                        trabajador.setDescripcion(d);
                        trabajador.setTelefono(t);

                        //String id= databaseReference.push().getKey();
                        databaseReference.child("Trabajador").child(databaseReference.push().getKey()).setValue(trabajador);
                        //databaseReference.child("Trabajador").setValue(trabajador);
                        Toast.makeText(RegistroTrabajador.this,"Trabajador Registrado",Toast.LENGTH_SHORT).show();


            }
        });

        imagePicker.setImagePickerCallback(new ImagePickerCallback() {
            @Override
            public void onImagesChosen(List<ChosenImage> list) {
                if (!list.isEmpty()){
                    String path=list.get(0).getOriginalPath();
                    FOTO_TRABAJADOR_URL =Uri.parse(path);
                    imgT.setImageURI(FOTO_TRABAJADOR_URL);
                }
            }
            @Override
            public void onError(String s) {
                Toast.makeText(RegistroTrabajador.this,"ERROR"+s,Toast.LENGTH_SHORT).show();
            }
        });

        imgT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePicker.pickImage();
            }
        });
    }

    private void InicializarFirebase() {
        FirebaseApp.initializeApp(RegistroTrabajador.this);
        firebaseDatabase=firebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }
}