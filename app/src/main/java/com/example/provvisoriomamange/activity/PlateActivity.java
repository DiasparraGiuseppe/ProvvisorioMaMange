package com.example.provvisoriomamange.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.provvisoriomamange.R;
import com.example.provvisoriomamange.model.Category;
import com.example.provvisoriomamange.model.Plate;
import com.example.provvisoriomamange.viewholder.ViewHolderCategory;
import com.example.provvisoriomamange.viewholder.ViewHolderPlate;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlateActivity extends AppCompatActivity {

    RecyclerView recyclerView2;
    FirebaseRecyclerOptions<Plate> options_plate;
    FirebaseRecyclerAdapter<Plate, ViewHolderPlate> adapter_plate;
    DatabaseReference dataref2;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plates_of_category);

        Intent intent = getIntent();
        String CategoryKey = intent.getStringExtra("CategoryKey");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(CategoryKey);
        actionBar.setDisplayHomeAsUpEnabled(true);

        dataref2 =  FirebaseDatabase.getInstance().getReference().child("Categories").child(CategoryKey);

        recyclerView2=findViewById(R.id.recyclerView_piatti);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView2.setHasFixedSize(true);


        loadData("");

    }

    private void loadData(String data) {


        Query query2= dataref2.orderByChild("nome").startAt(data).endAt(data+"\uf8ff");

        options_plate=new FirebaseRecyclerOptions.Builder<Plate>().setQuery(query2,Plate.class).build();
        adapter_plate=new FirebaseRecyclerAdapter<Plate, ViewHolderPlate>(options_plate) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderPlate holder, @SuppressLint("RecyclerView") int position, @NonNull Plate model) {
                holder.textViewPlate.setText(model.nome);
                holder.v_plate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      /*  Intent intent =new Intent(PlateActivity.this,PlateDescriptionActivity.class);
                        intent.putExtra("CategoryKey",getRef(position).getKey());
                        startActivity(intent);*/
                    }
                });
            }

            @NonNull
            @Override
            public ViewHolderPlate onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v_cat= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_plate,parent,false);
                return new ViewHolderPlate(v_cat);
            }
        };
        adapter_plate.startListening();
        recyclerView2.setAdapter(adapter_plate);

    }
}
