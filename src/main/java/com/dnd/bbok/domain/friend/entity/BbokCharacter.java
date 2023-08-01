package com.dnd.bbok.domain.friend.entity;

public enum BbokCharacter {
  CACTUS("인장이"),
  HEDGEHOG("고스미");
  private final String name;

  BbokCharacter(String name) {
    this.name = name;
  }
  public String getName() {
    return name;
  }
}
