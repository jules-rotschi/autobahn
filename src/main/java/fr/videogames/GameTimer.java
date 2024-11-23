package fr.videogames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameTimer implements ActionListener {
  private int secondsLeft;
  private Timer timer;

  GameTimer(int seconds) {
    secondsLeft = seconds;
    timer = new Timer(1000, this);
  }

  public void start() {
    timer.start();
  }

  public void stop() {
    timer.stop();
  }

  public int getSecondsLeft() {
    return secondsLeft;
  }

  public boolean isOut() {
    return secondsLeft == 0;
  }

  private void decrement() {
    if (!isOut()) {
      secondsLeft -= 1;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    decrement();
  }
}
