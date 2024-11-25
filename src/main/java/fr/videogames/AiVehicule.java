package fr.videogames;

public class AiVehicule extends Vehicule {

  boolean passed;

  AiVehicule(VehiculeType type, CarColor color, int x, int y, int width, int height, double speed, int brakingForce) {
    super(type, color, x, y, width, height, speed, brakingForce);
    passed = false;
  }
}
