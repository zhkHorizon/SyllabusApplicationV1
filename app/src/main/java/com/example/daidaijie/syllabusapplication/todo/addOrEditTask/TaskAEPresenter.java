package com.example.daidaijie.syllabusapplication.todo.addOrEditTask;

import android.app.ListActivity;
import android.app.ListFragment;
import android.util.Log;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.di.scope.PerFragment;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.DataManager;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBeanFromNet;
import com.example.daidaijie.syllabusapplication.todo.mainMenu.ITaskModel;
import com.example.daidaijie.syllabusapplication.todo.mainMenu.TaskListActivity;
import com.example.daidaijie.syllabusapplication.user.IUserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by 16zhchen on 2018/9/16.
 */

public class TaskAEPresenter implements TaskAEContract.Presenter {
    DataManager dataManager;
    private TaskAEContract.View mView;
    private final int defaultState = 0;
    private long taskType = 0;

    private IUserModel mIUserModel;
    private ITaskModel mTaskModel;
    private static final String TAG = "TaskAEPresenter";

    @Inject
    @PerFragment
    public TaskAEPresenter(ITaskModel iTaskModel,TaskAEContract.View view){
        dataManager = DataManager.getInstance();
        mView = view;
        mTaskModel  = iTaskModel;
        mIUserModel = mTaskModel.getmIUserModel();
    }

    @Override
    public void saveTaskForNew(String title, String content, boolean isAlarm, Date alarmTime) {

        TaskBean task = new TaskBean(
                null,title,content,defaultState,isAlarm,alarmTime
        );
        long temp = dataManager.addTasks(task);
        Log.d(TAG, "saveTaskForNew: DataManager:"+String.valueOf(temp));
        mView.showMsg("新建成功");
        //TODO，还要传入用户UID
        mTaskModel.addTask(title,content,defaultState)
                .subscribe(new Subscriber<HttpResult<String>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(HttpResult<String> voidHttpResult) {
                        Log.d(TAG, "onNext: "+voidHttpResult.getMessage());
                    }
                });

        mView.closePage();
    }

    @Override
    public void saveTaskForEdit(String title, String content, int state, boolean isAlarm, Date alarmTime) {
        TaskBean task = new TaskBean(
                taskType,title,content,state,isAlarm,alarmTime
        );
        dataManager.updateTasks(task);
        mView.showMsg("修改成功");
        mTaskModel.updateTask(task.getTitle(),task.getContext(),task.getStatus())
                .subscribe(new Subscriber<HttpResult<String>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(HttpResult<String> voidHttpResult) {
                        Log.d(TAG, "onNext: "+voidHttpResult.getMessage());
                    }
                });
        mView.closePage();
    }

    @Override
    public TaskBean findTask(Long id) {
        return dataManager.getTaskById(id);
    }

    @Override
    public void start() {
        //编辑状态则导入信息
        if(taskType != TaskListActivity.ADD)
            mView.setTask(new Long(taskType));
    }



    @Override
    public void setTaskType(Long taskType) {
        this.taskType = taskType;
    }

    @Override
    public long getTaskNum() {
        return dataManager.getAllTasks().size();
    }
}
