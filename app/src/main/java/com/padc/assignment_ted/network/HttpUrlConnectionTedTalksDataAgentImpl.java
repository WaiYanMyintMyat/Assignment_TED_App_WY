package com.padc.assignment_ted.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.padc.assignment_ted.events.ApiErrorEvent;
import com.padc.assignment_ted.events.SuccessGetTedTalksEvent;
import com.padc.assignment_ted.network.response.GetTedTalksResponse;
import com.padc.assignment_ted.utils.TedConstants;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HttpUrlConnectionTedTalksDataAgentImpl implements TedTalksDataAgent{
    private static HttpUrlConnectionTedTalksDataAgentImpl objInstance;

    private HttpUrlConnectionTedTalksDataAgentImpl(){

    }

    public static HttpUrlConnectionTedTalksDataAgentImpl getObjInstance(){
        if(objInstance==null){
            objInstance=new HttpUrlConnectionTedTalksDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadTedTalksList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                URL url;
                BufferedReader reader = null;
                StringBuilder stringBuilder;

                try {
                    // create the HttpURLConnection
                    //http://www.aungpyaephyo.xyz/myanmar_attractions/getAttractionsList.php
                    url = new URL(TedConstants.API_BASE + TedConstants.GET_TED_TALKS); //1.
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //2.

                    // just want to do an HTTP POST here
                    connection.setRequestMethod("POST"); //3.

                    // uncomment this if you want to write output to this url
                    //connection.setDoOutput(true);

                    // give it 15 seconds to respond
                    connection.setReadTimeout(15 * 1000); //4. ms

                    connection.setDoInput(true); //5.
                    connection.setDoOutput(true);

                    //put the request parameter into NameValuePair list.
                    List<NameValuePair> params = new ArrayList<>(); //6.
                    params.add(new BasicNameValuePair(TedConstants.PARAM_ACCESS_TOKEN,accessToken));
                    params.add(new BasicNameValuePair(TedConstants.PARAM_PAGE,String.valueOf(page)));

                    //write the parameters from NameValuePair list into connection obj.
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    connection.connect(); //7.

                    // read the output from the server
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); //8.
                    stringBuilder = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }

                    String responseString = stringBuilder.toString(); //9.
//                    AttractionListResponse response = CommonInstances.getGsonInstance().fromJson(responseString, AttractionListResponse.class);
//                    List<AttractionVO> attractionList = response.getAttractionList();

                    return responseString;

                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                    //AttractionModel.getInstance().notifyErrorInLoadingAttractions(e.getMessage());
                } finally {
                    // close the reader; this can throw an exception too, so
                    // wrap it in another try/catch block.
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson=new Gson();
                GetTedTalksResponse tedTalksResponse=gson.fromJson(responseString,GetTedTalksResponse.class);
                //Log.d("onPostExecute","TedTalks List Size:"+getTedTalksResponse.getTedTalksVOList().size());

                if(tedTalksResponse.isResponseOK()){
                    //success event
                    SuccessGetTedTalksEvent event=new SuccessGetTedTalksEvent(tedTalksResponse.getTedTalksVOList());
                    EventBus.getDefault().post(event);
                }else{
                    //fail event
                    ApiErrorEvent errorEvent=new ApiErrorEvent(tedTalksResponse.getMessage());
                    EventBus.getDefault().post(errorEvent);
                }

            }

        }.execute();
    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
