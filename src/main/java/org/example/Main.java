package org.example;

public class Main {

  public static void main(String[] args) {
    String path1 = "C:\\Users\\Lenovo\\Desktop\\java-intelli\\hw-module-10\\task1.txt";

    String path2txt = "C:\\Users\\Lenovo\\Desktop\\java-intelli\\hw-module-10\\task2.txt";
    String path2json = "C:\\Users\\Lenovo\\Desktop\\java-intelli\\hw-module-10\\task2.json";

    String path3Txt = "C:\\Users\\Lenovo\\Desktop\\java-intelli\\hw-module-10\\task3.txt";

    try {
      FileParser.getValidNumbers(path1).stream().forEach(n -> System.out.println(n));
      System.out.println("==================================================");
      FileParser.convertTxtToJson(path2txt, path2json);
      FileParser.showFileStats(path3Txt);
    } catch (Exception e) {
      System.out.println("Can not find file");
    }
  }
}