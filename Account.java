import java.util.Scanner;
public class Account {
    Scanner scanner = new Scanner(System.in);
    String name;
    String phone_number;
    int balance;
    String pin;
    public void createAccount(String name, String phone_number, int balance, String pin){
        this.name = name;
        this.phone_number = phone_number;
        this.balance = balance;
        this.pin = pin;
    }

    public void loggedInUser(){
       System.out.println("=================Welcome. Enter your PIN to login.===============");
       String loginPin = scanner.next();
       Account loggedInUserAccount = null;
       for(Account account : Main.accountList){
        if(account.pin.equals(loginPin)){
            loggedInUserAccount = account;
            System.out.println("Account found!");
            break;
        }
       }

       if(loggedInUserAccount != null){
            System.out.println("You have logged in successfully");
            Transaction transaction = new Transaction(loggedInUserAccount);
            transaction.Transactions();
       }
       else {
           System.out.println("Then PIN entered is wrong. Please try again");
       }
    }
}
