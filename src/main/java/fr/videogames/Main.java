package fr.videogames;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    final int BOARD_WIDTH = 1440;
    final int BOARD_HEIGHT = 720;

    final JFrame frame = new JFrame("Autobahn");
    frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final AutobahnGame autobahn = new AutobahnGame(BOARD_WIDTH, BOARD_HEIGHT, Double.valueOf(args[0]));
    frame.add(autobahn);
    frame.pack();
    autobahn.requestFocus();
    frame.setVisible(true);
  }
}