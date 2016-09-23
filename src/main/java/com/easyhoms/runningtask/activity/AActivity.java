package com.easyhoms.runningtask.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.easyhoms.runningtask.BaseApp;
import com.easyhoms.runningtask.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_task)
public class AActivity extends BaseActivity {

    @ViewInject(R.id.group1)
    RadioGroup group1;
    @ViewInject(R.id.group2)
    RadioGroup group2;
    @ViewInject(R.id.result)
    TextView result;
    @ViewInject(R.id.start)
    TextView start;
    @ViewInject(R.id.name)
    TextView name;
    @ViewInject(R.id.clear)
    TextView clear;

    Intent mIntent;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        name.setText(this.getClass().getName());
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.a:
                        mIntent=new Intent(mContext,AActivity.class);
                        break;
                    case R.id.b:
                        mIntent=new Intent(mContext,BActivity.class);
                        break;
                    case R.id.c:
                        mIntent=new Intent(mContext,CActivity.class);
                        break;

                }
            }
        });

        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.standand:

                        break;
                    case R.id.singTop:
                        mIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        break;
                    case R.id.singTask:
                        mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        break;
                    case R.id.singInstance:
                        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        break;

                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mIntent);
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText(getTask());
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApp.getIntance().mList.clear();
            }
        });



    }
}
