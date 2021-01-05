import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FunctionData {
  private ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
  
  public FunctionData() {}

  public void addPoint(double x, double y) {
    this.points.add(new Point2D.Double(x, y));
  }

  public void addPoint(Point2D.Double point) {
    this.points.add(point);
  }

  public Point2D getPoint(int i) {
    return this.points.get(i);
  }

  public int size() {
    return this.points.size();
  }

  public String toString() {
    var result = new StringBuilder();

    for (var point : this.points) {
      result.append(point.getX() + " " + point.getY());
    }

    return result.toString();
  }

  public void saveToFile(Path path) {
    try {
      BufferedWriter buffWriter = Files.newBufferedWriter(path);
      
      for (var point : this.points) {
        buffWriter.write(point.getX() + " " + point.getY());
        buffWriter.newLine();
      }

      buffWriter.close();
    } catch(IOException e) {
      System.out.println("Nie udalo sie poprawnie zapisac danych do pliku");
      System.exit(1);
    }
  }

  public static FunctionData fromFile(Path path) throws IOException {
    var dataReader = Files.newBufferedReader(path);
    var functionData = new FunctionData();
  
    dataReader.lines().forEach(line -> {
      String[] elements = line.trim().split("\\s+");
        
      functionData.addPoint(Double.parseDouble(elements[0]), Double.parseDouble(elements[1]));
    });

    return functionData;
  }


  public static FunctionData sumFunctions(FunctionData f, FunctionData g) throws Exception {
    if (g.size() != f.size()) {
      throw new Exception("Niezgodne dlugosci danych dla sumowanych funckji");
    }

    var resultFunctionData = new FunctionData();

    for (int i = 0; i < f.size(); i++) {
      var p1 = f.getPoint(i);
      var p2 = g.getPoint(i);

      if (p1.getX() != p2.getX()) {
        throw new Exception("Niezgodne wartosci x dla odpowiadajacych sobie punktow funkcji");
      }

      resultFunctionData.addPoint(new Point2D.Double(p1.getX(), p1.getY() + p2.getY()));
    }

    return resultFunctionData;
  }
}
