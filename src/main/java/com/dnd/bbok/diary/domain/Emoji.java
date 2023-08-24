package com.dnd.bbok.diary.domain;

/**
 * (순서대로) 평온, 웃음, 뭐지, 화남, 언짢, 슬픔
 */
public enum Emoji {

  CALM("calm.png"),
  HAPPY("happy.png"),
  PANIC("panic.png"),
  ANGRY("angry.png"),
  DISPLEASED("displeased.png"),
  SAD("sad.png");

  private final String iconFile;

  Emoji(String iconFile) {
    this.iconFile = iconFile;
  }

  public String getIconFile() {
    return iconFile;
  }
}
