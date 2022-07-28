package service;

import dao.BankData;
import dao.BankDataDao;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankService {

    public void processRequest() {
        BankDataDao dao = new BankDataDao();
        List<BankData> data;
        try {
            data = dao.getBankData();
        } catch (Exception e) {
            //Add monitoring alert
            System.out.println("Failed to read data " + e);
            return;
        }

        final ExecutorService executorService1 = Executors.newFixedThreadPool(20);
        final ExecutorService executorService2 = Executors.newFixedThreadPool(8);
        CountDownLatch latch1 = new CountDownLatch(data.size());

        //Handle Question 1, 2a, and 2b
        for (BankData row : data) {
            executorService1.execute(() -> {
                int threadNum = (int) Thread.currentThread().getId();
                question1Handler(row, threadNum);
                question2Handler(row, threadNum);
                latch1.countDown();
            });
        }

        try {
            latch1.await();
        } catch (InterruptedException e) {
            System.out.println("Failed to process on question 1 to 2b " + e);
            //Add monitoring alert
        }

        //Handle question 3
        CountDownLatch latch2 = new CountDownLatch(data.size());
        for (BankData row : data) {
            final int additional = data.indexOf(row) < 100 ? 100 : 0;
            executorService2.execute(() -> {
                int threadNum = (int) Thread.currentThread().getId();
                question3Handler(row, threadNum, additional);
                latch2.countDown();
            });
        }

        try {
            latch2.await();
        } catch (InterruptedException e) {
            System.out.println("Failed to process on question 3 " + e);
            //Add monitoring alert
        }

        dao.saveBankData(data);
    }

    private void question1Handler(BankData data, int threadNum) {
        data.setNo1(threadNum);
        float avg = (data.getBalance() + data.getPreviousBalance()) / 2f;
        data.setAverageBalance(avg);
    }

    private void question2Handler(BankData data, int threadNum) {
        data.setNo2a(threadNum);
        data.setNo2b(threadNum);

        if (data.getBalance() >= 100 && data.getBalance() <= 150) {
            data.setFreeTransfer(5);
        }
        if (data.getBalance() > 150) {
            data.setBalance(data.getBalance() + 25);
        }
    }

    private void question3Handler(BankData data, int threadNum, int additional) {
        data.setNo3(threadNum);
        data.setBalance(data.getBalance() + additional);
    }
}
