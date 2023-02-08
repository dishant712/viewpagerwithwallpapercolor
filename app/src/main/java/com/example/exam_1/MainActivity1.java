package com.example.exam_1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity1 extends AppCompatActivity {

    ArrayList<String> templistimage = new ArrayList<>();
    int pos;
    ImageView pre,next,setting;
    ViewPager imageView;

    LinearLayout linearphoto,linearsetting;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        imageView=findViewById(R.id.photo);
        pre=findViewById(R.id.pre);
        next=findViewById(R.id.next);
        setting=findViewById(R.id.setting);
        linearphoto=findViewById(R.id.linearphoto);
        linearsetting=findViewById(R.id.linearsetting);

        pos=getIntent().getIntExtra("pos",0);
        templistimage=getIntent().getStringArrayListExtra("array");

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Nature");
        ab.setBackgroundDrawable(new ColorDrawable(getColor(R.color.white)));



//        InputStream inputstream= null;
//        try
//        {
//            inputstream =getAssets().open("" +templistimage.get(pos));
//            Drawable drawable = Drawable.createFromStream(inputstream, null);
//            imageView.setBackground(drawable);
//
//            Bitmap bitmap = drawableToBitmap(drawable);
//            Palette p = createPaletteSync(bitmap);
//            Palette.Swatch vibrantSwatch = p.getLightVibrantSwatch();
//
//
//            if (vibrantSwatch != null) {
//                linearphoto.setBackgroundColor(vibrantSwatch.getRgb());
//                linearsetting.setBackgroundColor(vibrantSwatch.getRgb());
//                ab.setBackgroundDrawable(new ColorDrawable(vibrantSwatch.getRgb()));
//
//            }
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }


//        adapter1 adapter1 = new adapter1(this,templistimage);
//        imageView.setAdapter(adapter1);

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Drawable drawable = null;
                if (pos > 0) {
                    pos--;
                    InputStream inputstream = null;
                    try {
                        inputstream = getAssets().open("" + templistimage.get(pos));
                        drawable = Drawable.createFromStream(inputstream, null);
                        imageView.setBackground(drawable);
                        Bitmap bitmap = drawableToBitmap(drawable);
                        Palette p = createPaletteSync(bitmap);
                        Palette.Swatch vibrantSwatch = p.getLightVibrantSwatch();

                        if (vibrantSwatch != null) {
                            linearphoto.setBackgroundColor(vibrantSwatch.getRgb());
                            linearsetting.setBackgroundColor(vibrantSwatch.getRgb());
                            ab.setBackgroundDrawable(new ColorDrawable(vibrantSwatch.getRgb()));

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Drawable drawable = null;
                if (pos < templistimage.size() - 1) {
                    pos++;
                    InputStream inputstream = null;
                    try {
                        inputstream = getAssets().open("" + templistimage.get(pos));
                        drawable = Drawable.createFromStream(inputstream, null);
                        imageView.setBackground(drawable);
                        Bitmap bitmap = drawableToBitmap(drawable);
                        Palette p = createPaletteSync(bitmap);
                        Palette.Swatch vibrantSwatch = p.getDarkVibrantSwatch();

                        if (vibrantSwatch != null) {
                            linearphoto.setBackgroundColor(vibrantSwatch.getRgb());
                            linearsetting.setBackgroundColor(vibrantSwatch.getRgb());
                            ab.setBackgroundDrawable(new ColorDrawable(vibrantSwatch.getRgb()));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        String[] arr={"Home screen","Lock screen"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("wallpaper");
        builder.setItems(arr, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(getApplicationContext());
                if (i==0)
                {
                    InputStream inputstream= null;
                    try
                    {
                        inputstream = getAssets().open("" +templistimage.get(pos));
                        myWallpaperManager.setStream(inputstream,null,true,WallpaperManager.FLAG_SYSTEM);

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                       }
                if (i==1)
                {
                    InputStream inputstream= null;
                    try
                    {
                        inputstream = getAssets().open("" +templistimage.get(pos));
                        myWallpaperManager.setStream(inputstream,null,true,WallpaperManager.FLAG_SYSTEM);

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.show();
            }
        });



    }
    public Palette createPaletteSync(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        return p;

    }
    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}