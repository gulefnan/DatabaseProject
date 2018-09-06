package com.example.efnangul.firebasedatabase;

public class UserModel {
    public enum Sex {MALE, FEMALE}

    private String email;
    private String name;
    private String lastName;
    private int salary;
    private String department;
    private int age;
    private Sex sex;

    public UserModel(String email, String name, String lastName, int salary, String department, int age, Sex sex) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
        this.age = age;
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
