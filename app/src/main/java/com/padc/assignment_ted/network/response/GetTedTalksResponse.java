package com.padc.assignment_ted.network.response;

import com.google.gson.annotations.SerializedName;
import com.padc.assignment_ted.data.vos.TedTalksVO;

import java.util.List;

public class GetTedTalksResponse {
    //Api Specific
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("ted_talks")
    private List<TedTalksVO> tedTalksVOList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<TedTalksVO> getTedTalksVOList() {
        return tedTalksVOList;
    }

    public boolean isResponseOK(){
        return (code==200 && tedTalksVOList != null);//short term Take Note...if else ma use buu!!!
    }
}
