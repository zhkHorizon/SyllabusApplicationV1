package com.example.daidaijie.syllabusapplication.todo.mainMenu;

import android.util.Log;
import android.widget.Toast;

import com.example.daidaijie.syllabusapplication.di.scope.PerFragment;
import com.example.daidaijie.syllabusapplication.todo.ITaskModel;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.DataManager;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 16zhchen on 2018/9/14.
 */

public class TaskListPresenter implements TaskListContract.presenter {

    private static final String TAG = "TaskListFragment";
    //数据模型
    private ITaskModel mITaskModel;
    //碎片界面
    private TaskListContract.view mView;
    private int mPosition;

    private DataManager dataManager;
    //TODO
    //传入TaskModel，用Model中的方法实现
//    @Inject
//    @PerFragment
//    public TaskListPresenter(ITaskModel ITaskModel, TaskListContract.view view, int position){
//        mITaskModel = ITaskModel;
//        mView = view;
//        mPosition = position;
//    }

    @Inject
    @PerFragment
    public TaskListPresenter(TaskListContract.view view){

        mView = view;
        //mPosition = position;
    }

    @Override
    public void loadData() {
        mView.showFailMessage("Presenter-loadData");
    }

    @Override
    public void start() {
        dataManager = DataManager.getInstance();
        List<TaskBean> temp = getList();
        for(int i=0;i<temp.size();i++){
            Log.d(TAG, "start: "+temp.get(i).getId()+" "+ temp.get(i).getIsAlarm());
        }
        mView.showList(getList());
    }

    @Override
    public List<TaskBean> getList() {
        return dataManager.getAllTasks();
    }
}
