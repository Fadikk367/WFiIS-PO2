public class Point {
  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void translate(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  }

  public void move(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void move(Point point) {
    this.x = point.x;
    this.y = point.y;
  }

  public boolean equals(Object obj) {
    if (obj instanceof Point) {
      Point other = (Point) obj;
      return this.x == other.x && this.y == other.y;
    } else {
      return false;
    }
  }
}
