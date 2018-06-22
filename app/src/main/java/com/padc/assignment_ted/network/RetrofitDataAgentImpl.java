package com.padc.assignment_ted.network;

import android.util.Log;

import com.padc.assignment_ted.events.ApiErrorEvent;
import com.padc.assignment_ted.events.SuccessGetTedTalksEvent;
import com.padc.assignment_ted.network.response.GetTedTalksResponse;
import com.padc.assignment_ted.utils.TedConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements TedTalksDataAgent{

    private static RetrofitDataAgentImpl objectReference;

    private TedTalksApi mTheApi;

    private RetrofitDataAgentImpl() {

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TedConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mTheApi = retrofit.create(TedTalksApi.class);

    }

    public static RetrofitDataAgentImpl getObjectReference() {
        if (objectReference == null) {
            objectReference = new RetrofitDataAgentImpl();
        }
        return objectReference;
    }

    @Override
    public void loadTedTalksList(int page, String accessToken) {
        Call<GetTedTalksResponse> apiCall = mTheApi.loadTalkList(accessToken, page);
        apiCall.enqueue(new Callback<GetTedTalksResponse>() {
            @Override
            public void onResponse(Call<GetTedTalksResponse> call, Response<GetTedTalksResponse> response) {
                GetTedTalksResponse tedTalkResponse = response.body();
                //Log.d("retrofit",tedTalkResponse.getTedTalksVOList().size()+"");
                if (tedTalkResponse != null && tedTalkResponse.isResponseOK()) {
                    SuccessGetTedTalksEvent event = new SuccessGetTedTalksEvent(tedTalkResponse.getTedTalksVOList());
                    EventBus.getDefault().post(event);
                } else {
                    if (tedTalkResponse == null) {
                        ApiErrorEvent errorEvent = new ApiErrorEvent("Empty in Response");
                        EventBus.getDefault().post(errorEvent);

                    } else {
                        ApiErrorEvent errorEvent = new ApiErrorEvent(tedTalkResponse.getMessage());
                        EventBus.getDefault().post(errorEvent);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTedTalksResponse> call, Throwable t) {
                ApiErrorEvent errorEvent = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });
    }
}
