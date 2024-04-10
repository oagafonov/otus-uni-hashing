package com.example;

import java.io.Serializable;

class Entry implements Serializable {

  private static final long serialVersionUID = 1L;
  Entry next;
  UserData userData;

  public Entry(UserData userData) {
    this.userData = userData;
  }
}
