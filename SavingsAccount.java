public class SavingsAccount {
    String accountName;
    int accountNumber;
    float interest;
    float withdrawlLimit;
    float balance;

    SavingsAccount(String accountName, int accountNumber, float interest, float withdrawalLimit) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.interest = interest;
        this.withdrawlLimit = withdrawalLimit;
        this.balance = 0.0f;
    }

    public float getInterest(int months) {
        return balance * (interest / 100) * months;
    }

    public float applyInterest() {
        float interestAmount = balance * (interest / 100);
        balance += interestAmount;
        return interestAmount;
    }


}