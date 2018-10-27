package com.example.daidaijie.syllabusapplication.threeInfo.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daidaijie.syllabusapplication.R;

/**
 * Created by 16zhchen on 2018/10/21.
 */

public class InfoFragment extends Fragment {
    public static final String TABLAYOUT_FRAGMENR = "tab_fragment";
    private TextView txt;
    private int type;

    public static InfoFragment newInstance(int type){
        InfoFragment fragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TABLAYOUT_FRAGMENR,type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            type = (int) getArguments().getSerializable(TABLAYOUT_FRAGMENR);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        initView(view);
        return view;
    }

    protected void initView(View view){
        txt = (TextView) view.findViewById(R.id.textView3);
        switch (type) {
            case 1: txt.setText("这是综艺Fragment");
                break;
            case 2: txt.setText("这是体育Fragment");
                break;
            case 3: txt.setText("这是新闻Fragment");
                break;
            case 4: txt.setText("这是热点Fragment");
                break;
            case 5: txt.setText("这是头条Fragment");
                break;
            case 6: txt.setText("这是军事Fragment");
                break;
            case 7: txt.setText("这是历史Fragment");
                break;
            case 8: txt.setText("这是科技Fragment");
                break;
            case 9: txt.setText("这是人文Fragment");
                break;
            case 10: txt.setText("这是地理Fragment");
                break;
        }

    }
}
