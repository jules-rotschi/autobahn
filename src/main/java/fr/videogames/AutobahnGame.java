package fr.videogames;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AutobahnGame extends JPanel implements ActionListener {
  int boardWidth;
  int boardHeight;

  ImageIcon backgroundImageIcon;
  ImageIcon roadImageIcon;
  ImageIcon blueCarImageIcon;
  ImageIcon blueCarBrakingImageIcon;
  ImageIcon orangeCarImageIcon;
  ImageIcon orangeCarBrakingImageIcon;
  ImageIcon greenCarImageIcon;
  ImageIcon greenCarBrakingImageIcon;
  ImageIcon truckImageIcon;
  ImageIcon truckBrakingImageIcon;

  Scene scene;
  Renderer renderer;
  Timer gameLoop;

  GameData data;

  double traficDensity;

  AutobahnGame(int width, int height, double traficDensity) {
    this.boardWidth = width;
    this.boardHeight = height;

    this.traficDensity = traficDensity;

    setPreferredSize(new Dimension(boardWidth, boardHeight));
    setFocusable(true);

    backgroundImageIcon = new ImageIcon(getClass().getResource("/images/background.png"));
    roadImageIcon = new ImageIcon(getClass().getResource("/images/road.png"));
    blueCarImageIcon = new ImageIcon(getClass().getResource("/images/blue-car.png"));
    blueCarBrakingImageIcon = new ImageIcon(getClass().getResource("/images/blue-car-braking.png"));
    orangeCarImageIcon = new ImageIcon(getClass().getResource("/images/orange-car.png"));
    orangeCarBrakingImageIcon = new ImageIcon(getClass().getResource("/images/orange-car-braking.png"));
    greenCarImageIcon = new ImageIcon(getClass().getResource("/images/green-car.png"));
    greenCarBrakingImageIcon = new ImageIcon(getClass().getResource("/images/green-car-braking.png"));
    truckImageIcon = new ImageIcon(getClass().getResource("/images/truck.png"));
    truckBrakingImageIcon = new ImageIcon(getClass().getResource("/images/truck-braking.png"));

    gameLoop = new Timer(1000 / 50, this);
    initializeGame();
  }

  public void initializeGame() {
    scene = new Scene(0.5);
    data = new GameData(scene);
    gameLoop.start();
    renderer =
      new Renderer(
        scene,
        boardWidth,
        boardHeight,
        backgroundImageIcon.getImage(),
        roadImageIcon.getImage(),
        blueCarImageIcon.getImage(),
        blueCarBrakingImageIcon.getImage(),
        orangeCarImageIcon.getImage(),
        orangeCarBrakingImageIcon.getImage(),
        greenCarImageIcon.getImage(),
        greenCarBrakingImageIcon.getImage(),
        truckImageIcon.getImage(),
        truckBrakingImageIcon.getImage()
      );
    GameControl gameControl = new GameControl(this);
    UserVehiculeControl userVehiculeControl = new UserVehiculeControl(scene.userVehicule);
    addKeyListener(gameControl);
    addKeyListener(userVehiculeControl);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    renderer.render(g, data);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    data.update();
    try {
      scene.update();
    } catch (Exception exception) {}
    repaint();
    if (data.gameOver || data.timer.isOut()) {
      gameLoop.stop();
    }
  }
}