package com.example;

public class StringGenerator {

  private final String end = "zzzzzz";
  private final StringBuilder sb = new StringBuilder("aaaaaa");

  private static void incrementString(StringBuilder sb, int index) {
    if (sb.charAt(index) < 'z') {
      sb.setCharAt(index, (char) (sb.charAt(index) + 1));
    } else {
      sb.setCharAt(index, 'a');
      if (index > 0) {
        incrementString(sb, index - 1);
      }
    }
  }

  public String getNext() {
    if (sb.toString().equals(end)) {
      return null;
    }

    incrementString(sb, sb.length() - 1);
    return sb.toString();
  }
}
