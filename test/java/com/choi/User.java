package com.choi;

import java.util.Objects;

public class User {
    private int id;
    private int grade;

    public User() {
    }

    public User(int id, int grade) {
        this.id = id;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && grade == user.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grade);
    }
}
