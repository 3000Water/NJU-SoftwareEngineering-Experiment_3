package weather.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JLabel;

public class DisplayTextLabel extends JLabel implements Displayable {

  private final String text;
  private int x;
  private int y;
  private int width;
  private int height;

  public DisplayTextLabel(String msg, int fontSize, int _x, int _y, TextPosition position) {
    super(msg);
    text = msg;
    Font font = new Font("微软雅黑", Font.BOLD, fontSize);
    setFont(font);
    setForeground(new Color(85, 85, 85));

    FontMetrics metrics = new JLabel().getFontMetrics(font);
    width = metrics.stringWidth(msg) + 10;
    height = metrics.getHeight();

    x = DisplayMethods.scaleLength(_x);
    y = DisplayMethods.scaleLength(_y);

    if (position == TextPosition.Horizontal || position == TextPosition.Whole) {
      x = (_x - width) / 2;
      System.out.println("Middle start x: " + x);
    }

    if (position == TextPosition.Vertical || position == TextPosition.Whole) {
      y = (y - height) / 2;
    }
    System.out.println("[<<Generating text label: " + "'" + msg + "'>>]");
    System.out.println(width + ", " + height);

    setBounds(x, y, width, height);
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

  public enum TextPosition {
    None(0),
    Vertical(1),
    Horizontal(2),
    Whole(3);

    private final int type;

    TextPosition(int i) {
      this.type = i;
    }
  }
}
