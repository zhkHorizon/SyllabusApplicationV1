package com.example.daidaijie.syllabusapplication.todo.addOrEditTask;

import android.app.ListActivity;
import android.app.ListFragment;
import android.util.Log;

import com.example.daidaijie.syllabusapplication.di.scope.PerFragment;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.DataManager;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;
import com.example.daidaijie.syllabusapplication.todo.mainMenu.TaskListActivity;

import java.util.Date;

import javax.inject.Inject;

/**
 * Created by 16zhchen on 2018/9/16.
 */

public class TaskAEPresenter implements TaskAEContract.Presenter {
    DataManager dataManager;
    private TaskAEContract.View mView;
    private final int defaultState = 0;
    private long taskType = 0;
    private static final String TAG = "TaskAEPresenter";

    @Inject
    @PerFragment
    public TaskAEPresenter(TaskAEContract.View view){
        dataManager = DataManager.getInstance();
        mView = view;
    }

    @Override
    public void saveTaskForNew(String title, String content, boolean isAlarm, Date alarmTime) {

        TaskBean task = new TaskBean(
                null,title,content,defaultState,isAlarm,alarmTime
        );
        long temp = dataManager.addTasks(task);
        Log.d(TAG, "saveTaskForNew: DataManager:"+String.valueOf(temp));
        mView.showMsg("新建成功");
        mView.closePage();
    }

    @Override
    public void saveTaskForEdit(String title, String content, int state, boolean isAlarm, Date alarmTime) {
        TaskBean task = new TaskBean(
                taskType,title,content,state,isAlarm,alarmTime
        );
        dataManager.updateTasks(task);
        mView.showMsg("新建成功");
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
