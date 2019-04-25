package anna;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class CustomerCountAnalyzer {

    private final List<Visit> visits;

    public CustomerCountAnalyzer(List<Visit> visits) {
        this.visits = visits;
    }

    public List<Period> getMaxIntensivePeriods() {
        List<Event> events = visits.stream().
                flatMap(CustomerCountAnalyzer::getEventStream)
                .collect(groupingBy(Event::getTime))
                .values()
                .stream()
                .map(CustomerCountAnalyzer::merge)
                .filter(e -> e.getDiff() != 0)
                .sorted(comparingInt(Event::getTime))
                .collect(toList());

        List<Period> periods = getPeriods(events);

        int maxCount = periods.stream()
                .mapToInt(Period::getCount)
                .max()
                .orElse(0);

        return periods.stream()
                .filter(p -> p.getCount() == maxCount)
                .sorted(comparingInt(Period::getStartTime))
                .collect(toList());
    }

    private List<Period> getPeriods(List<Event> events) {
        ArrayList<Period> periods = new ArrayList<>();

        int acc = 0;

        for (int i = 0; i < events.size() - 1; i++) {
            Event e1 = events.get(i);
            Event e2 = events.get(i + 1);

            acc += e1.getDiff();

            periods.add(new Period(e1.getTime(), e2.getTime(), acc));
        }

        return periods;
    }

    private static Event merge(List<Event> events) {
        int sum = events.stream()
                .mapToInt(Event::getDiff)
                .sum();

        return new Event(events.get(0).getTime(), sum);
    }

    private static Stream<Event> getEventStream(Visit v) {
        return Stream.of(
                new Event(v.getEntryTime(), 1),
                new Event(v.getExitTime(), -1)
        );
    }
}
