package com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation;

import android.util.Log;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.syllabus.ISyllabusModel;
import com.example.daidaijie.syllabusapplication.user.IUserModel;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 16zhchen on 2018/9/22.
 */

public class LessonEvalModel implements ILessonEvalModel {
    private static final String TAG = "LessonEvalModel";
    EvalApi mEvalApi;
    EvalBean mEvalBean;
    IUserModel mIUserModel;
    ISyllabusModel mISyllabusModel;
    public LessonEvalModel(EvalApi evalApi, ISyllabusModel iSyllabusModel){
        mEvalApi = evalApi;
        mISyllabusModel = iSyllabusModel;
        mIUserModel = mISyllabusModel.getmIUserModel();


    }

    public ISyllabusModel getmISyllabusModel() {
        return mISyllabusModel;
    }

    @Override
    public Observable<HttpResult<EvalBean>> getEvaluationFromNet(long classID) {

        return mEvalApi.getLessonEval(classID,mIUserModel.getUserInfoNormal().getUser_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //TODO,先将返回的值存为string，有需要再去解析
    @Override
    public Observable<HttpResult<String>> sendEvaluationToNet(int score, String content,long classID) {
        PostEvalBean postEvalBean = new PostEvalBean(
                mIUserModel.getUserInfoNormal().getUser_id(),
                score,
                content,
                mIUserModel.getUserInfoNormal().getToken(),
                classID
        );
        Observable<HttpResult<String>> temp1 = mEvalApi.sendComment(postEvalBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return temp1;
    }
}
