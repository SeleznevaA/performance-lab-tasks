package anna;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerCountAnalyzerTest {

    @Test
    void test() {
        ArrayList<Visit> visits = new ArrayList<>();
        visits.add(new Visit(1, 55));
        visits.add(new Visit(3, 56));
        visits.add(new Visit(6, 52));
        visits.add(new Visit(60, 78));
        visits.add(new Visit(79, 100));
        visits.add(new Visit(80, 100));
        visits.add(new Visit(81, 100));
        visits.add(new Visit(82, 100));
        visits.add(new Visit(83, 90));
        visits.add(new Visit(83, 90));



        CustomerCountAnalyzer customerCountAnalyzer = new CustomerCountAnalyzer(visits);

        List<Period> maxIntensivePeriods = customerCountAnalyzer.getMaxIntensivePeriods();

        List<Period> expected = new ArrayList<>();
        expected.add(new Period(83, 90, 5));

        assertEquals(1, maxIntensivePeriods.size());
        assertEquals(expected.get(0).getStartTime(), maxIntensivePeriods.get(0).getStartTime());
        assertEquals(expected.get(0).getEndTime(), maxIntensivePeriods.get(0).getEndTime());


    }
}
