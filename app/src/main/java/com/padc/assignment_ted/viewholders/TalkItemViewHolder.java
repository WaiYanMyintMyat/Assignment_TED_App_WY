package com.padc.assignment_ted.viewholders;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.padc.assignment_ted.R;
import com.padc.assignment_ted.data.vos.TedTalksVO;
import com.padc.assignment_ted.delegates.TalksDelegate;
import com.padc.assignment_ted.utils.GlideApp;
import com.padc.assignment_ted.utils.UtilMethods;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by Phyo Thiha on 5/29/18.
 */

public class TalkItemViewHolder extends RecyclerView.ViewHolder {

    private TalksDelegate mTalkDelegate;
    private TedTalksVO tedTalksVO;

    @BindView(R.id.rl_talk)
    RelativeLayout rlTalk;

    @BindView(R.id.tv_talker)
    TextView tvTalker;

    @BindView(R.id.tv_talk_content)
    TextView tvTalkContent;

    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.iv_talk)
    ImageView ivTalk;


    public TalkItemViewHolder(View itemView,TalksDelegate talksDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        mTalkDelegate = talksDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTalkDelegate.onTapTalk(tedTalksVO);
            }
        });
    }

    public void setTedTalksData(TedTalksVO tedTalksVO){
        this.tedTalksVO=tedTalksVO;

        //RelativeLayout Background with Glide
//        GlideApp.with(tvTalker.getContext()).load(tedTalksVO.getImageUrl()).into(new SimpleTarget<Drawable>() {
//            @Override
//            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    rlTalk.setBackground(resource);
//                }
//            }
//        });

        GlideApp.with(ivTalk.getContext())
                .load(tedTalksVO.getImageUrl())
                .placeholder(R.drawable.placeholderone)
                .error(R.drawable.placeholderone)
                .centerCrop()
                .into(ivTalk);

        tvTalker.setText(tedTalksVO.getSpeaker().getSpeakerName());
        tvTalkContent.setText(tedTalksVO.getTitle());

        String time = UtilMethods.getHourMinuteSecond(Double.parseDouble(String.valueOf(tedTalksVO.getTalkDurationSecs())));
        tvTime.setText(time);
    }

}
