package com.kood.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembers extends AppCompatActivity {

    ArrayList<Word> familyMembers = new ArrayList<>();
    ListView familyMembersListView;
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


        familyMembers.add(new Word("Father", "әpә", R.drawable.family_father, R.raw.family_father));
        familyMembers.add(new Word("Mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        familyMembers.add(new Word("Son", "angsi", R.drawable.family_son, R.raw.family_son));
        familyMembers.add(new Word("Daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        familyMembers.add(new Word("Older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        familyMembers.add(new Word("Younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyMembers.add(new Word("Older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyMembers.add(new Word("Younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyMembers.add(new Word("Grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        familyMembers.add(new Word("Grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        itemsAdapter = new WordAdapter(this, familyMembers, R.color.category_family);

        familyMembersListView = findViewById(R.id.words_list_view);
        familyMembersListView.setAdapter(itemsAdapter);

        familyMembersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();
                //get the {@Link Word} object at the given position the user clicked on
                Word word = familyMembers.get(position);
                Log.v("NumbersActivity", "Current word: " + word);
                //create and setup the MediaPlayer for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(FamilyMembers.this, word.getAudioResId());
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