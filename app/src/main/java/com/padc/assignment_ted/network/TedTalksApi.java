package com.padc.assignment_ted.network;

import com.padc.assignment_ted.network.response.GetTedTalksResponse;
import com.padc.assignment_ted.utils.TedConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TedTalksApi {

    @FormUrlEncoded
    @POST(TedConstants.GET_TED_TALKS)
    Call<GetTedTalksResponse> loadTalkList(
            @Field(TedConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(TedConstants.PARAM_PAGE) int page
    );

}
