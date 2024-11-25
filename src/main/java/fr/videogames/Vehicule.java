package fr.videogames;

public class Vehicule {
  
  final static int MIN_SPEED = 0;
  final static int MAX_SPEED = 300;
  
  final static int VELOCITY_FACTOR = 520 / 125;
  
  VehiculeType type;
  int x;
  int y;
  int width = 80;
  int height = 125;
  double speed;
  int brakingForce;

  boolean goingToLeft;
  boolean goingToRight;
  boolean accelerating;
  boolean braking;

  CarColor color;

  Vehicule(VehiculeType type, CarColor color, int x, int y, int width, int height, double speed, int brakingForce) {
    this.type = type;
    this.color = color;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.speed = speed;
    this.brakingForce = brakingForce;
    goingToLeft = false;
    goingToRight = false;
    accelerating = false;
    braking = false;
  }

  private void goToLeft() {
    x -= speed / 20;

  }

  private void goToRight() {
    x += speed / 20;
  }

  private void accelerate() {
    if (speed < 150) {
      speed++;
    } else {
      speed += -(1.0 / 150.0) * (speed - 150) + 1;
    }
    speed = Math.min(speed, MAX_SPEED);
  }

  private void brake() {
    speed = Math.max(speed - brakingForce, MIN_SPEED);
  }

  LaneName getLane(Road road) throws Exception {
    for (int i = 0; i < road.lanes.size(); i++) {
      Lane lane = road.lanes.get(i);
      if (x > Road.X + lane.x && x < Road.X + lane.x + Lane.WIDTH) {
        return lane.name;
      }
    }
    throw new Exception("Vehicule is not on a lane");
  }

  void setLane(LaneName laneName, Road road) {
    for (int i = 0; i < road.lanes.size(); i++) {
      Lane lane = road.lanes.get(i);
      if (laneName == lane.name) {
        x = Road.X + lane.x + Lane.WIDTH / 2 - width / 2;
      }
    }
  }

  double getVelocity() {
    return speed / -VELOCITY_FACTOR;
  }

  void move(double sceneVelocityY) {

    y += Math.round(getVelocity() + sceneVelocityY);

    if (goingToLeft) {
      goToLeft();
    }

    if (goingToRight) {
      goToRight();
    }

    if (accelerating) {
      accelerate();
    }

    if (braking) {
      brake();
    }
  }

  boolean collision(Vehicule vehicule) {
    return x + width > vehicule.x
        && x < vehicule.x + vehicule.width
        && y + height > vehicule.y
        && y < vehicule.y + vehicule.height;
  }

  boolean collision(RoadPortion roadPortion) {
    boolean leftCollision = x < roadPortion.x + Road.BARRIER_WIDTH;
    boolean rightCollision = x + width > roadPortion.x + Road.WIDTH - Road.BARRIER_WIDTH;
    return leftCollision || rightCollision;
  }
}
