package com.example.daidaijie.syllabusapplication.todo.mainMenu;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.todo.PostTaskBean;
import com.example.daidaijie.syllabusapplication.todo.TodoApi;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBeanFromNet;
import com.example.daidaijie.syllabusapplication.user.IUserModel;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 16zhchen on 2018/9/28.
 */

public class TaskModel implements ITaskModel {

    TodoApi mTodoApi;
    IUserModel mUserModel;

    public TaskModel(TodoApi todoApi,IUserModel iUserModel){

        mUserModel = iUserModel;
        mTodoApi = todoApi;
    }

    @Override
    public Observable<List<TaskBeanFromNet>> getTaskFromNet() {
        //TODO
        return mTodoApi.getAllTask(mUserModel.getUserInfoNormal().getUser_id())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<HttpResult<List<TaskBeanFromNet>>, Observable<List<TaskBeanFromNet>>>() {
                    @Override
                    public Observable<List<TaskBeanFromNet>> call(HttpResult<List<TaskBeanFromNet>> listHttpResult) {
                        return Observable.just(listHttpResult.getData());
                    }
                });
    }

    @Override
    public IUserModel getmIUserModel() {
        return mUserModel;
    }

    @Override
    public Observable<HttpResult<String>> updateTask(String title, String context, int status) {
        PostTaskBean postTaskBean = new PostTaskBean(title,context,2,status);
        Observable<HttpResult<String>> temp1 = mTodoApi.updateTask(postTaskBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return temp1;
    }

    @Override
    public Observable<HttpResult<String>> deleteTask(int id) {
        Observable<HttpResult<String>> temp1 = mTodoApi.deleteTask(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return temp1;
    }

    @Override
    public Observable<HttpResult<String>> addTask(String title, String context, int status) {
        PostTaskBean postTaskBean = new PostTaskBean(title,context,0,status);
        Observable<HttpResult<String>> temp1 = mTodoApi.updateTask(postTaskBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return temp1;
    }
}
