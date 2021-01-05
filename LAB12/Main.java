import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {
  public static void main(String[] args) {
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    try {
      System.out.println("Podaj nazwe pierwszego pliku z danymi");
      String filenameA= inputReader.readLine();
      System.out.println("Podaj nazwe drugiego pliku z danymi");
      String filenameB = inputReader.readLine();

      var functionDataA = FunctionData.fromFile(Paths.get(filenameA));
      var functionDataB = FunctionData.fromFile(Paths.get(filenameB));

      var resultFunctionData = FunctionData.sumFunctions(functionDataA, functionDataB);

      saveResultToFile(resultFunctionData, inputReader);
    } catch(IOException e) {
      System.out.println("Wystapil blad podczas operacji na plikach");
      System.exit(1);
    } catch(Exception e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  public static void saveResultToFile(FunctionData result, BufferedReader inputReader) throws IOException {
    boolean isFilenameSpecified = false;
    String resultFilename = "";
    do {
      isFilenameSpecified = false;
      System.out.println("Podaj nazwe dla pliku wynikowego:");
      resultFilename = inputReader.readLine();

      if (Files.exists(Paths.get(resultFilename))){
        isFilenameSpecified = true;
            
        System.out.println("Plik o podanej nazwie juz istnieje, czy chcesz go nadpisac? [TAK/NIE]");
        String userAnswer = inputReader.readLine();

        if (userAnswer.equals("TAK")){
          isFilenameSpecified = false;
        }
      }
    } while (isFilenameSpecified);

    result.saveToFile(Paths.get(resultFilename));
  }
}
