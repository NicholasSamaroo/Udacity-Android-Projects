package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.miwok.Models.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView defaultTranslation;
        public TextView miwokTranslation;
        public ImageView imageView;
        public LinearLayout wordContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            defaultTranslation = itemView.findViewById(R.id.defaultTranslation);
            miwokTranslation = itemView.findViewById(R.id.miwokTranslation);
            imageView = itemView.findViewById(R.id.icon);
            wordContainer = itemView.findViewById(R.id.wordContainer);

        }
    }

    private final List<Word> mWords;
    private final int mColor;
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private final MediaPlayer.OnCompletionListener mediaListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if(focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    public WordAdapter(List<Word> wordList, int color) {
        mWords = wordList;
        this.mColor = color;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View wordView = inflater.inflate(R.layout.word_row, parent, false);
        return new ViewHolder(wordView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Word word = mWords.get(position);
        mAudioManager = (AudioManager) holder.wordContainer.getContext().getSystemService(Context.AUDIO_SERVICE);

        TextView defaultTranslation = holder.defaultTranslation;
        defaultTranslation.setText(word.getmDefaultTranslation());

        TextView miwokTranslation = holder.miwokTranslation;
        miwokTranslation.setText(word.getmMiwokTranslation());

        ImageView imageView = holder.imageView;
        if (word.hasImage()) {
            imageView.setImageResource(word.getmImageResourceID());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        LinearLayout linearLayout = holder.wordContainer;
        linearLayout.setBackgroundResource(mColor);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // A sound may be currently playing, so to make sure they don't overlap
                // release the media player to allow the current clicked sound to be played
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(holder.wordContainer.getContext(), word.getmAudioSound());
                    mMediaPlayer.start();

                    // Release the media resources when the sound is completed
                    // We don't want to have the media player waiting for another sound to be played
                    // which causes a waste of resources
                    mMediaPlayer.setOnCompletionListener(mediaListener);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mWords != null) {
            return mWords.size();
        } else {
            return 0;
        }
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    public void releaseMediaPlayer() {
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
}
