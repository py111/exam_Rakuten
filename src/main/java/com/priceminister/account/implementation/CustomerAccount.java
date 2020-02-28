package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {
	
	private Double balance;

    public void add(Double addedAmount) {
    	if (addedAmount == null) {
            throw new IllegalArgumentException("Amount is null");
        }
        if (addedAmount < 0) {
            throw new IllegalArgumentException("Amount added is less than zero");
        }
        this.balance += addedAmount;
    }

    public Double getBalance() {
        // TODO Auto-generated method stub
        return this.balance;
    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) 
    		throws IllegalBalanceException {
    	if (withdrawnAmount == null) {
            throw new IllegalArgumentException("Withdrawn Amount is null");
        }
        if (rule == null) {
            throw new IllegalArgumentException("Rule is null");
        }
        if (withdrawnAmount < 0) {
            throw new IllegalArgumentException("Check Withdrawn Amount");
        }

        double newBal = this.balance - withdrawnAmount;
        
        if (rule.withdrawPermitted(newBal)) {
            this.balance = newBal;
        } else {
            throw new IllegalBalanceException(newBal);
        }
        return balance;
    }

}
