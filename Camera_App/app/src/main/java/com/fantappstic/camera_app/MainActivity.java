package com.fantappstic.camera_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=findViewById(R.id.tablayout);
        tabLayout.getTabAt(0).select();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TabResponse(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                TabResponse(tab);
            }
        });
    }

    public void TabResponse(TabLayout.Tab tab){
        if (tab.getPosition() == 0){
           // Toast.makeText(MainActivity.this,"Tab1",Toast.LENGTH_SHORT).show();
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File directory=new File(getExternalMediaDirs()[0].getAbsolutePath()+File.separator+getResources().getString(R.string.app_name));
            if(!directory.exists())
            {
                directory.mkdirs();
            }
            file=new File(directory,System.currentTimeMillis()+".jpg");
            Uri uri= FileProvider.getUriForFile(MainActivity.this,getPackageName()+".provider",file);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(cameraIntent,1406);
        }
        else {
            //Toast.makeText(MainActivity.this,"Tab2",Toast.LENGTH_SHORT).show();
            Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            File directory=new
                    File(getExternalMediaDirs()[0].getAbsolutePath()+File.separator+getResources().getString(R.string.app_name));
            if(!directory.exists())
            {
                directory.mkdirs();
            }
            file=new File(directory,System.currentTimeMillis()+".mp4");
            Uri uri=
                    FileProvider.getUriForFile(MainActivity.this,getPackageName()+".provider",file);
            videoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(videoIntent,101);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode  == 1406){
            Toast.makeText(this, "Image captured", Toast.LENGTH_SHORT).show();
            MediaScannerConnection.scanFile(this,new String[]{file.getAbsolutePath()},null,null);
        }
        if (requestCode == 101 && resultCode == RESULT_OK) {
            Toast.makeText(this, "Video captured", Toast.LENGTH_SHORT).show();
            MediaScannerConnection.scanFile(this,new String[]{file.getAbsolutePath()},null,null);
        }
    }
}