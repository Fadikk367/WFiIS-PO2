import java.util.concurrent.Callable;


public class CalculateMatrixItemRun implements Callable<Void> {
  public Matrix A;
  public Matrix B;
  public Matrix C;
  public int i;
  public int j;

  CalculateMatrixItemRun(Matrix A, Matrix B, Matrix C, int i, int j) {
    this.A = A;
    this.B = B;
    this.i = i;
    this.j = j;
  }

  public Void call() {
    double itemResult = 0.0;
    System.out.println("i=" + this.i + ", j=" + this.j);
    for (int k = 0; k < this.A.getN(); k++) {
      itemResult += this.A.getData()[this.i][k] + this.B.getData()[k][this.j];
    }

    this.C.getData()[this.i][this.j] = itemResult;
    return null;
  }
  
}
