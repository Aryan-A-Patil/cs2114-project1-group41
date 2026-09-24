
public class SavingsAccount
extends CheckingAccount {
    //~ Fields ................................................................
    float interest;
    float withdrawlLimit;

    
    //~ Constructors ..........................................................
    /**
     * Constructs the SavingsAccount object
     */
    SavingsAccount(String accountName, int accountNumber) {
        super(accountName, accountNumber);
        this.interest = 0.05F;
        this.withdrawlLimit = 500;
    }

//~Public  Methods ........................................................
    /**
     * Returns the interest rate for the savings account
     */
    public float getInterest() {
        return interest;
    }
    /**
     * Applies interest to the account balance
     * @param months
     * @return the amount of interest applied
     */
    public float applyInterest(int months) {
        float interestAmount = super.getBalance() * (interest) * months;
        super.deposit(interestAmount);
        return interestAmount;
    }

    /**
     * Returns the withdrawal limit for the savings account
     * @return the withdrawal limit
     */
    public float getWithdrawalLimit() {
        return withdrawlLimit;
    }

    @Override 
    public boolean withdraw(float amount) {
        if (amount > withdrawlLimit) {
            return false;
        }
        return super.withdraw(amount);
    }

}