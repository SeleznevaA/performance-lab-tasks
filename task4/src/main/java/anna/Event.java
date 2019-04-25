package anna;

public class Event {
    private final int time;
    private final int diff;

    public Event(int time, int diff) {
        this.time = time;
        this.diff = diff;
    }

    public int getTime() {
        return time;
    }

    public int getDiff() {
        return diff;
    }
}
