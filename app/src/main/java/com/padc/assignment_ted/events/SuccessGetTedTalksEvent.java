package com.padc.assignment_ted.events;

import com.padc.assignment_ted.data.vos.TedTalksVO;
import java.util.List;

public class SuccessGetTedTalksEvent {
    private List<TedTalksVO> tedTalksList;

    public SuccessGetTedTalksEvent(List<TedTalksVO> tedTalksList) {
        this.tedTalksList = tedTalksList;
    }

    public List<TedTalksVO> getTedTalksList() {
        return tedTalksList;
    }
}
