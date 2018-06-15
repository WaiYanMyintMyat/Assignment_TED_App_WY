package com.padc.assignment_ted.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.padc.assignment_ted.delegates.TalksDelegate;

/**
 *
 * Created by Phyo Thiha on 5/29/18.
 */

public class TalkItemViewHolder extends RecyclerView.ViewHolder {

    private TalksDelegate mTalkDelegate;

    public TalkItemViewHolder(View itemView,TalksDelegate talksDelegate) {
        super(itemView);
        mTalkDelegate = talksDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTalkDelegate.onTapTalk();
            }
        });
    }
}
