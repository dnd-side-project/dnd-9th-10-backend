package com.dnd.bbok.diary.domain;

/**
 * (순서대로)똥, 고구마, 하트, 별, 반창고, 엥, ㅋ, 무지개
 */
public enum Sticker {

  POOP("poop.png"),
  SWEETPOTATO("sweetPotato.png"),
  HEART("heart.png"),
  STAR("star.png"),
  BAND("band.png"),
  AENG("aeng.png"),
  KK("kk.png"),
  RAINBOW("rainbow.png");

  private final String iconFile;

  Sticker(String iconFile) {
    this.iconFile = iconFile;
  }

  public String getIconFile() {
    return iconFile;
  }
}
