package com.example;

import java.util.Random;

// создает таблицу с пользователями со случайными логинами и паролями
// сохраняет ее на диске
public class LoginsFactory {

  final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  public static void initLogins() {
    var logins = new LoginHashTable();
    for (int i = 0; i < 100_000; i++) {
      var login = generate();
      var password = generate();
      logins.put(login, password);
    }

    logins.printStat();

    logins.save();
  }

  private static String generate() {
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    int length = 10;
    for (int i = 0; i < length; i++) {
      int index = random.nextInt(alphabet.length());
      char randomChar = alphabet.charAt(index);
      sb.append(randomChar);
    }
    return sb.toString();
  }

}
