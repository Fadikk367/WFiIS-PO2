public class Main {
  public static void main(String[] args) {
    int boardWidth = Integer.parseInt(args[0]);
    int boardHeight = Integer.parseInt(args[1]);
    double probability = Double.parseDouble(args[2]);

    Game game = new Game(boardWidth, boardHeight, probability);
    game.start();
  }
}