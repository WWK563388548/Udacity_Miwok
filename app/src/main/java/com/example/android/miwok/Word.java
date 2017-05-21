package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word that the users want to learn
 * It contains a default translation and a Japanese translation for that word
 *
 * Created by wwk on 17/4/11.
 */

public class Word {
    // Default translation for the word
    private String mDefaultTranslation;

    // Japanese translation for the word
    private String mJapaneseTranslation;

    // ImageID for the words
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    // Audio resource ID for the word
    private int mAudioResourceId;

    // Constant value that represents no image was provided for this word
    private static final int NO_IMAGE_PROVIDED = -1;



    // @param audioResourceId is the resource ID for the audio file associated with this word
    // get two string and one image for the activity that need to display two strings and one image
    public Word (String defaultTranslation, String japaneseTranslation, int imageResourceId, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mJapaneseTranslation = japaneseTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    // get two string for the activity that need to display two strings and one image
    public Word (String defaultTranslation, String japaneseTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mJapaneseTranslation = japaneseTranslation;
        mAudioResourceId = audioResourceId;
    }


    // Get the default translation of the word
    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    // Get the Japanese translation of the word
    public String getmJapaneseTranslation() {
        return mJapaneseTranslation;
    }

    // Get the Image resource id of the words
    public int getmImageResourceId() {
        return mImageResourceId;
    }

    // Returns whether or not there is an image for this word.
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    // Return the audio resource ID of the word.
    public int getmAudioResourceId() {
        return mAudioResourceId;
    }
}