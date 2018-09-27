package com.example.daidaijie.syllabusapplication.todo.mainMenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.daidaijie.syllabusapplication.R;
import com.example.daidaijie.syllabusapplication.adapter.TaskItemAdapter;
import com.example.daidaijie.syllabusapplication.base.BaseFragment;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;
import com.example.daidaijie.syllabusapplication.todo.taskDetail.TaskDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class TaskListFragment extends BaseFragment implements TaskListContract.view{

    @BindView(R.id.tasklist_RecyclerView)
    RecyclerView mTaskListRecycleView;
    @BindView(R.id.refreshTaskLayout)
    SwipeRefreshLayout mRefreshTaskLayout;



    @Inject
    TaskListPresenter mTaskListPresenter;
    //int Position;
    private TaskItemAdapter mTaskItemAdapter;
    private static final String TAG = "TaskListFragment";
    public static TaskListFragment newInstance(){
        TaskListFragment fragment = new TaskListFragment();
        return fragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Log.d(TAG, "init:refreshtest ");
        mTaskItemAdapter = new TaskItemAdapter(new ArrayList<TaskBean>());

        mTaskListRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTaskListRecycleView.setAdapter(mTaskItemAdapter);
        mTaskItemAdapter.setOnItemClickListener(new TaskItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击子项后跳转
                if(mTaskItemAdapter.getTask(position).getId()!=0){
                    Intent intent = new Intent(getContext(), TaskDetailActivity.class);
                    intent.putExtra("TYPE",mTaskItemAdapter.getTask(position).getId());
                    startActivity(intent); }
                    //Toast.makeText(getActivity(),"跳转",Toast.LENGTH_SHORT).show();
            }
        });

        DaggerTaskListComponent.builder()
                .taskListModule(new TaskListModule(this))
                .build().inject(this);


    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        mTaskListPresenter.start();
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_task_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        //TODO
        //在这边读取数据
    }




    @Override
    public void showFailMessage(String msg) {
        Toast.makeText(getActivity(),"ERROR",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showList(List<TaskBean> taskBeen) {
        mTaskItemAdapter.updateData(taskBeen);
    }

    public void refreshTask(){
        showList(mTaskListPresenter.getList());
    }
}
