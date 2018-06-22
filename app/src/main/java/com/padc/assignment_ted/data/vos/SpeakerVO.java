package com.padc.assignment_ted.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyo Thiha on 6/10/18.
 */
public class SpeakerVO {

    @SerializedName("speaker_id")
    private int speakerId;

    @SerializedName("name")
    private String speakerName;

    public int getSpeakerId() {
        return speakerId;
    }

    public String getSpeakerName() {
        return speakerName;
    }
}
