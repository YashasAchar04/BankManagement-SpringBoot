package com.yashas.BankManagement;

import com.yashas.BankManagement.model.Bank;
import com.yashas.BankManagement.service.BankService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class BankManagementApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(BankManagementApplication.class, args);
		Bank obj = context.getBean(Bank.class);
		Scanner sc = new Scanner(System.in);
		BankService service = context.getBean(BankService.class);
		System.out.println();
		System.out.println();
		List<Bank> allAccounts = service.AllAccounts();
		System.out.println("******************* Welcome to Bank Management system *******************");
		System.out.println("Enter your choice");
		System.out.print("1.Create Account\t2.Delete your Account\t3.Banking Transactions\t4.Get your details");
		System.out.println();
		int input=sc.nextInt();
		String aadharNo;
		if(input==1){
			System.out.println("Enter your aadhaar Number");
			aadharNo = sc.next();
			boolean accountExists = false;
			for (Bank account : allAccounts) {
				if (aadharNo.equals(account.getAadhaarno())) {
					accountExists = true;
					System.out.println("You already have an existing account for this aadhaar number");
					System.out.println("Your Account number is : " + account.getAccno());
					break; // Stop checking once we find a matching account
				}
			}
				if(!accountExists){
					obj.setAadhaarno(aadharNo);
					System.out.println("Enter your pan number");
					String panNo = sc.next();
					obj.setPanno(panNo);

					System.out.println("Enter your name");
					String name = sc.next();
					obj.setName(name);

					Random ran = new Random();
					long accnoInNumber= ran.nextLong(1000000000);
					String accno = ""+accnoInNumber;
					obj.setAccno(accno);

					System.out.println("Please enter your initial deposit");
					int balance = sc.nextInt();
					obj.setBalance(balance);
					service.addAccount(obj);
					System.out.println("Bank account created successfully..");
					System.out.println("Your Account number is : "+obj.getAccno());

				}
			}
		else if(input==2){
			System.out.println("Please Enter your aadhaar number to delete your account");
			aadharNo = sc.next();
			boolean accountExists = false;
			for(Bank account:allAccounts){
				if(aadharNo.equals(account.getAadhaarno())){
					accountExists=true;
					break;
				}

			}
			if(accountExists){
				service.deleteAccount(aadharNo);
				System.out.println("Account deleted successfully");
			}
			else{
				System.out.println("No Matching account found");
			}
		}
		else if(input==3){
			System.out.println("Please login first by entering your aadhaar Number");
			aadharNo = sc.next();
			boolean accountExists = false;
			for(Bank account : allAccounts){
				if(aadharNo.equals(account.getAadhaarno())){
					accountExists=true;
					break;
				}
			}
			if(accountExists){
				System.out.println("***************** You Have Logged In *****************");
				System.out.println("Enter your choice");
				System.out.println("1.Deposit\t2.Withdrawal\t3.Balance Enquiry");
				int input2 = sc.nextInt();
				if(input2==1){
					System.out.println("Enter the amount to be deposited");
					int depositAmt = sc.nextInt();
					for(Bank account : allAccounts){
						if(aadharNo.equals(account.getAadhaarno())){
							account.setBalance(account.getBalance()+depositAmt);
							System.out.println("Amount deposited successfully");
							System.out.println("**************** Thank you ****************");
							service.updateAccount(account);
							break;
						}
					}
				}
				else if(input2==2){
					System.out.println("Enter the amount to be withdrawn");
					int withdrwalAmt = sc.nextInt();
					for(Bank account : allAccounts){
						if(aadharNo.equals(account.getAadhaarno())){
							if(withdrwalAmt<account.getBalance()) {
								if (account.getBalance() > 0) {
									account.setBalance(account.getBalance() - withdrwalAmt);
									System.out.println("Amount withdrawn successfully");
									System.out.println("**************** Thank you ****************");
									service.updateAccount(account);
									break;
								}
							}
							else{
								System.out.println("Not enough money to withdraw");
							}
						}
					}
				}
				else if(input2==3){
					int balance=0;
					for(Bank account : allAccounts){
						if(aadharNo.equals(account.getAadhaarno())){
							 balance = account.getBalance();
						}
					}
					System.out.println("Your current balance is : "+balance);
					System.out.println("**************** Thank you ****************");
				}
			}
		}
		else if(input==4){
			System.out.println("Enter your aadhaar Number");
			aadharNo = sc.next();
			for(Bank account : allAccounts){
				if(aadharNo.equals(account.getAadhaarno())){
					System.out.println("Your Details are");
					System.out.println("Your Name : "+account.getName());
					System.out.println("Your Account Number : "+account.getAccno());
					System.out.println("Your Pan number : "+account.getPanno());
					System.out.println("Your Aadhaar Number : "+account.getAadhaarno());
					System.out.println("Your Current balance : "+account.getBalance());
				}
			}
		}

	}}


