package weather.display;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DisplayMethods {

  public static BufferedImage scaleImage(
      String screenClass, String componentClass, String componentName, String type) {
    String srcImagePath;
    String scaleImagePath;
    String componentPath =
        System.getProperty("user.dir")
            + "\\src\\main\\resources\\"
            + screenClass
            + "\\"
            + componentClass
            + "\\"
            + componentName
            + "\\";

    srcImagePath = componentPath + type + "-size.png";
    scaleImagePath = componentPath + type + "-scale.png";

    System.out.println("(" + type + "): " + srcImagePath);
    System.out.println("(" + type + "): " + scaleImagePath);

    BufferedImage srcImage;
    BufferedImage scaleImage;
    try {
      srcImage = ImageIO.read(new File(srcImagePath));
    } catch (IOException e) {
      throw new RuntimeException();
    }
    try {
      scaleImage = ImageIO.read(new File(scaleImagePath));
    } catch (IOException e) {
      throw new RuntimeException();
    }

    int targetWidth = scaleLength(srcImage.getWidth());
    int targetHeight = scaleLength(srcImage.getHeight());

    BufferedImage scaledImage =
        new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = scaledImage.createGraphics();

    // 设置高质量渲染
    g2d.setRenderingHint(
        RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // 缩放绘制
    g2d.drawImage(scaleImage, 0, 0, targetWidth, targetHeight, null);
    g2d.dispose();

    return scaledImage;
  }

  public static int scaleLength(int srcLen) {
    return srcLen * 32 / 45;
  }
}
