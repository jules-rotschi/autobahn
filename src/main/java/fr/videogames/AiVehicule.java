package fr.videogames;

public class AiVehicule extends Vehicule {

  boolean passed;

  AiVehicule(VehiculeType type, int x, int y, int width, int height, double speed, int brakingForce) {
    super(type, x, y, width, height, speed, brakingForce);
    passed = false;
  }
}
