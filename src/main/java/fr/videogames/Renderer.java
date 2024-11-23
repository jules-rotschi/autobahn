package fr.videogames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import fr.videogames.utils.StringUtils;

public class Renderer {
  Scene scene;

  int boardWidth;
  int boardHeight;

  Image backgroundImage;
  Image roadImage;
  Image userCarImage;
  Image userCarBrakingImage;
  Image randomCarImage;
  Image randomCarBrakingImage;
  Image randomTruckImage;
  Image randomTruckBrakingImage;

  StringUtils stringUtils;

  Renderer(
    Scene scene,
    int width,
    int height,
    Image backgroundImage,
    Image roadImage,
    Image userCarImage,
    Image userCarBrakingImage,
    Image randomCarImage,
    Image randomCarBrakingImage,
    Image randomTruckImage,
    Image randomTruckBrakingImage
  ) {
    this.scene = scene;
    boardWidth = width;
    boardHeight = height;
    this.backgroundImage = backgroundImage;
    this.roadImage = roadImage;
    this.userCarImage = userCarImage;
    this.userCarBrakingImage = userCarBrakingImage;
    this.randomCarImage = randomCarImage;
    this.randomCarBrakingImage = randomCarBrakingImage;
    this.randomTruckImage = randomTruckImage;
    this.randomTruckBrakingImage = randomTruckBrakingImage;

    stringUtils = new StringUtils();
  }

  public void render(Graphics g, GameData data) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("Figtree", Font.PLAIN, 32));

    g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
    
    for (int i = 0; i < scene.road.portions.size(); i++) {
      RoadPortion roadPortion = scene.road.portions.get(i);
      g.drawImage(roadImage, roadPortion.x, roadPortion.y, Road.WIDTH, RoadPortion.HEIGHT, null);
    }

    for (int i = 0; i < scene.vehicules.size(); i++) {
      Vehicule vehicule = scene.vehicules.get(i);
      if (vehicule.type == VehiculeType.CAR) {
        if (vehicule.braking) {
          g.drawImage(randomCarBrakingImage, vehicule.x, vehicule.y, vehicule.width, vehicule.height, null);
        } else {
          g.drawImage(randomCarImage, vehicule.x, vehicule.y, vehicule.width, vehicule.height, null);
        }
      }
      if (vehicule.type == VehiculeType.TRUCK) {
        if (vehicule.braking) {
          g.drawImage(randomTruckBrakingImage, vehicule.x, vehicule.y, vehicule.width, vehicule.height, null);
        } else {
          g.drawImage(randomTruckImage, vehicule.x, vehicule.y, vehicule.width, vehicule.height, null);
        }
      }
    }

    if (scene.userVehicule.braking) {
      g.drawImage(userCarBrakingImage, scene.userVehicule.x, scene.userVehicule.y, scene.userVehicule.width, scene.userVehicule.height, null);
    } else {
      g.drawImage(userCarImage, scene.userVehicule.x, scene.userVehicule.y, scene.userVehicule.width, scene.userVehicule.height, null);
    }

    String speedString = Double.valueOf(scene.userVehicule.speed).intValue() + " km/h";
    g.drawString(speedString, stringUtils.getStickToRightX(g, speedString, boardWidth) - 10, stringUtils.getStickToBottomY(g, boardHeight) + 25);

    String scoreString = "Score : " + data.score;

    if (data.gameOver || data.timer.isOut()) {
      g.setFont(new Font("Figtree", Font.BOLD, 64));
      g.setColor(new Color(0, 0, 0, 150));
      g.fillRect(0, 0, boardWidth, boardHeight);;
      g.setColor(Color.WHITE);
      g.drawString(scoreString, stringUtils.getCenteredX(g, scoreString, boardWidth), stringUtils.getCenteredY(g, boardHeight) + 48);
    }

    if (data.gameOver) {
      String crashString = "Crash !";
      g.drawString(crashString, stringUtils.getCenteredX(g, crashString, boardWidth), stringUtils.getCenteredY(g, boardHeight) - 48);
    } else if (data.timer.isOut()) {
      String timeOutString = "Temps écoulé !";
      g.drawString(timeOutString, stringUtils.getCenteredX(g, timeOutString, boardWidth), stringUtils.getCenteredY(g, boardHeight) - 48);
    } else {
      g.drawString(scoreString, 10, 35);
      String remainingTimeString = String.valueOf(data.timer.getSecondsLeft());
      g.drawString(remainingTimeString, stringUtils.getStickToRightX(g, remainingTimeString, boardWidth) - 10, 35);
    }
  }
  
}
