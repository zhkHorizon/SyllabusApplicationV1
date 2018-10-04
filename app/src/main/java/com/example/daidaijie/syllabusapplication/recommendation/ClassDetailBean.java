package com.example.daidaijie.syllabusapplication.recommendation;

import android.support.annotation.NonNull;

import com.example.daidaijie.syllabusapplication.recommendation.ClassDetail.ClassDetailContract;

/**
 * Created by 16zhchen on 2018/10/4.
 */

public class ClassDetailBean {
    int classID;
    String className;
    String teacherName;
    float score;

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

}
