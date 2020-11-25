import java.util.Random;
import java.util.Scanner;
import java.awt.Point;

interface CheckStep {
  boolean test(char[][] board, int i0, int j0, Direction dir);
}

public class Game {
  private char[][] board;
  private int nx;
  private int ny;
  private double p;
  private int playerX;
  private int playerY;
  private Point goalPoint;

  public Game(int nx, int ny, double p) {
    this.board = new char[ny][nx];
    this.nx = nx;
    this.ny = ny;
    this.p = p;
    this.playerY = ny - 2;
    this.playerX = 1;

    this.goalPoint = new Point(0, this.nx / 2);

    this.initialize();
  }

  public void start() {
    printManual();

    runGameLoop();
  }

  public void printManual() {
    for (Option opt : Option.values()) {
      System.out.println(opt);
    }
    System.out.println();
  }

  private void runGameLoop() {
    Scanner s = new Scanner(System.in);
    char input;

    while (true) {
      printBoard();
      System.out.print("Wybierz opcje: ");
      input = s.next().charAt(0);

      Option option = parseUserInput(input);
      Direction direction = option.getDirection();

      if (direction != null) {
        movePlayer(direction, (char[][] board, int prevX, int prevY, Direction dir) -> (
          board[prevY + dir.getY()][prevX + dir.getX()] == 'X' ? false : true
        ));
      }

      if (isPlayerAtGoalPoint()) {
        System.out.println("WYGRANA!!!");
        break;
      }

      if (option == Option.RESET)
        fillBoardInterior();

      if (option == Option.EXIT)
        break;
    }

    s.close();
  }

  private boolean isPlayerAtGoalPoint() {
    return (this.playerX == this.goalPoint.getY() && this.playerY == this.goalPoint.getX());
  }

  private Option parseUserInput(char input) {
    Option parsedOption = null;

    switch(input) {
      case 'a':
        parsedOption = Option.LEFT;
        break;
      case 'd':
        parsedOption = Option.RIGHT;
        break;
      case 'w':
        parsedOption = Option.UP;
        break;
      case 's':
        parsedOption = Option.DOWN;
        break;
      case 'r':
        parsedOption = Option.RESET;
        break;
      case 'q':
        parsedOption = Option.EXIT;
        break;
      default:
        break;
    }
    return parsedOption;
  }

  private void movePlayer(Direction direction, CheckStep isValidMove) {
    if (isValidMove.test(this.board, this.playerX, this.playerY, direction)) {
      int x = direction.getX();
      int y = direction.getY();
  
      clearPreviousPlayerPosition();
  
      this.playerX += x;
      this.playerY += y;
    } else {
      System.out.println("NIE UDALO SIE WYKONAC TAKIEGO RUCHU");
    }
  }

  private void drawPlayer() {
    this.board[this.playerY][this.playerX] = 'o';
  }

  private void clearPreviousPlayerPosition() {
    this.board[this.playerY][this.playerX] = ' ';
  }

  private void initialize() {
    fillOuterWall();
    fillBoardInterior();
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

  private void fillBoardInterior() {
    Random generator = new Random();

    for (int j = 1; j < this.ny - 1; j++) {
      for (int i = 1; i < this.nx - 1; i++) {
        this.board[j][i] = generator.nextInt(1000) > this.p*1000 ? ' ' : 'X';
      }
    }
  }

  public void printBoard() {
    drawPlayer();

    for (int j = 0; j < this.ny; j++) {
      for (int i = 0; i < this.nx; i++) {
        System.out.print(this.board[j][i]);
      }
      System.out.println();
    }
  }
}
