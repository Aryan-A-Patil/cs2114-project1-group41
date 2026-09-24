import java.util.Scanner;
/**
 * // -------------------------------------------------------------------------
/**
 *  The UI for the bank application
 * 
 *  @author Caleb Terstegen
 *  @version 9/24/2026
 */
public class UserInterface
{
    //~ Fields ................................................................
    private static AccountManagment accounts;
    private static  Scanner scanner;
    private static String input;

    //~ Constructors ..........................................................
    /**
     * Constructs the user interface and initializes the data
     */
    public UserInterface() {
        accounts = new AccountManagment();
        scanner = new Scanner(System.in);
        input = "";
    }
    //~Public  Methods ........................................................
    /**
     * The main menu where other functionalities and options are selected from
     * @param
     *      args is the arguments for running the program
     */
    public static void main(String[] args) {
        input = "";
        while(true) {
            System.out.println("Your listed options are:/ncreate account/ndelete account/nlist account/ndeposit/napply interest/ntransfer");
            System.out.println("Please type the name of the action you wish to perform");
            System.out.println("type exit at anytime to exit an action or the app");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("exit")) {
                return;
            }
            if(input.toLowerCase().equals("create account")) {
                createAccount();
            }else if(input.toLowerCase().equals("delete account")) {
                deleteAccount();
            }else if(input.toLowerCase().equals("list account")) {
                listAccount();
            }else if(input.toLowerCase().equals("deposit")) {
                deposit();
            }else if(input.toLowerCase().equals("withdraw")) {
                withdraw();
            }else if(input.toLowerCase().equals("apply interest")) {
                interest();
            }else if(input.toLowerCase().equals("transfer")) {
                transfer();
            }else {
                System.out.println("Invalid action");
                continue;
            }
            
        }
    }
    /**
     * Allows for the creation of a new account
     */
    public static void createAccount() {
        input = "";
        System.out.println("You are choosing to make an account");
        System.out.println("");
        while(true) {
            System.out.println("Specify account type");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("checking") || input.toLowerCase().equals("checkings")) {
                System.out.println("Specify account name");
                input = scanner.nextLine();
                System.out.println("Successfully created checking account named " + 
                input + " with account number " + accounts.createChecking(input));
                break;
            }else if(input.toLowerCase().equals("saving") || input.toLowerCase().equals("savings")){
                System.out.println("Specify account name");
                input = scanner.nextLine();
                System.out.println("Successfully created checking account named " + 
                input + " with account number " + accounts.createChecking(input));
                break;
            
            }else if(input.toLowerCase().equals("exit")){
                return;
            }else {
                System.out.println("Invalid option");
            }  
        } 
    }
    /**
     * Allows for the deletion of existing accounts
     */
    public static void deleteAccount() {
        input = "";
        int account = 0;
        System.out.println("You are choosing to delete an account");
        System.out.println("");
        while(true) {
            System.out.println("Specify account number of account to be deleted");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("exit")) {
                return;
            }
            try {
                account = Integer.parseInt(input);
            }
            catch(Exception e) {
                System.out.println("Invalid account number");
                continue;
            }
            accounts.deleteAccount(account);
            System.out.println("successfuly deleted account of account number " + account); 
            break;
        }
    }
    
    /**
     * lists the accounts
     */
    public static void listAccount() {
        input = "";
        CheckingAccount account;
        System.out.println("You are choosing to list an account");
        System.out.println("");
        while(true) {
            System.out.println("Input account number");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("exit")) {
                return;
            }
            try {
            account = accounts.getAccount(Integer.parseInt(input));
            }
            catch(Exception e) {
                System.out.println("Invalid account number");
                continue;
            }
            System.out.println("Account details:");
            System.out.println(account.toString());
            break;
        } 
    }
    
    /**
     * Allows for depositing into an account
     */
    public static void deposit() {
        int other = 0; 
        input = "";
        CheckingAccount account;
        float deposit = 0;
        System.out.println("You are choosing to deposit");
        System.out.println("");
        while(true) {
            System.out.println("Please type the account number of the account you would like to deposit into");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("exit")) {
                return;
            }
            try {
            account = accounts.getAccount(Integer.parseInt(input));
            }
            catch(Exception e) {
                System.out.println("Invalid account number");
                continue;
            }
            break;
        }
        while(true) {
            System.out.println("Depositing into account " + input + " please input amount to deposit");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("exit")) {
                return;
            }
            try {
            deposit = Float.parseFloat(input);
            }
            catch(Exception e) {
                System.out.println("Invalid deposit amount");
                continue;
            } 
            System.out.println("Depositing $" + deposit);
            account.deposit(deposit);
            return;
        }
    }
    
    /**
     * Allows for withdrawal from an account
     */
    public static void withdraw() {
        input = "";
        CheckingAccount account;
        float withdraw = 0;
        System.out.println("You are choosing to withdraw");
        System.out.println("");
        while(true) {
            System.out.println("Please type the account number of the account you would like to withdraw from");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("exit")) {
                return;
            }
            try {
            account = accounts.getAccount(Integer.parseInt(input));
            }
            catch(Exception e) {
                System.out.println("Invalid account number");
                continue;
            }
            break;
        }
        while(true) {
            System.out.println("Withdrawing from account " + input + " please input amount to withdraw");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("exit")) {
                return;
            }
            try {
            withdraw = Float.parseFloat(input);
            }
            catch(Exception e) {
                System.out.println("Invalid withdraw amount");
                continue;
            } 
            System.out.println("Depositing $" + withdraw);
            account.withdraw(withdraw);
            return;
        }
    }
    /**
     * applies interest to a savings account
     */
    public static void interest() {
        input = "";
        SavingsAccount account;
        int months = 0;
        System.out.println("You are choosing to apply interest");
        System.out.println("");
        while(true) {
            System.out.println("Please type the account number of the account you would like to apply interest to");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("exit")) {
                return;
            }
            try {
            account = (SavingsAccount) accounts.getAccount(Integer.parseInt(input));
            }
            catch(Exception e) {
                System.out.println("Invalid account number");
                continue;
            }
            break;
        }
        while(true) {
            System.out.println("Applying interest to account " + input + " please input amount of months to apply interest");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("exit")) {
                return;
            }
            try {
            months = Integer.parseInt(input);
            }
            catch(Exception e) {
                System.out.println("Invalid month amount");
                continue;
            } 
            System.out.println("Applying interest for " + months + " months");
            account.applyInterest(months);
            return;
        }
    }
    
    /**
     * Transfers from account to account
     */
    public static void transfer() {
        input = "";
        int from = 0;
        int to = 0;
        CheckingAccount account;
        float withdraw = 0;
        System.out.println("You are choosing to transfer");
        System.out.println("");
        while(true) {
            System.out.println("Please type the account number of the account you would like to withdraw from");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("exit")) {
                return;
            }
            try {
            from = Integer.parseInt(input);
            }
            catch(Exception e) {
                System.out.println("Invalid account number");
                continue;
            }
            break;
        }
        while(true) {
            System.out.println("Please type the account number of the account you would like to deposit into");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("exit")) {
                return;
            }
            try {
            to = Integer.parseInt(input);
            }
            catch(Exception e) {
                System.out.println("Invalid account number");
                continue;
            }
            break;
        }
        while(true) {
            System.out.println("Withdrawing from account " + from + " please input amount to withdraw");
            input = scanner.nextLine();
            if(input.toLowerCase().equals("exit")) {
                return;
            }
            try {
            withdraw = Float.parseFloat(input);
            }
            catch(Exception e) {
                System.out.println("Invalid withdraw amount");
                continue;
            } 
            System.out.println("Depositing $" + withdraw);
            if(accounts.transfer(from, to, withdraw)) {
                System.out.println("Transfer successful");
            }else {
                System.out.println("Transfer unsuccessful");
            }
            return;
        }
    }
    
}
