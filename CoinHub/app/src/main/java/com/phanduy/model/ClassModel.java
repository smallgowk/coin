package com.phanduy.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by duyuno on 7/30/16.
 */
public class ClassModel {
    boolean isExpand;

    private int id;
    private int size;
    private int count;
    private long fee;
    private String name;
    private String language;
    private String status;
    private String schedule;
    private String remark;
    private String contact;
    private String address;
    private Date start;

    private ArrayList<TargetModel> enrollments;

    public ArrayList<TargetModel> getStudents() {
        return enrollments;
    }

    public void setStudents(ArrayList<TargetModel> students) {
        this.enrollments = students;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }


    public ArrayList<TargetModel> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(ArrayList<TargetModel> enrollments) {
        this.enrollments = enrollments;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
