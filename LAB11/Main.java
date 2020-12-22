import java.util.Random;
import java.lang.Math;
import java.lang.System;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;


public class Main {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int m = Integer.parseInt(args[1]);

    System.out.println("n = " + n + ", m = " + m + "\n");

    Random generator = new Random();
    int minLength = 5;
    int maxLength = 20;


    String alphabet = "abcdefghijklmnoprsqtuwz";

    String[] t1 = new String[n];
    String[] t2 = new String[m];
    String[] t3 = new String[m];

    // Randomly generate t1
    for (int i = 0; i < n; i++) {
      int length = generator.nextInt(maxLength - minLength) + minLength;

      StringBuilder builder = new StringBuilder();
      for (int j = 0; j < length; j++) {
        char c = alphabet.charAt(generator.nextInt(alphabet.length()));
        builder.append( Math.random() > 0.5 ? c : Character.toUpperCase(c));
      }

      t1[i] = builder.toString();
    }

    // Fill t2
    for (int i = 0; i < m; i++) {
      t2[i] = t1[generator.nextInt(t1.length)];
    }

    //  Fill t3
    HashSet<Integer> alreadyUsedIndexes = new HashSet<>();
    for (int i = 0; i < m; i++) {
      while (true) {
        int index = generator.nextInt(t1.length);

        if (!alreadyUsedIndexes.contains(index)) {
          t3[i] = t1[index];
          alreadyUsedIndexes.add(index);
          break;
        }
      }
    }

    ArrayList<String> arrayList = new ArrayList<>();
    LinkedList<String> linkedList = new LinkedList<>();
    HashMap<String, String> hashMap = new HashMap<>();
    TreeMap<String, String> treeMap = new TreeMap<>();

    System.out.println("Experiment 1. - ArrayList");
    runListExperiment(arrayList ,t1, t2, t3, n, m);

    System.out.println("Experiment 2. - LinkedList");
    runListExperiment(linkedList ,t1, t2, t3, n, m);

    System.out.println("Experiment 3. - TreeMap");
    runMapExperiment(treeMap, t1, t2, t3, n, m);

    System.out.println("Experiment 4. - HashMap");
    runMapExperiment(hashMap, t1, t2, t3, n, m);
  }


  public static void runListExperiment(List<String> list, String[] t1, String[] t2,String[] t3, int n, int m) {
    long time0, time1;

    // Insert
    time0 = System.nanoTime();
    for (int i = 0; i < n; i++) {
      list.add(t1[i]);
    }

    time1 = System.nanoTime();
    System.out.println("Czas wstawiania: " + (time1 - time0)/1000000.0 + "ms");

    // Find from t2
    time0 = System.nanoTime();
    for (int i = 0; i < m; i++) {
      list.indexOf(t2[i]);
    }

    time1 = System.nanoTime();
    System.out.println("Czas wyszukiwania elementow z t2: " + (time1 - time0)/1000000.0 + "ms");

    // Find from t3
    time0 = System.nanoTime();
    for (int i = 0; i < m; i++) {
      list.indexOf(t3[i]);
    }

    time1 = System.nanoTime();
    System.out.println("Czas wyszukiwania elementow z t3: " + (time1 - time0)/1000000.0 + "ms");

    // Go through times
    // 1. for
    time0 = System.nanoTime();
    for (int i = 0; i < m; i++) {
      list.get(i);
    }

    double forTime = (System.nanoTime() - time0)/1000000.0;
    // 2. for-each
    time0 = System.nanoTime();
    for (String item : list) {}
    
    double forEachTime = (System.nanoTime() - time0)/1000000.0;
    // 3. iterator
    time0 = System.nanoTime();
    Iterator it = list.iterator();
    while (it.hasNext()) {
      it.next();
    }

    double iteratorTime = (System.nanoTime() - time0)/1000000.0;
    System.out.println("Czasy przechodzenia: for(" + forTime + "ms), for-each(" + forEachTime + "ms), iterator(" + iteratorTime + "ms)" );

    // Delete all
    time0 = System.nanoTime();
    for (int i = 0; i < m; i++) {
      list.clear();
    }

    time1 = System.nanoTime();
    System.out.println("Czas usuwania: " + (time1 - time0)/1000000.0 + "ms");
    System.out.println();
  }


  public static void runMapExperiment(Map<String, String> map, String[] t1, String[] t2,String[] t3, int n, int m) {
    long time0, time1;

    // Insert
    time0 = System.nanoTime();
    for (int i = 0; i < n; i++) {
      map.put(t1[i], t1[i]);
    }

    time1 = System.nanoTime();
    System.out.println("Czas wstawiania: " + (time1 - time0)/1000000.0 + "ms");

    // Find from t2
    time0 = System.nanoTime();
    for (int i = 0; i < m; i++) {
      map.get(t2[i]);
    }

    time1 = System.nanoTime();
    System.out.println("Czas wyszukiwania elementow z t2: " + (time1 - time0)/1000000.0 + "ms");

    // Find from t3
    time0 = System.nanoTime();
    for (int i = 0; i < m; i++) {
      map.get(t3[i]);
    }

    time1 = System.nanoTime();
    System.out.println("Czas wyszukiwania elementow z t3: " + (time1 - time0)/1000000.0 + "ms");

    // Delete all
    time0 = System.nanoTime();
    for (int i = 0; i < m; i++) {
      map.clear();
    }

    time1 = System.nanoTime();
    System.out.println("Czas usuwania: " + (time1 - time0)/1000000.0 + "ms");
    System.out.println();
  }
}