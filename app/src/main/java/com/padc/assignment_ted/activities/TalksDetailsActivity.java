package com.padc.assignment_ted.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.padc.assignment_ted.R;
import com.padc.assignment_ted.adapters.SugestedTalkListAdapter;

/**
 * Created by Phyo Thiha on 6/3/18.
 */
public class TalksDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        setContentView(R.layout.activity_talks_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){

       RecyclerView rvWatchNext =  findViewById(R.id.rv_watch_next);
       rvWatchNext.setAdapter(new SugestedTalkListAdapter());
       rvWatchNext.setLayoutManager(new LinearLayoutManager(getApplicationContext()
               ,LinearLayoutManager.VERTICAL,false));
    }
}
