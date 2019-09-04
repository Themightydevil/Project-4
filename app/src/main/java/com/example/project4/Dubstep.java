package com.example.project4;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Dubstep extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {

                    // If Audio Focus is lost then pause and the audio.
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                    }

                    // If Audio Focus is gained then play the audio back from beginning
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    }

                    // Release the Media Player resources and stop the audio if there is a permanent loss
                    // of Audio Focus
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create an array of songs
        final ArrayList<Songs> songs = new ArrayList<>();
        final ListView rootView = findViewById(R.id.rootView);

        // Add 2 items of type Song in variable songs.
        songs.add(new Songs(getString(R.string.gold_love_song), getString(R.string.gold_love_artist),
                getString(R.string.gold_love_remix), R.drawable.flux_pavilion, R.raw.gold_love));
        songs.add(new Songs(getString(R.string.both_song), getString(R.string.both_artist),
                getString(R.string.both_remix), R.drawable.drake, R.raw.both));


        // Set our customer Adapter to display the layout.
        SongsAdapter itemsAdapter = new SongsAdapter(this, songs, R.color.category_dubstep);
        rootView.setAdapter(itemsAdapter);


        // Add onClickListener on layout
        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                releaseMediaPlayer();
                Songs song = songs.get(position);

                // Request audio focus for play back
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request temporary focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                // We now have Audio Focus and can play the audio

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(Dubstep.this, song.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
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
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    /**
     * When the activity is stopped, clean up the resources by releasing the MediaPlayer files.
     */
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
