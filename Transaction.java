import java.util.ArrayList;
import java.util.Scanner;

public class Transaction {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<TransactionRecords> transactionRecords = new ArrayList<>();
    public void Transactions(){
        while (true) {
            listOptions();
            int selectedOption = scanner.nextInt();
            if (selectedOption == 1) {
                sendMoney();
            } else if (selectedOption == 2) {
                viewSIngleTransaction();
            } else if (selectedOption == 3) {
                viewAllTransactions();
            } else if (selectedOption == 4) {
                viewBalance();
            } else if (selectedOption == 5) {
                break;
            }
            else if(selectedOption == 6){
                return;
            }
            else {
                System.out.println("Selected option exists not");
            }
        }
    }

    public void listOptions() {
        System.out.println("1. Send Money");
        System.out.println("2. View Transaction");
        System.out.println("3. View All Transactions");
        System.out.println("4. View User Balance");
        System.out.println("5. Exit");
        System.out.println("6. Back");
    }

    Account loggedInAccount;
    public Transaction(Account loggeAccount){
        this.loggedInAccount = loggeAccount;
    }

    public void sendMoney()
    {
        System.out.println("Enter the receiver's account number: ");
        String accountNumber = scanner.next();
        if(loggedInAccount.pin.equals(accountNumber)){
            System.out.println("The sender ID can not be the same as receiver ID");
            return;
        }
        Account userAccounts = null;
        for(Account receivingAccount : Main.accountList){
            if(receivingAccount.pin.equals(accountNumber)){
                userAccounts = receivingAccount;
                break;
            }
        }
        if(userAccounts != null){
            System.out.println("Enter the amount you want to send: ");
            int amountToSend = scanner.nextInt();
            String transaction_id = TransactionRecords.generateTransactionId();
            int amount = amountToSend;
            if(amountToSend <= 0){
                TransactionRecords failedTransactionRecords = new TransactionRecords(
                    transaction_id,
                     loggedInAccount.pin,
                     userAccounts.pin,
                      amount,
                      "FAILED"
                );
                transactionRecords.addFirst(failedTransactionRecords);
                System.out.println("Please enter the valid amount.");
            }
            if (loggedInAccount.balance >= amountToSend) {
                loggedInAccount.balance -= amountToSend;
                userAccounts.balance += amountToSend;
                // String transaction_id = TransactionRecords.generateTransactionId();
                // int amount = amountToSend;
                TransactionRecords transactionPayload = new TransactionRecords(
                    transaction_id,
                    loggedInAccount.pin,
                    userAccounts.pin,
                    amount,
                    "SUCCESS"
                );
                transactionRecords.add(transactionPayload);
                System.out.println("You have sent " + amountToSend + " shillings to " + userAccounts.name);
                System.out.println("Your new account balace is " + loggedInAccount.balance);
            } else {
                System.out.println("You have insufficient balance on your account");
            }
        }
        else{
            System.out.println("The account number entered does not exist in our database.");
        }
    }
    public void viewBalance(){
        System.out.println("Your account balance is " + loggedInAccount.balance);
    }

    public void viewAllTransactions(){
        TransactionRecords allTransactions = null;
        for(TransactionRecords viewTransactions : transactionRecords){
            allTransactions = viewTransactions;
            System.out.println("=====================All Transactions================");
            if (allTransactions != null) {
                System.out.println("Transaction ID: " + allTransactions.transaction_id + " || Sender ID: " + allTransactions.sender_id
                 + " || Receiver ID: " + allTransactions.recever_id + " || Amount: " + allTransactions.amount + " ||Status: " + allTransactions.status
                );
            } else {
                System.out.println("No transactions found.");
            }
        }
    }

    public void viewSIngleTransaction(){
       System.out.println("Enter the transaction ID: ");
       String singleTransactionId = scanner.next();
       TransactionRecords currentTransactionRecords = null;
       for(TransactionRecords transRecords : transactionRecords){
        if(transRecords.transaction_id.equals(singleTransactionId)){
            currentTransactionRecords = transRecords;
        }
       }
       if(currentTransactionRecords != null){
        System.out.println("Transaction ID: " + currentTransactionRecords.transaction_id);
        System.out.println("Sender ID " + currentTransactionRecords.sender_id);
        System.out.println("Receiver ID " + currentTransactionRecords.recever_id);
        System.out.println("Amount " + currentTransactionRecords.amount);
        System.out.println("Status " + currentTransactionRecords.status);
       }
    }
}
