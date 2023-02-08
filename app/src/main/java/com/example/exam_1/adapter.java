package com.example.exam_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class adapter extends RecyclerView.Adapter<adapter.myclass> {

    MainActivity mainActivity;
    ArrayList<String> templistimage;
    public adapter(MainActivity mainActivity, ArrayList<String> templistimage) {
        this.mainActivity=mainActivity;
        this.templistimage=templistimage;
    }

    @NonNull
    @Override
    public myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mainActivity).inflate(R.layout.item,parent,false);
        myclass m = new myclass(view);

        return m;
    }



    class myclass extends RecyclerView.ViewHolder {
        ImageView imageView;
        public myclass(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image1);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull myclass holder,@SuppressLint("RecyclerView") int position) {


        InputStream inputstream= null;
        try
        {
            inputstream = mainActivity.getAssets().open("" +templistimage.get(position));
            Drawable drawable = Drawable.createFromStream(inputstream, null);
            holder.imageView.setImageDrawable(drawable);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity,MainActivity1.class);
                intent.putExtra("pos",holder.getAdapterPosition());
                intent.putExtra("array",templistimage);
                mainActivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return templistimage.size();
    }
}
