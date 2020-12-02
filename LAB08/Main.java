import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
  public static void main(String[] args) {
    int nx = readBoardSize();
    Game game = new Game(nx);

    game.start();
  }

  public static int readBoardSize() {
    int nx;

    while (true) {
      try {
        System.out.println("> Podaj liczbe calkowita wieksza od 1:");
        nx = SystemInWrapper.sc.nextInt();

        if (nx > 1) {
          SystemInWrapper.sc.nextLine();
          break;
        } else {
          if (nx < 2) {
            throw new Exception("Zbyt mala wartosc nx: " + nx + "!");
          }
        }

      } catch(Exception e) {
        System.out.print("BLAD: ");
        if (e instanceof InputMismatchException) {
          SystemInWrapper.sc.nextLine();
          System.out.print("Podaj liczbe calkowita!");
        } else {
          System.out.print(e.getMessage());
        }
        System.out.print("\n\n\n");
      }
    }

    return nx;
  }
}
