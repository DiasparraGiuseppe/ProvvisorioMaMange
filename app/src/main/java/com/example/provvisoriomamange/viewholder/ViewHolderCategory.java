package com.example.provvisoriomamange.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.provvisoriomamange.R;

public class ViewHolderCategory extends RecyclerView.ViewHolder {
    public TextView textViewcategory;
    public View v_cat;

    public ViewHolderCategory(@NonNull View itemView) {
        super(itemView);
        textViewcategory= itemView.findViewById(R.id.category_plate);
        v_cat = itemView;
    }
}