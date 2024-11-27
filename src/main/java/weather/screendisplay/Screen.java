/**
 * 界面基类
 *
 * @author 3000_Water
 * @version 1.0
 */
package weather.screendisplay;

import static weather.application.WeatherAPP.windowHeight;
import static weather.application.WeatherAPP.windowWidth;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import weather.application.ScreenManager;
import weather.display.DisplayButton;
import weather.display.DisplayImageLabel;
import weather.display.DisplayMethods;
import weather.display.DisplayTextField;
import weather.display.DisplayTextLabel;
import weather.display.DisplayTextLabel.TextPosition;
import weather.display.Displayable;

public class Screen extends JPanel {

  ScreenManager screenManager;
  private BufferedImage backgroundImage;
  private DisplaySection[] disSections;

  /**
   * @param screenManager 界面管理器
   */
  public Screen(ScreenManager screenManager) {
    this.screenManager = screenManager;
    initBackground();
    setBounds(0, 0, windowWidth, windowHeight);
    setLayout(null);
  }

  /**
   * @return 获取界面类型名称
   */
  public String getScreenClass() {
    String[] classPart = this.getClass().getName().split("\\.");
    return classPart[classPart.length - 1];
  }

  /** 初始化背景 */
  public void initBackground() {
    try {
      System.out.println(
          "BackGround Initiating: "
              + System.getProperty("user.dir")
              + "\\src\\main\\resources\\BackGround\\"
              + "default"
              + ".png");
      backgroundImage =
          ImageIO.read(
              new File(
                  System.getProperty("user.dir")
                      + "\\src\\main\\resources\\BackGround\\"
                      + "default"
                      + ".png"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * @param x x坐标
   * @param y y坐标
   * @param width 宽度
   * @param height 高度
   * @return 新建组件区
   */
  public DisplaySection addSection(int x, int y, int width, int height) {
    DisplaySection section = new DisplaySection(x, y, width, height);
    add(section);
    return section;
  }

  /**
   * @param text 按钮标签
   * @param buttonName 按钮名称
   * @param x x坐标
   * @param y y坐标
   * @param action 点击行为
   * @return 新建按钮
   */
  public DisplayButton addButton(
      String text, String buttonName, int x, int y, ActionListener action) {
    Displayable button =
        new DisplayButton(null, getScreenClass(), text, 36, buttonName, x, y, action);
    add((JButton) button);
    return (DisplayButton) button;
  }

  /**
   * @param text 按钮标签
   * @param fontSize 字体大小
   * @param buttonName 按钮名称
   * @param x x坐标
   * @param y y坐标
   * @param action 点击行为
   * @return 新建按钮
   */
  public DisplayButton addButton(
      String text, int fontSize, String buttonName, int x, int y, ActionListener action) {
    Displayable button =
        new DisplayButton(null, getScreenClass(), text, fontSize, buttonName, x, y, action);
    add((JButton) button);
    return (DisplayButton) button;
  }

  /**
   * @param text 图标标签
   * @param labelName 图标名称
   * @param levelOrType 显示层级或类型
   * @param fontSize 字体大小
   * @param x x坐标
   * @param y y坐标
   */
  public void addImageLabel(
      String text, String labelName, String levelOrType, int fontSize, int x, int y) {
    Displayable imageLabel =
        new DisplayImageLabel(getScreenClass(), text, labelName, levelOrType, fontSize, x, y);
    add((JLabel) imageLabel);
  }

  /**
   * @param text 文本内容
   * @param fontSize 字体大小
   * @param x x坐标
   * @param y y坐标
   * @param position 排布方式
   */
  public void addTextLabel(String text, int fontSize, int x, int y, TextPosition position) {
    Displayable textLabel = new DisplayTextLabel(text, fontSize, x, y, position);
    add((JLabel) textLabel);
  }

  /**
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // 如果背景图片存在，则绘制它
    if (backgroundImage != null) {
      // 图片按面板大小缩放并绘制
      g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
  }

  public class DisplaySection extends JPanel {

    Displayable[] displayable;

    /**
     * @param x x坐标
     * @param y y坐标
     * @param width 宽度
     * @param height 高度
     */
    DisplaySection(int x, int y, int width, int height) {
      super();
      this.setOpaque(false);
      setLayout(null);

      int scaleX = DisplayMethods.scaleLength(x);
      int scaleY = DisplayMethods.scaleLength(y);
      int scaleWidth = DisplayMethods.scaleLength(width);
      int scaleHeight = DisplayMethods.scaleLength(height);

      setBounds(scaleX, scaleY, scaleWidth, scaleHeight);
    }

    /**
     * @param x x坐标
     * @param y y坐标
     * @param width 宽度
     * @param height 高度
     * @return 新建组件区
     */
    public DisplaySection addSection(int x, int y, int width, int height) {
      DisplaySection section = new DisplaySection(x, y, width, height);
      add(section);
      return section;
    }

    /**
     * @param text 按钮标签
     * @param buttonName 按钮名称
     * @param x x坐标
     * @param y y坐标
     * @param action 点击行为
     * @return 新建按钮
     */
    public DisplayButton addButton(
        String text, String buttonName, int x, int y, ActionListener action) {
      Displayable button =
          new DisplayButton(this, getScreenClass(), text, 36, buttonName, x, y, action);
      add((JButton) button);
      return (DisplayButton) button;
    }

    /**
     * @param text 按钮标签
     * @param fontSize 字体大小
     * @param buttonName 按钮名称
     * @param x x坐标
     * @param y y坐标
     * @param action 点击行为
     * @return 新建按钮
     */
    public DisplayButton addButton(
        String text, int fontSize, String buttonName, int x, int y, ActionListener action) {
      Displayable button =
          new DisplayButton(this, getScreenClass(), text, fontSize, buttonName, x, y, action);
      add((JButton) button);
      return (DisplayButton) button;
    }

    /**
     * @param text 图标标签
     * @param labelName 图标名称
     * @param levelOrType 显示层级或类型
     * @param fontSize 字体大小
     * @param x x坐标
     * @param y y坐标
     */
    public void addImageLabel(
        String text, String labelName, String levelOrType, int fontSize, int x, int y) {
      Displayable imageLabel =
          new DisplayImageLabel(getScreenClass(), text, labelName, levelOrType, fontSize, x, y);
      add((JLabel) imageLabel);
    }

    /**
     * @param labelName 图标名称
     * @param levelOrType 显示层级或类型
     * @param x x坐标
     * @param y y坐标
     */
    public void addImageLabel(String labelName, String levelOrType, int x, int y) {
      Displayable imageLabel =
          new DisplayImageLabel(getScreenClass(), "", labelName, levelOrType, 0, x, y);
      add((JLabel) imageLabel);
    }

    /**
     * @param text 文本内容
     * @param fontSize 字体大小
     * @param x x坐标
     * @param y y坐标
     * @param position 排布方式
     */
    public void addTextLabel(String text, int fontSize, int x, int y, TextPosition position) {
      Displayable textLabel = new DisplayTextLabel(text, fontSize, x, y, position);
      add((JLabel) textLabel);
    }

    /**
     * @param width 宽度
     * @param fieldName 填充内容
     * @param fontSize 字体大小
     * @param x x坐标
     * @param y y坐标
     * @return 新建填充区
     */
    public DisplayTextField addTextField(int width, String fieldName, int fontSize, int x, int y) {
      Displayable textField = new DisplayTextField(fieldName, fontSize, x, y, width);
      add((JTextField) textField);
      return (DisplayTextField) textField;
    }
  }
}
