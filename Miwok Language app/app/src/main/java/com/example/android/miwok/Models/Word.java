package com.example.android.miwok.Models;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceID = NO_IMAGE_RESOURCE;
    private static final int NO_IMAGE_RESOURCE = -1;
    private int mAudioSound;

    public Word(String defaultTranslation, String miwokTranslation, int sound) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioSound = sound;
    }

    public Word(String defaultTranslation, String miwokTranslation, int resourceID, int sound) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceID = resourceID;
        mAudioSound = sound;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageResourceID() {
        return mImageResourceID;
    }

    public boolean hasImage() {
        return mImageResourceID != NO_IMAGE_RESOURCE;
    }

    public int getmAudioSound() {
        return mAudioSound;
    }
}
