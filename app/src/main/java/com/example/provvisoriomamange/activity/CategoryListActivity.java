package com.example.provvisoriomamange.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.provvisoriomamange.model.Category;
import com.example.provvisoriomamange.viewholder.ViewHolderCategory;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.example.provvisoriomamange.R;

public class CategoryListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<Category> options_cat;
    FirebaseRecyclerAdapter<Category, ViewHolderCategory> adapter_cat;
    DatabaseReference dataref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        dataref =  FirebaseDatabase.getInstance().getReference().child("Categories");

        recyclerView=findViewById(R.id.recyclerView_categorie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Metti nome ristorante");
        actionBar.setDisplayHomeAsUpEnabled(true);

        loadData("");

    }

    private void loadData(String data) {


        Query query= dataref.orderByChild("nome").startAt(data).endAt(data+"\uf8ff");

        options_cat=new FirebaseRecyclerOptions.Builder<Category>().setQuery(query,Category.class).build();
        adapter_cat=new FirebaseRecyclerAdapter<Category, ViewHolderCategory>(options_cat) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderCategory holder, @SuppressLint("RecyclerView") int position, @NonNull Category model) {
                holder.textViewcategory.setText(model.nome);
                holder.v_cat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(CategoryListActivity.this,PlateActivity.class);
                        intent.putExtra("CategoryKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public ViewHolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v_cat= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_category,parent,false);
                return new ViewHolderCategory(v_cat);
            }
        };
        adapter_cat.startListening();
        recyclerView.setAdapter(adapter_cat);

    }
}
