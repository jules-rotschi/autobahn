package fr.videogames;

import java.util.ArrayList;
import java.util.List;

public class Road {
  final static int WIDTH = 512;
  final static int BARRIER_WIDTH = 20;
  final static int X = 464;

  List<RoadPortion> portions;
  List<Lane> lanes;

  static final int LEFT_LANE_X_POSITION = 500;
  static final int MIDDLE_LEFT_LANE_X_POSITION = 620;
  static final int MIDDLE_RIGHT_LANE_X_POSITION = 740;
  static final int RIGHT_LANE_X_POSITION = 860;

  Road() {
    portions = new ArrayList<RoadPortion>();
    lanes = new ArrayList<Lane>();
    lanes.add(new Lane(LaneName.RIGHT_LANE, 379));
    lanes.add(new Lane(LaneName.MIDDLE_RIGHT_LANE, 261));
    lanes.add(new Lane(LaneName.MIDDLE_LEFT_LANE, 143));
    lanes.add(new Lane(LaneName.LEFT_LANE, 20));
  }

  public boolean hasPastFirstPortion() {
    return portions.get(0).y > RoadPortion.HEIGHT;
  }

  public void extend() {
    int newRoadPortionY = portions.get(0).y - 2 * RoadPortion.HEIGHT;
    portions.remove(0);
    portions.add(new RoadPortion(Road.X, newRoadPortionY));
  }
}
