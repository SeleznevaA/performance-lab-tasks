package anna;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        List<Point> points;
        try {
            points = readPoints("C:/Users/Anna/Desktop/performance-lab/task2/input.txt");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        Quadrangle quadrangle = new Quadrangle(points);

        System.out.println("target point:");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        Point target = Point.p(x, y);

        PointPosition pointPosition = quadrangle.getPointPosition(target);
        String pointPositionDescription = pointPositionDescription(pointPosition);

        System.out.println(pointPositionDescription);
    }

    private static List<Point> readPoints(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(line -> {
                    String[] coordinates = line.split(" ");
                    return Point.p(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
                })
                .collect(Collectors.toList());
    }

    private static String pointPositionDescription(PointPosition pointPosition) {
        switch (pointPosition) {
            case ON_APEX: return "точка - вершина четырехугольника";
            case INSIDE: return "точка внутри четырехугольника";
            case OUTSIDE: return "точка снаружи четырехугольника";
            case ON_SIDE: return "точка лежит на сторонах четырехугольника";
            default: return "";
        }
    }

}
