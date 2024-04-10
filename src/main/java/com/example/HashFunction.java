package com.example;

import java.util.Random;

public class HashFunction {
  final static int N = 100_000;
  Random r = new Random();
  int p = 655360001;
  public int hash(String key) {
    int A = 1 + r.nextInt(p-1);
    int B = 0 + r.nextInt(p-1);
//    return (A*key.hashCode() + B) % p % N;
    return Math.abs(key.hashCode()) % N;
  }
}
