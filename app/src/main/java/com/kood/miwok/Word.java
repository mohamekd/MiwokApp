package com.kood.miwok;

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int imgResId = NO_IMAGE_PROVIDED;

    private int mAudioResId;

    private static final int NO_IMAGE_PROVIDED = -1;


    /*
    * Create a new Word object.
    * @Param defaultTranslation is the word in the language that the user speaks (such English)
    * @Param mMiwokTranslation is the word in the Miwok language
    */

    public Word(String defaultTranslation, String mMiwokTranslation,int audioResId ) {
        mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        mAudioResId = audioResId;
    }

    /*
     * Create a new Word object.
     * @Param defaultTranslation is the word in the language that the user speaks (such English)
     * @Param mMiwokTranslation is the word in the Miwok language
     * @param imgResId is the drawable resource ID for the image assets
     */
    public Word(String defaultTranslation, String mMiwokTranslation,int imgResId,int audioResId) {
        mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.imgResId = imgResId;
        mAudioResId = audioResId;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }


    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImgResId() {
        return imgResId;
    }
    public int getAudioResId() {
        return mAudioResId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return imgResId != NO_IMAGE_PROVIDED;
    }


    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", imgResId=" + imgResId +
                ", mAudioResId=" + mAudioResId +
                '}';
    }
}
