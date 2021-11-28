package com.choi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MillListTest {
    @Test
    void name(){
        System.out.println("before ->" + Runtime.getRuntime().freeMemory()/1024/1024);
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 150_0000; i++) {
            users.add(new User(i, 0));
        }
        System.out.println("after ->" + Runtime.getRuntime().freeMemory()/1024/1024);
    }

    public class User{
        int id;
        int grade;

        public User(int id, int grade) {
            this.id = id;
            this.grade = grade;
        }
    }
}
