package fr.videogames;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserVehiculeControl implements KeyListener {

  Vehicule userVehicule;

  UserVehiculeControl(Vehicule vehicule) {
    userVehicule = vehicule;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      userVehicule.goingToLeft = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      userVehicule.goingToRight = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      userVehicule.accelerating = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      userVehicule.braking = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      userVehicule.goingToLeft = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      userVehicule.goingToRight = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      userVehicule.accelerating = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      userVehicule.braking = false;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {}

}
