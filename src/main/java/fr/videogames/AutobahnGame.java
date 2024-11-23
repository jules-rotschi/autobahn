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
  ImageIcon userCarImageIcon;
  ImageIcon userCarBrakingImageIcon;
  ImageIcon randomCarImageIcon;
  ImageIcon randomCarBrakingImageIcon;
  ImageIcon randomTruckImageIcon;
  ImageIcon randomTruckBrakingImageIcon;

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
    userCarImageIcon = new ImageIcon(getClass().getResource("/images/user-car.png"));
    userCarBrakingImageIcon = new ImageIcon(getClass().getResource("/images/user-car-braking.png"));
    randomCarImageIcon = new ImageIcon(getClass().getResource("/images/random-car.png"));
    randomCarBrakingImageIcon = new ImageIcon(getClass().getResource("/images/random-car-braking.png"));
    randomTruckImageIcon = new ImageIcon(getClass().getResource("/images/random-truck.png"));
    randomTruckBrakingImageIcon = new ImageIcon(getClass().getResource("/images/random-truck-braking.png"));

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
        userCarImageIcon.getImage(),
        userCarBrakingImageIcon.getImage(),
        randomCarImageIcon.getImage(),
        randomCarBrakingImageIcon.getImage(),
        randomTruckImageIcon.getImage(),
        randomTruckBrakingImageIcon.getImage()
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