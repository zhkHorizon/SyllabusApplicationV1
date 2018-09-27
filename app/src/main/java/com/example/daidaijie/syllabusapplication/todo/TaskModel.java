package com.example.daidaijie.syllabusapplication.todo;

import com.example.daidaijie.syllabusapplication.bean.TaskAllBean;
import com.example.daidaijie.syllabusapplication.retrofitApi.TaskApi;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import rx.Observable;

/**
 * Created by 16zhchen on 2018/9/14.
 */

public class TaskModel implements ITaskModel {
    TaskApi mTaskApi;
    TaskAllBean mTaskAllBean;
    Map<Integer,List<TaskBean>> mTaskListMap;
    Realm mRealm;
    //TODO
    public TaskModel(TaskApi taskApi, TaskAllBean taskAllBean,Realm realm) {
        mTaskApi = taskApi;
        mTaskAllBean = taskAllBean;
        mRealm = realm;
        mTaskListMap = new HashMap<>();
    }

    @Override
    public Observable<List<TaskBean>> getTask(int position) {
        return null;
    }

    @Override
    public Observable<List<TaskBean>> getTaskFromMemory(int position) {
        return null;
    }

    @Override
    public Observable<List<TaskBean>> getTaskFromNet(int position) {
        return null;
    }

    @Override
    public TaskBean getTaskBean(int position, int subPosition) {
        return null;
    }
}
