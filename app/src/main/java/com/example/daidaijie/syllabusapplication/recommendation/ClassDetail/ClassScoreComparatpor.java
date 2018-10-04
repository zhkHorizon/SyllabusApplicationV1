package com.example.daidaijie.syllabusapplication.recommendation.ClassDetail;

import com.example.daidaijie.syllabusapplication.recommendation.ClassDetailBean;

import java.util.Comparator;


public class ClassScoreComparatpor implements Comparator<ClassDetailBean> {
    @Override
    public int compare(ClassDetailBean c1, ClassDetailBean c2) {
        if(c1.getScore() > c2.getScore())
            return -1;
        if(c1.getScore() == c2.getScore())
            return 0;
        return 1;
    }
}
