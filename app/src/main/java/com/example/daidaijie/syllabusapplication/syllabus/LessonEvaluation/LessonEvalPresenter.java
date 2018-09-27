package com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation;

import android.util.Log;

import com.example.daidaijie.syllabusapplication.base.IBaseModel;
import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.bean.Syllabus;
import com.example.daidaijie.syllabusapplication.di.scope.PerActivity;
import com.example.daidaijie.syllabusapplication.syllabus.ISyllabusModel;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by 16zhchen on 2018/9/22.
 */

public class LessonEvalPresenter implements LessonEvalContract.presenter {
    private static final String TAG = "LessonEvalPresenter";
    //long mLessonId;
    LessonEvalContract.view mView;
    ILessonEvalModel mILessonEvalModel;
    private long mID;
    @PerActivity
    @Inject
    public LessonEvalPresenter(ILessonEvalModel lessonEvalModel,LessonEvalContract.view view){
        mILessonEvalModel = lessonEvalModel;
       // mLessonId = lessonId;
        mView = view;
    }

    @Override
    public void postEval(int score, String eval) {
        mILessonEvalModel.sendEvaluationToNet(score,eval,mID)
                .subscribe(new Subscriber<HttpResult<String>>() {
                    @Override
                    public void onCompleted() {
                        mView.showMessage("发送成功");
                        mView.closePage();

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage("发送失败");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(HttpResult<String> voidHttpResult) {
                        Log.d(TAG, "onNext: "+voidHttpResult.getMessage());
                    }
                });
    }

    public void setmID(long mID) {
        this.mID = mID;
    }

    @Override
    public void start() {
        mILessonEvalModel.getEvaluationFromNet(mID)
                .subscribe(new Subscriber<HttpResult<EvalBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(HttpResult<EvalBean> evalBeanHttpResult) {
                        if(evalBeanHttpResult.getCode()==200){
                            mView.setRating(evalBeanHttpResult.getData().getScore());
                            mView.setText(evalBeanHttpResult.getData().getContent());
                        }
                    }
                });
        mILessonEvalModel.getmISyllabusModel().getSyllabusNormal(new IBaseModel.OnGetSuccessCallBack<Syllabus>() {
            @Override
            public void onGetSuccess(Syllabus syllabus) {
                mView.showData(syllabus.getLessonByID(mID));
            }
        }, null);
    }
}
