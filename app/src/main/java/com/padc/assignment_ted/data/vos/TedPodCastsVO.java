package com.padc.assignment_ted.data.vos;

import java.util.List;

/**
 * Created by Phyo Thiha on 6/10/18.
 */
public class TedPodCastsVO {

    private int podcastId;
    private String podcastTitle;
    private String imageUrl;
    private String description;
    private List<SegmentVO> segment;

    public int getPodcastId() {
        return podcastId;
    }

    public String getPodcastTitle() {
        return podcastTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public List<SegmentVO> getSegment() {
        return segment;
    }
}
