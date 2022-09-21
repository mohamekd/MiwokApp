package com.kood.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {
    //String[] words = {"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"};
    ArrayList<Word> numbers = new ArrayList<>();
    ListView numbersListView;
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


        numbers.add(new Word("One", "Lutti", R.drawable.number_one, R.raw.number_one));
        numbers.add(new Word("Two", "Otiiko", R.drawable.number_two, R.raw.number_two));
        numbers.add(new Word("Three", "Tolookosu", R.drawable.number_three, R.raw.number_three));
        numbers.add(new Word("Four", "Oyyisa", R.drawable.number_four, R.raw.number_four));
        numbers.add(new Word("Five", "Massokka", R.drawable.number_four, R.raw.number_five));
        numbers.add(new Word("Six", "Temmokka", R.drawable.number_five, R.raw.number_six));
        numbers.add(new Word("Seven", "Kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numbers.add(new Word("Eight", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbers.add(new Word("Nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        numbers.add(new Word("Ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        /*
        int index = 0;
        while (index < numbers.size()) {
            //Log.v("Numbers", "Word at Index " + index + ": " + numbers.get(index));
            TextView textView = new TextView(this);
            textView.setText(numbers.get(index));
            numbersViewRoot.addView(textView);
            index++;
        }
        for (int index = 0; index < numbers.size(); index++) {
            TextView textView = new TextView(this);
            textView.setText(numbers.get(index));
            numbersViewRoot.addView(textView);
        }

 */

        itemsAdapter = new WordAdapter(this, numbers, R.color.category_numbers);

        numbersListView = findViewById(R.id.words_list_view);
        numbersListView.setAdapter(itemsAdapter);
        numbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();
                Word word = numbers.get(position);
                Log.v("NumbersActivity", "Current word: " + word);
                mMediaPlayer = MediaPlayer.create(Numbers.this, word.getAudioResId());
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
