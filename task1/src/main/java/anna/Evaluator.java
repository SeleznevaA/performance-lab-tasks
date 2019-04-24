package anna;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Evaluator {
    private final List<Integer> numbers;

    public Evaluator(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("numbers can not be null or empty");
        }

        this.numbers = numbers.stream()
                .sorted()
                .collect(toList());
    }

    public int getMin() {
        return numbers.get(0);
    }

    public int getMax() {
        return numbers.get(numbers.size() - 1);
    }

    public int getPercentile(int percentile) {
        int indexOfPercentile = getNearestRankMethodPercentileIndex(percentile, numbers.size());

        return numbers.get(indexOfPercentile);
    }

    private int getNearestRankMethodPercentileIndex(int percentile, int size) {
        return (int) Math.ceil((percentile / 100.0) * size) - 1;
    }

    public double getMedian() {
        if (numbers.size() % 2 == 1) {
            return numbers.get(numbers.size() / 2);
        }

        int left = numbers.get(numbers.size() / 2 - 1);
        int right = numbers.get(numbers.size() / 2);

        return (left + right) / 2.0;
    }

    public double getAverage() {
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();

        return sum / (double) numbers.size();
    }
}
