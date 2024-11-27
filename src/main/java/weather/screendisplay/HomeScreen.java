package weather.screendisplay;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import weather.application.ScreenManager;
import weather.application.WeatherAPP;
import weather.data.singledata.Location;
import weather.data.singledata.Time;
import weather.data.singledata.WeatherData;
import weather.display.DisplayTextLabel;
import weather.notify.AlertNotification;
import weather.notify.Notification;

public class HomeScreen extends Screen {

  public HomeScreen(
      ScreenManager screenManager,
      Location currentLocation,
      List<WeatherData> weatherDataList,
      List<Notification> notificationList) {
    super(screenManager);
    String[] classPart = this.getClass().getName().split("\\.");
    String screenClass = classPart[classPart.length - 1];

    /*
    按钮部分
     */
    addButton("主题", "Theme", 8, 67, e -> JOptionPane.showMessageDialog(WeatherAPP.window, "Todo"));
    addButton("反馈", "Feedback", 225, 46, e -> screenManager.feedbackAction());
    addButton("登出", "Logout", 225, 179, e -> screenManager.logoutAction());
    addButton("切换地区", "LocationSwitch", 1041, 52, e -> screenManager.switchToLocationScreen());
    addButton("查看详细", "CheckDetail", 1041, 185, e -> screenManager.switchToTodayScreen());

    /*
    地区和日期信息
     */
    Time currentTime = new Time();
    DisplaySection locationDateSection = addSection(419, 50, 590, 252);
    locationDateSection.addTextLabel(
        String.format(
            "%d月%d日 %d点", currentTime.getMonth(), currentTime.getDay(), currentTime.getHour()),
        36,
        locationDateSection.getWidth(),
        152,
        DisplayTextLabel.TextPosition.Horizontal);
    locationDateSection.addImageLabel(currentLocation.getCity(), "LocationDate", "1", 75, 0, 0);
    locationDateSection.addImageLabel("LocationDate", "2", 26, 7);

    /*
    天气列表信息
     */
    int date = LocalDate.now().getDayOfMonth();
    DisplaySection weatherNotification = addSection(75, 315, 1284, 6410);
    for (int i = 0; i != WeatherAPP.numOfDisplayWeatherDay; i++) {
      weatherNotification.addImageLabel(
          "WeatherData", weatherDataList.get(i).getConditionName(), 17 + i * 212, 40);
      DisplaySection eachWeatherInfo = weatherNotification.addSection(17 + i * 212, 240, 190, 148);
      eachWeatherInfo.addTextLabel(
          weatherDataList.get(i).getTemperature(),
          36,
          eachWeatherInfo.getWidth(),
          0,
          DisplayTextLabel.TextPosition.Horizontal);
      eachWeatherInfo.addTextLabel(
          String.valueOf(date - 1 + i) + "日",
          36,
          eachWeatherInfo.getWidth(),
          69,
          DisplayTextLabel.TextPosition.Horizontal);
    }

    /*
    通知信息
     */
    Random random = new Random();
    int lastRandomNumber;
    DisplaySection notification1 = weatherNotification.addSection(88, 404, 1130, 110);
    lastRandomNumber = random.nextInt(notificationList.size());
    Notification notifyInfo1 = notificationList.get(lastRandomNumber);
    if (notifyInfo1 instanceof AlertNotification) {
      notification1.addImageLabel("Notification", "alert", 0, 0);
      notification1.addTextLabel(
          notifyInfo1.getMessage(), 36, 111, 20, DisplayTextLabel.TextPosition.None);
    } else {
      notification1.addImageLabel("Notification", "notification", 0, 0);
      notification1.addTextLabel(
          notifyInfo1.getMessage(), 36, 111, 20, DisplayTextLabel.TextPosition.None);
    }
    if (notificationList.size() > 1) {
      DisplaySection notification2 = weatherNotification.addSection(88, 518, 1130, 110);
      int newRandom = random.nextInt(notificationList.size());
      while (newRandom == lastRandomNumber) {
        newRandom = random.nextInt(notificationList.size());
      }
      Notification notifyInfo2 = notificationList.get(newRandom);
      if (notifyInfo2 instanceof AlertNotification) {
        notification2.addImageLabel("Notification", "alert", 0, 0);
        notification2.addTextLabel(
            notifyInfo2.getMessage(), 36, 111, 20, DisplayTextLabel.TextPosition.None);
      } else {
        notification2.addImageLabel("Notification", "notification", 0, 0);
        notification2.addTextLabel(
            notifyInfo2.getMessage(), 36, 111, 20, DisplayTextLabel.TextPosition.None);
      }
    }

    weatherNotification.addImageLabel("WeatherNotification", "1", 0, 0);
    weatherNotification.addImageLabel("WeatherNotification", "2", 73, 0);
  }
}
