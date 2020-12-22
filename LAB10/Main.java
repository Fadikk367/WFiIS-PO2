public class Main {
  public static void main(String[] args) {
    String inputExpression = args[0];

    try {
      String result = ONPTranslator.translate(inputExpression);
      System.out.println("Wynik: " + result);
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
