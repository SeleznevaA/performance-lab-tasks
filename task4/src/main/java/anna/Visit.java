package anna;

public class Visit {
    private final int entryTime;
    private final int exitTime;

    public Visit(int entryTime, int exitTime) {
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public int getEntryTime() {
        return entryTime;
    }

    public int getExitTime() {
        return exitTime;
    }
}
