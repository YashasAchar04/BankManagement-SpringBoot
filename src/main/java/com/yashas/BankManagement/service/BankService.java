package com.yashas.BankManagement.service;

import com.yashas.BankManagement.model.Bank;
import com.yashas.BankManagement.repository.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    private Bank b;
    private BankRepo repo;

    public BankRepo getRepo() {
        return repo;
    }
    @Autowired
    public void setRepo(BankRepo repo) {
        this.repo = repo;
    }

    public void addAccount(Bank b){
        repo.save(b);
    }
    public void deleteAccount(String aadharNumber){
        repo.delete(b,aadharNumber);
    }
    public void updateAccount(Bank b) {
        repo.update(b);
    }

    public List<Bank> AllAccounts(){
        return repo.fetchDetails();
    }
}
