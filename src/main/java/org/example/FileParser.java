package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FileParser {

  private FileParser() {
  }

  private static final Pattern PHONE_PATTERN_WITH_BRACKETS = Pattern.compile(
      "[(][0-9]{3}[)] [0-9]{3}-[0-9]{4}");
  private static final Pattern PHONE_PATTERN_WITHOUT_BRACKETS = Pattern.compile(
      "[0-9]{3} [0-9]{3}-[0-9]{4}");

  public static List<String> getValidNumbers(String path) throws FileNotFoundException {
    File file = new File(path);
    Scanner sc = new Scanner(file);
    List<String> result = new ArrayList<>();

    while (sc.hasNextLine()) {
      String nextLine = sc.nextLine();
      if (PHONE_PATTERN_WITH_BRACKETS.matcher(nextLine).matches()
          || PHONE_PATTERN_WITHOUT_BRACKETS.matcher(nextLine).matches()) {
        result.add(nextLine);
      }
    }
    return result;
  }

  public static void convertTxtToJson(String txtPath, String jsonToCreatePath)
      throws FileNotFoundException {
    File file = new File(txtPath);
    Scanner sc = new Scanner(file);
    List<String> keys = new ArrayList<>();

    if (sc.hasNextLine()) {
      keys = Arrays.asList(sc.nextLine().split(" "));
    }

    JSONArray jsonResult = new JSONArray();
    while (sc.hasNextLine()) {
      List<String> values = Arrays.asList(sc.nextLine().split(" "));
      JSONObject tempObject = new JSONObject();
      for (int i = 0; i < keys.size(); i++) {
        tempObject.put(keys.get(i), values.get(i));
      }
      jsonResult.add(tempObject);
    }

    try (FileWriter resultFile = new FileWriter(jsonToCreatePath)) {
      resultFile.write(jsonResult.toJSONString());
      resultFile.flush();

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void showFileStats(String path) throws FileNotFoundException {
    File file = new File(path);
    Scanner sc = new Scanner(file);
    List<String> words = new ArrayList<>();

    while (sc.hasNextLine()) {
      words.addAll(Arrays.asList(sc.nextLine().split(" ")));
    }

    Map<String, Long> counts = words.stream()
        .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

    counts.entrySet().stream().sorted((y, x) -> (int) (x.getValue() - y.getValue()))
        .forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));
  }
}