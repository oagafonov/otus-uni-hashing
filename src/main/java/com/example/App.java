package com.example;

import java.util.HashMap;
import java.util.Map;

public class App {

  public static void main(String[] args) {
    // хеш-таблица - API ()
    // хеш-функция
    // 1. генерировать ключи-логины из диапазона aaaaaaa - zzzzzzz
    // 2. вычислять index в хеш-таблице
    // 3. выбирать только те сгенерированные логины, которые имеют index = 0
    // 4. зарегистрируем всех фейковых пользователей в системе
    // 5. скорость авторизации и регистрации снизиться

    LoginsFactory.initLogins();
    LoginHashTable table = new LoginHashTable();
    table.load();
    table.printStat();
    get(table, "aaaaab");
    LoginHackerGenerator loginHackerGenerator = new LoginHackerGenerator();
    loginHackerGenerator.read().forEach(login->table.put(login,login));
//    loginHackerGenerator.generate();
    table.printStat();
    get(table, "aaaaab");
  }

  static void get(LoginHashTable table, String login) {
    long startTime = System.nanoTime();

    table.get(login);

    long endTime = System.nanoTime();

    long duration = (endTime - startTime);  // In nanoseconds
    System.out.println("The operation took " + duration + " nanoseconds");
  }
}
