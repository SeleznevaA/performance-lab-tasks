package anna;

import java.util.Objects;

public class Period {
    private final int startTime;
    private final int endTime;
    private final int count;

    public Period(int startTime, int endTime, int count) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.count = count;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return startTime == period.startTime &&
                endTime == period.endTime &&
                count == period.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, count);
    }
}
