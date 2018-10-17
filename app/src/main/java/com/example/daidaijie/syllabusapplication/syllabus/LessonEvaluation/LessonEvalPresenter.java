package com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation;

import android.util.Log;

import com.example.daidaijie.syllabusapplication.base.IBaseModel;
import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.bean.Syllabus;
import com.example.daidaijie.syllabusapplication.di.scope.PerActivity;
import com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation.bean.EvalAllBean;
import com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation.bean.EvalBeanSimple;
import com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation.local.EvalBean;
import com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation.local.EvalDataManager;
import com.example.daidaijie.syllabusapplication.todo.bean.HttpBean;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
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
    EvalDataManager dataManager;
    boolean isFirst = true;
    EvalBean mEval = null;

    @PerActivity
    @Inject
    public LessonEvalPresenter(ILessonEvalModel lessonEvalModel,LessonEvalContract.view view){
        mILessonEvalModel = lessonEvalModel;
       // mLessonId = lessonId;
        mView = view;
        dataManager = EvalDataManager.getInstance();
    }

    @Override
    public void postEval(int score, String eval) {
//        mILessonEvalModel.sendEvaluationToNet(score,eval,mID)
//                .subscribe(new Subscriber<HttpResult<String>>() {
//                    @Override
//                    public void onCompleted() {
//                        mView.showMessage("发送成功");
//                        mView.closePage();
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.showMessage("发送失败");
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<String> voidHttpResult) {
//                        Log.d(TAG, "onNext: "+voidHttpResult.getMessage());
//                    }
//                });
        if(isFirst){
            mILessonEvalModel.addNewEval(score,eval,(int)mID,"")
                    .subscribe(new Subscriber<HttpBean>() {
                        @Override
                        public void onCompleted() {
                            mView.showMessage("发送成功");
                            mView.closePage();
                        }

                        @Override
                        public void onError(Throwable e) {
                            showErrorMsg(e);
                        }

                        @Override
                        public void onNext(HttpBean httpBean) {
                            Log.d(TAG, "onNext: "+httpBean.getStatus());
                        }
                    });
        }else{
            mILessonEvalModel.editEval(mEval.getEva_id(),score,"",eval)
                    .subscribe(new Subscriber<HttpBean>() {
                        @Override
                        public void onCompleted() {
                            mView.showMessage("发送成功");
                            mView.closePage();
                        }

                        @Override
                        public void onError(Throwable e) {
                            showErrorMsg(e);
                        }

                        @Override
                        public void onNext(HttpBean httpBean) {
                            Log.d(TAG, "onNext: "+httpBean.getStatus());
                        }
                    });
        }

    }

    public void setmID(long mID) {
        this.mID = mID;
    }

    @Override
    public void start() {
//        mILessonEvalModel.getEvaluationFromNet(mID)
//                .subscribe(new Subscriber<HttpResult<EvalBeanSimple>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<EvalBeanSimple> evalBeanHttpResult) {
//                        if(evalBeanHttpResult.getCode()==200){
//                            mView.setRating(evalBeanHttpResult.getData().getScore());
//                            mView.setText(evalBeanHttpResult.getData().getContent());
//                        }
//                    }
//                });
        Log.d(TAG, "start: eval");
        mEval = dataManager.getEvalByclassID((int)mID);
        if(mEval != null){
            mView.setRating(mEval.getEva_score());
            mView.setText(mEval.getEva_content());
            isFirst = false;
        }
        mILessonEvalModel.getmISyllabusModel().getSyllabusNormal(new IBaseModel.OnGetSuccessCallBack<Syllabus>() {
            @Override
            public void onGetSuccess(Syllabus syllabus) {
                mView.showData(syllabus.getLessonByID(mID));
            }
        }, null);
    }



    @Override
    public void deleteEval() {
        mILessonEvalModel.deleteEval(mEval.getEva_id())
                .subscribe(new Subscriber<HttpBean>() {
                    @Override
                    public void onCompleted() {
                        mView.closePage();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showErrorMsg(e);
                    }

                    @Override
                    public void onNext(HttpBean httpBean) {
                        Log.d(TAG, "onNext: "+httpBean.getStatus());
                    }
                });
    }

    private void showErrorMsg(Throwable e){
        if(e instanceof HttpException){
            ResponseBody body =  ((HttpException) e).response().errorBody();
            try{
                mView.showMessage(body.string());
            }catch (IOException IOe) {
                IOe.printStackTrace();
            }
        }
    }
}
