package com.padc.assignment_ted.data.vos;

import java.util.List;

/**
 * Created by Phyo Thiha on 6/10/18.
 */
public class TedPlayListsVO {

    private int playlistId;
    private String title;
    private String imageUrl;
    private int totalTalks;
    private String description;
    private List<TedTalksVO> talksInPlayList;

    public int getPlaylistId() {
        return playlistId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getTotalTalks() {
        return totalTalks;
    }

    public String getDescription() {
        return description;
    }

    public List<TedTalksVO> getTalksInPlayList() {
        return talksInPlayList;
    }
}
