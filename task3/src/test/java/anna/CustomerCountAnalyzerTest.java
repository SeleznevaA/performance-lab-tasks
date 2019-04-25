package anna;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CustomerCountAnalyzerTest {

    @Test
    void getIntensiveNumberOfHalfAnHour() {
        List<CashierMeasures> cashierMeasures = ImmutableList.of(
                new CashierMeasures(ImmutableList.of(22.8, 34.8, 23.7, 33.9)),
                new CashierMeasures(ImmutableList.of(24.2, 37.9, 21.5, 23.4)),
                new CashierMeasures(ImmutableList.of(26.8, 25.8, 23.2, 22.7)),
                new CashierMeasures(ImmutableList.of(43.1, 35.8, 24.4, 19.9)),
                new CashierMeasures(ImmutableList.of(34.2, 20.5, 20.7, 22.6))
        );

        int expectedResult = 2; //151.1, 154.8, 113.5, 122.5
        int intensiveNumberOfHalfAnHour = new CustomerCountAnalyzer(cashierMeasures).getIntensiveNumberOfHalfHour();

        Assertions.assertEquals(expectedResult, intensiveNumberOfHalfAnHour);

    }

}
