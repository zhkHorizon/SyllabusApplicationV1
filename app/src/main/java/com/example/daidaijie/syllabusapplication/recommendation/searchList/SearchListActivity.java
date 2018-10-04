package com.example.daidaijie.syllabusapplication.recommendation.searchList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.daidaijie.syllabusapplication.R;
import com.example.daidaijie.syllabusapplication.base.BaseActivity;
import com.example.daidaijie.syllabusapplication.recommendation.ClassDetail.ClassDetailAcvitity;
import com.example.daidaijie.syllabusapplication.recommendation.SearchClassResultBean;
import com.example.daidaijie.syllabusapplication.recommendation.UnitBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class SearchListActivity extends BaseActivity implements SearchListContract.view{
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.recomClass_recycleview)
    RecyclerView mRecycleView;

    SearchItemAdapter mAdapter;
    @Inject
    SearchListPrsenter mPresenter;

    private static final String TAG = "SearchListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTitleBar(mToolBar);

        mAdapter = new SearchItemAdapter(new ArrayList<SearchClassResultBean>());
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new SearchItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(view.getContext(),"search",Toast.LENGTH_SHORT).show();
                //TODO,修改为课程详细页面
                Intent intent = new Intent(SearchListActivity.this, ClassDetailAcvitity.class);
                intent.putExtra("CLASSNAME",mAdapter.getClassName(position));
                startActivity(intent);
            }
        });

        DaggerSearchListComponnent.builder()
                .appComponent(mAppComponent)
                .searchListModule(new SearchListModule(this))
                .build().inject(this);

        Intent intent = getIntent();
        int type = intent.getIntExtra("TYPE",0);
        if(type==0){
            //按单位搜索
            Log.d(TAG, "onCreate: UNITID"+intent.getIntExtra("text",0));
            mPresenter.loadDataForUnit(intent.getIntExtra("text",0));
        }else{
            //搜索查找
            mPresenter.loadDataForSearch(intent.getStringExtra("text"));
        }
    }


    @Override
    public void showList(List<SearchClassResultBean> Bean) {
        mAdapter.updateData(Bean);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_recom_list;
    }
}
