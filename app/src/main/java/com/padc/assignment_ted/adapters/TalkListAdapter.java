package com.padc.assignment_ted.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.assignment_ted.R;
import com.padc.assignment_ted.delegates.TalksDelegate;
import com.padc.assignment_ted.viewholders.TalkItemViewHolder;

/**
 *
 * Created by Phyo Thiha on 5/29/18.
 */

public class TalkListAdapter extends RecyclerView.Adapter {

    private TalksDelegate mTalksDelegate;
    public TalkListAdapter(TalksDelegate talksDelegate){
     mTalksDelegate = talksDelegate;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater
                                        .from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.viewholder_talk,parent,false);
        return new TalkItemViewHolder(view,mTalksDelegate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }
}
