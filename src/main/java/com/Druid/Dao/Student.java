package com.Druid.Dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class Student {
    private Integer id;
    private String name;
    private Double chinese;
    private Double math;
    private Double  eng;
    private Double sport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getChinese() {
        return chinese;
    }

    public void setChinese(Double chinese) {
        this.chinese = chinese;
    }

    public Double getMath() {
        return math;
    }

    public void setMath(Double math) {
        this.math = math;
    }

    public Double getEng() {
        return eng;
    }

    public void setEng(Double eng) {
        this.eng = eng;
    }

    public Double getSport() {
        return sport;
    }

    public void setSport(Double sport) {
        this.sport = sport;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chinese=" + chinese +
                ", math=" + math +
                ", eng=" + eng +
                ", sport=" + sport +
                '}';
    }
}
