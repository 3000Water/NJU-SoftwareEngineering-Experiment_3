package weather.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DisplayTextField extends JTextField implements Displayable {

  private int x;
  private int y;
  private int width;
  private int height;

  public DisplayTextField(String fieldName, int fontSize, int _x, int _y, int width) {
    super(width);
    setToolTipText(String.format("请输入%s", fieldName));
    Font font = new Font("微软雅黑", Font.BOLD, fontSize);
    setFont(font);
    setForeground(new Color(85, 85, 85));
    setOpaque(false);
    setBorder(null);

    FontMetrics metrics = new JLabel().getFontMetrics(font);
    this.height = metrics.getHeight();
    this.width = width;

    x = DisplayMethods.scaleLength(_x);
    y = DisplayMethods.scaleLength(_y);

    setBounds(x, y, width, height);
  }

  @Override
  public void setPosition(int x, int y) {
    setBounds(x, y, width, height);
  }
}
