import java.util.Random;

interface CheckStep {
  boolean test(char[][] board, int i0, int j0, Direction dir);
}

public class Maze {
  char[][] board;
  int nx;
  int ny;
  double p;
   
  public Maze(int nx, int ny, double p) {
    this.board = new char[ny][nx];
    this.nx = nx;
    this.ny = ny;
    this.p = p;

    initialize();
  }

  private void initialize() {
    fillOuterWall();
    fillBoardInterior();
  }

  public void printBoardToConsole() {
    for (int j = 0; j < this.ny; j++) {
      for (int i = 0; i < this.nx; i++) {
        System.out.print(this.board[j][i]);
      }
      System.out.println();
    }
    System.out.println();
  }

  public void resetBoard() {
    fillBoardInterior();
  }

  public boolean isValidMove(Point player, Direction dir, CheckStep constraint) {
    return constraint.test(this.board, player.getX(), player.getY(), dir);
  }

  public void drawPlayer(Point player) {
    this.board[player.getY()][player.getX()] = 'o';
  }

  public void clearPlayerPreviousPosition(Point player) {
    this.board[player.getY()][player.getX()] = ' ';
  }

  private void fillBoardInterior() {
    Random generator = new Random();

    for (int j = 1; j < this.ny - 1; j++) {
      for (int i = 1; i < this.nx - 1; i++) {
        this.board[j][i] = generator.nextInt(1000) > this.p*1000 ? ' ' : 'X';
      }
    }
  }

  private void fillOuterWall() {
    for (int j = 0; j < this.ny; j++) {
      this.board[j][0] = 'X';
      this.board[j][this.nx - 1] = 'X';
    }

    for (int i = 0; i < this.nx; i++) {
      this.board[0][i] = 'X';
      this.board[this.ny - 1][i] = 'X';
    }

    int centerPoint = this.nx / 2; 
    this.board[0][centerPoint] = ' ';
  }
}
