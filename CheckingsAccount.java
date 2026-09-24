public class CheckingsAccount {
        //~ Fields ................................................................
    String accountName;
    int accountNumber;
    float balance;

     //~ Constructors ..........................................................
    /**
     * Constructs the SavingsAccount object
     */
    CheckingsAccount(String accountName, int accountNumber) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
         this.balance = 0.0f;}


         //~Public  Methods ........................................................
    /**
     * Returns the interest rate for the savings account
     */
    public float getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public float withdraw(float amount) {
        if (amount <= balance) {
            balance -= amount;
        }
        else{
            System.out.println("Insufficient funds");
        }
        return balance;
    }

    public float deposit(float amount) {
        balance += amount;
        return balance;
    }
}


