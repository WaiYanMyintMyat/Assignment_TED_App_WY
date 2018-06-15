package com.padc.assignment_ted.data.models;

import com.padc.assignment_ted.network.HttpUrlConnectionTedTalksDataAgentImpl;
import com.padc.assignment_ted.network.TedTalksDataAgent;

public class TedModel {
    private static TedModel objInstance;
    private TedTalksDataAgent mtedTalksDataAgent;
    private static final String DUMMY_ACCESS_TOKEN="b002c7e1a528b7cb460933fc2875e916";

    private TedModel() {
        mtedTalksDataAgent=HttpUrlConnectionTedTalksDataAgentImpl.getObjInstance();
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
}
