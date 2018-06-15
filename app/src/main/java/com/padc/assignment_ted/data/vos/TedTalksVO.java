package com.padc.assignment_ted.data.vos;

import java.util.List;

/**
 * Created by Phyo Thiha on 6/10/18.
 */
public class TedTalksVO {

    private String talkId;
    private String title;
    private SpeakerVO speaker;
    private String imageUrl;
    private int talkDurationSecs;
    private String description;
    private List<TagVO> tags;

    public String getTalkId() {
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
        return tags;
    }
}
