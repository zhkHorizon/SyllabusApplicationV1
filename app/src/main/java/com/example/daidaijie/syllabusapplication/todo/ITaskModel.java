package com.example.daidaijie.syllabusapplication.todo;

import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;

import java.util.List;

import rx.Observable;


public interface ITaskModel {
    Observable<List<TaskBean>> getTask(int position);
    Observable<List<TaskBean>>  getTaskFromMemory(int position);
    Observable<List<TaskBean>>  getTaskFromNet(int position);
    TaskBean getTaskBean(int position,int subPosition);
}
