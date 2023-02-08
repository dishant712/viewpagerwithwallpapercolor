package com.example.exam_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> templistimage = new ArrayList<>();
    ArrayList<String> listimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] image=new String[0];
        try {
            image= getAssets().list("");
            listimage=new ArrayList<String>(Arrays.asList(image));
            for (int i=0;i<listimage.size();i++)
            {
                if (listimage.get(i).endsWith(".jpg"))
                {
                    templistimage.add(listimage.get(i));
                }
            }

            System.out.println("assahGAHG========="+templistimage);

        } catch (IOException e) {
            e.printStackTrace();
        }



        recyclerView=findViewById(R.id.recycle);

        adapter adapter = new adapter(this,templistimage);
        recyclerView.setAdapter(adapter);

        GridLayoutManager linearLayoutManager=new GridLayoutManager(this,2,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}