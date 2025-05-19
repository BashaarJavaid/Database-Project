package com.library.model;

public class LibraryBranch {
    private int branchId;
    private String name;
    private String address;
    private String phone;
    private String manager;

    public LibraryBranch() {
    }

    public LibraryBranch(int branchId, String name, String address, String phone, String manager) {
        this.branchId = branchId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.manager = manager;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "LibraryBranch{" +
                "branchId=" + branchId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", manager='" + manager + '\'' +
                '}';
    }
} 