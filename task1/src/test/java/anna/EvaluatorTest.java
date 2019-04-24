package anna;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EvaluatorTest {

    @Test
    void createEvaluator_emptyList_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> new Evaluator(emptyList()));
    }

    @ParameterizedTest(name = "{1} -> {0}")
    @MethodSource("provideGetMinArguments")
    void getMin(int expectedResult, List<Integer> numbers) {
        assertEquals(expectedResult, new Evaluator(numbers).getMin());
    }

    private static Stream<Arguments> provideGetMinArguments() {
        return Stream.of(
                Arguments.of(10, singletonList(10)),
                Arguments.of(-3, ImmutableList.of(11, 56, 90, 90, 45, 0, -2, -1, -3))
        );
    }

    @ParameterizedTest(name = "{1} -> {0}")
    @MethodSource("provideGetMaxArguments")
    void getMax(int expectedResult, List<Integer> numbers) {
        assertEquals(expectedResult, new Evaluator(numbers).getMax());
    }

    private static Stream<Arguments> provideGetMaxArguments() {
        return Stream.of(
                Arguments.of(10, singletonList(10)),
                Arguments.of(90, ImmutableList.of(11, 56, 90, 90, 45, 0, -2, -1, -3))
        );
    }

    @ParameterizedTest(name = "{1} -> {0}")
    @MethodSource("provideGetMedianArguments")
    void getMedian(double expectedResult, List<Integer> numbers) {
        assertEquals(expectedResult, new Evaluator(numbers).getMedian(), 1e-6);

    }

    private static Stream<Arguments> provideGetMedianArguments() {
        return Stream.of(
                Arguments.of(10, singletonList(10)),
                Arguments.of(34, ImmutableList.of(-4, 9, 34, 78, 78)),
                Arguments.of(56, ImmutableList.of(234, 56, 0)),
                Arguments.of(50.5, ImmutableList.of(234, 41, 60, 0))
        );
    }

    @ParameterizedTest(name = "{1} -> {0}")
    @MethodSource("provideGetAverageArguments")
    void getAverage(double expectedResult, List<Integer> numbers) {
        assertEquals(expectedResult, new Evaluator(numbers).getAverage(), 1e-6);
    }

    private static Stream<Arguments> provideGetAverageArguments() {
        return Stream.of(
                Arguments.of(10, singletonList(10)),
                Arguments.of(25, ImmutableList.of(25, 75, -25)),
                Arguments.of(5.5, ImmutableList.of(5, 6))
        );
    }

    @ParameterizedTest(name = "{1}th percentile of {2} -> {0}")
    @MethodSource("provideGetPercentileArguments")
    void getPercentile(int expectedResult, int percentile, List<Integer> numbers) {
        assertEquals(expectedResult, new Evaluator(numbers).getPercentile(percentile));
    }

    private static Stream<Arguments> provideGetPercentileArguments() {
        return Stream.of(
                Arguments.of(10, 20, singletonList(10)),
                Arguments.of(10, 70, singletonList(10)),
                Arguments.of(15, 5, ImmutableList.of(15, 20, 35, 40, 50)),
                Arguments.of(20, 30, ImmutableList.of(15, 20, 35, 40, 50)),
                Arguments.of(20, 40, ImmutableList.of(15, 20, 35, 40, 50)),
                Arguments.of(35, 50, ImmutableList.of(15, 20, 35, 40, 50)),
                Arguments.of(50, 100, ImmutableList.of(15, 20, 35, 40, 50))
        );
    }
}
