import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Paths;

public class Main {
  public static void main(String[] args) {
    BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));

    try {
      System.out.println("Podaj nazwę pierwszego pliku z danymi");
      String filenameA= buffReader.readLine();
      System.out.println("Podaj nazwę drugiego pliku z danymi");
      String filenameB = buffReader.readLine();

      var dataReaderA = Files.newBufferedReader(Paths.get(filenameA));
      var dataReaderB = Files.newBufferedReader(Paths.get(filenameB));
      // var dataStream1 = dataReader.lines().map(line -> Double.parseDouble(line.split("\\s+")[1])).collect(Collectors.toList());

      // var xA = new ArrayList<Double>();
      // var yA = new ArrayList<Double>();
      // var xB = new ArrayList<Double>();
      // var yB = new ArrayList<Double>();

      var functionDataA = new FunctionData();
      var functionDataB = new FunctionData();


      dataReaderA.lines().forEach(line -> {
        String[] elements = line.trim().split("\\s+");
        
        functionDataA.addPoint(Double.parseDouble(elements[0]), Double.parseDouble(elements[1]));
      });

      dataReaderB.lines().forEach(line -> {
        String[] elements = line.trim().split("\\s+");
        
        functionDataB.addPoint(Double.parseDouble(elements[0]), Double.parseDouble(elements[1]));
      });


      var resultFunctionData = FunctionData.sumFunctions(functionDataA, functionDataB);

      resultFunctionData.saveToFile(Paths.get("output.dat"));

    } catch(IOException e) {
      System.out.println("Wystąpił bład podczas wczytywania danych wejściowych");
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
