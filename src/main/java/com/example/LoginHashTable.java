package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// custom (helpful) hash table
public class LoginHashTable {

  final static String fileName = "logins.dat";
  final static int N = 100_000;
  Entry[] logins = new Entry[N];

  public void put(String login, String password) {
    var entry = new Entry(new UserData(login, password));
    int index = getHash(login);

    if (logins[index] == null) {
      logins[index] = entry;
      return;
    }

    entry.next = logins[index];
    logins[index] = entry;
  }

  public UserData get(String login) {
    var index = getHash(login);
    var entry = logins[index];

    return findUserData(entry, login);
  }

  private UserData findUserData(Entry entry, String login) {
    if (entry == null) {
      return null;
    }
    if (entry.userData.login.equals(login)) {
      return entry.userData;
    }

    return findUserData(entry.next, login);
  }

  // string -> int
  // key-> hash code -> index [0..N-1]
  private int getHash(String login) {
    return Math.abs(login.hashCode()) % N;
  }

  public void printStat() {
    int total = logins.length;
    int totalMore1 = 0;
    int totalMore10 = 0;
    int totalMore100 = 0;
    int totalMore1000 = 0;
    int totalMore10000 = 0;

    for (var e : logins) {
      int count = 0;
      while (e != null) {
        count++;
        e = e.next;
      }

      if (count > 1) {
        totalMore1 += 1;
      }

      if (count >= 10) {
        totalMore10 += 1;
      }

      if (count >= 100) {
        totalMore100 += 1;
      }
      if (count >= 1000) {
        totalMore1000 += 1;
      }
      if (count >= 10000) {
        totalMore10000 += 1;
      }
    }
    System.out.printf("Total=%d, >1=%d, >10=%d, >100=%d, >1000=%d, >10000=%d\n", total, totalMore1, totalMore10,
        totalMore100, totalMore1000, totalMore10000);
  }

  void save() {
    try (FileOutputStream fos = new FileOutputStream(fileName); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(logins);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  void load() {
    try (FileInputStream fileInputStream = new FileInputStream(
        fileName); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

      logins = (Entry[]) objectInputStream.readObject();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
