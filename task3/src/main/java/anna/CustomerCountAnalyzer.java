package anna;

import java.util.List;

public class CustomerCountAnalyzer {

    private final List<CashierMeasures> measuresLists;

    public CustomerCountAnalyzer(List<CashierMeasures> cashierMeasures) {
        this.measuresLists = cashierMeasures;
    }

    public int getIntensiveNumberOfHalfHour() {
        double maxCustomerCount = 0;
        int time = 0;

        for (int i = 0; i < measuresLists.get(0).getMeasuresCount(); i++) {
            double currentCustomerCount = getCustomerCountForNumberOfInterval(i);

            if (maxCustomerCount < currentCustomerCount) {
                maxCustomerCount = currentCustomerCount;
                time = i;
            }
        }

        return time + 1;
    }

    private double getCustomerCountForNumberOfInterval(int measureNumber) {
        return measuresLists.stream()
                .mapToDouble(list -> list.getMeasure(measureNumber))
                .sum();
    }
}
