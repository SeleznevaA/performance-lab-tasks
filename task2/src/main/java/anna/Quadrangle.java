package anna;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.stream.Collectors.toList;

public class Quadrangle {

    private final List<Point> points;

    public Quadrangle(List<Point> points) {
        this.points = points;
    }

    static Quadrangle quadrangle(Point p1, Point p2, Point p3, Point p4) {
        return new Quadrangle(ImmutableList.of(p1, p2, p3, p4));
    }

    public PointPosition getPointPosition(Point target) {
        if (isOnApex(target)) {
            return PointPosition.ON_APEX;
        }

        if (isOnSide(target)) {
            return PointPosition.ON_SIDE;
        }

        if (isInSide(target)) {
            return PointPosition.INSIDE;
        }

        return PointPosition.OUTSIDE;
    }

    private boolean isInSide(Point target) {
        ArrayList<Point> points = new ArrayList<>(this.points);
        points.add(points.get(0));

        for (int i = 0; i < points.size() - 1; i++) {
            if(!isInSideRegardingOneLine(points.get(i), points.get(i + 1), target)) {
                return false;
            }
        }

        return true;
    }

    private boolean isInSideRegardingOneLine(Point p1, Point p2, Point target) {
        List<Point> points = getOtherPoints(p1, p2);
        points.add(target);

        return points.stream()
                .map(p -> p.y() - getY(p1, p2, p))
                .map(Math::signum)
                .distinct()
                .count() == 1;
    }

    private List<Point> getOtherPoints(Point p1, Point p2) {
        return points.stream()
                .filter(p -> !p.equals(p1))
                .filter(p -> !p.equals(p2))
                .collect(toList());
    }

    private boolean isOnApex(Point target) {
        return points.stream().anyMatch(target::equals);
    }

    private boolean isOnSide(Point target) {
        ArrayList<Point> points = new ArrayList<>(this.points);
        points.add(points.get(0));

        for (int i = 0; i < points.size() - 1; i++) {
            if (onLine(points.get(i), points.get(i + 1), target)) {
                return true;
            }
        }

        return false;
    }

    private boolean onLine(Point p1, Point p2, Point target) {
        double y = getY(p1, p2, target);

        int maxX = max(p1.x(), p2.x());
        int minX = min(p1.x(), p2.x());

        int maxY = max(p1.y(), p2.y());
        int minY = min(p1.y(), p2.y());

        return abs(target.y() - y) < 1e-6
                && minX <= target.x() && target.x() <= maxX
                && minY <= target.y() && target.y() <= maxY;
    }

    private double getY(Point p1, Point p2, Point target) {
        return (target.x() - p1.x()) * (p2.y() - p1.y()) / (double) (p2.x() - p1.x()) + p1.y();
    }

    @Override
    public String toString() {
        return String.valueOf(points);
    }
}
