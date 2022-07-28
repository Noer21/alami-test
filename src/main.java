import service.BankService;

public class main {

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        BankService service = new BankService();
        service.processRequest();
        System.exit(0);
    }
}
