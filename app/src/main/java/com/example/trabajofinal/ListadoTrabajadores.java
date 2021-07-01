package com.example.trabajofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.trabajofinal.Adaptadores.TrabajadorAdaptadores;
import com.example.trabajofinal.Modelo.Trabajador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListadoTrabajadores extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    private TrabajadorAdaptadores trabajadorAdaptadores;
    private final ArrayList<Trabajador> trabajadorArrayList=new ArrayList<Trabajador>();


    public ListadoTrabajadores() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_trabajadores);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        recyclerView=findViewById(R.id.rcTrabajadores);
        recyclerView.setHasFixedSize(true);
        /*GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);*/
        recyclerView.setLayoutManager(new LinearLayoutManager(ListadoTrabajadores.this));
        getTrbajadorFromFirebase();
    }

    public void getTrbajadorFromFirebase(){
        databaseReference.child("Trabajador").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        Trabajador trabajadorOP=ds.getValue(Trabajador.class);
                        System.out.println("Trabajador:"+trabajadorOP);
                        trabajadorArrayList.add(trabajadorOP);
                    }
                    trabajadorAdaptadores = new TrabajadorAdaptadores(trabajadorArrayList,R.layout.cardview);
                    recyclerView.setAdapter(trabajadorAdaptadores);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}