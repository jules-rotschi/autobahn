package fr.videogames;

public class Lane {
  final static int WIDTH = 110;
  LaneName name;
  int x;

  Lane(LaneName name, int x) {
    this.name = name;
    this.x = x;
  }
}
