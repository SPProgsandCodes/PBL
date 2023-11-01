package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
    ListView listView;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mediaPlayer = new MediaPlayer();
        listView = findViewById(R.id.listView);

        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Toast.makeText(HomeScreen.this, "Permission Successfully Granted", Toast.LENGTH_SHORT).show();
                        ArrayList<File> mySongs = fetchSongs(Environment.getExternalStorageDirectory());
                        String [] items = new String[mySongs.size()];
                        for (int i = 0; i<mySongs.size(); i++){
                            items[i] = mySongs.get(i).getName().replace("mp3", "");
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(HomeScreen.this, android.R.layout.simple_list_item_1, items);
                        listView.setAdapter(adapter);

                        for (File file : mySongs){
                            String filePath = file.getPath();
                            Log.d("Arraylist File Path: ", filePath);
                        }


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Uri uri = Uri.parse(mySongs.get(i).toString());
                                //                                    mediaPlayer.setDataSource();
                                mediaPlayer = MediaPlayer.create(HomeScreen.this, uri);
                                mediaPlayer.start();
                            }
                        });
                        /*
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(HomeScreen.this, PlayMusic.class);
                                String currentSong = listView.getItemAtPosition(i).toString();
//                                intent.putExtra("songList", mySongs);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("songList", mySongs);
                                intent.putExtra("currentSong", currentSong);
                                intent.putExtra("position", i);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });

                         */
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
    }

    public ArrayList<File> fetchSongs(File file){
        ArrayList arrayList = new ArrayList();
        File [] songs = file.listFiles();
        if (songs != null){
            for (File myFile:songs){
                if (!myFile.isHidden() && myFile.isDirectory()){
//                    arrayList.addAll(fetchSongs(myFile));
                }
                else {
                    if (myFile.getName().endsWith(".mp3") &&  !myFile.getName().startsWith(".")){
                        arrayList.add(myFile);
                    }
                }
            }
        }
        return arrayList;
    }

}