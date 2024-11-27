package weather.application;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import weather.application.control.FeedbackController;
import weather.application.control.LocationController;
import weather.application.control.NotificationController;
import weather.application.control.WeatherServiceController;
import weather.data.database.UserDatabase;
import weather.data.singledata.Location;
import weather.data.singledata.Time;

public class WeatherAPP {

  public static final int windowWidth = 1024;
  public static final int windowHeight = 768;

  public static final int numOfDisplayWeatherDay = 6;
  public static final int numOfNotifyWeatherHours = 8;

  public static final JFrame window = new JFrame("Weather Application");
  public static User user;
  public static ScreenManager screenManager;
  protected static UserDatabase userDatabase = new UserDatabase();
  protected static Location currentLocation;
  protected static Time currentTime;
  protected static WeatherServiceController weatherServiceController;
  protected static NotificationController notificationController;
  protected static LocationController locationController;
  protected static FeedbackController feedbackController;
  protected ThemeManager themeManager;

  public static void main(String[] args) {
    window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    System.setProperty("sun.java2d.uiScale", "1.0");
    window.setSize(windowWidth, windowHeight);
    window.setLayout(null);

    window.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            // 调用保存数据方法
            exitUpdateUserInfo();
            System.exit(0);
          }
        });

    try {
      window.setIconImage(
          ImageIO.read(
              new File(System.getProperty("user.dir") + "\\src\\main\\resources\\icon.png")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    initData();

    screenManager = new ScreenManager(window);

    window.setVisible(true);
  }

  public static void initData() {
    locationController = new LocationController();
    weatherServiceController = new WeatherServiceController(locationController);
    feedbackController = new FeedbackController();
    currentTime = new Time(2024, 11, 23, 5);
  }

  public static void initFromUser() {
    currentLocation = user.getCurrentLocation();
    notificationController =
        new NotificationController(
            weatherServiceController.getOneWeather(currentLocation, currentTime),
            weatherServiceController.getHourWeatherByGivenNum(
                currentLocation, currentTime, numOfNotifyWeatherHours),
            weatherServiceController.getDayWeatherByGivenNum(
                currentLocation, currentTime, numOfDisplayWeatherDay));
    notificationController.modifyNotificationListByWeatherData();
  }

  public static void exitUpdateUserInfo() {
    System.out.println("[Saving Datas]");
    if (user != null) {
      System.out.println("User: " + user.getAccount());
      userDatabase.updateData(user);
      user.showPreferLocations();
      userDatabase.saveIntoFile();
      locationController.saveData();
      feedbackController.saveData();
    }
    System.out.println("[Saving Done]");
  }
}
