public class CheckingAccount {
        //~ Fields ................................................................
    String accountName;
    int accountNumber;
    float balance;

     //~ Constructors ..........................................................
    /**
     * Constructs the SavingsAccount object
     */
    CheckingAccount(String accountName, int accountNumber) {
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

    public boolean withdraw(float amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }   

    public boolean deposit(float amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    public String toString() {
        return "CheckingAccount{name='" + accountName + "', number=" + accountNumber + ", balance=" + balance + "}";
    }
}


