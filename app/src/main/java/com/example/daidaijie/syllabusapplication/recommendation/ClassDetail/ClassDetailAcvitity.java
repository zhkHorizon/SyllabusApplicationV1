package com.example.daidaijie.syllabusapplication.recommendation.ClassDetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.daidaijie.syllabusapplication.R;
import com.example.daidaijie.syllabusapplication.base.BaseActivity;
import com.example.daidaijie.syllabusapplication.recommendation.ClassDetailBean;
import com.example.daidaijie.syllabusapplication.recommendation.searchList.DaggerSearchListComponnent;
import com.example.daidaijie.syllabusapplication.recommendation.searchList.SearchListModule;
import com.example.daidaijie.syllabusapplication.syllabus.classmateDetail.ClassmatePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class ClassDetailAcvitity extends BaseActivity implements ClassDetailContract.view {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.classDetail_recycleview)
    RecyclerView mRecycleView;

    ClassItemAdapter mAdapter;
    @Inject
    ClassDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTitleBar(mToolBar);

        mAdapter = new ClassItemAdapter(new ArrayList<ClassDetailBean>());
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mAdapter);

        DaggerClassDetailComponnent.builder()
                .appComponent(mAppComponent)
                .classDetailModule(new ClassDetailModule(this))
                .build().inject(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("CLASSNAME");
        mPresenter.loadData(name);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_class_detail_acvitity;
    }

    @Override
    public void showList(List<ClassDetailBean> list) {
        mAdapter.updateData(list);
    }
}
