package com.example.naivebayes;

/**
 * Created by hadoop on 4/16/16.
 */
public class Counter {
    public int countAttribute = 0;
    public  int countYes = 0;
    public  int countNo = 0;

    public int getCountAttribute() {
        return countAttribute;
    }

    public void setCountAttribute(int countAttribute) {
        this.countAttribute = countAttribute;
    }

    public int getCountYes() {
        return countYes;
    }

    public void setCountYes(int countYes) {
        this.countYes = countYes;
    }

    public int getCountNo() {
        return countNo;
    }

    public void setCountNo(int countNo) {
        this.countNo = countNo;
    }
}
