package anna;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        CashierMeasures measures1;
        CashierMeasures measures2;
        CashierMeasures measures3;
        CashierMeasures measures4;
        CashierMeasures measures5;
        try {
            measures1 = new CashierMeasures(readNumbers(args[0]));
            measures2 = new CashierMeasures(readNumbers(args[1]));
            measures3 = new CashierMeasures(readNumbers(args[2]));
            measures4 = new CashierMeasures(readNumbers(args[3]));
            measures5 = new CashierMeasures(readNumbers(args[4]));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        List<CashierMeasures> measuresOfAllCashier = new ArrayList<>();
        measuresOfAllCashier.add(measures1);
        measuresOfAllCashier.add(measures2);
        measuresOfAllCashier.add(measures3);
        measuresOfAllCashier.add(measures4);
        measuresOfAllCashier.add(measures5);

        CustomerCountAnalyzer customerCountAnalyzer = new CustomerCountAnalyzer(measuresOfAllCashier);

        int halfHour = customerCountAnalyzer.getIntensiveNumberOfHalfHour();

        System.out.println(halfHour + "'th half hour of work");
    }

    private static List<Double> readNumbers(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
}
