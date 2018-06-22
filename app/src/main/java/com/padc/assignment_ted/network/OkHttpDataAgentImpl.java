package com.padc.assignment_ted.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.padc.assignment_ted.events.ApiErrorEvent;
import com.padc.assignment_ted.events.SuccessGetTedTalksEvent;
import com.padc.assignment_ted.network.response.GetTedTalksResponse;
import com.padc.assignment_ted.utils.TedConstants;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpDataAgentImpl implements TedTalksDataAgent {

    private static OkHttpDataAgentImpl objectReference;

    private OkHttpClient mOkHttpClient;

    private OkHttpDataAgentImpl() {

        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

    }

    public static OkHttpDataAgentImpl getObjectReference() {
        if (objectReference == null) {
            objectReference = new OkHttpDataAgentImpl();
        }
        return objectReference;
    }

    @Override
    public void loadTedTalksList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                RequestBody requestBody = new FormBody.Builder()
                        .add(TedConstants.PARAM_PAGE, String.valueOf(page))
                        .add(TedConstants.PARAM_ACCESS_TOKEN, accessToken)
                        .build();

                Request request = new Request.Builder()
                        .url(TedConstants.API_BASE + TedConstants.GET_TED_TALKS)
                        .post(requestBody)
                        .build();

                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseString = response.body().string();
                        return responseString;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetTedTalksResponse getTedTalkResponse = gson.fromJson(responseString, GetTedTalksResponse.class);

                if (getTedTalkResponse.isResponseOK()) {
                    SuccessGetTedTalksEvent event = new SuccessGetTedTalksEvent(getTedTalkResponse.getTedTalksVOList());
                    EventBus.getDefault().post(event);
                } else {
                    ApiErrorEvent errorEvent = new ApiErrorEvent(getTedTalkResponse.getMessage());
                    EventBus.getDefault().post(errorEvent);
                }

            }
        }.execute();
    }
}
