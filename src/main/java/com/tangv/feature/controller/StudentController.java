package com.tangv.feature.controller;

import com.tangv.feature.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
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
        /*redisTemplate.opsForZSet().add("student_score",s1,s1.getScore());
        redisTemplate.opsForZSet().add("student_score",s2,s2.getScore());
        redisTemplate.opsForZSet().add("student_score",s3,s3.getScore());
        redisTemplate.opsForZSet().add("student_score",s4,s4.getScore());
        redisTemplate.opsForZSet().add("student_score",s5,s5.getScore());
        redisTemplate.opsForZSet().add("student_score",s6,s6.getScore());*/
        Long student_score = redisTemplate.opsForZSet().remove("student_score", s1);
        Long student_score1 = redisTemplate.opsForZSet().remove("student_score", s2);
        redisTemplate.opsForZSet().remove("student_score",s3);
        redisTemplate.opsForZSet().remove("student_score",s4);
        redisTemplate.opsForZSet().remove("student_score",s5);
        redisTemplate.opsForZSet().remove("student_score",s6);
    }

    @PostMapping("/setPoint")
    public void setPoint() {
        Student s1 = new Student(1L,"james","E");
        Student s2 = new Student(2L,"kobe","A");
        Student s3 = new Student(3L,"durant","C");
        Student s4 = new Student(4L,"bosh","F");
        Student s5 = new Student(5L,"walace","B");
        Student s6 = new Student(6L,"nash","D");

        ZSetOperations.TypedTuple typedTuple1 = new DefaultTypedTuple(s2,1.0);
        ZSetOperations.TypedTuple typedTuple2 = new DefaultTypedTuple(s5,2.0);
        ZSetOperations.TypedTuple typedTuple3 = new DefaultTypedTuple(s3,3.0);
        ZSetOperations.TypedTuple typedTuple4 = new DefaultTypedTuple(s6,4.0);
        ZSetOperations.TypedTuple typedTuple5 = new DefaultTypedTuple(s1,5.0);
        ZSetOperations.TypedTuple typedTuple6 = new DefaultTypedTuple(s4,6.0);

        Set<ZSetOperations.TypedTuple> typedTupleSet = new HashSet<ZSetOperations.TypedTuple>();
        typedTupleSet.add(typedTuple1);
        typedTupleSet.add(typedTuple2);
        typedTupleSet.add(typedTuple3);
        typedTupleSet.add(typedTuple4);
        typedTupleSet.add(typedTuple5);
        typedTupleSet.add(typedTuple6);

        redisTemplate.opsForZSet().add("student_point",typedTupleSet);
    }

    @PostMapping("/get")
    public Long get() {
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

        /**
         * durant分数减110
         */
        Student s3 = new Student(3L,"durant",57);
        redisTemplate.opsForZSet().incrementScore("student_score",s3,-30);

        return redisTemplate.opsForZSet().rank("student_score",s3);
    }

    @PostMapping("/setStudent")
    public void setStu() {
        Student s1 = new Student(1L,"james","E");
        redisTemplate.opsForValue().set("key::student",s1);
    }
}