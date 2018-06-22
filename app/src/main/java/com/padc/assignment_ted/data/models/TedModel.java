package com.padc.assignment_ted.data.models;

import com.padc.assignment_ted.data.vos.TedTalksVO;
import com.padc.assignment_ted.events.SuccessGetTedTalksEvent;
import com.padc.assignment_ted.network.HttpUrlConnectionTedTalksDataAgentImpl;
import com.padc.assignment_ted.network.OkHttpDataAgentImpl;
import com.padc.assignment_ted.network.RetrofitDataAgentImpl;
import com.padc.assignment_ted.network.TedTalksDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class TedModel {
    private static TedModel objInstance;
    private TedTalksDataAgent mtedTalksDataAgent;
    private static final String DUMMY_ACCESS_TOKEN="b002c7e1a528b7cb460933fc2875e916";

    //Data Repository
    private Map<Integer, TedTalksVO> mTedTalks;//<String,NewsVO> String news_id use ya mal...

    private TedModel() {
        mTedTalks = new HashMap<>();
        //mtedTalksDataAgent=HttpUrlConnectionTedTalksDataAgentImpl.getObjInstance();
        //mtedTalksDataAgent= OkHttpDataAgentImpl.getObjectReference();
        mtedTalksDataAgent= RetrofitDataAgentImpl.getObjectReference();
        EventBus.getDefault().register(this);
    }

    public static TedModel getObjInstance(){
        if(objInstance==null){
            objInstance=new TedModel();
        }
        return objInstance;
    }

    public void loadTedTalksList(){
        mtedTalksDataAgent.loadTedTalksList(1,DUMMY_ACCESS_TOKEN);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetTedTalks(SuccessGetTedTalksEvent successGetTedTalkEvent) {

        for (TedTalksVO tedTalk : successGetTedTalkEvent.getTedTalksList()) {
            mTedTalks.put(tedTalk.getTalkId(), tedTalk);
        }
    }

    public TedTalksVO getTedTalkById(int talkId) {
        return mTedTalks.get(talkId);
    }
}
