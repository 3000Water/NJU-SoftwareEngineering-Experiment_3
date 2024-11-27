package weather.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import weather.screendisplay.Screen.DisplaySection;

public class DisplayButton extends JButton implements Displayable {

  private final ImageIcon defaultIcon;
  private final ImageIcon hoverIcon;
  private final ImageIcon clickIcon;
  private DisplaySection belongingSection;
  private int x;
  private int y;

  public DisplayButton(
      DisplaySection section,
      String screenClass,
      String name,
      int fontSize,
      String buttonName,
      int _x,
      int _y,
      ActionListener actionListener) {
    super(name);
    belongingSection = section;

    setContentAreaFilled(false);
    setFocusPainted(false);
    setBorderPainted(false);
    setFont(new Font("微软雅黑", Font.BOLD, fontSize));
    setForeground(new Color(85, 85, 85));

    System.out.println("[<<Loading icons of button: " + buttonName + ">>]");
    defaultIcon =
        new ImageIcon(DisplayMethods.scaleImage(screenClass, "Button", buttonName, "default"));
    hoverIcon =
        new ImageIcon(DisplayMethods.scaleImage(screenClass, "Button", buttonName, "hover"));
    clickIcon =
        new ImageIcon(DisplayMethods.scaleImage(screenClass, "Button", buttonName, "click"));
    System.out.println("[<<Loading Done>>]");

    setIcon(defaultIcon);
    System.out.println(defaultIcon.getIconWidth() + ", " + defaultIcon.getIconHeight());
    x = DisplayMethods.scaleLength(_x);
    y = DisplayMethods.scaleLength(_y);
    setBounds(x, y, defaultIcon.getIconWidth(), defaultIcon.getIconHeight());

    addActionListener(actionListener);

    setMouseListener();

    setHorizontalTextPosition(SwingConstants.CENTER);
    setVerticalTextPosition(SwingConstants.CENTER);
  }

  private void initButton() {}

  private void setMouseListener() {
    addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseEntered(MouseEvent e) {
            setIcon(hoverIcon); // 鼠标悬停时设置悬停图标
          }

          @Override
          public void mouseExited(MouseEvent e) {
            setIcon(defaultIcon); // 鼠标移出时恢复默认图标
          }

          @Override
          public void mousePressed(MouseEvent e) {
            setIcon(clickIcon);
          }

          @Override
          public void mouseReleased(MouseEvent e) {
            if (getBounds().contains(e.getPoint())) {
              setIcon(hoverIcon); // 鼠标释放时恢复悬停图标
            } else {
              setIcon(defaultIcon); // 如果不在按钮上，恢复默认图标
            }
          }
        });
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
