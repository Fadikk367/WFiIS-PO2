import java.util.Arrays;

public class ONPTranslator {
  public static final String[] operators = {"+", "-", "*", "/"};

  public static String translate(String onpExpression) throws Exception {
    String[] tokens = onpExpression.split("");
    Stack<String> stack = new Stack<String>(tokens.length);

    try {
      for (String token : tokens) {
        if (isOperator(token)) {
          String rightOperand = stack.pop();
          String leftOperand = stack.pop();
  
          stack.push("(" + leftOperand + token + rightOperand + ")");
        } else {
          stack.push(token);
        }
        // System.out.println("DEBUG: " + stack);
      }
    } catch(Exception e) {
      if (e instanceof StackUnderflowException) {
        throw new Exception("BLAD DANYCH WEJSCIOWYCH! Na stosie jest za malo elementow, zeby wykonac operacje!");
      }
    }

    String result = stack.pop();

    if (!stack.isEmpty()) {
      // We have to add result at the end because we have popped it before
      throw new Exception("BLAD DANYCH WEJSCIOWYCH! Koniec algorytmu, a stos nie jest pusty: " + stack + ", " + result);
    }

    return result;
  }

  private static boolean isOperator(String token) {
    return Arrays.asList(ONPTranslator.operators).contains(token);
  }
}
