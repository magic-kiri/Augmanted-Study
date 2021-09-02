package com.example.augmantedreality.ui;


import java.util.ArrayList;

public class User {
    private String username; // Initialized by Constructor
    private String email; // Initialized by Constructor
    private String password; // Initialized by Constructor
    private String phone; // Initialized by Constructor
    private String birthday; // Initialized by Constructor
    private String id; // Initialized by Constructor
    private int chap1,chap2,chap3,chap4,chap5,chap6;
    private int totalMarks;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)

    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public User(String username, String email, String password, String phone, String birthday,String id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthday = birthday;
        this.id = id;

    }
    private void evaluate()
    {
        totalMarks = chap1+chap2+chap3+chap4+chap5+chap6;
    }
    public int getChap1() {
        return chap1;
    }

    public void setChap1(int chap1) {
        this.chap1 = chap1;
        evaluate();
    }

    public int getChap2() {
        return chap2;
    }

    public void setChap2(int chap2) {
        this.chap2 = chap2;evaluate();
    }

    public int getChap3() {
        return chap3;
    }

    public void setChap3(int chap3) {
        this.chap3 = chap3;evaluate();
    }

    public int getChap4() {
        return chap4;
    }

    public void setChap4(int chap4) {
        this.chap4 = chap4;evaluate();
    }

    public int getChap5() {
        return chap5;
    }

    public void setChap5(int chap5) {
        this.chap5 = chap5;
        evaluate();
    }

    public int getChap6() {
        return chap6;
    }

    public void setChap6(int chap6) {
        this.chap6 = chap6;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getTotalMarks() {
        totalMarks = chap1+chap2+chap3+chap4+chap5+chap6;
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

}
