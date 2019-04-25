package anna;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        List<Visit> visits;
        try {
            visits = readLines(args[0]);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        CustomerCountAnalyzer customerCountAnalyzer = new CustomerCountAnalyzer(visits);

        List<Period> maxIntensivePeriods = customerCountAnalyzer.getMaxIntensivePeriods();

        System.out.println("Time in minutes from the beginning of work");

        for (Period period : maxIntensivePeriods) {
            System.out.println("startTime: " + period.getStartTime());
            System.out.println("endTime: " + period.getEndTime());
            System.out.println("customerCount: " + period.getCount());
            System.out.println();
        }

    }

    private static List<Visit> readLines(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(s -> {
                    String[] times = s.split(" ");
                    return new Visit(Integer.parseInt(times[0]), Integer.parseInt(times[1]));
                })
                .collect(Collectors.toList());
    }
}
