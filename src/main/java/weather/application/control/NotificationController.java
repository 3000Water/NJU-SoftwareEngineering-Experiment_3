package weather.application.control;

import java.util.List;
import weather.data.singledata.Time;
import weather.data.singledata.WeatherData;
import weather.notify.AlertNotification;
import weather.notify.Notification;
import weather.notify.NotificationListManager;

public class NotificationController {

  private WeatherData currentWeather;
  private List<WeatherData> weatherDataHourList;
  private List<WeatherData> weatherDataDayList;
  private NotificationListManager notifyListManager;

  public NotificationController(
      WeatherData current, List<WeatherData> weatherDataHour, List<WeatherData> weatherDataDay) {
    currentWeather = current;
    weatherDataHourList = weatherDataHour;
    weatherDataDayList = weatherDataDay;
    notifyListManager = new NotificationListManager();
  }

  public void setWeatherHourList(List<WeatherData> weatherDataHour) {
    weatherDataHourList = weatherDataHour;
  }

  public void setWeatherDayList(List<WeatherData> weatherDataDay) {
    weatherDataDayList = weatherDataDay;
  }

  public void modifyNotificationListByWeatherData() {
    // int currentDay = LocalDate.now().getDayOfMonth();
    // int currentHour = LocalTime.now().getHour();
    // Time currentTime = new Time();
    notifyListManager.clearList();
    int currentDay = 23;
    int currentHour = 5;
    Time currentTime = new Time(2024, 11, 23, 5);

    /*
    Hour Notification
     */
    if (currentWeather.getCondition() == WeatherData.Condition.HEAVY_RAIN
        || currentWeather.getCondition() == WeatherData.Condition.LIGHT_RAIN) {
      for (WeatherData futureWeather : weatherDataHourList) {
        if (futureWeather.getTime().earlyThan(currentTime)) {
          continue;
        }

        if (futureWeather.getCondition() == WeatherData.Condition.SUNNY) {
          notifyListManager.addNotification(
              new Notification(
                  String.format(
                      "%d小时后大雨转%s, 迎接好天气",
                      currentTime.differHoursFromLaterTime(futureWeather.getTime()),
                      futureWeather.getCondition().toString())));
        } else if (futureWeather.getCondition() != WeatherData.Condition.THUNDER) {
          notifyListManager.addNotification(
              new Notification(
                  String.format(
                      "%d小时后大雨转%s",
                      currentTime.differHoursFromLaterTime(futureWeather.getTime()),
                      futureWeather.getCondition().toString())));
        } else {
          notifyListManager.addNotification(
              new AlertNotification(
                  String.format(
                      "%d小时后有雷电天气",
                      currentTime.differHoursFromLaterTime(futureWeather.getTime()))));
        }
      }
    } else if (currentWeather.getCondition() == WeatherData.Condition.SUNNY
        || currentWeather.getCondition() == WeatherData.Condition.CLOUDY
        || currentWeather.getCondition() == WeatherData.Condition.DARK_CLOUDY) {
      for (WeatherData futureWeather : weatherDataHourList) {
        if (futureWeather.getTime().earlyThan(currentTime)) {
          continue;
        }

        if (futureWeather.getCondition() == WeatherData.Condition.HEAVY_RAIN
            || futureWeather.getCondition() == WeatherData.Condition.LIGHT_RAIN) {
          notifyListManager.addNotification(
              new Notification(
                  String.format(
                      "%d小时后有%s, 出门记得带伞",
                      currentTime.differHoursFromLaterTime(futureWeather.getTime()),
                      futureWeather.getCondition().toString())));
        } else if (futureWeather.getCondition() == WeatherData.Condition.THUNDER) {
          notifyListManager.addNotification(
              new AlertNotification(
                  String.format(
                      "%d小时后有雷电天气",
                      currentTime.differHoursFromLaterTime(futureWeather.getTime()))));
        }
      }
    }

    /*
    Day Notification
     */
    for (WeatherData laterDayWeather : weatherDataDayList) {
      if (laterDayWeather.getTime().getDay() == currentDay + 1) {
        notifyListManager.addNotification(
            new Notification(String.format("明日天气为%s", laterDayWeather.getCondition().toString())));
        break;
      }
    }
    // TODO

    System.out.println("[Modify notification list]");
    for (int i = 1; i != notifyListManager.getNotificationList().size() + 1; i++) {
      System.out.printf(
          "[%d] %s%n", i, notifyListManager.getNotificationList().get(i - 1).getMessage());
    }
  }

  public List<Notification> getSpecificNotifications() {
    return notifyListManager.getNotificationList();
  }
}
