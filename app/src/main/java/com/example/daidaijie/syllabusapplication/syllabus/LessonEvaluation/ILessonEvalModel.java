package com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.syllabus.ISyllabusModel;

import java.util.List;

import rx.Observable;

/**
 * 处理数据的接口
 */

public interface ILessonEvalModel {
    Observable<HttpResult<EvalBean>> getEvaluationFromNet(long classID);
    Observable<HttpResult<String>> sendEvaluationToNet(int score, String content,long classID);
    ISyllabusModel getmISyllabusModel();
}
