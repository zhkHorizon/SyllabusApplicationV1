package com.example.daidaijie.syllabusapplication.recommendation;

import java.util.List;

import rx.Observable;

/**
 * Created by 16zhchen on 2018/10/4.
 */

public interface IRecomModel {
    Observable<List<UnitBean>> getAllUnitFromNet();
    Observable<List<ClassDetailBean>> getClassDetailByCLassName(String className);
    Observable<List<SearchClassResultBean>> getResultFromSearch(String text);
    Observable<List<SearchClassResultBean>> getResultFromUnit(int UnitID);

    Observable<UnitBean> getAllUnitFromNetTest();
 }
