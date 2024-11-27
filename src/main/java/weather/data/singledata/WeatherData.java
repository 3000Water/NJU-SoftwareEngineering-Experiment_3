package weather.data.singledata;

public class WeatherData {

  public static final int conditionNum = Condition.values().length;
  private final int temperature;
  private final float probabilityPrecipitation;
  private final float precipitation;
  private final Condition condition;
  private final Time time;

  public WeatherData(
      int temperature,
      float probabilityPrecipitation,
      float precipitation,
      Condition condition,
      Time time) {
    this.temperature = temperature;
    this.probabilityPrecipitation = probabilityPrecipitation;
    this.precipitation = precipitation;
    this.condition = condition;
    this.time = time;
  }

  public String getTemperature() {
    return String.format("%d℃", temperature);
  }

  public String getProbabilityPrecipitation() {
    return String.format("%d%%", (int) (probabilityPrecipitation * 100));
  }

  public String getPrecipitation() {
    return String.format("%.1fmm", precipitation);
  }

  public Condition getCondition() {
    return condition;
  }

  public String getConditionName() {
    return condition.name;
  }

  public Time getTime() {
    return time;
  }

  @Override
  public String toString() {
    return String.format(
        "[天气状况: %s, 温度: %d℃, 降水概率: %d%%, 降水量: %.1fmm]",
        condition, temperature, (int) (probabilityPrecipitation * 100), precipitation);
  }

  public enum Condition {
    LIGHT_RAIN(0),
    HEAVY_RAIN(1),
    SUNNY(2),
    CLOUDY(3),
    DARK_CLOUDY(4),
    THUNDER(5);

    private final int type;
    private final String name;

    Condition(int i) {
      this.type = i;
      String temp = "error";
      switch (i) {
        case 0 -> temp = "lightrain";
        case 1 -> temp = "heavyrain";
        case 2 -> temp = "sunny";
        case 3 -> temp = "cloudy";
        case 4 -> temp = "darkcloudy";
        case 5 -> temp = "thunder";
      }
      this.name = temp;
    }

    @Override
    public String toString() {
      String result = "";
      switch (type) {
        case 0 -> {
          result = "小雨";
        }
        case 1 -> {
          result = "大雨";
        }
        case 2 -> {
          result = "晴朗";
        }
        case 3 -> {
          result = "多云";
        }
        case 4 -> {
          result = "阴云";
        }
        case 5 -> {
          result = "雷电";
        }
      }
      return result;
    }
  }
}
