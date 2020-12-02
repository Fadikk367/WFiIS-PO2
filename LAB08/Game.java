import java.util.Map;
import java.util.Random;

public class Game {
  private int nx;
  private int playerX;
  private int playerY;
  private double p = 0.3;
  char[][] board;

  public Game(int nx) {
    this.nx = nx;
    this.playerX = 0;
    this.playerY = nx - 1;
    this.board = new char[nx][nx];
    fillBoardInterior();
  }
  
  public void start() {
    this.board[this.playerY][this.playerX] = 'o';
    boolean isRunning = true;

    char userInput;
    while (isRunning) {
      try {
        printBoardToConsole();
        System.out.print("Kolejny ruch: ");
        System.out.println();
        userInput = SystemInWrapper.sc.next().charAt(0);

        switch(userInput) {
          case 'a':
            movePlayer(-1, 0);
            break;
          case 'd':
            movePlayer(1, 0);
            break;
          case 'w':
            movePlayer(0, -1);
            break;
          case 's':
            movePlayer(0, 1);
            break;
          case 'q':
            isRunning = false;
            break;
          default:
            throw new OptionNotRecognizedException("Nieznana operacja: " + userInput);
        }

      } catch(Exception e) {
        System.out.println("BLAD: " + e.getMessage());
      }
    }
  }

  private void fillBoardInterior() {
    Random generator = new Random();

    for (int j = 0; j < this.nx; j++) {
      for (int i = 0; i < this.nx; i++) {
        this.board[j][i] = generator.nextInt(1000) > this.p*1000 ? ' ' : 'X';
      }
    }
  }

  private void movePlayer(int dx, int dy) throws WallException, OutOfBoardException {
    int newPlayerX = this.playerX + dx;
    int newPlayerY = this.playerY + dy;

    if ((newPlayerX >= this.nx || newPlayerX < 0) || (newPlayerY >= this.nx || newPlayerY < 0)) {
      throw new OutOfBoardException("Nieprawidlowy ruh, nie mozesz wyjsc poza plansze");
    }

    if (this.board[newPlayerY][newPlayerX] == 'X') {
      throw new WallException("Nieprawidlowy ruch, nie mozesz przejsc przez sciane 'X'");
    }

    // Clear previous player position and move to new one
    this.board[this.playerY][this.playerX] = ' ';
    this.playerX = newPlayerX;
    this.playerY = newPlayerY;
    this.board[this.playerY][this.playerX] = 'o';
  }

  private void printBoardToConsole() {
    for (int j = 0; j < this.nx; j++) {
      for (int i = 0; i < this.nx; i++) {
        System.out.print(this.board[j][i]);
      }
      System.out.println();
    }
    System.out.println();
  }
}
