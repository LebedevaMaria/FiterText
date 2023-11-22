package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws IOException {
    Scanner input = new Scanner(System.in);
    System.out.println("В файлах должна быть информация на английском языке либо фильтрация будет происходить с учетом регистра");
    System.out.print("Введите путь первого файла: ");
    String firstFile = input.nextLine();
    System.out.print("Введите путь второго файла: ");
    String secondFile = input.nextLine();
    System.out.print("Введите путь третьего файла: ");
    String newFile = input.nextLine();
    FilterFile filterFile = new FilterFile(firstFile, secondFile, newFile);
    filterFile.filterFileAndRecord();
  }
}
