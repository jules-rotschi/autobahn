package fr.videogames.utils;

import java.awt.Graphics;
import java.awt.FontMetrics;

public class StringUtils {

  public int getCenteredX(Graphics g, String string, int rectangleWidth) {
    FontMetrics metrics = g.getFontMetrics();
    return (rectangleWidth - metrics.stringWidth(string)) / 2;
  }

  public int getCenteredY(Graphics g, int rectangleHeight) {
    FontMetrics metrics = g.getFontMetrics();
    return (rectangleHeight - metrics.getHeight()) / 2 + metrics.getAscent();
  }

  public int getStickToRightX(Graphics g, String string, int rectangleWidth) {
    FontMetrics metrics = g.getFontMetrics();
    return rectangleWidth - metrics.stringWidth(string);
  }

  public int getStickToBottomY(Graphics g, int rectangleHeight) {
    FontMetrics metrics = g.getFontMetrics();
    return rectangleHeight - metrics.getHeight();
  }
}
