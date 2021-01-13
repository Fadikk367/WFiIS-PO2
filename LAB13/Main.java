public class Main {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int m = Integer.parseInt(args[1]);
    int p = Integer.parseInt(args[2]);

    var A = new Matrix(n, m);
    var B = new Matrix(m, p);

    A.random();
    B.random();

    var C = A.multiply(B);

    System.out.println("Multiplication result:");

    var D =A.multiplyParallel(B);

    System.out.println(C);
    System.out.println(D);
  }
}
