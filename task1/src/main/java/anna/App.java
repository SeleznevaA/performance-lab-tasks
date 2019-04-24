package anna;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        List<Integer> numbers;
        try {
            numbers = readNumbers(args[0]);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        Evaluator evaluator = new Evaluator(numbers);

        System.out.println("90 percentile " + evaluator.getPercentile(90));
        System.out.println("median " + evaluator.getMedian());
        System.out.println("average " + evaluator.getAverage());
        System.out.println("max " + evaluator.getMax());
        System.out.println("min " + evaluator.getMin());
    }

    private static List<Integer> readNumbers(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
