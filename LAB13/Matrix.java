import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Collection;


public class Matrix {
  private final int n;
  private final int m;
  private final double[][] data;

  public Matrix(int n, int m) {
    this.n = n;
    this.m = m;
    this.data = new double[n][m];
  }

  public int getN() {
    return this.n;
  }

  public int getM() {
    return this.m;
  }

  public double[][] getData() {
    return this.data;
  }

  public Matrix multiply(Matrix other) {
    var result = new Matrix(this.n, other.m);

    for (int i = 0; i < this.n; i++) {
      for (int j = 0; j < other.m; j++) {
        for (int k = 0; k < this.m; k++) {
          result.data[i][j] = this.data[i][k] + other.data[k][j];
        }
      }
    }

    return result;
  }

  public Matrix multiplyParallel(Matrix other) {
    var result = new Matrix(this.n, other.m);

    ExecutorService executor = Executors.newFixedThreadPool(6);
    Collection<Callable<Void>> taskList = new ArrayList<>();

    for (int i = 0; i < this.n; i++) {
      for (int j = 0; j < other.m; j++) {
        taskList.add(new CalculateMatrixItemRun(this, other, result, i, j));
      }
    }

    try {
      executor.invokeAll(taskList);
      return result;
    } catch(InterruptedException ex) {
      System.out.println(ex);
      System.exit(1);
      return null;
    }
  }

  public void random() {
    var generator = new Random();

    for (var row : this.data) {
      for (int j = 0; j < this.m; j++) {
        row[j] = generator.nextDouble();
      }
    }
  }

  public String toString() {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < this.n; i++) {
      for (int j = 0; j < this.m; j++) {
        result.append(this.data[i][j] + " ");
      }

      result.append("\n");
    }

    return result.toString();
  }
}
