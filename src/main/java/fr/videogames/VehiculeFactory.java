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
    Vehicule userCar = new Vehicule(VehiculeType.CAR, CarColor.BLUE, 0, y, CAR_WIDTH, CAR_HEIGHT, speed, CAR_BRAKING_FORCE);
    userCar.setLane(laneName, road);
    return userCar;
  }

  AiVehicule createAiCar(CarColor color, LaneName laneName, int y, double speed) {
    AiVehicule vehicule = new AiVehicule(VehiculeType.CAR, color, 0, y, CAR_WIDTH, CAR_HEIGHT, speed, CAR_BRAKING_FORCE);
    vehicule.setLane(laneName, road);
    return vehicule;
  }

  AiVehicule createAiTruck(LaneName laneName, int y, double speed) {
    AiVehicule vehicule = new AiVehicule(VehiculeType.TRUCK, CarColor.DEFAULT, 0, y, TRUCK_WIDTH, TRUCK_HEIGHT, speed, TRUCK_BRAKING_FORCE);
    vehicule.setLane(laneName, road);
    return vehicule;
  }

  AiVehicule createRandomAiVehicule(LaneName laneName, int y) {
    double randomForTruck = Math.random();
    
    if (randomForTruck >= TRUCK_PROBABILITY) {
      int randomCarColor = Long.valueOf(Math.round(Math.random() * 2)).intValue();
      CarColor color;
      switch (randomCarColor) {
        case 0 :
          color = CarColor.BLUE;
          break;
        case 1 :
          color = CarColor.ORANGE;
          break;
        case 2 :
          color = CarColor.GREEN;
          break;
        default :
          color = CarColor.BLUE;
      }
      return createAiCar(color, laneName, y, generateRandomSpeed(CAR_MIN_SPEED, CAR_MAX_SPEED));
    } else {
      return createAiTruck(laneName, y, generateRandomSpeed(TRUCK_MIN_SPEED, TRUCK_MAX_SPEED));
    }
  }
}
