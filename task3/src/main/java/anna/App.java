package anna;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class App {

    public static void main(String[] args) {
        List<CashierMeasures> measuresOfAllCashier = IntStream.rangeClosed(0, 4)
                .mapToObj(i -> args[i])
                .map(App::readNumbers)
                .map(CashierMeasures::new)
                .collect(toList());

        CustomerCountAnalyzer customerCountAnalyzer = new CustomerCountAnalyzer(measuresOfAllCashier);

        int halfHour = customerCountAnalyzer.getIntensiveNumberOfHalfHour();

        // с момента откртытия на halfHour получасовом интервале наблюдалось наибольшее количество посетителей
        System.out.println(halfHour + "'th half hour from the beginning of work");
    }

    private static List<Double> readNumbers(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName))
                    .stream()
                    .map(Double::parseDouble)
                    .collect(toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
