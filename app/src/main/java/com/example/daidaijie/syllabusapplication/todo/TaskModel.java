package com.example.daidaijie.syllabusapplication.todo;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.todo.bean.HttpBean;
import com.example.daidaijie.syllabusapplication.todo.bean.TODOAllBean;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBeanFromNet;
import com.example.daidaijie.syllabusapplication.user.IUserModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public IUserModel getmIUserModel() {
        return mUserModel;
    }

    @Override
    public Observable<TODOAllBean.DataBean.EvaListBean> getAllTaskFromNet() {
        //TODO，后期可能要改成传参，现在先暂且这样,用户名等还要改
        return mTodoApi.getAllTask(3,"100002",1,1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<TODOAllBean, Observable<TODOAllBean.DataBean.EvaListBean>>() {
                    @Override
                    public Observable<TODOAllBean.DataBean.EvaListBean> call(TODOAllBean todoAllBean) {
                        return Observable.from(todoAllBean.getData().getEva_list());
                    }
                });
    }

    @Override
    public Observable<HttpBean> addNewTask(String title, String context) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String start = sdf.format(calendar.getTime());
        calendar.add(Calendar.HOUR,1);
        String dead = sdf.format(calendar.getTime());
        return mTodoApi.addNewTask(3,"100002","",title,context,start,dead,"",1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<HttpBean> editTask(int todoID, String title, String context) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String start = sdf.format(calendar.getTime());
        calendar.add(Calendar.HOUR,1);
        String dead = sdf.format(calendar.getTime());
        return mTodoApi.editTask(3,"100002",todoID,"",title,context,start,dead,"",1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<HttpBean> updateStatus(int todoID, int status) {
        return mTodoApi.updateStatus(3,"100002",todoID,status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<HttpBean> deleteTask(int todoID) {
        return mTodoApi.deleteTask(3,"100002",todoID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
