package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class FilterFile {
  public final String firstFile;
  public final String filterWord;
  public final String newFile;

  public FilterFile(String firstFile, String filterWord, String newFile) {
    this.firstFile = firstFile;
    this.filterWord = filterWord;
    this.newFile = newFile;
  }
  public ArrayList<String> stopWord = new ArrayList<>();


  public void filterFileAndRecord() throws IOException {
    var readerFirst = new BufferedReader(new FileReader(firstFile));

    var readerSecond = new BufferedReader(new FileReader(filterWord));

    // Список строк первого файла
    ArrayList<String> firstFileList = new ArrayList<>();
    String lineFirstFile = readerFirst.readLine();
    while (lineFirstFile != null) {
      firstFileList.add(lineFirstFile);
      lineFirstFile = readerFirst.readLine();
    }

    // Список запрещенных слов
    String lineSecondFile = readerSecond.readLine();

    while (lineSecondFile != null) {
      stopWord.add(lineSecondFile.replaceAll(" ", "").toLowerCase());
      lineSecondFile = readerSecond.readLine();
    }

    // Отфильтрованный список строк первого файла
    var newFileList = firstFileList.stream().filter(this :: findStopWords).collect(Collectors.toList());

    // Запись строк в новый файл
    FileWriter writer = new FileWriter(newFile);

    for (int i = 0; i < newFileList.size(); i++) {
      try {
        writer.write(newFileList.get(i));
        writer.write("\n");
      } catch (IOException e) {
        System.out.println("Ошибка при записи в файл");
        e.printStackTrace();
      }
    }
    writer.close();


  }

  public boolean findStopWords(String line){
    String newLine = line.toLowerCase();
    boolean flag = true;
    for (int i = 0; i < stopWord.size(); i++) {
      if (newLine.contains(stopWord.get(i))){
        flag = false;
        break;
      }
    }
    return flag;
  }
}
