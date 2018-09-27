package com.example.daidaijie.syllabusapplication.todo.taskDetail;


import com.example.daidaijie.syllabusapplication.di.scope.PerFragment;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.DataManager;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;

import javax.inject.Inject;

public class TaskDetailPresenter implements TaskDetailContract.Presenter{
    private long mTaskID;
    private DataManager dataManager;
    private TaskDetailContract.View mView;

    @Inject
    @PerFragment
    public TaskDetailPresenter(TaskDetailContract.View view){
        dataManager = DataManager.getInstance();
        mView = view;
    }

    @Override
    public void deleteTask() {
        dataManager.deleteTaskById(new Long(mTaskID));
        mView.showMsg("已删除");
        mView.closePage();
    }

    @Override
    public void start() {
        TaskBean task = dataManager.getTaskById(mTaskID);
        mView.showTaskDetail(task);
    }
    public void setTASK_ID(long TASK_ID) {
        mTaskID = TASK_ID;
    }
}
