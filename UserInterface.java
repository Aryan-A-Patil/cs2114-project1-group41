import java.util.Scanner;

public class UserInterface
{
    //~ Fields ................................................................
    private AccountManagment accounts;
    private Scanner scanner;
    private String input;

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
     */
    public void mainMenu() {
        System.out.println("aaa");
    }
    /**
     * Allows for the creation of a new account
     */
    public void createAccount() {
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
    public void deleteAccount() {
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
     * Allows for deposit for an account
     */
    public void deposit() {
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
     * Allows for withdraw for an account
     */
    public void withdraw() {
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
}
