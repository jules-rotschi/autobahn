package fr.videogames;

public class VehiculeFactory {

  private final int CAR_WIDTH = 80;
  private final int CAR_HEIGHT = 125;
  private final int CAR_MIN_SPEED = 90;
  private final int CAR_MAX_SPEED = 130;
  private final int CAR_BRAKING_FORCE = 4;

  private final int TRUCK_WIDTH = 100;
  private final int TRUCK_HEIGHT = 200;
  private final int TRUCK_MIN_SPEED = 90;
  private final int TRUCK_MAX_SPEED = 90;
  private final int TRUCK_BRAKING_FORCE = 2;

  private final double TRUCK_PROBABILITY = 0.2;

  Road road;

  VehiculeFactory(Road road) {
    this.road = road;
  }

  private double generateRandomSpeed(double minSpeed, double maxSpeed) {
    return Math.random() * (maxSpeed - minSpeed) + minSpeed;
  } 

  Vehicule createUserCar(LaneName laneName, int y, double speed) {
    Vehicule userCar = new Vehicule(VehiculeType.CAR, 0, y, CAR_WIDTH, CAR_HEIGHT, speed, CAR_BRAKING_FORCE);
    userCar.setLane(laneName, road);
    return userCar;
  }

  AiVehicule createAiCar(LaneName laneName, int y, double speed) {
    AiVehicule vehicule = new AiVehicule(VehiculeType.CAR, 0, y, CAR_WIDTH, CAR_HEIGHT, speed, CAR_BRAKING_FORCE);
    vehicule.setLane(laneName, road);
    return vehicule;
  }

  AiVehicule createAiTruck(LaneName laneName, int y, double speed) {
    AiVehicule vehicule = new AiVehicule(VehiculeType.TRUCK, 0, y, TRUCK_WIDTH, TRUCK_HEIGHT, speed, TRUCK_BRAKING_FORCE);
    vehicule.setLane(laneName, road);
    return vehicule;
  }

  AiVehicule createRandomAiVehicule(LaneName laneName, int y) {
    double random = Math.random();

    if (random >= TRUCK_PROBABILITY) {
      return createAiCar(laneName, y, generateRandomSpeed(CAR_MIN_SPEED, CAR_MAX_SPEED));
    } else {
      return createAiTruck(laneName, y, generateRandomSpeed(TRUCK_MIN_SPEED, TRUCK_MAX_SPEED));
    }
  }
}
