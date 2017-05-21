package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class JapaneseWordsListOneFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    // Handles audio focus when playing a sound file
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange ==
                    AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.
                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
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
        public void onCompletion(MediaPlayer mp) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };


    public JapaneseWordsListOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // add words about numbers (an array)
        final ArrayList<Word> Words = new ArrayList<Word>();

        /**
         *
         * can do the same thing with less code with this line:
         * String words [] = new String []
         * {"one","two","three","four","five","six","seven","eight","nine","ten"};
         *
         * or
         *
         * String[] words = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
         *
         */

        // numbersWords.add(0, "One");

        Words.add(new Word("n. 公园", "公園（こうえん）", R.mipmap.ic_launcher, R.raw.number_one));
        Words.add(new Word("n. 附近，周围", "辺り（あたり）", R.mipmap.ic_launcher, R.raw.number_two));
        Words.add(new Word("n. 建筑物", "建物（たてもの）", R.mipmap.ic_launcher, R.raw.number_three));
        Words.add(new Word("n. 广场", "広場（ひろば）", R.mipmap.ic_launcher, R.raw.number_four));
        Words.add(new Word("ナadj.　漂亮的，干净的 ", "綺麗（きれい）", R.mipmap.ic_launcher, R.raw.number_five));
        Words.add(new Word("n. 体育馆", "体育館（たいいくかん）", R.mipmap.ic_launcher, R.raw.number_six));
        Words.add(new Word("n. 池塘", "池（いけ）", R.mipmap.ic_launcher, R.raw.number_seven));
        Words.add(new Word("ナadj. 美观的，出色的", "立派（りっぱ）", R.mipmap.ic_launcher, R.raw.number_eight));
        Words.add(new Word("イadj. 红的", "赤い（あかい）", R.mipmap.ic_launcher, R.raw.number_nine));
        Words.add(new Word("n. 图书馆", "図書館（としょかん）", R.mipmap.ic_launcher, R.raw.number_ten));
        Words.add(new Word("n. 下午", "午後（ごご）", R.mipmap.ic_launcher, R.raw.number_one));


        // Log.v("NumbersActivity", "Word at Index 0:" + numbersWords.get(0));


        // Find the root view of the whole layout
        //LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        //TextView wordView = new TextView(this);
        //wordView.setText(numbersWords.get(0));
        //rootView.addView(wordView);
        /**
         * int index = 0;
         while(index < numbersWords.size()){
         // Create a new {@link TextView} that displayed the words at the screen
         // and add the view as a child to the rootView
         TextView wordView = new TextView(this);
         wordView.setText(numbersWords.get(index));
         rootView.addView(wordView);
         index++;
         }
         */

        // Try using for loop to rewrite while statement above
        /**
         * for (int index = 0; index < numbersWords.size(); index++) {
         TextView wordView = new TextView(this);
         wordView.setText(numbersWords.get(index));
         rootView.addView(wordView);
         }
         */

        /**
         * Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
         * adapter knows how to create layouts for each item in the list, using the
         * simple_list_item_1.xml layout resource defined in the Android framework.
         * This list item layout contains a single {@link TextView}, which the adapter will set to
         * display a single word.
         */
        wordAdapter itemsAdapter = new wordAdapter(getActivity(), Words, R.color.category_numbers);
        // android框架预定义了一个xml文件，叫做simple_list_item_1，我们可以直接使用
        // 最后一个参数是对象列表，既ArrayAdapter的数据来源
        // 需要传入两个列表参数，因为需要一个日语和一个中文翻译
        // 所以创建我们自己的对象 words类，并通过它来创建words对象

        /**
         * Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
         * There should be a {@link ListView} with the view ID called list, which is declared in the
         * word_list.xml file.
         */
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        /**
         * Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
         * {@link ListView} will display list items for each word in the list of words.
         * Do this by calling the setAdapter method on the {@link ListView} object and pass in
         * 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
         */
        listView.setAdapter(itemsAdapter);

        /**
         * ArrayAdapter itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, numbersWords);
         * GridView gridView = (GridView) findViewById(R.id.grid);
         * gridView.setAdapter(itemsAdapter);
         */
        // Complete GridView effect: java part.

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                Word word = Words.get(position);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChange, AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);


                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
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

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChange);
        }
    }
}
