package dao;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BankDataDao {
    private static String DELIMITER = "\n";
    private static String DELIMITER_LINE = ";";
    private static String HEADER_CSV = "id;Nama;Age;Balanced;No 2b Thread-No;No 3 Thread-No;Previous Balanced;Average Balanced;No 1 Thread-No;Free Transfer;No 2a Thread-No";

    public List<BankData> getBankData() throws Exception {
        Scanner line = new Scanner(new File("src/dao/Before Eod.csv"));
        line.useDelimiter(DELIMITER);
        List<BankData> result = new ArrayList<>();
        line.next(); // Skip header
        while (line.hasNext())
        {
            BankData data = new BankData(line.next().split(DELIMITER_LINE));
            result.add(data);
        }
        line.close();
        return result;
    }

    public void saveBankData(List<BankData> bankDataList) {
        String filename = "After Eod.csv";
        File csvOutputFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            List<String> rows = bankDataList.stream()
                .map(this::toCsvRow)
                .collect(Collectors.toList());
            rows.add(0, HEADER_CSV);
            rows.forEach(pw::println);
        } catch (Exception e) {
            return;
        }
        System.out.println("Success save file as " + filename);
    }

    private String toCsvRow(BankData data) {
        String[] row = {
            data.getId(),
            data.getName(),
            String.valueOf(data.getAge()),
            String.valueOf(data.getBalance()),
            String.valueOf(data.getNo2b()),
            String.valueOf(data.getNo3()),
            String.valueOf(data.getPreviousBalance()),
            String.valueOf(data.getAverageBalance()),
            String.valueOf(data.getNo1()),
            String.valueOf(data.getFreeTransfer()),
            String.valueOf(data.getNo2a())
        };
        return String.join(";", row);
    }
}
