package com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.syllabus.ISyllabusModel;
import com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation.bean.EvalAllBean;
import com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation.bean.EvalBeanSimple;
import com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation.bean.PostEvalBean;
import com.example.daidaijie.syllabusapplication.todo.bean.HttpBean;
import com.example.daidaijie.syllabusapplication.user.IUserModel;

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

//    @Override
//    public Observable<HttpResult<EvalBeanSimple>> getEvaluationFromNet(long classID) {
//
//        return mEvalApi.getLessonEval(classID,mIUserModel.getUserInfoNormal().getUser_id())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    //TODO,先将返回的值存为string，有需要再去解析
//    @Override
//    public Observable<HttpResult<String>> sendEvaluationToNet(int score, String content,long classID) {
//        PostEvalBean postEvalBean = new PostEvalBean(
//                mIUserModel.getUserInfoNormal().getUser_id(),
//                score,
//                content,
//                mIUserModel.getUserInfoNormal().getToken(),
//                classID
//        );
//        Observable<HttpResult<String>> temp1 = mEvalApi.sendComment(postEvalBean)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//        return temp1;
//    }

    @Override
    public Observable<EvalAllBean.DataBean.EvaListBean> getAllEval() {
        //TODO,改成用户相关信息
        //尽在LessonDetailPresenter中调用一次，存储到数据库中
        return mEvalApi.getAllLessonEval(3,"100002",1,1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<EvalAllBean, Observable<EvalAllBean.DataBean.EvaListBean>>() {
                    @Override
                    public Observable<EvalAllBean.DataBean.EvaListBean> call(EvalAllBean evalAllBean) {
                        return Observable.from(evalAllBean.getData().getEva_list());
                    }
                });
    }

    @Override
    public Observable<HttpBean> addNewEval(int score, String content, int classID, String tags) {
        //TODO,处理tags
        return mEvalApi.addLessonEval(3,"100002",classID,score,"",content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<HttpBean> editEval(int evaID, int score, String tags, String content) {
        return mEvalApi.editLessonEval(3,"100002",evaID,score,tags,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<HttpBean> deleteEval(int evaID) {
        return mEvalApi.deleteTask(3,"100002",evaID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
