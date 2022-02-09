package com.example.provvisoriomamange.viewholder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.provvisoriomamange.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderPlate extends RecyclerView.ViewHolder {
    public TextView textViewPlate;
    public ImageView imageView;
    public View v_plate;

    public ViewHolderPlate(@NonNull View itemView) {
        super(itemView);
        textViewPlate= itemView.findViewById(R.id.plate);
        imageView=itemView.findViewById(R.id.imgplate);
        v_plate = itemView;
    }
}