public enum Direction {
  LEFT(-1, 0), 
  RIGHT(1, 0), 
  UP(0, 1), 
  DOWN(0, -1);

  private int x;
  private int y;

  private Direction(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return "[" + this.x + ", " + this.y + "]";
  }
}
