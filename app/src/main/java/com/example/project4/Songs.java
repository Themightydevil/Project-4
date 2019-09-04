package com.example.project4;

public class Songs {

    private String mSongName;
    private String mArtistName;
    private String mRemixBy;
    private int mImageAsset;
    private int mAudioResourceId;

    public Songs(String songName, String artistName, String remixedBy, int imageAsset, int audioResourceId) {
        mSongName = songName;
        mArtistName = artistName;
        mRemixBy = remixedBy;
        mImageAsset = imageAsset;
        mAudioResourceId = audioResourceId;
    }

    public String getSongName() {
        return mSongName;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getRemixBy() {
        return mRemixBy;
    }

    public int getImageAsset() {
        return mImageAsset;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
