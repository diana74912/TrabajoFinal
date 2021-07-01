package com.example.trabajofinal.Persistencia;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TrabajadorDAO {

    public interface IDevolverUrlFoto{
        public void devolverUrlString(String url);
    }
    private static TrabajadorDAO trabajadorDAO;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseStorage storage;
    private StorageReference referenceFotoPerfil;

    public static TrabajadorDAO getInstance(){
        if (trabajadorDAO ==null) trabajadorDAO =new TrabajadorDAO();
        return trabajadorDAO;
    }

    private TrabajadorDAO(){
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        reference = database.getReference("Trabajador/"+getKeyUsuario());
        referenceFotoPerfil=storage.getReference("Fotos_Perfil/"+getKeyUsuario());
    }

    public static String getKeyUsuario(){
        return FirebaseAuth.getInstance().getUid();
    }


    public void SubirFotoUri(Uri uri, final IDevolverUrlFoto iDevolverUrlFoto){
        String nombreFoto="";
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("SSS.ss-mm-hh-dd-MM-yyyy", Locale.getDefault());
        nombreFoto=simpleDateFormat.format(date);
        final StorageReference fotoReferencia=referenceFotoPerfil.child(nombreFoto);
        fotoReferencia.putFile(uri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()){
                    throw task.getException();
                }
                return fotoReferencia.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Uri uri=task.getResult();
                    iDevolverUrlFoto.devolverUrlString(uri.toString());
                }
            }
        });
    }

}
