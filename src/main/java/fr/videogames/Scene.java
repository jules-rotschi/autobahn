package fr.videogames;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Scene {

  static final int SECURITY_DISTANCE = 200;

  double velocityY;

  Road road;
  Vehicule userVehicule;
  List<AiVehicule> vehicules;

  VehiculeFactory vehiculeFactory;

  Image backgroundImage;
  Image roadImage;
  Image userVehiculeImage;
  Image randomVehiculeImage;

  int vehiculesAddingCycle;
  double traficDensity;

  Scene(double traficDensity) {
    this.traficDensity = traficDensity;
    road = new Road();
    road.portions.add(new RoadPortion(Road.X, 0));
    road.portions.add(new RoadPortion(Road.X, -RoadPortion.HEIGHT));
    vehiculeFactory = new VehiculeFactory(road);
    userVehicule = vehiculeFactory.createUserCar(LaneName.RIGHT_LANE, 450, 130);
    vehicules = new ArrayList<AiVehicule>();
    vehicules.add(vehiculeFactory.createAiCar(LaneName.RIGHT_LANE, -250, 90));
    vehiculesAddingCycle = 0;
  }

  private void addVehicule() throws Exception {

    int y = (int)Math.round(Math.random() * -(RoadPortion.HEIGHT - 200) - 200);
    AiVehicule vehicule = vehiculeFactory.createRandomAiVehicule(LaneName.RIGHT_LANE, y);

    boolean sameLine = false;
    boolean tooClose = false;
    boolean validVehiculePosition = true;
    
    List<Integer> triedLanes = new ArrayList<Integer>();
    boolean noMoreLaneToTry = false;

    do {
      int randomLane = (int)(Math.round(Math.random() * 3));
      
      triedLanes.add(randomLane);
      noMoreLaneToTry =
          triedLanes.contains(0) &&
          triedLanes.contains(1) &&
          triedLanes.contains(2) &&
          triedLanes.contains(3);

      switch (randomLane) {
        case 0:
          vehicule.setLane(LaneName.RIGHT_LANE, road);
          break;
        case 1:
          vehicule.setLane(LaneName.MIDDLE_RIGHT_LANE, road);
          break;
        case 2:
          vehicule.setLane(LaneName.MIDDLE_LEFT_LANE, road);
          break;
        case 3:
          vehicule.setLane(LaneName.LEFT_LANE, road);
          break;
      }

      validVehiculePosition = true;

      for (int i = 0; i < vehicules.size(); i++) {
        Vehicule otherVehicule = vehicules.get(i);

        sameLine = otherVehicule.getLane(road) == vehicule.getLane(road);

        int vehiculeBackY = vehicule.y + vehicule.height;
        int otherVehiculeBackY = otherVehicule.y + otherVehicule.height;

        boolean otherVehiculeAhead = otherVehicule.y <= vehicule.y;
        boolean otherVehiculeBehind = otherVehicule.y > vehicule.y;

        boolean tooCloseAhead = otherVehiculeAhead && vehicule.y - otherVehiculeBackY < SECURITY_DISTANCE;
        boolean tooCloseBehind = otherVehiculeBehind && otherVehicule.y - vehiculeBackY < SECURITY_DISTANCE;

        tooClose = (tooCloseAhead) || (tooCloseBehind);

        if (sameLine && tooClose) {
          validVehiculePosition = false;
        }
      }

    } while (!validVehiculePosition && !noMoreLaneToTry);

    if (validVehiculePosition) {
      System.out.println("Adding a vehicule");
      vehicules.add(vehicule);
    }
  }

  public void update() throws Exception {

    velocityY = -userVehicule.getVelocity();

    for (int i = 0; i < road.portions.size(); i++) {
      RoadPortion roadPortion = road.portions.get(i);
      roadPortion.y += Math.round(velocityY);
    }

    if (road.hasPastFirstPortion()) {
      road.extend(  );

      if (vehiculesAddingCycle < Math.round(1 / traficDensity) - 1) {
        vehiculesAddingCycle++;
      } else {
        addVehicule();
        vehiculesAddingCycle = 0;
      }
    }

    for (int i = 0; i < vehicules.size(); i++) {

      Vehicule vehicule = vehicules.get(i);
      vehicule.braking = false;

      for (int j = 0; j < vehicules.size(); j++) {

        Vehicule otherVehicule = vehicules.get(j);

        int otherVehiculeBackY = otherVehicule.y + otherVehicule.height;

        boolean otherVehiculeAhead = vehicule.y > otherVehicule.y;
        boolean sameLine = otherVehicule.getLane(road) == vehicule.getLane(road);
        boolean tooClose = vehicule.y - otherVehiculeBackY < SECURITY_DISTANCE;
        boolean slower = otherVehicule.speed < vehicule.speed;

        if (otherVehiculeAhead && sameLine && tooClose && slower) {
          vehicule.braking = true;
        }
      }

      vehicule.move(velocityY);
    }

    userVehicule.move(velocityY);
  }
}
