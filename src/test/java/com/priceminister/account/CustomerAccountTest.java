package com.priceminister.account;


import static org.junit.Assert.*;

import org.junit.*;

import com.priceminister.account.implementation.*;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {
    
    Account customerAccount;
    AccountRule rule;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
    }
    
    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
        Double money = customerAccount.getBalance();
        assertNotNull(money);
        assertEquals(Double.valueOf(0.0), money);
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
    	Double money = customerAccount.getBalance();
        double newAmt = 5.0;
        Double expectBal = money + newAmt;

        customerAccount.add(newAmt);
        Double newBal = customerAccount.getBalance();
        assertNotNull(newBal);
        assertEquals(expectBal, newBal);
    }
    
    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     */
    @Test(expected = IllegalBalanceException.class)
    public void testWithdrawAndReportBalanceIllegalBalance()  throws IllegalBalanceException {
    	 rule = new CustomerAccountRule();
         customerAccount.withdrawAndReportBalance(50.0, rule);
    }
    
    // Also implement missing unit tests for the above functionalities.
    /**
     * Tests to withdraw a negative amount
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawNegativeAmount() {
        rule = new CustomerAccountRule();
        try {
            customerAccount.withdrawAndReportBalance(-50.0, rule);
        } catch (IllegalBalanceException e) {
            fail("Exception while withdrawing");
        }
    }
    /**
     * Tests to withdraw a null Amount
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawNullAmount() {
        rule = new CustomerAccountRule();
        try {
            customerAccount.withdrawAndReportBalance(null, rule);
        } catch (IllegalBalanceException e) {
            fail("Exception while withdrawing");
        }
    }
    /**
     * Tests to withdraw an Amount with no rule passed
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawWithNoRule() {
        rule = new CustomerAccountRule();
        try {
            customerAccount.withdrawAndReportBalance(50.0, null);
        } catch (IllegalBalanceException e) {
            fail("Exception while withdrawing");
        }
    }
    /**
     * Tests to add Null value to the account
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullToAccount() {
        customerAccount.add(null);
    }
    /**
     * Tests to add Negative amount to the account
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNegativeAmount() {
        customerAccount.add(-10.0);
    }
    /**
     * Tests General
     */
    @Test
    public void testWithdrawGeneral() {
        try {
            rule = new CustomerAccountRule();
            customerAccount.add(500.0);
            Double expectedBal = customerAccount.getBalance() - 100.0;
            Double newBal =  customerAccount.withdrawAndReportBalance(100.0, rule);
            assertTrue(newBal >= 0.0);
            assertEquals(expectedBal, newBal);
        } catch (IllegalBalanceException e) {
            fail("Exception while withdrawing");
        }
    }
}
