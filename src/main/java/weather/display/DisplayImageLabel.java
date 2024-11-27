package weather.display;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DisplayImageLabel extends JLabel implements Displayable {

  private final ImageIcon icon;

  private int x;
  private int y;

  public DisplayImageLabel(
      String screenClass,
      String name,
      String labelName,
      String levelOrType,
      int fontSize,
      int _x,
      int _y) {
    super(name);
    setFont(new Font("微软雅黑", Font.BOLD, fontSize));
    setForeground(new Color(85, 85, 85));

    System.out.println("[<<Loading icon of label: " + labelName + ">>]");
    icon =
        new ImageIcon(DisplayMethods.scaleImage(screenClass, "ImageLabel", labelName, levelOrType));
    System.out.println("[<<Loading Done>>]");

    setIcon(icon);
    x = DisplayMethods.scaleLength(_x);
    y = DisplayMethods.scaleLength(_y);
    setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());

    setHorizontalTextPosition(SwingConstants.CENTER);
    setVerticalTextPosition(SwingConstants.CENTER);
  }

  @Override
  public void setPosition(int x, int y) {
    setBounds(x, y, this.getWidth(), this.getHeight());
  }

  @Override
  public void setVisible(boolean flag) {
    super.setVisible(flag);
  }

  @Override
  public void setSize(int width, int height) {
    this.setBounds(this.getX(), this.getY(), width, height);
  }

  @Override
  public int getX() {
    return super.getX();
  }

  @Override
  public int getY() {
    return super.getY();
  }

  @Override
  public int getWidth() {
    return super.getWidth();
  }

  @Override
  public int getHeight() {
    return super.getHeight();
  }
}
