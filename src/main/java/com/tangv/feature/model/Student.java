package com.tangv.feature.model;

/**
 * author:   tangwei
 * Date:     2020/12/23 15:35
 * Description:
 */

public class Student {

    private Long id;

    private String name;

    private Integer score;

    private String point;

    public Student() {
    }

    public Student(Long id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Student(Long id, String name, String point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}