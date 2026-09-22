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
     * Allows for accessing an account for purposes such as depositing/withdrawing
     */
    public void accessAccount() {
        
    }
}
