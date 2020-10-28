import java.math.BigInteger;
import java.util.Random; 

public class Main {
  public static void main(String[] args) {
    Random generator = new Random();

    for (int i = 0; i < 1000; i++) {
      long first = Math.abs(generator.nextLong()) / 2L;
      long seccond = Math.abs(generator.nextLong()) / 2L;
      testWithLongs(first, seccond);
    }
    System.out.println("Successfully completed 1000 tests for random long numbers");

    customTest1();
    customTest2();
    System.out.println("Successfully completed custom tests");
  }

  private static void testWithLongs(long a, long b) {
    long testResult = a + b;
    BigInt correctResult = new BigInt(Long.toString(testResult));

    BigInt A = new BigInt(Long.toString(a));
    BigInt B = new BigInt(Long.toString(b));

    BigInt result = A.add(B);

    if (!result.equals(correctResult)) {
      System.out.println("Program terminated after failed test for: a = " + A + ", b = " + B);
      System.exit(1);
    }
  }

  private static void customTest1() {
    long a = 1L;
    long b = 999999999999999999L;

    testWithLongs(a, b);
  }

  private static void customTest2() {
    long a = 999999999999999999L;
    long b = 1L;

    testWithLongs(a, b);
  }
 }
