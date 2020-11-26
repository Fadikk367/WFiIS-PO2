import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import static java.util.Map.entry;


public class Game {
  private Maze maze;
  private Point player;
  private Point defaultPlayerPosition;
  private Point goalPoint;
  private Map<Character, Option> gameOptionsByCharacters = Map.ofEntries(
    entry('a', Option.LEFT),
    entry('d', Option.RIGHT),
    entry('w', Option.UP),
    entry('s', Option.DOWN),
    entry('r', Option.RESET),
    entry('q', Option.EXIT)
  );

  public Game(int nx, int ny, double p) {
    this.maze = new Maze(nx, ny, p);
    this.player = new Point(1, ny - 2);
    this.defaultPlayerPosition = new Point(1, ny - 2);
    this.goalPoint = new Point(nx / 2, 0);
  }

  public void start() {
    printManual();
    this.maze.drawPlayer(this.player);
    this.maze.printBoardToConsole();
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
    char inputChar;

    while (true) {
      System.out.print("Wybierz opcje: ");
      inputChar = s.next().charAt(0);

      Option option = this.gameOptionsByCharacters.get(inputChar);
      Direction direction = option.getDirection();

      if (direction != null) {
        movePlayer(direction);
      }

      if (option == Option.RESET) {
        this.maze.resetBoard();
        this.player.move(this.defaultPlayerPosition);
        this.maze.drawPlayer(this.player);
      }

      this.maze.printBoardToConsole();

      if (isPlayerAtGoalPoint()) {
        System.out.println("WYGRANA!!!");
        break;
      }

      if (option == Option.EXIT)
        break;
    }

    s.close();
  }

  private boolean isPlayerAtGoalPoint() {
    return (this.player.getX() == this.goalPoint.getX() && this.player.getY() == this.goalPoint.getY());
  }

  private void movePlayer(Direction direction) {
    if (this.maze.isValidMove(
      this.player, 
      direction, 
      (char[][] board, int prevX, int prevY, Direction dir) -> (
        board[prevY + dir.getY()][prevX + dir.getX()] == 'X' ? false : true
      ))
    ) {
      this.maze.clearPlayerPreviousPosition(this.player);
      this.player.translate(direction.getX(), direction.getY());
      this.maze.drawPlayer(this.player);
    } else {
      System.out.println("NIE UDALO SIE WYKONAC TAKIEGO RUCHU");
    }
  }
}
