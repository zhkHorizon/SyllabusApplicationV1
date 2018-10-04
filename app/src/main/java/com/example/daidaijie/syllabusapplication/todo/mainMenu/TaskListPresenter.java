package com.example.daidaijie.syllabusapplication.todo.mainMenu;

import android.util.Log;
import android.widget.Toast;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.bean.UserInfo;
import com.example.daidaijie.syllabusapplication.di.qualifier.user.LoginUser;
import com.example.daidaijie.syllabusapplication.di.scope.PerFragment;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.DataManager;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBeanFromNet;
import com.example.daidaijie.syllabusapplication.user.IUserModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by 16zhchen on 2018/9/14.
 */

public class TaskListPresenter implements TaskListContract.presenter {

    private static final String TAG = "TaskListFragment";



    private UserInfo mUserInfo;
    private TaskListContract.view mView;
    private ITaskModel mTaskModel;
    private IUserModel mIUserModel;
    private DataManager dataManager;


    @Inject
    @PerFragment
    public TaskListPresenter(ITaskModel iTaskModel, TaskListContract.view view){
        mTaskModel  = iTaskModel;
        mIUserModel = mTaskModel.getmIUserModel();
        mView = view;
    }

    @Override
    public void loadData() {

        mTaskModel.getTaskFromNet()
                .subscribe(new Subscriber<List<TaskBeanFromNet>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showFailMessage("LOADINGERORR");
                    }

                    @Override
                    public void onNext(List<TaskBeanFromNet> taskBeanFromNets) {
                        List<TaskBean> test = new ArrayList<TaskBean>();
                        for(TaskBeanFromNet temp:taskBeanFromNets){
                            TaskBean t1 = new TaskBean(null,temp.getTitle(),temp.getContext(),temp.getStatus(),false,null);
                            test.add(t1);
                        }
                        DeteleAllTask();
                        dataManager.insertAll(test);
                        mView.showList(dataManager.getAllTasks());

                    }
                });
    }

    public void loadUserInfo() {
        mUserInfo = mIUserModel.getUserInfoNormal();
    }
    @Override
    public void start() {
        dataManager = DataManager.getInstance();
        List<TaskBean> temp = getList();
        loadUserInfo();
        Log.d(TAG, "start: "+mUserInfo.getNickname());
        mView.showList(getList());
    }

    @Override
    public List<TaskBean> getList() {
        return dataManager.getAllTasks();
    }
    @Override
    public void DeteleTask(long TaskId){
        dataManager.deleteTaskById(TaskId);
        //TODO
        mTaskModel.deleteTask((int)TaskId)
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
    }

    @Override
    public void DeteleAllTask() {
        dataManager.deleteAll();
    }

    @Override
    public void updateTask(long TaskId, int status) {
        TaskBean  task = dataManager.getTaskById(TaskId);
        task.setStatus(status);
        dataManager.updateTasks(task);
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
    }
}
