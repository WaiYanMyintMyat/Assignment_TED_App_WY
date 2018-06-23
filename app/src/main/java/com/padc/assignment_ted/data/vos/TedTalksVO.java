package com.padc.assignment_ted.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyo Thiha on 6/10/18.
 */
public class TedTalksVO {

    @SerializedName("talk_id")
    private int talkId;

    @SerializedName("title")
    private String title;

    @SerializedName("speaker")
    private SpeakerVO speaker;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("durationInSec")
    private int talkDurationSecs;

    @SerializedName("description")
    private String description;

    @SerializedName("tag")
    private List<TagVO> tags;

    public int getTalkId() {
        return talkId;
    }

    public String getTitle() {
        return title;
    }

    public SpeakerVO getSpeaker() {
        return speaker;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getTalkDurationSecs() {
        return talkDurationSecs;
    }

    public String getDescription() {
        return description;
    }

    public List<TagVO> getTags() {
        if(tags==null){
            return new ArrayList<>();
        }
        return tags;
    }
}
