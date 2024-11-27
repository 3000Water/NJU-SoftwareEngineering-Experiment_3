/**
 * 详细信息界面
 *
 * @author 3000_Water
 * @version 1.0
 */
package weather.screendisplay;

import java.util.List;
import weather.application.ScreenManager;
import weather.data.singledata.Location;
import weather.data.singledata.Time;
import weather.data.singledata.WeatherData;
import weather.display.DisplayTextLabel;
import weather.notify.AlertNotification;
import weather.notify.Notification;

public class TodayScreen extends Screen {

  public TodayScreen(
      ScreenManager screenManager,
      Location currentLocation,
      WeatherData currentWeather,
      List<Notification> notificationList) {
    super(screenManager);
    Time currentTime = new Time();

    addButton("返回", "Back", 28, 33, e -> screenManager.switchToMainScreen());

    /*
    Weather Info
    */
    DisplaySection weather = addSection(226, 33, 988, 479);

    DisplaySection locationTime = weather.addSection(234, 35, 550, 99);
    locationTime.addTextLabel(
        currentLocation.getCity(), 48, 0, 0, DisplayTextLabel.TextPosition.None);
    locationTime.addTextLabel(
        String.format(
            "%d月%d日%d时", currentTime.getMonth(), currentTime.getDay(), currentTime.getHour()),
        36,
        186,
        11,
        DisplayTextLabel.TextPosition.None);

    DisplaySection temperatureWind = weather.addSection(24, 262, 317, 194);
    temperatureWind.addTextLabel(
        String.format("温度: %s", currentWeather.getTemperature()),
        24,
        24,
        31,
        DisplayTextLabel.TextPosition.None);
    temperatureWind.addTextLabel(
        String.format("状况: %s", currentWeather.getCondition().toString()),
        24,
        24,
        104,
        DisplayTextLabel.TextPosition.None);
    temperatureWind.addImageLabel("Weather", "1", 0, 0);

    DisplaySection precipitation = weather.addSection(647, 262, 317, 194);
    precipitation.addTextLabel(
        String.format("降水概率: %s", currentWeather.getProbabilityPrecipitation()),
        24,
        24,
        31,
        DisplayTextLabel.TextPosition.None);
    precipitation.addTextLabel(
        String.format("降水量: %s", currentWeather.getPrecipitation()),
        24,
        24,
        104,
        DisplayTextLabel.TextPosition.None);
    precipitation.addImageLabel("Weather", "2", 0, 0);

    weather.addImageLabel("WeatherData", currentWeather.getConditionName(), 359, 140);

    weather.addImageLabel("Weather", "3", 0, 0);

    /*
    Notification Info
    */
    DisplaySection notification = addSection(226, 527, 988, 479);
    int currentY = 24;
    for (int i = 0;
        currentY < notification.getHeight() + 110 && i != notificationList.size();
        i++) {
      DisplaySection newNotify = notification.addSection(24, currentY, 900, 110);
      if (notificationList.get(i) instanceof AlertNotification) {
        newNotify.addImageLabel("Notification", "alert", 0, 0);
      } else {
        newNotify.addImageLabel("Notification", "notification", 0, 0);
      }
      newNotify.addTextLabel(
          notificationList.get(i).getMessage(), 36, 111, 20, DisplayTextLabel.TextPosition.None);
      currentY += 110;
    }
    notification.addImageLabel("Notification", "1", 0, 0);
  }
}
