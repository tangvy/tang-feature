package com.tangv.feature.controller;

import com.tangv.feature.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * author:   tangwei
 * Date:     2020/12/23 14:23
 * Description:
 */
@RestController
public class StudentController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/set")
    public void set() {
        Student s1 = new Student(1L,"james",65);
        Student s2 = new Student(2L,"kobe",78);
        Student s3 = new Student(3L,"durant",57);
        Student s4 = new Student(4L,"bosh",93);
        Student s5 = new Student(5L,"walace",85);
        Student s6 = new Student(6L,"nash",70);
        redisTemplate.opsForZSet().add("student_score",s1,s1.getScore());
        redisTemplate.opsForZSet().add("student_score",s2,s2.getScore());
        redisTemplate.opsForZSet().add("student_score",s3,s3.getScore());
        redisTemplate.opsForZSet().add("student_score",s4,s4.getScore());
        redisTemplate.opsForZSet().add("student_score",s5,s5.getScore());
        redisTemplate.opsForZSet().add("student_score",s6,s6.getScore());
    }

    @PostMapping("/get")
    public Set<Student> get() {
        /**
         * 正序（从低到高）序获取按学生成绩排列的列表，可通过index，end指定数据条数
         * Set<Student> student = redisTemplate.opsForZSet().range("student_score", 0, 1);
         */

        /**
         *  倒序（从高到低）获取按学生成绩排列的列表，可通过index，end指定数据条数
         */
        Set<Student> student = redisTemplate.opsForZSet().reverseRange("student_score", 0, 1);

        /**
         * 获取分数在70-85之间的学生成绩
         */
        Set<Student> student1 = redisTemplate.opsForZSet().rangeByScore("student_score", 70, 85);
        return student1;
    }
}