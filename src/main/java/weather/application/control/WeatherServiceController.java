package weather.application.control;

import java.util.ArrayList;
import java.util.List;
import weather.data.database.WeatherDatabase;
import weather.data.singledata.Location;
import weather.data.singledata.Time;
import weather.data.singledata.WeatherData;

public class WeatherServiceController {

  private final WeatherDatabase database;

  public WeatherServiceController(LocationController locationController) {
    database = new WeatherDatabase(locationController.getDatabase());
  }

  public WeatherData getOneWeather(Location location, Time time) {
    return database.getWeatherData(location, time);
  }

  // 暂不考虑月份跨越
  public List<WeatherData> getDayWeatherByGivenNum(Location location, Time present, int num) {
    List<WeatherData> list = new ArrayList<>();
    System.out.printf("[<<Fetch %d days' weather data from database>>]%n", num);
    for (int day = present.getDay() - 1; day != present.getDay() - 1 + num; day++) {
      Time eachTime = new Time(present.getYear(), present.getMonth(), day, present.getHour());
      list.add(database.getWeatherData(location, eachTime));
      System.out.printf(
          "(%d)%s%s%n",
          day - present.getDay() + 2,
          eachTime.toString(),
          database.getWeatherData(location, eachTime).toString());
    }
    System.out.println("[<<Fetch done>>]");
    return list;
  }

  public List<WeatherData> getHourWeatherByGivenNum(Location location, Time present, int num) {
    List<WeatherData> list = new ArrayList<>();
    System.out.printf("[<<Fetch %d hours' weather data from database>>]%n", num);
    if (present.getHour() + num < 24) {
      for (int hour = present.getHour(); hour != present.getHour() + num; hour++) {
        if (hour == present.getHour()) {
          continue;
        }
        Time eachTime = new Time(present.getYear(), present.getMonth(), present.getDay(), hour);
        list.add(database.getWeatherData(location, eachTime));
        System.out.printf(
            "(%d)%s%s%n",
            hour - present.getHour() + 1,
            eachTime.toString(),
            database.getWeatherData(location, eachTime).toString());
      }
      System.out.println("[<<Fetch done>>]");
    } else {
      for (int hour = present.getHour(); hour != 24; hour++) {
        if (hour == present.getHour()) {
          continue;
        }
        Time eachTime = new Time(present.getYear(), present.getMonth(), present.getDay(), hour);
        list.add(database.getWeatherData(location, eachTime));
        System.out.printf(
            "(%d)%s%s%n",
            hour - present.getHour() + 1,
            eachTime.toString(),
            database.getWeatherData(location, eachTime).toString());
      }
      for (int hour = 0; hour != present.getHour() + num - 24; hour++) {
        if (hour == present.getHour()) {
          continue;
        }
        Time eachTime = new Time(present.getYear(), present.getMonth(), present.getDay(), hour);
        list.add(database.getWeatherData(location, eachTime));
        System.out.printf(
            "(%d)%s%s%n",
            hour - present.getHour() + 1,
            eachTime.toString(),
            database.getWeatherData(location, eachTime).toString());
      }
    }
    return list;
  }
}
