import java.lang.reflect.Method;
import java.lang.Math;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Nie podano nic do obliczenia!");
      System.exit(1);
    }

    String[] splitted = splitArgs(args[0]);
    if (splitted.length == 0) {
      System.out.println("Nie podano nic do obliczenia!");
      System.exit(1);
    }

    String methodName = splitted[0];
    double arg1 = 0.0;
    double arg2 = 0.0;
    Double result = null;

    try {
      if (splitted.length < 2 || splitted.length > 3) {
        System.out.println("Zla liczba argumentow funkcji! Podaj jedna lub dwie liczby.");
        System.exit(1);
      }

      arg1 = Double.parseDouble(splitted[1]);
      if (splitted.length == 3) {
        arg2 = Double.parseDouble(splitted[2]);
      }
    } catch(NumberFormatException e) {
      System.out.println("Argumenty funkcji musza byc liczbami!");
      System.exit(1);
    }

    try {
      if (splitted.length == 2) {
        Method userMethod = Math.class.getMethod(methodName, double.class);
        System.out.println("1 arg: arg1: " + arg1);
        result = (Double) userMethod.invoke(null, arg1);
      } else if (splitted.length == 3) {
        Method userMethod = Math.class.getMethod(methodName, double.class, double.class);
        System.out.println("2 args: arg1: " + arg1 + ", arg2: " + arg2);
        result = (Double) userMethod.invoke(null, arg1, arg2);
      }

      System.out.println("Wynik: " + result);
    } catch(NoSuchMethodException e) {
      System.out.println("Nie ma takiej metody");
      System.exit(1);
    } catch(Exception e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  public static String[] splitArgs(String arg) {
    return Arrays.stream(arg.split("[\\s+(),]")).filter(w -> w.isEmpty() == false).toArray(String[]::new);
  }
}