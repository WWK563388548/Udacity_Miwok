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
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class JapaneseWordsListFourFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    public JapaneseWordsListFourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> Words = new ArrayList<Word>();


        Words.add(new Word("n. 公园", "公園（こうえん）", R.raw.phrase_are_you_coming));
        Words.add(new Word("n. 附近，周围", "辺り（あたり）", R.raw.phrase_come_here));
        Words.add(new Word("n. 建筑物", "建物（たてもの）", R.raw.phrase_how_are_you_feeling));
        Words.add(new Word("n. 广场", "広場（ひろば）", R.raw.phrase_im_coming));
        Words.add(new Word("ナadj.　漂亮的，干净的 ", "綺麗（きれい）", R.raw.phrase_im_feeling_good));
        Words.add(new Word("n. 体育馆", "体育館（たいいくかん）", R.raw.phrase_lets_go));
        Words.add(new Word("n. 池塘", "池（いけ）", R.raw.phrase_my_name_is));
        Words.add(new Word("ナadj. 美观的，出色的", "立派（りっぱ）", R.raw.phrase_what_is_your_name));
        Words.add(new Word("イadj. 红的", "赤い（あかい）", R.raw.phrase_where_are_you_going));
        Words.add(new Word("n. 图书馆", "図書館（としょかん）", R.raw.phrase_yes_im_coming));
        Words.add(new Word("n. 下午", "午後（ごご）", R.raw.phrase_how_are_you_feeling));

        wordAdapter itemsAdapter = new wordAdapter(getActivity(), Words, R.color.category_family);
        // android框架预定义了一个xml文件，叫做simple_list_item_1，我们可以直接使用
        // 最后一个参数是对象列表，既ArrayAdapter的数据来源
        // 需要传入两个列表参数，因为需要一个日语和一个中文翻译
        // 所以创建我们自己的对象 words类，并通过它来创建words对象

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word = Words.get(position);
                Toast.makeText(getActivity(), "Click Item", Toast.LENGTH_SHORT).show();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChange, AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });


        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChange);
        }
    }

}
