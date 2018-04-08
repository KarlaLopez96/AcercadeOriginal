package com.karla00058615.acercade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.FileOutputStream;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClickShare(View view){

        BitmapDrawable drawable = (BitmapDrawable)getBaseContext().getResources().getDrawable(R.drawable.foto1);
        Bitmap bitmap = drawable.getBitmap();
        try {
            File file = new File(this.getExternalCacheDir(),"foto1.jpg");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.putExtra(Intent.EXTRA_TEXT, "Karla Esperanza López\n" +
                    "gitHub:KarlaLopez96\n" +
                    "Facebook:Karla López\n" +
                    "Gmail:00058615@uca.edu.sv\n" +
                    "Cel:73459867");
            intent.setType("*/*");
            startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
