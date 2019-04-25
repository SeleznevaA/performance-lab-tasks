package anna;

import java.util.List;

public class CashierMeasures {
    private final List<Double> measures;
    private final int measuresCount;

    public CashierMeasures(List<Double> measures) {
        this.measures = measures;
        this.measuresCount = measures.size();
    }

    public double getMeasure(int index) {
        return measures.get(index);
    }

    public int getMeasuresCount() {
        return measuresCount;
    }
}
