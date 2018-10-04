package com.example.daidaijie.syllabusapplication.todo.taskDetail;


import android.util.Log;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.di.scope.PerFragment;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.DataManager;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;
import com.example.daidaijie.syllabusapplication.todo.mainMenu.ITaskModel;
import com.example.daidaijie.syllabusapplication.user.IUserModel;

import javax.inject.Inject;

import rx.Subscriber;

public class TaskDetailPresenter implements TaskDetailContract.Presenter{
    private long mTaskID;
    private DataManager dataManager;
    private TaskDetailContract.View mView;
    private ITaskModel mTaskModel;
    private IUserModel mIUserModel;
    private static final String TAG = "TaskDetailPresenter";

    @Inject
    @PerFragment
    public TaskDetailPresenter(ITaskModel iTaskModel,TaskDetailContract.View view){
        dataManager = DataManager.getInstance();
        mTaskModel  = iTaskModel;
        mIUserModel = mTaskModel.getmIUserModel();
        mView = view;
    }

    @Override
    public void deleteTask() {
        dataManager.deleteTaskById(new Long(mTaskID));
        mTaskModel.deleteTask((int)mTaskID)
                .subscribe(new Subscriber<HttpResult<String>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: delete");

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
