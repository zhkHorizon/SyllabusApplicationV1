package com.example.daidaijie.syllabusapplication.recommendation;

import java.util.List;

import rx.Observable;

/**
 * Created by 16zhchen on 2018/10/4.
 */

public interface IRecomModel {
    Observable<UnitBean> getAllUnitFromNet();
    Observable<ClassDetailBean> getClassDetailByCLassName(String className);
    Observable<SearchClassResultBean> getResultFromSearch(String text);
    Observable<SearchClassResultBean> getResultFromUnit(int UnitID);

 }
