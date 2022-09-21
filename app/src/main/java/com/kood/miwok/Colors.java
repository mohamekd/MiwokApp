package com.kood.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {

    ArrayList<Word> colors = new ArrayList<>();
    ListView colorsListView;
    WordAdapter itemsAdapter;
    MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);


        colors.add(new Word("Red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colors.add(new Word("Green", "chokokki", R.drawable.color_green, R.raw.color_green));
        colors.add(new Word("Brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colors.add(new Word("Gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colors.add(new Word("Black", "kululli", R.drawable.color_black, R.raw.color_black));
        colors.add(new Word("White", "kelelli", R.drawable.color_white, R.raw.color_white));
        colors.add(new Word("Dusty Yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colors.add(new Word("Mustard Yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));


        itemsAdapter = new WordAdapter(this, colors, R.color.category_colors);

        colorsListView = findViewById(R.id.words_list_view);
        colorsListView.setAdapter(itemsAdapter);

        colorsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();
                Word word = colors.get(position);
                Log.v("NumbersActivity", "Current word: " + word);
                mMediaPlayer = MediaPlayer.create(Colors.this, word.getAudioResId());
                mMediaPlayer.start();
                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}