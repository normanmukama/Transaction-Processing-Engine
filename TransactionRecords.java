public class TransactionRecords {

    String transaction_id;
    String sender_id;
    String recever_id;
    int amount;
    String status;
    private static int transactionId = 0;

    public TransactionRecords(
        String transaction_id,
        String sender_id,
        String receiver_id,
        int amount,
        String status
    ){
        this.transaction_id = transaction_id;
        this.sender_id = sender_id;
        this.recever_id = receiver_id;
        this.amount = amount;
        this.status = status;
    }

    public static String generateTransactionId(){
        transactionId ++;
        return String.format("TXN%03d", transactionId);
    }
}
