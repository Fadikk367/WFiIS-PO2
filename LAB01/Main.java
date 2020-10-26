import java.lang.Math;
import java.util.*;

class Main {
  public static void main(String args[]) {
    int N = Integer.parseInt(args[0]);
    double A_MAX = Double.parseDouble(args[1]);
    double B_MAX = Double.parseDouble(args[2]);

    ArrayList<Point2D> points = new ArrayList<Point2D>();

    for (int i = 0; i < N; i++) {
      Point2D point = Point2D.createRandomlyInsideElipse(A_MAX, B_MAX);
      points.add(point);
    }

    for (Point2D point : points)
      point.print();
  }
}

class Point2D {
  double x, y;

  Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public void print() {
    System.out.println(this.x + "  " + this.y);
  }

  public static Point2D createRandomlyInsideElipse(double aMax, double bMax) {
    double a = Math.random() * aMax;
    double b = Math.random() * bMax;
    double angle = Math.toRadians(Math.random() * 360.0);

    double x = a * Math.cos(angle);
    double y = b * Math.sin(angle);

    return new Point2D(x, y);
  }
}