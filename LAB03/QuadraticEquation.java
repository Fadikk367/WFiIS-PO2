import java.lang.Math;


public class QuadraticEquation {
  private double a;
  private double b;
  private double c;
  private Complex root1 = null;
  private Complex root2 = null;
  private boolean isSolved = false;

  QuadraticEquation(double a, double b, double c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public boolean getIsSolved() {
    return this.isSolved;
  }

  public String toString() {
    String result = this.a + "x^2" + " + " + this.b + "x" + " + " + this.c + '\n';
    return result;
  }

  public void printSolution() {
    if (this.isSolved) {
      System.out.println("x1 = " + this.root1 + ", x2 = " + this.root2);
    } else {
      System.out.println("Equation has not been solved yet");
    }
  }
  
  public void solve() {
    double delta = this.b*this.b - 4*this.a*this.c;
    Complex deltaRoot = Complex.sqrt(delta);
    Complex denominator = new Complex(2*this.a);

    this.root1 = Complex.divide(Complex.add(-this.b, deltaRoot), denominator);
    deltaRoot.opposite();
    this.root2 = Complex.divide(Complex.add(-this.b, deltaRoot), denominator);

    this.isSolved = true;
  }
}
