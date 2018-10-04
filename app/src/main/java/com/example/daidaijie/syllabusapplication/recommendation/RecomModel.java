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
    public Observable<List<UnitBean>> getAllUnitFromNet() {
        return mRecomApi.getAllUnit()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<HttpResult<List<UnitBean>>, Observable<List<UnitBean>>>() {
                    @Override
                    public Observable<List<UnitBean>> call(HttpResult<List<UnitBean>> listHttpResult) {
                        return Observable.just(listHttpResult.getData());
                    }
                });
    }


    public Observable<UnitBean> getAllUnitFromNetTest() {
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
    public Observable<List<ClassDetailBean>> getClassDetailByCLassName(String className) {
        return mRecomApi.getClassDetailByClassName(className)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<HttpResult<List<ClassDetailBean>>, Observable<List<ClassDetailBean>>>() {
                    @Override
                    public Observable<List<ClassDetailBean>> call(HttpResult<List<ClassDetailBean>> listHttpResult) {
                        Log.d(TAG, "call: "+listHttpResult.getMessage());
                        return Observable.just(listHttpResult.getData());
                    }
                });
    }

    @Override
    public Observable<List<SearchClassResultBean>> getResultFromSearch(String text) {
        return mRecomApi.getResultFromSearch(1,text)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<HttpResult<List<SearchClassResultBean>>, Observable<List<SearchClassResultBean>>>() {
                    @Override
                    public Observable<List<SearchClassResultBean>> call(HttpResult<List<SearchClassResultBean>> listHttpResult) {
                        return Observable.just(listHttpResult.getData());
                    }
                });
    }

    @Override
    public Observable<List<SearchClassResultBean>> getResultFromUnit(int UnitID) {
        return mRecomApi.getResultFromUnit(0,UnitID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<HttpResult<List<SearchClassResultBean>>, Observable<List<SearchClassResultBean>>>() {
                    @Override
                    public Observable<List<SearchClassResultBean>> call(HttpResult<List<SearchClassResultBean>> listHttpResult) {
                        return Observable.just(listHttpResult.getData());
                    }
                });
    }

}
