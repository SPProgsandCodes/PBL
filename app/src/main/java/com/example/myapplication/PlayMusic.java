package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayMusic extends AppCompatActivity {
    TextView textMusicTitle;
    ImageView imgPreviousMusic, imgPlayMusic, imgNextMusic;
    ArrayList<File> songs;
    MediaPlayer mediaPlayer;
//    ArrayList<String> songs;
    String textContent;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        mediaPlayer = new MediaPlayer();
        textMusicTitle = findViewById(R.id.textViewMusicTitle);
        imgPreviousMusic = findViewById(R.id.imageViewPrevious);
        imgPlayMusic = findViewById(R.id.imageViewPlayMusic);
        imgNextMusic = findViewById(R.id.imageViewNextMusic);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        songs = new ArrayList<>();
//        songs =  (ArrayList) bundle.getParcelableArrayList("songList");
//        songs =  ;
        songs = (ArrayList<File>) getIntent().getSerializableExtra("songList");
        textContent = intent.getStringExtra("currentSong");
        position = intent.getIntExtra("position", 0);
        textMusicTitle.setText(textContent);

        for (File file : songs){
            String filePath = file.getPath();
            Log.d("Arraylist File Path: ", filePath);
        }



/*
        imgPlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Uri uri = Uri.parse(songs.get(position).toString());

                try {
//                    mediaPlayer.createVolumeShaper(songs.get(position).toString());
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(songs.get(position).getAbsolutePath());
//                    mediaPlayer.prepare();
//                mediaPlayer = MediaPlayer.create(PlayMusic.this, uri);
                    mediaPlayer.start();

                }catch (Exception e){
                    Toast.makeText(PlayMusic.this, e.toString(), Toast.LENGTH_SHORT).show();
//                    error = e.toString();
                }
            }
        });

    }
}
*/
    }}