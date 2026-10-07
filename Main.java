import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Account> accountList = new ArrayList<>();
    int balance;
    public static void main(String[] args ){
        System.out.println("====================Transaction Processing System Hackathon================");
        Main account = new Main();
        account.userAccount();
    }

    public boolean validateContact(String phoneNumber){
       if(phoneNumber.length() != 10){
        return false;
       }
       if(!phoneNumber.startsWith("0")){
        return false;
       }
       return true;
    }

    public void userAccount(){
        while(true){
            listOptions();
            int accountOption = scanner.nextInt();
            if(accountOption == 1){
               Account account = new Account();
               account.loggedInUser();
            }
            else if(accountOption == 2){
                System.out.println("============Enter the account information to register=============");
                System.out.println("Enter your name: ");
                String name = scanner.next();
                System.out.println("Enter your phone number: ");
                String phone_number = scanner.next();
                if(!validateContact(phone_number)){
                    System.out.println("The phone number entered is incorrect.");
                    continue;
                }
                System.out.println("Enter you initial deposit (It should be 20000 and above");
                int balance = scanner.nextInt();
                System.out.println("Enter your 4-digit pin");
                String pin = scanner.next();

                Account userAccount = new Account();
                userAccount.createAccount(name, phone_number, balance, pin);
                accountList.add(userAccount);
                System.out.println("Account created!. You can now login");
                System.out.println("Number of accounts: " + accountList.size());
            }
            else if(accountOption == 3){
                break;
            }
            else if(accountOption == 4){
                return;
            }
            else{
                System.out.println("Selected option exists not");
            }
        }
    }

    public void listOptions(){
        System.out.println("1. Login your account");
        System.out.println("2. Sign Up");
        System.out.println("3. Exit");
        System.out.println("4. Back");
    }
}
