package com.dnd.bbok.domain.friend.entity;

public enum BbokCharacter {
  FRONT_CACTUS("CACTUS", "인장이", "front_cactus.png"),
  FRONT_HEDGEHOG("HEDGEHOG", "고스미", "front_hedgehog.png"),
  SIDE_CACTUS("CACTUS", "인장이", "side_cactus.png"),
  SIDE_HEDGEHOG("HEDGEHOG", "고스미", "side_hedgehog.png");

  private final String type;
  private final String name;
  private final String iconFile;

  BbokCharacter(String type, String name, String iconFile) {
    this.type = type;
    this.name = name;
    this.iconFile = iconFile;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getIconFile() {
    return iconFile;
  }

}
