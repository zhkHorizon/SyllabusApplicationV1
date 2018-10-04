package com.example.daidaijie.syllabusapplication.recommendation.mainMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.daidaijie.syllabusapplication.R;
import com.example.daidaijie.syllabusapplication.base.BaseActivity;
import com.example.daidaijie.syllabusapplication.recommendation.UnitBean;
import com.example.daidaijie.syllabusapplication.recommendation.searchList.SearchListActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;



public class RecomActivity extends BaseActivity implements RecomContract.view {

    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.recom_search)
    SearchView mSearchView;
    @BindView(R.id.recom_recycleview)
    RecyclerView mRecycleView;
    private static final String TAG = "RecomActivity";
    RecomItemAdapter mAdapter;
    @Inject
    RecomPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTitleBar(mToolBar);

        mAdapter = new RecomItemAdapter(new ArrayList<UnitBean>());
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecomItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击item后读取课程ID并开启新界面
                Intent intent = new Intent(RecomActivity.this, SearchListActivity.class);
                intent.putExtra("TYPE",0);
                //0:按单位查找，1:搜索查找
                intent.putExtra("text",mAdapter.getClassId(position));
                startActivity(intent);
            }
        });
        mSearchView.clearFocus();
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { 
            @Override 
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "onQueryTextSubmit: ");
                Intent intent = new Intent(RecomActivity.this, SearchListActivity.class);
                intent.putExtra("TYPE",1);
                //0:按单位查找，1:搜索查找
                intent.putExtra("text",s);
                startActivity(intent); 
                return false; 
            } 
            @Override 
            public boolean onQueryTextChange(String s) { 
                Log.e(TAG, "TextChange --> " + s); 
                return false; } 
        });


        DaggerRecomComponnent.builder()
                .appComponent(mAppComponent)
                .recomModule(new RecomModule(this))
                .build().inject(this);
        //读取所有单位并显示
        mPresenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSearchView.setFocusable(false);
    }

    @Override
    public void showList(List<UnitBean> unitBeen) {
        mAdapter.updateData(unitBeen);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_recom;
    }
}
