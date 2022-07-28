package dao;

public class BankData {
    private String id;
    private String name;
    private int age;
    private int balance;
    private int previousBalance;
    private float averageBalance;
    private int freeTransfer;
    private int no1;
    private int no2a;
    private int no2b;
    private int no3;

    public BankData(String[] data) {
        this.id = data[0];
        this.name = data[1];
        this.age = Integer.parseInt(data[2]);
        this.balance = Integer.parseInt(data[3]);
        this.previousBalance = Integer.parseInt(data[4]);
        this.freeTransfer = Integer.parseInt(data[6].replace("\r", ""));
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setAverageBalance(float averageBalance) {
        this.averageBalance = averageBalance;
    }

    public void setFreeTransfer(int freeTransfer) {
        this.freeTransfer = freeTransfer;
    }

    public void setNo1(int no1) {
        this.no1 = no1;
    }

    public void setNo2a(int no2a) {
        this.no2a = no2a;
    }

    public void setNo2b(int no2b) {
        this.no2b = no2b;
    }

    public void setNo3(int no3) {
        this.no3 = no3;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getBalance() {
        return balance;
    }

    public int getPreviousBalance() {
        return previousBalance;
    }

    public float getAverageBalance() {
        return averageBalance;
    }

    public int getFreeTransfer() {
        return freeTransfer;
    }

    public int getNo1() {
        return no1;
    }

    public int getNo2a() {
        return no2a;
    }

    public int getNo2b() {
        return no2b;
    }

    public int getNo3() {
        return no3;
    }
}
