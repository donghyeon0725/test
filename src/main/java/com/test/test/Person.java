package com.test.test;

public class Person {
    private String name;
    private Integer age;
    private PersonStatus status;

    public Person(String name, Integer age, PersonStatus status) {
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }

    public PersonStatus getStatus() {
        return status;
    }

    public Person setStatus(PersonStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", status=" + status +
                '}';
    }
}
