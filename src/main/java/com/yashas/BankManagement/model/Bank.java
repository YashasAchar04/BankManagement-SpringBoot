package com.yashas.BankManagement.model;

import org.springframework.stereotype.Component;

@Component
public class Bank {
    private String accno;
    private String name;
    private String aadhaarno;
    private String panno;
    private int balance;

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadhaarno() {
        return aadhaarno;
    }

    public void setAadhaarno(String aadhaarno) {
        this.aadhaarno = aadhaarno;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "accno='" + accno + '\'' +
                ", name='" + name + '\'' +
                ", aadhaarno='" + aadhaarno + '\'' +
                ", panno='" + panno + '\'' +
                ", balance=" + balance +
                '}';
    }
}
