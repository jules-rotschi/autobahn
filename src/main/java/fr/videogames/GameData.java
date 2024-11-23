package fr.videogames;

public class GameData {

  Scene scene;

  boolean gameOver;
  int score;
  GameTimer timer;
  boolean paused;

  GameData(Scene scene) {
    this.scene = scene;
    gameOver = false;
    score = 0;
    timer = new GameTimer(60);
    timer.start();
    paused = false;
  }

  void update() {

    for (int i = 0; i < scene.road.portions.size(); i++) {
      RoadPortion roadPortion = scene.road.portions.get(i);
      if (scene.userVehicule.collision(roadPortion)) {
        gameOver = true;
      }
    }

    for (int i = 0; i < scene.vehicules.size(); i++) {
      AiVehicule vehicule = scene.vehicules.get(i);
      if (!vehicule.passed && vehicule.y > scene.userVehicule.y) {
        score++;
        vehicule.passed = true;
      }
      if (scene.userVehicule.collision(vehicule)) {
        gameOver = true;
      }
    }
  }
}
