package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class LoginHackerGenerator {

  final static String fileName = "collisions.dat";

  public Stream<String> read(){
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      return reader.lines();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return Stream.empty();
  }

  public void generate() {
    int attackIndex = 0;
    StringGenerator generator = new StringGenerator();
    HashFunction h = new HashFunction();
    String login;

    long step = 0L;

    try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      while ((login = generator.getNext()) != null) {
        int index = h.hash(login);
//      int index = login.hashCode();

        if (attackIndex == 0) {
          attackIndex = index;
        }

        if (attackIndex == index) {
          writer.write(login);
          writer.newLine();
        }

        if (step % 1_000_000 == 0) {
          System.out.printf("processed %d: %s\n", step, login);
        }

        step++;
      }
      writer.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
