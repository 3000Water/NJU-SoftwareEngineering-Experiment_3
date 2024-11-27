package weather.data.database;

import java.time.LocalDate;
import java.util.Random;
import weather.application.WeatherAPP;
import weather.data.singledata.Location;
import weather.data.singledata.Time;
import weather.data.singledata.WeatherData;

public class WeatherDatabase extends BasicDatabase<WeatherData> {

  protected DataMap<Location, DataList<WeatherData>> datas;

  public WeatherDatabase(LocationDatabase locationDatabase) {
    datas = new DataMap<>();

    // 此处暂为显式随机赋值，准确数据更新作为后续完善
    for (Location location : locationDatabase.datas) {
      datas.put(location, initWeather());
    }
  }

  // 随机初始化，用以展示
  private DataList<WeatherData> initWeather() {
    DataList<WeatherData> list = new DataList<>();
    LocalDate date = LocalDate.now();
    // Time localTime = new Time();
    Time localTime = new Time(2024, 11, 23, 5);
    for (int day = -1; day != WeatherAPP.numOfDisplayWeatherDay - 1; day++) {
      for (int hour = 0; hour != 24; hour++) {
        list.add(randomGenerator(localTime.addDay(day).setHour(hour)));
      }
    }
    return list;
  }

  // 显式随机取值
  private WeatherData randomGenerator(Time time) {
    Random random = new Random();
    int temperature = random.nextInt(-10, 35);
    float probabilityPrecipitation = random.nextFloat(0.0F, 1.0F);
    float precipitation = random.nextFloat(0.0F, 4.0F);
    WeatherData.Condition[] conditionPool = WeatherData.Condition.values();
    WeatherData.Condition condition = conditionPool[random.nextInt(0, conditionPool.length)];
    return new WeatherData(temperature, probabilityPrecipitation, precipitation, condition, time);
  }

  public WeatherData getWeatherData(Location location, Time time) {
    WeatherData weatherData = null;
    for (WeatherData weather : datas.get(location)) {
      if (time.equals(weather.getTime())) {
        weatherData = weather;
        break;
      }
    }
    return weatherData;
  }

  public void addData(Location location, WeatherData data) {
    if (!datas.containsKey(location)) {
      datas.put(location, new DataList<>());
    }
    datas.get(location).add(data);
  }

  public void removeData(Location location, Time time, WeatherData data) {
    // TODO
  }

  public void saveIntoFile() {}
}
