package com.example.daidaijie.syllabusapplication.todo.mainMenu;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBeanFromNet;
import com.example.daidaijie.syllabusapplication.user.IUserModel;

import java.util.List;

import rx.Observable;

/**
 * Created by 16zhchen on 2018/9/28.
 */

public interface ITaskModel {
    Observable<List<TaskBeanFromNet>> getTaskFromNet();
    IUserModel getmIUserModel();
    Observable<HttpResult<String>> updateTask(String title,String context,int status);
    Observable<HttpResult<String>> deleteTask(int id);
    Observable<HttpResult<String>> addTask(String title,String context,int status);
}
