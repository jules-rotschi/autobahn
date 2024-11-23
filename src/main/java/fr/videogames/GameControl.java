package fr.videogames;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControl implements KeyListener {

  AutobahnGame game;

  GameControl(AutobahnGame game) {
    this.game = game;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if ((game.data.gameOver || game.data.timer.isOut()) && e.getKeyCode() == KeyEvent.VK_SPACE) {
      game.initializeGame();
    }
    if (e.getKeyCode() == KeyEvent.VK_P) {
      if (game.data.paused) {
        game.gameLoop.start();
        game.data.timer.start();
        game.data.paused = false;
      } else {
        game.gameLoop.stop();
        game.data.timer.stop();
        game.data.paused = true;
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  @Override
  public void keyTyped(KeyEvent e) {}

}
