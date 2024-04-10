package com.example;

import java.io.Serializable;

class UserData implements Serializable {

  private static final long serialVersionUID = 1L;

  String login;
  String password;

  public UserData(String login, String password) {
    this.login = login;
    this.password = password;
  }
}
