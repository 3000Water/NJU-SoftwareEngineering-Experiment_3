package weather.display;

public interface Displayable {

  void setPosition(int x, int y);

  void setVisible(boolean flag);

  void setSize(int weight, int height);

  int getX();

  int getY();

  int getWidth();

  int getHeight();
}
