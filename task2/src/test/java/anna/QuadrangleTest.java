package anna;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static anna.Point.p;
import static anna.PointPosition.*;
import static anna.Quadrangle.quadrangle;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuadrangleTest {

    @ParameterizedTest
    @MethodSource("provideArguments")
    void getPointPosition(PointPosition position, Point target, Quadrangle quadrangle) {
        assertEquals(position, quadrangle.getPointPosition(target));
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(ON_APEX, p(1, 3), quadrangle(p(0, 0), p(1, 3), p(3, 3), p(5, 0))),
                Arguments.of(ON_SIDE, p(3, 0), quadrangle(p(0, 0), p(1, 3), p(3, 3), p(5, 0))),
                Arguments.of(OUTSIDE, p(6, 0), quadrangle(p(0, 0), p(1, 3), p(3, 3), p(5, 0))),
                Arguments.of(INSIDE, p(1, 1), quadrangle(p(0, 0), p(0, 2), p(2, 2), p(2, 0))),

                Arguments.of(INSIDE, p(6, 3), quadrangle(p(4, 6), p(10, 2), p(7, 1), p(2, 3))),
                Arguments.of(INSIDE, p(4, 5), quadrangle(p(4, 6), p(10, 2), p(7, 1), p(2, 3))),
                Arguments.of(OUTSIDE, p(9, 1), quadrangle(p(4, 6), p(10, 2), p(7, 1), p(2, 3))),
                Arguments.of(OUTSIDE, p(3, 2), quadrangle(p(4, 6), p(10, 2), p(7, 1), p(2, 3))),
                Arguments.of(OUTSIDE, p(9, 3), quadrangle(p(4, 6), p(10, 2), p(7, 1), p(2, 3))),
                Arguments.of(ON_SIDE, p(7, 4), quadrangle(p(4, 6), p(10, 2), p(7, 1), p(2, 3))),
                Arguments.of(ON_APEX, p(2, 3), quadrangle(p(4, 6), p(10, 2), p(7, 1), p(2, 3))),
                Arguments.of(ON_APEX, p(4, 6), quadrangle(p(4, 6), p(10, 2), p(7, 1), p(2, 3))),
                Arguments.of(ON_APEX, p(10, 2), quadrangle(p(4, 6), p(10, 2), p(7, 1), p(2, 3))),
                Arguments.of(ON_APEX, p(7, 1), quadrangle(p(4, 6), p(10, 2), p(7, 1), p(2, 3)))
        );
    }
}
