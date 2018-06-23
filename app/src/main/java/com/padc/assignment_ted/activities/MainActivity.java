package com.padc.assignment_ted.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.padc.assignment_ted.R;
import com.padc.assignment_ted.adapters.TalkListAdapter;
import com.padc.assignment_ted.data.models.TedModel;
import com.padc.assignment_ted.data.vos.TedTalksVO;
import com.padc.assignment_ted.delegates.TalksDelegate;
import com.padc.assignment_ted.events.SuccessGetTedTalksEvent;
import com.padc.assignment_ted.utils.TedConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity
        implements TalksDelegate {

    private TalkListAdapter talkListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar.setNavigationIcon(R.drawable.ted_logo);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RecyclerView rvTalkList = findViewById(R.id.rv_talks);
        talkListAdapter = new TalkListAdapter(this);
        rvTalkList.setAdapter(talkListAdapter);
        rvTalkList.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                , LinearLayoutManager.VERTICAL, false));

        //load Talks List...
        TedModel.getObjInstance().loadTedTalksList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapTalk(TedTalksVO tedTalksVO) {
        Intent intent = new Intent(getApplicationContext(), TalksDetailsActivity.class);
        intent.putExtra(TedConstants.TED_TALKS_ID,tedTalksVO.getTalkId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)//UI mhar display ya mhar mo lo Main Thread use tar...
    public void onSuccessGetTedTalks(SuccessGetTedTalksEvent event){
        //Log.d("onSuccessGetTedTalks","onSuccessGetTedTalks:"+event.getTedTalksList().size());
        talkListAdapter.setTedTalksList(event.getTedTalksList());
    }
}
