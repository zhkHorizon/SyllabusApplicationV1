package com.example.daidaijie.syllabusapplication.recommendation;

import android.provider.SearchRecentSuggestions;
import android.util.Log;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 16zhchen on 2018/10/4.
 */

public class RecomModel implements IRecomModel{

    recomApi mRecomApi;
    private static final String TAG = "RecomModel";

    public RecomModel(recomApi recomApi){
        mRecomApi = recomApi;
    }

    @Override
    public Observable<UnitBean> getAllUnitFromNet() {
        return mRecomApi.getAllUnit()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<HttpResult<List<UnitBean>>, Observable<UnitBean>>() {
                    @Override
                    public Observable<UnitBean> call(HttpResult<List<UnitBean>> listHttpResult) {
                        return Observable.from(listHttpResult.getData());
                    }
                });
    }



    @Override
    public Observable<ClassDetailBean> getClassDetailByCLassName(String className) {
        return mRecomApi.getClassDetailByClassName(className)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<HttpResult<List<ClassDetailBean>>, Observable<ClassDetailBean>>() {
                    @Override
                    public Observable<ClassDetailBean> call(HttpResult<List<ClassDetailBean>> listHttpResult) {
                        Log.d(TAG, "call: "+listHttpResult.getMessage());
                        return Observable.from(listHttpResult.getData());
                    }
                });
    }

    @Override
    public Observable<SearchClassResultBean> getResultFromSearch(String text) {
        return mRecomApi.getResultFromSearch(1,text)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<HttpResult<List<SearchClassResultBean>>, Observable<SearchClassResultBean>>() {
                    @Override
                    public Observable<SearchClassResultBean> call(HttpResult<List<SearchClassResultBean>> listHttpResult) {
                        return Observable.from(listHttpResult.getData());
                    }
                });
    }

    @Override
    public Observable<SearchClassResultBean> getResultFromUnit(int UnitID) {
        return mRecomApi.getResultFromUnit(0,UnitID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<HttpResult<List<SearchClassResultBean>>, Observable<SearchClassResultBean>>() {
                    @Override
                    public Observable<SearchClassResultBean> call(HttpResult<List<SearchClassResultBean>> listHttpResult) {
                        return Observable.from(listHttpResult.getData());
                    }
                });
    }

}
